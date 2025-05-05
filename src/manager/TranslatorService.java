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

     public static void main(String[] args) throws IOException, InterruptedException {
         Dotenv dotenv = Dotenv.configure().load();
         String apiKey = dotenv.get("APIKEY");
         System.out.println("ApiKey");
         String text = "ê, đệt con mẹ nhà mày";
         String url = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey;

         String requestBody = String.format(
                 "{\"q\":\"%s\",\"source\":\"vi\",\"target\":\"en\"}", text
         );

         HttpClient client = HttpClient.newHttpClient();
         HttpRequest request = HttpRequest.newBuilder()
                 .uri(URI.create(url))
                 .header("Content-Type", "application/json")
                 .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                 .build();

         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

         // Phân tích JSON để lấy phần văn bản dịch
         JSONObject jsonObject = new JSONObject(response.body());
         JSONArray translations = jsonObject.getJSONObject("data").getJSONArray("translations");
         String translatedText = translations.getJSONObject(0).getString("translatedText");

         System.out.println(translatedText);
    }

    public String translate(String text, String sourceLang, String targetLang) {
        return null;
    }
}

