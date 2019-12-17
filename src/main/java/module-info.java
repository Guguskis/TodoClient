module lt.liutikas.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports lt.liutikas.todoapp;
    opens lt.liutikas.todoapp.controller to javafx.fxml, javafx.controls;
}

// Unable to run program because Controllers cannot be accessed from javafx