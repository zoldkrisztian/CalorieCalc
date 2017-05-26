package hu.unideb.inf.prt.CalorieCalc.controller;


import hu.unideb.inf.prt.CalorieCalc.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainViewController {
	
	private Main main;
	private Stage stage;
	
	@FXML
	public void pressedLoginButton(){
		
		main.showLoginView();
		stage.close();
	}
	
	@FXML
	public void pressedRegistrationButton(){
		
		main.showRegView();
		stage.close();
		
	}
	
	@FXML
	public void pressedExitButton(){
		
		stage.close();
		
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}

	public void setMain(Main main) {
		this.main = main;
		
	}
}
