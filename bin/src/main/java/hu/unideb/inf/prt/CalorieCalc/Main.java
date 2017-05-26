package hu.unideb.inf.prt.CalorieCalc;
	
import java.io.IOException;

import hu.unideb.inf.prt.CalorieCalc.controller.MainViewController;
import hu.unideb.inf.prt.CalorieCalc.controller.RegistrationViewController;
import hu.unideb.inf.prt.CalorieCalc.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	private static ObservableList<User> user = FXCollections.observableArrayList();
	
	public static ObservableList<User> getUser(){
		return user;
	}
	
	private static Main main;
	@Override
	public void start(Stage primaryStage) throws IOException {
		main = new Main();
		
		
			 main.primaryStage = primaryStage;
			 main.primaryStage.setTitle("CalorieCalc");
				showMainView();			
	}
	/*
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/MainView.fxml"));
		
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		
		
		
		primaryStage.show();
	}
	*/
	public void showMainView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/MainView.fxml"));
		try {
			BorderPane entry = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Bejelentkezés");
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
		loader.setLocation(Main.class.getResource("/view/LoginView.fxml"));
		try {
		BorderPane loginView = (BorderPane) loader.load();
		Stage loginStage = new Stage();
		loginStage.setTitle("Bejelentkezés");
		loginStage.initModality(Modality.WINDOW_MODAL);
		loginStage.initOwner(null);
		
		Scene scene = new Scene(loginView);
		loginStage.setScene(scene);
		
		loginStage.show();
		
		} catch (IOException | IllegalStateException e) {
			
		}
		
	}
	
	public void showRegView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/RegistrationView.fxml"));
		try {
			BorderPane pane = (BorderPane) loader.load();
			Stage stage = new Stage();
			//stage.setResizable(false);
			stage.setTitle("Regisztráció");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(null);

			RegistrationViewController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(stage);

			Scene scene = new Scene(pane);
			stage.setScene(scene);

			stage.show();

		} catch (IOException | IllegalStateException e) {
			
		}
	}
	/*
	public static void showRegView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/RegistrationView.fxml"));
		BorderPane registrationView = loader.load();
		Stage registrationStage = new Stage();
		registrationStage.setTitle("Bejelentkezés");
		registrationStage.initModality(Modality.WINDOW_MODAL);
		registrationStage.initOwner(primaryStage);
		
		RegistrationViewController controller = loader.getController();
		controller.setMain(main);
		controller.setStage(registrationStage);
		
		Scene scene = new Scene(registrationView);
		registrationStage.setScene(scene);
		registrationStage.show();
		
	}
	*/
	public static void main(String[] args) {
		launch(args);
	}
	
	public User getUserByUserName(String name){
		return getUser().stream().filter(u->u.getUserName().equals(name)).findFirst().orElse(null);
	}
	
	
	
}
