package lt.liutikas.todoapp.controller;

import javafx.fxml.Initializable;
import lt.liutikas.todoapp.ClientApplication;
import lt.liutikas.todoapp.service.AppManager;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {
	protected ClientApplication clientApplication;
	protected AppManager appManager;

	protected abstract void start();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clientApplication = ClientApplication.getInstance();
		this.appManager = clientApplication.getAppManager();
		start();
	}
}
