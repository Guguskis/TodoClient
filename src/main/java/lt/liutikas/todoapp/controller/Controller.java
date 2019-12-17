package lt.liutikas.todoapp.controller;

import javafx.fxml.Initializable;
import lt.liutikas.todoapp.ViewManager;
import lt.liutikas.todoapp.service.AppManager;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {
	protected ViewManager viewManager;
	protected AppManager appManager;

	protected abstract void start();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.viewManager = ViewManager.getInstance();
		this.appManager = viewManager.getAppManager();
		start();
	}
}
