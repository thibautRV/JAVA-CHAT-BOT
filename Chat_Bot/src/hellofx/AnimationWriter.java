package hellofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class AnimationWriter {

    public void writeTextAnimation(TextArea textArea, String text) {
        final int[] index = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (index[0] < text.length()) {
                textArea.appendText(String.valueOf(text.charAt(index[0])));
                index[0]++;
            }
        }));
        timeline.setCycleCount(text.length());
        timeline.play();
    }
}
