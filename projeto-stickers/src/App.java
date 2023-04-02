import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        API api = API.IMDB_TOP_SERIES;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradordeStickers();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}


//     + "\u001b[1m Image: \u001b[m" + conteudo.get("image"));
// for(int i = 1; i <= numeroEstrelas; i++){
    //     //Digitar chcp65001 resolveu o problema das estrelas no Windows
    //     System.out.print("⭐");
    // }

    // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    // String url = "https://api.nasa.gov/planetary/apod?api_key=CyzQCveL75sKaO0hQPj4Ji1qGMeQAamv4gr5zuIC&start_date=2022-06-12&end_date=2022-06-14";