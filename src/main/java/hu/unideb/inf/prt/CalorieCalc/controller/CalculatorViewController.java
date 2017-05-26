package hu.unideb.inf.prt.CalorieCalc.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

public class CalculatorViewController {

	private Main main;
	private Stage stage;
	private User user;
	private NumberFormat formatting = new DecimalFormat("#0.00");
	//private static int button;
	private static Boolean carbohydrateButton = false;
	private static Boolean proteinButton = false;
	private static Boolean fatButton = false;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private ProgressIndicator carbohydratePI;
	
	@FXML
	private ProgressIndicator proteinPI;
	
	@FXML
	private ProgressIndicator fatPI;
	
	@FXML
	private ProgressBar sumPB;
	
	@FXML
	private Label  carbohydrateLabel;
	
	@FXML
	private Label  proteinLabel;
	
	@FXML
	private Label  fatLabel;
	
	@FXML
	private Label  sumLabel;
	
	
	
	public void setLabels(){
		nameLabel.setText(user.getFirstName());
		carbohydrateLabel.setText(formatting.format(user.getIntakeCarbohydrate()) + "/" + formatting.format(user.getCarbohydrate()) + " g");
		proteinLabel.setText(formatting.format(user.getIntakeProtein()) + "/" + formatting.format(user.getProtein()) + " g");
		fatLabel.setText(formatting.format(user.getIntakeFat()) + "/" + formatting.format(user.getFat()) + " g");
		
		sumLabel.setText(formatting.format(user.getIntakeBMR()) + "/" + formatting.format(user.getBMR()) +" g");
		
		carbohydratePI.setProgress(user.getIntakeCarbohydrate() / user.getCarbohydrate());
		proteinPI.setProgress(user.getIntakeProtein() / user.getProtein());
		fatPI.setProgress(user.getIntakeFat() / user.getFat());
		sumPB.setProgress(user.getIntakeBMR() / user.getBMR());
	}
	
	public void setStageFocusListener(Stage stage){
		stage.focusedProperty().addListener((o, oldValue, newValue) -> setLabels());
		System.out.printf("Stage: %s", stage);
		
	}
	
	@FXML
	public void addCarbohydrateButton(){
		carbohydrateButton = true;
		System.err.printf("Carbon: %b", carbohydrateButton);
		main.showAddNutrientView(user);
	}
	
	@FXML
	public void addProteinButton(){
		CalculatorViewController.proteinButton = true;

		main.showAddNutrientView(user);
	}
	
	@FXML
	public void addFatButton(){
		CalculatorViewController.fatButton = true;
		main.showAddNutrientView(user);
	}
	
	public void setCarbohydrateButton(boolean carbohydrateButton) {
		CalculatorViewController.carbohydrateButton = carbohydrateButton;
	}
	
	public Boolean getCarbohydrateButton() {
		return CalculatorViewController.carbohydrateButton;
	}
	
	public void setProteinButton(boolean proteinButton) {
		CalculatorViewController.proteinButton = proteinButton;
	}
	
	public boolean getProteinButton() {
		return CalculatorViewController.proteinButton.booleanValue();
	}
	
	public void setFatButton(boolean fatButton) {
		CalculatorViewController.fatButton = fatButton;
	}
	
	public Boolean getFatButton() {
		return CalculatorViewController.fatButton;
	}
	
	@FXML
	public void showWeeklyStatistics(){
		main.showWeeklyStatistics(user);
	}
	
	@FXML
	public void pressedEditDataButton(){
		main.showEditUserDataView(user);
	}
	
	@FXML
	public void pressedLogoutButton(){
		stage.close();
		main.showLoginView();
	}
	
	@FXML
	public void pressedDeleteButton(){
		user.setDeletedUser(true);
		stage.close();
		main.showLoginView();
	}
	
	
	
}
