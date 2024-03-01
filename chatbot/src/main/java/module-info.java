module projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    

    opens projet to javafx.fxml;
    exports projet;
}
