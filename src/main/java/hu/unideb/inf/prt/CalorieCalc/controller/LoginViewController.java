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

/**
 * @author Zöld Krisztián
 *
 */
public class LoginViewController {

	private Stage stage;
	private Main main;
	private User user;

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	private void passOnSelectedUser() {
		Main.getLogger().info("A felhasználó kiválasztott egy felhasználónevet.");
		if ((user = userTable.getSelectionModel().selectedItemProperty().getValue()) != null)
			;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
		ObservableList<User> users = FXCollections.observableArrayList();
		for (User user : Main.getUsers()) {
			if (!user.isDeletedUser()) {
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
	@SuppressWarnings("checkstyle:javadocmethod")
	private Label userNotPickErrorMessage;

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void initialize() {
		userColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getUserName()));
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressCancel() {
		Main.getLogger().info("A felhasználó megnyomta a Mégsem gombot.");
		stage.close();
		main.showMainView();
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedLoginButton() {
		Main.getLogger().info("A felhasználó megnyomta a Belépés gombot.");
		if (user == null) {
			userNotPickErrorMessage.setVisible(true);

		} else {
			Main.getLogger().info("A felhasználó be tudott jelentkezni.");
			main.showCalculatorView(user);
			stage.close();
			userNotPickErrorMessage.setVisible(false);
		}
	}

}
