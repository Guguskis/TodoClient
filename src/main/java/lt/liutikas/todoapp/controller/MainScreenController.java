package lt.liutikas.todoapp.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import lt.liutikas.todoapp.exceptions.FileNotFoundException;


public class MainScreenController extends Controller {
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private Parent middleContainer;


	public MainScreenController() {

	}

	@FXML
	public void logout() throws FileNotFoundException {
		// Todo Use appManager singleton?
		appManager.logout();
		clientApplication.changeScene("LoginScreen");

	}


	public void openProjects() {
	}

	public void openAccountSettings() throws FileNotFoundException {
	}

	@Override
	protected void start() {

	}
}
