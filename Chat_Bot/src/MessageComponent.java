import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;



// Définition de la classe MessageComponent
public class MessageComponent extends HBox {

    private Label messageLabel;

    public MessageComponent(String message, MessageRole role) {
        messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(500); // Définissez la largeur maximale du message

        if (role == MessageRole.USER) {
            this.getChildren().add(messageLabel); // Ajoutez le label à droite pour l'utilisateur
            this.setStyle("-fx-alignment: CENTER-RIGHT;"); // Alignement à droite
        } else {
            this.getChildren().add(messageLabel); // Ajoutez le label à gauche pour le chatbot
            this.setStyle("-fx-alignment: CENTER-LEFT;"); // Alignement à gauche
        }
    }
}
