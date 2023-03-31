import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Necessário procurar um parseador de JSON em Java, caso não queira ou possa usar regex

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);


        var geradora = new GeradordeStickers();

        for (Map<String,String> filme : listaDeFilmes) {

            Double rating = Double.parseDouble(filme.get("imDbRating")); 
            int numeroEstrelas = rating.intValue();

            String titulo = filme.get("title");
            String urlImage = filme.get("image");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";
            // System.out.println("{" + "\u001b[37m \u001b[44m Rank:  " + filme.get("rank")  + "\u001b[m" + '\n' 
            //     + "\u001b[1m Title: \u001b[m" + filme.get("title") + '\n'
            //     + "\u001b[1m Rating:\u001b[m" + filme.get("imDbRating") + '\n'
            //     + "\u001b[1m Image: \u001b[m" + filme.get("image"));

            geradora.cria(inputStream, nomeArquivo);

            // for(int i = 1; i <= numeroEstrelas; i++){
            //     //Digitar chcp65001 resolveu o problema das estrelas no Windows
                // System.out.print("⭐");
            // }
            System.out.print(filme.get("titulo"));
            // System.out.print('\n');
            // System.out.print('\n');
            
        }
    }
}

