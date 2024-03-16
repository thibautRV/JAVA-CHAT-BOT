import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ChatController {

    @FXML
    private TextField inputField;

    @FXML
    private TextArea chatArea;

    private ChatbotClient chatbotClient = new ChatbotClient();

    @FXML
    private void sendMessage() {
        String message = inputField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        chatArea.appendText("You: " + message + "\n");
        inputField.clear();

        new Thread(() -> {
            try {
                String response = chatbotClient.chat(message);
                chatArea.appendText("Chatbot: " + response + "\n");
            } catch (IOException e) {
                chatArea.appendText("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }
}