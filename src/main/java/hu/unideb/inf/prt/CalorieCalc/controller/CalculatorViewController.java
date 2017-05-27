package hu.unideb.inf.prt.CalorieCalc.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

/**
 * @author Zöld Krisztián
 *
 */
public class CalculatorViewController {

	private Main main;
	private Stage stage;
	private User user;
	private NumberFormat formatting = new DecimalFormat("#0.00");

	private static Boolean carbohydrateButton = false;
	private static Boolean proteinButton = false;
	private static Boolean fatButton = false;

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
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
	private Label carbohydrateLabel;

	@FXML
	private Label proteinLabel;

	@FXML
	private Label fatLabel;

	@FXML
	private Label sumLabel;

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setLabels() {
		nameLabel.setText(user.getFirstName());
		carbohydrateLabel.setText(formatting.format(user.getIntakeCarbohydrate()) + "/"
				+ formatting.format(user.getCarbohydrate()) + " g");
		proteinLabel.setText(
				formatting.format(user.getIntakeProtein()) + "/" + formatting.format(user.getProtein()) + " g");
		fatLabel.setText(formatting.format(user.getIntakeFat()) + "/" + formatting.format(user.getFat()) + " g");
		sumLabel.setText(formatting.format(user.getIntakeBMR()) + "/" + formatting.format(user.getBMR()) + " g");
		carbohydratePI.setProgress(user.getIntakeCarbohydrate() / user.getCarbohydrate());
		proteinPI.setProgress(user.getIntakeProtein() / user.getProtein());
		fatPI.setProgress(user.getIntakeFat() / user.getFat());
		sumPB.setProgress(user.getIntakeBMR() / user.getBMR());
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStageFocusListener(Stage stage) {
		Main.getLogger().info("A bevitt tápanyag mennyiség frissült.");
		stage.focusedProperty().addListener((o, oldValue, newValue) -> setLabels());

	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void addCarbohydrateButton() {
		Main.getLogger().info("A felhasználó megnyomta a szénhidrát hozzáadásához tartozó + gombot.");
		carbohydrateButton = true;
		System.err.printf("Carbon: %b", carbohydrateButton);
		main.showAddNutrientView(user);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void addProteinButton() {
		Main.getLogger().info("A felhasználó megnyomta a fehérje hozzáadásához tartozó + gombot.");
		CalculatorViewController.proteinButton = true;
		main.showAddNutrientView(user);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void addFatButton() {
		Main.getLogger().info("A felhasználó megnyomta a zsír hozzáadásához tartozó + gombot.");
		CalculatorViewController.fatButton = true;
		main.showAddNutrientView(user);
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setCarbohydrateButton(boolean carbohydrateButton) {
		CalculatorViewController.carbohydrateButton = carbohydrateButton;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public Boolean getCarbohydrateButton() {
		return CalculatorViewController.carbohydrateButton;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setProteinButton(boolean proteinButton) {
		CalculatorViewController.proteinButton = proteinButton;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public boolean getProteinButton() {
		return CalculatorViewController.proteinButton.booleanValue();
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setFatButton(boolean fatButton) {
		CalculatorViewController.fatButton = fatButton;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public Boolean getFatButton() {
		return CalculatorViewController.fatButton;
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void showWeeklyStatistics() {
		Main.getLogger().info("A felhasználó megnyomta a Heti statisztika gombot.");
		main.showWeeklyStatistics(user);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedEditDataButton() {
		Main.getLogger().info("A felhasználó megnyomta a Személyes adatok gombot.");
		main.showEditUserDataView(user);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedLogoutButton() {
		Main.getLogger().info("A felhasználó megnyomta a kijelentkezés gombot.");
		stage.close();
		main.showLoginView();
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedDeleteButton() {
		Main.getLogger().info("A felhasználó megnyomta a Felhasználó törlése gombot.");
		user.setDeletedUser(true);
		stage.close();
		main.showLoginView();
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressWithdraw() {
		Main.getLogger().info("A felhasználó megnyomta a Visszavonás gombot.");
		Calculation.withdraw(user);
		setLabels();
	}
}
