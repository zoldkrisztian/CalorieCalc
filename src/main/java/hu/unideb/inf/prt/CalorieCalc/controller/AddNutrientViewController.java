package hu.unideb.inf.prt.CalorieCalc.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;

import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNutrientViewController {
	
	private User user;
	private Stage stage;
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@FXML
	private TextField amount;
	
	@FXML
	private Label numberErrorMessage;
	
	@FXML
	public void pressedCancelButton() {
		
		stage.close();
	}
	
	@FXML
	public void pressedAddButton(){
		
		CalculatorViewController choose = new CalculatorViewController();
		
		boolean correct = true;
		
		if(!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", amount.getText())){
			correct = false;
			numberErrorMessage.setVisible(true);
		}else {
			numberErrorMessage.setVisible(false);
		}	

		if(choose.getCarbohydrateButton()){
			
			Calculation.increaseIntakeCarbohydrate(Double.valueOf(amount.getText()), user);
			choose.setCarbohydrateButton(false);
		}
		else if(choose.getProteinButton()){
			Calculation.increaseIntakeProtein(Double.valueOf(amount.getText()), user);
		choose.setProteinButton(false);
		}
		else if(choose.getFatButton()){
			Calculation.increaseIntakeFat(Double.valueOf(amount.getText()), user);
			choose.setFatButton(false);
		}
		if(correct){
			stage.close();
		}
	}

}
