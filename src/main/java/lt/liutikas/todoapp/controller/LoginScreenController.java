package lt.liutikas.todoapp.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lt.liutikas.todoapp.exceptions.FileNotFoundException;
import lt.liutikas.todoapp.exceptions.LoginFailedException;

public class LoginScreenController extends Controller {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @Override
    protected void start() {

    }

    public void login() {
        tryLogin(usernameField.getText(), passwordField.getText());
    }

    private void tryLogin(String username, String password) {
        try {
            appManager.login(username, password);
            clientApplication.changeScene("MainScreen");
        } catch (LoginFailedException e) {
            triggerAlert(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void triggerAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void startRegistration() throws FileNotFoundException {
        clientApplication.changeScene("RegistrationScreen");

    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }

}
