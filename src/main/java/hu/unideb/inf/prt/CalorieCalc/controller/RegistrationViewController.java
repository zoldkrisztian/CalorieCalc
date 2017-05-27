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

/**
 * @author Zöld Krisztián
 *
 */
public class RegistrationViewController {

	private Main main;
	private Stage stage;
	private User user;
	private ObservableList<String> genders = FXCollections.observableArrayList();
	private ObservableList<String> goals = FXCollections.observableArrayList();

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void initialize() {
		genders.add("Nő");
		genders.add("Férfi");
		genderComboBox.setItems(genders);

		goals.add("Súlycsökkentés");
		goals.add("Súlytartás");
		goalComboBox.setItems(goals);
	}

	@FXML
	private TextField userNameField;

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

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressCancel() {
		Main.getLogger().info("A felhasználó megnyomta a Mégsem gombot.");
		stage.close();
		main.showMainView();
	}

	@SuppressWarnings("checkstyle:javadocmethod")
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
	public void pressRegistrationButton() {
		Main.getLogger().info("A felhasználó megnyomta a Regisztráció gombot.");
		if (verifyFields()) {
			Gender gender = (genderComboBox.getSelectionModel().selectedIndexProperty().getValue() == 1)
					? (gender = Gender.MALE) : (gender = Gender.FEMALE);

			Goal goal = (goalComboBox.getSelectionModel().selectedIndexProperty().getValue() == 1)
					? (goal = Goal.KEEP_BODYWEIGHT) : (goal = Goal.LOSE_BODYWEIGHT);

			user = new User(userNameField.getText(), firstNameField.getText(), lastNameField.getText(),
					Double.valueOf(bodyheightField.getText()), Double.valueOf(bodyweightField.getText()),
					Integer.parseInt(ageField.getText()), gender, goal);
			Main.getUsers().add(user);
			stage.close();
			main.showMainView();
		}
	}

}
