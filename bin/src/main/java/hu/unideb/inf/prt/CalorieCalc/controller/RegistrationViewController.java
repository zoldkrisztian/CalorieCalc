package hu.unideb.inf.prt.CalorieCalc.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.model.Gender;
import hu.unideb.inf.prt.CalorieCalc.model.Goal;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationViewController {

	private Main main;
	private Stage stage;
	private User user;
	private ObservableList<String> genders = FXCollections.observableArrayList();
	private ObservableList<String> goals = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		genders.add("Férfi");
		genders.add("Nõ");
		genderComboBox.setItems(genders);

		goals.add("Súlycsökkentés");
		goals.add("Súlytartás");
		goalComboBox.setItems(goals);
	}

	@FXML
	private TextField userNameField;

	@FXML
	private TextField userPasswordField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField firstNameField;

	@FXML
	private ComboBox<String> genderComboBox;

	@FXML
	private TextField ageField;

	@FXML
	private TextField bodyheightField;

	@FXML
	private TextField bodyweightField;

	@FXML
	private ComboBox<String> goalComboBox;

	@FXML
	private Label userExistErrorMessage;

	@FXML
	private Label genderErrorMessage;

	@FXML
	private Label ageErrorMessage;

	@FXML
	private Label bodyheightErrorMessage;

	@FXML
	private Label bodyweightErrorMessage;

	@FXML
	private Label goalErrorMessage;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void pressCancel() {
		stage.close();
		main.showMainView();
	}

	private boolean verifyFields() {
		boolean correct = true;

		if (main.getUserByUserName(userNameField.getText()) != null) {
			userExistErrorMessage.setVisible(true);
			correct = false;
		} else {
			userExistErrorMessage.setVisible(false);
		}

		if (userNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
				|| firstNameField.getText().isEmpty()) {
			correct = false;
		}

		if (genderComboBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			genderErrorMessage.setVisible(true);
			correct = false;
		} else {
			genderErrorMessage.setVisible(false);
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
	public void pressRegistrationButton() {

		if (verifyFields()) {

			Gender gender = (genderComboBox.getSelectionModel().selectedIndexProperty().getValue() == 1)
					? (gender = Gender.FEMALE) : (gender = Gender.MALE);

			Goal goal = (goalComboBox.getSelectionModel().selectedIndexProperty().getValue()
					.toString() == "Súlycsökkentés") ? (goal = Goal.LOSE_BODYWEIGHT) : (goal = Goal.KEEP_BODYWEIGHT);

			user = new User(userNameField.getText(), firstNameField.getText(), lastNameField.getText(),
					Double.valueOf(bodyweightField.getText()), Double.valueOf(bodyheightField.getText()),
					Integer.parseInt(ageField.getText()), gender, goal);
			Main.getUser().add(user);
			stage.close();
		}
	}

}
