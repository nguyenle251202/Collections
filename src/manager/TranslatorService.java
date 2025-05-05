package manager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

public class TranslatorService {
    private final Dictionary dictionary;

    public TranslatorService(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String translate(String text, String sourceLang, String targetLang) throws IOException, InterruptedException {
        Dotenv dotenv = Dotenv.configure().load();
        String apiKey = dotenv.get("APIKEY");

        String url = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey;
        String requestBody = String.format(
                "{\"q\":\"%s\",\"source\":\"%s\",\"target\":\"%s\"}",
                text, sourceLang, targetLang
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray translations = jsonObject.getJSONObject("data").getJSONArray("translations");
        return translations.getJSONObject(0).getString("translatedText");
    }
}