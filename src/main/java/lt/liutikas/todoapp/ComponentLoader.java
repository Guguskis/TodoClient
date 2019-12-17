package lt.liutikas.todoapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lt.liutikas.todoapp.exceptions.FileNotFoundException;

import java.io.IOException;


public class ComponentLoader {
    private static final String FXML_Extension = ".fxml";

    public Parent getComponent(String relativeFilePath) throws FileNotFoundException {
        String filePath = "" + relativeFilePath + FXML_Extension;

        return handleLoading(filePath);
    }

    private Parent handleLoading(String filePath) throws FileNotFoundException {
        try {
            return new FXMLLoader().load(getClass().getClassLoader().getResource(filePath));
        } catch (IOException e) {
            throw new FileNotFoundException("Something with FXML file loading.", e);
        }
    }
}
