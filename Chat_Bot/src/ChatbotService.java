import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatbotService {

    // Cette méthode extrait le contenu de la réponse JSON et le retourne.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11; // Marqueur pour le début du contenu.
        int endMarker = response.indexOf("\"", startMarker); // Marqueur pour la fin du contenu.
        return response.substring(startMarker, endMarker); // Retourne la sous-chaîne contenant uniquement la réponse.
    }

    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chatgpt api
    
        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");
    
            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
    
            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
    
            // Check if the response is the default greeting message
            if (response.toString().contains("Hello! How can I assist you today?")) {
                return ""; // Return an empty string if it's the default greeting
            }
    
            // Otherwise, return the extracted contents of the response
            return extractContentFromResponse(response.toString());
    
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
