package hu.unideb.inf.prt.CalorieCalc;
	
import java.io.IOException;

import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;
import hu.unideb.inf.prt.CalorieCalc.controller.AddNutrientViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.CalculatorViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.EditUserDataViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.LoginViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.MainViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.RegistrationViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.WeeklyStatisticsController;
import hu.unideb.inf.prt.CalorieCalc.io.UserDAOImpl;
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
	 * @return A felhasználók listája.
	 */
	public static ObservableList<User> getUsers(){
		return users;
	}
	
	private static Main main;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
			main = new Main();
			UserDAOImpl dao = new UserDAOImpl();
			dao.loadUsers(null);
			
			Calculation.resetGotNutrients(Main.getUsers());
			for(User user : getUsers()) {						
				Calculation.handleSkippedDays(user.getWeek());
			}
		
			 main.primaryStage = primaryStage;
			 main.primaryStage.setTitle("CalorieCalc");
			 showMainView();			
	}
	
	public void showMainView() {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("MainView.fxml"));
		try {
			BorderPane entry = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Fomenu");
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
			
		}

	}
	
	public void showLoginView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("LoginView.fxml"));
		try {
			BorderPane loginView = (BorderPane) loader.load();
			Stage loginStage = new Stage();
			loginStage.setTitle("Bejelentkezes");
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
			
		}
		
	}
	
	
	public void showRegView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("RegistrationView.fxml"));
		try {
			BorderPane registrationView = loader.load();
			Stage registrationStage = new Stage();
			registrationStage.setTitle("Regisztracio");
			registrationStage.initModality(Modality.WINDOW_MODAL);
			registrationStage.initOwner(null);
			
			RegistrationViewController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(registrationStage);
			
			Scene scene = new Scene(registrationView);
			registrationStage.setScene(scene);

			registrationStage.show();
		} catch (IOException | IllegalStateException e) {
	
		}
		
	}
	
	public void showCalculatorView(User user){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("CalculatorView.fxml"));
		try {
		rootPane = (BorderPane) loader.load();
		Scene scene = new Scene(rootPane);
		main.primaryStage.setScene(scene);

		CalculatorViewController controller = loader.getController();
		controller.setMain(main);
		controller.setStage(main.primaryStage);
		controller.setUser(user);
		controller.setStageFocusListener(main.primaryStage);

		primaryStage.setResizable(false);
		primaryStage.show();
		
		} catch (IOException | IllegalStateException e) {
			
		}
		
	}
	
	public void showAddNutrientView(User user){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("AddNutrientView.fxml"));
		try{
			AnchorPane pane = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Tapanyag hozzaadasa");
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
			
		}
	}
	
	public void showEditUserDataView(User user) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("EditUserDataView.fxml"));
		try {
			BorderPane pane = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Felhasznalo adatai");
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
			
		}

	}
	
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
	

	public static void main(String[] args) {
		launch(args);
		Calculation.resetUndos(getUsers());
		UserDAOImpl dao = new UserDAOImpl();
		dao.saveUsers(getUsers(), null);
		}
	
	public User getUserByUserName(String name){
		return getUsers().stream().filter(u->u.getUserName().equals(name)).findFirst().orElse(null);
	}
	
	
	
}
