package hu.unideb.inf.prt.CalorieCalc.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;
import hu.unideb.inf.prt.CalorieCalc.model.Goal;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Zöld Krisztián
 *
 */
public class EditUserDataViewController {

	private User user;

	private Stage stage;

	private Main main;

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setUser(User user) {
		this.user = user;
	}

	private ObservableList<String> goals = FXCollections.observableArrayList();

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField ageField;

	@FXML
	private TextField bodyheightField;

	@FXML
	private TextField bodyweightField;

	@FXML
	private ComboBox<String> goalComboBox;

	@FXML
	private Label nameErrorMessage;

	@FXML
	private Label ageErrorMessage;

	@FXML
	private Label bodyheightErrorMessage;

	@FXML
	private Label bodyweightErrorMessage;

	@FXML
	private Label goalErrorMessage;

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void initialize() {
		goals.add("Súlycsökkentés");
		goals.add("Súlytartás");
		goalComboBox.setItems(goals);
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void adjustUserData() {
		lastNameField.setText(user.getLastName());
		firstNameField.setText(user.getFirstName());

		if (user.getGoal() == Goal.KEEP_BODYWEIGHT) {
			goalComboBox.getSelectionModel().select(1);
		} else {
			goalComboBox.getSelectionModel().select(0);
		}

		ageField.setText(user.getAge().toString());
		bodyheightField.setText(user.getBodyHeight().toString());
		bodyweightField.setText(user.getBodyWeight().toString());
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	private boolean verifyFields() {
		boolean correct = true;

		if (lastNameField.getText().isEmpty() || firstNameField.getText().isEmpty()) {
			nameErrorMessage.setVisible(true);
			correct = false;
		} else {
			nameErrorMessage.setVisible(false);
		}

		if (!Pattern.matches("[1-9][0-9]*", ageField.getText())) {
			ageErrorMessage.setVisible(true);
			correct = false;
		} else if (Integer.parseInt(ageField.getText()) < 10) {
			correct = false;
		} else {
			ageErrorMessage.setVisible(false);
		}

		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", bodyheightField.getText())) {
			bodyheightErrorMessage.setVisible(true);
			correct = false;
		} else {
			bodyheightErrorMessage.setVisible(false);
		}

		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", bodyweightField.getText())) {
			bodyweightErrorMessage.setVisible(true);
			correct = false;
		} else {
			bodyweightErrorMessage.setVisible(false);
		}

		if (goalComboBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			goalErrorMessage.setVisible(true);
			correct = false;
		} else {
			goalErrorMessage.setVisible(false);
		}

		return correct;
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedModifyButton() {
		Main.getLogger().info("A felhasználó megnyomta a Módosítás gombot.");
		if (verifyFields()) {
			main.getUserByUserName(user.getUserName()).setLastName(lastNameField.getText());
			main.getUserByUserName(user.getUserName()).setFirstName(firstNameField.getText());
			main.getUserByUserName(user.getUserName()).setAge(Integer.parseInt(ageField.getText()));
			main.getUserByUserName(user.getUserName()).setBodyHeight(Double.valueOf(bodyheightField.getText()));
			main.getUserByUserName(user.getUserName()).setBodyWeight(Double.valueOf(bodyweightField.getText()));
			if (goalComboBox.getSelectionModel().selectedItemProperty().getValue() == "Súlycsökkentés")
				main.getUserByUserName(user.getUserName()).setGoal(Goal.LOSE_BODYWEIGHT);
			else
				main.getUserByUserName(user.getUserName()).setGoal(Goal.KEEP_BODYWEIGHT);
			Calculation.setDailyNutrients(main.getUserByUserName(user.getUserName()));
			stage.close();
		}
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedCancelButton() {
		Main.getLogger().info("A felhasználó megnyomta a Mégsem gombot.");
		stage.close();
	}

}
