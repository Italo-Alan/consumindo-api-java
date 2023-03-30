import java.net.URI;
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


        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("{" + '\n' + "Rank: "+ filme.get("rank") + '\n' 
                + "title: " + filme.get("title") + '\n'
                + "Rating: " + filme.get("imDbRating") + '\n'
                + "Image: " + filme.get("image") + '\n' + "}");
        }
        }
    }

