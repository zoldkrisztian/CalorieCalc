package hu.unideb.inf.prt.CalorieCalc.controller;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class LoginViewController {
	
	private Stage stage;
	private Main main;
	private User user;
	
	@FXML
	private void passOnSelectedUser(){
		if((user = userTable.getSelectionModel().selectedItemProperty().getValue()) != null);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMain(Main main) {
		this.main = main;
		ObservableList<User> users = FXCollections.observableArrayList();
		for(User user : Main.getUsers()){
			if(!user.isDeletedUser()){
				users.add(user);
				System.out.printf("user: %s", user);
				System.out.println("");
				
			}
		}
		userTable.setItems(users);
	}
	
	
	@FXML
	private TableView<User> userTable;
	
	@FXML
	private TableColumn<User, String> userColumn;
	
	@FXML
	private Label userNotPickErrorMessage;
	
	@FXML
	public void initialize(){
		userColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getUserName()));
	}
	
	@FXML
	public void pressCancel() {
		stage.close();
		main.showMainView();
	}
	
	
	@FXML
	public void pressedLoginButton(){
		if(user == null){
			userNotPickErrorMessage.setVisible(true);
			
		}else{
			main.showCalculatorView(user);
			stage.close();
			userNotPickErrorMessage.setVisible(false);
		}
	}
	
}
