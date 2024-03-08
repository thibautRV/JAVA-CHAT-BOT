
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextArea inputArea;

    private Chatbot chatbot;
    private AnimationWriter animationWriter;

    @FXML
    public void initialize() {
        chatbot = new Chatbot();
        animationWriter = new AnimationWriter();
    }

    @FXML
    void onSendButtonClicked(ActionEvent event) {
        String question = inputArea.getText();
        String response = chatbot.respondTo(question);
        animationWriter.writeTextAnimation(chatArea, response);
    }
}
