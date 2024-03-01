module projet {
    requires javafx.controls;
    requires javafx.fxml;

    opens projet to javafx.fxml;
    exports projet;
}
