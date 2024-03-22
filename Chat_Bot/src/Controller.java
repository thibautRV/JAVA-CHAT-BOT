import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;



public class Controller {

    @FXML
    private TextField messageTextField;

    @FXML
    private TextField chatArea;

    @FXML
    private VBox messageContainer;

    @FXML
    private ScrollPane scrollPane;


    @FXML
    public void initialize() {
    
        messageTextField.setOnKeyPressed(event -> {
        if (event.getCode().equals(KeyCode.ENTER)) {
            sendMessage(); // Appelle la méthode sendMessage lorsque la touche Entrée est pressée
            }
        });

        scrollPane.vvalueProperty().bind(messageContainer.heightProperty());

    }

   

    @FXML
    public void sendMessage() {
        String message = messageTextField.getText();
        // Créer un composant pour le message de l'utilisateur
        MessageComponent userMessageComponent = new MessageComponent(message, MessageRole.USER);
        // Ajouter le message à la droite de la boîte de messages
        messageContainer.getChildren().add(userMessageComponent);

        // Envoyer le message au chatbot et recevoir la réponse
        String response = ChatbotService.chatGPT(message);
        if (response != null) {
            // Créer un composant pour la réponse du chatbot
            MessageComponent chatbotMessageComponent = new MessageComponent(response, MessageRole.CHATBOT);
            // Ajouter le message à la gauche de la boîte de messages
            messageContainer.getChildren().add(chatbotMessageComponent);
        } else {
            // Gestion de l'erreur si la réponse est nulle
            System.out.println("Error: Unable to get response from the chatbot.");
        }


        messageTextField.clear(); // Efface le champ de saisie après l'envoi du message
    }

    @FXML
    void quitter(MouseEvent event) {
        Platform.exit(); // Quitter l'application
    }


}
