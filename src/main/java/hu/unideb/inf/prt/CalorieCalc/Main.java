package hu.unideb.inf.prt.CalorieCalc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.prt.CalorieCalc.DAO.UserDAOImpl;
import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;
import hu.unideb.inf.prt.CalorieCalc.controller.AddNutrientViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.CalculatorViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.EditUserDataViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.LoginViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.MainViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.RegistrationViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.WeeklyStatisticsController;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Zöld Krisztián
 *
 */

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootPane;
	private static ObservableList<User> users = FXCollections.observableArrayList();

	/**
	 * Visszaadja a felhasználókat tartalmazó listát.
	 * 
	 * @return A felhasználók listája.
	 */
	public static ObservableList<User> getUsers() {
		return users;
	}

	private static Main main;
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * Viszaadja a {@code Main} osztály loggerét.
	 * @return A {@code Main} osztály loggere.
	 */
	public static Logger getLogger() {
		return logger;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		main = new Main();
		UserDAOImpl dao = new UserDAOImpl();
		dao.loadUsers(null);
		Calculation.resetIntakeNutrients(Main.getUsers());
		for (User user : getUsers()) {
			Calculation.fillSkippedDays(user.getWeek());
		}
		main.primaryStage = primaryStage;
		showMainView();
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void showMainView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("MainView.fxml"));
		try {
			BorderPane entry = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Főmenü");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(null);

			Scene scene = new Scene(entry);
			stage.setScene(scene);

			MainViewController controller = loader.getController();
			controller.setStage(stage);
			controller.setMain(main);

			stage.setResizable(false);
			stage.show();
		} catch (IOException | IllegalStateException e) {
			logger.error("Nem létezik vagy nem elérhető a MainView.fxml", e);
		}

	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void showLoginView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("LoginView.fxml"));
		try {
			BorderPane loginView = (BorderPane) loader.load();
			Stage loginStage = new Stage();
			loginStage.setTitle("Bejelentkezés");
			loginStage.initModality(Modality.WINDOW_MODAL);
			loginStage.initOwner(null);

			Scene scene = new Scene(loginView);
			loginStage.setScene(scene);

			LoginViewController controller = loader.getController();
			controller.setStage(loginStage);
			controller.setMain(main);

			loginStage.setResizable(false);
			loginStage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem létezik vagy nem elérhető a LoginView.fxml", e);
		}

	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void showRegView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("RegistrationView.fxml"));
		try {
			BorderPane registrationView = loader.load();
			Stage registrationStage = new Stage();
			registrationStage.setTitle("Regisztráció");
			registrationStage.initModality(Modality.WINDOW_MODAL);
			registrationStage.initOwner(null);

			RegistrationViewController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(registrationStage);

			Scene scene = new Scene(registrationView);
			registrationStage.setScene(scene);

			registrationStage.show();
		} catch (IOException | IllegalStateException e) {
			logger.error("Nem létezik vagy nem elérhető a RegistrationView.fxml", e);
		}

	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void showCalculatorView(User user) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("CalculatorView.fxml"));
		try {
			rootPane = (BorderPane) loader.load();
			Scene scene = new Scene(rootPane);
			main.primaryStage.setScene(scene);
			main.primaryStage.setTitle("CalorieCalc");
			CalculatorViewController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(main.primaryStage);
			controller.setUser(user);
			controller.setStageFocusListener(main.primaryStage);

			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem létezik vagy nem elérhető a CalculatorView.fxml", e);
		}

	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void showAddNutrientView(User user) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("AddNutrientView.fxml"));
		try {
			AnchorPane pane = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Tápanyag hozzáadása");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(main.primaryStage);

			Scene scene = new Scene(pane);
			stage.setScene(scene);

			AddNutrientViewController controller = loader.getController();
			controller.setUser(user);
			controller.setStage(stage);

			stage.setResizable(false);
			stage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem létezik vagy nem elérhető a AddNutrientView.fxml", e);
		}
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void showEditUserDataView(User user) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("EditUserDataView.fxml"));
		try {
			BorderPane pane = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Felhasználó adatai");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(main.primaryStage);

			EditUserDataViewController controller = loader.getController();
			controller.setUser(user);
			controller.adjustUserData();
			controller.setStage(stage);
			controller.setMain(main);

			Scene scene = new Scene(pane);
			stage.setScene(scene);

			stage.setResizable(false);
			stage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem létezik vagy nem elérhető a EditUserDataView.fxml", e);
		}

	}

	
	@SuppressWarnings("checkstyle:javadocmethod")
	public void showWeeklyStatistics(User user) {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Heti Statisztika");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(main.primaryStage);
		WeeklyStatisticsController controller = new WeeklyStatisticsController();
		controller.setUser(user);
		Scene scene = controller.setBarChart();
		stage.setScene(scene);
		stage.getScene().getStylesheets().add("style.css");
		stage.show();
	}

	/**
	 * Felhasználónév alapján megkeres egy felhasználót és visszaadja azt.
	 * 
	 * @param username
	 *            A felhasználó felhasználóneve.
	 * @return Egy {@code User} típusú objektumot.
	 */
	public User getUserByUserName(String username) {
		return getUsers()
				.stream()
				.filter(u -> u.getUserName().equals(username))
				.findFirst()
				.orElse(null);
	}

	/**
	 * A main függvény, mely elindítja a grafikus felületet, majd annak
	 * leállásakor meghívja a
	 * {@link hu.unideb.inf.prt.CalorieCalc.DAO.UserDAOImpl#saveUsers(java.util.List, java.nio.file.Path)}
	 * függvényt, mely perzisztálja a felhasználókat.
	 * 
	 * @param args
	 *            Indításkor megadott agrumentumtömb.
	 */
	public static void main(String[] args) {
		launch(args);
		Calculation.clearWithdraws(getUsers());
		UserDAOImpl dao = new UserDAOImpl();
		dao.saveUsers(getUsers(), null);
	}
}
