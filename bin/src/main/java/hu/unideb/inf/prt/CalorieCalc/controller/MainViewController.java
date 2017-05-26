package hu.unideb.inf.prt.CalorieCalc.controller;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainViewController {
	/*@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	
	public void login(ActionEvent event){
		if (txtUsername.getText().equals("user") && txtPassword.getText().equals("pass")){
			lblStatus.setText("Sikeres Belépés");
		}else {
			lblStatus.setText("Sikertelen Belépés");
		}
	}*/
	
	private Main main;
	private Stage stage;
	private User user;
	
	@FXML
	public void login(){
		
		main.showLoginView();
	}
	
	@FXML
	public void reg(){
		
		main.showRegView();
		
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}

	public void setMain(Main main) {
		this.main = main;
		
	}
}
