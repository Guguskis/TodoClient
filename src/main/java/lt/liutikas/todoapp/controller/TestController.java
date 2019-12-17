package lt.liutikas.todoapp.controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lt.liutikas.todoapp.exceptions.LoginFailedException;

public class TestController extends Controller {
    @FXML
    private Text projectNameText;


    @Override
    protected void start() {
        try {
            appManager.login("admin", "admin");
        } catch (LoginFailedException e) {
            e.printStackTrace();
        }
        String username = appManager.getCurrentUser().getUsername();
        projectNameText.setText(username);

    }

    public void changeText(KeyEvent keyEvent) {
    }
}
