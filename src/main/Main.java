package main;

import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.DataStorageService;

import java.io.*;

public class Main extends Application {

    public static User USER = new User();

    public static void enterLogin(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("../login/login_scene.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    public static void enterFleet(Stage stage) throws IOException {


        Parent root = FXMLLoader.load(Main.class.getResource("../garage/garage_scene.fxml"));
        stage.setTitle("Fleet");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    public static void enterTransactions(Stage stage) throws IOException {


        Parent root = FXMLLoader.load(Main.class.getResource("../transactions/transactions_scene.fxml"));
        stage.setTitle("Transactions");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    public static void enterSignUp(Stage stage) throws IOException {


        Parent root = FXMLLoader.load(Main.class.getResource("../signup/signup_scene.fxml"));
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    public static void enterMain(Stage stage) throws IOException {

        if (DataStorageService.verifyUserData(USER)) {

            Parent root = FXMLLoader.load(Main.class.getResource("Main_scene.fxml"));
            stage.setTitle("Car Sale Manager");
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        }
        else{

            showWrongCredentialsPopUp();
        }
    }
    public static void showWrongCredentialsPopUp() throws IOException {

        Stage popup = new Stage();
        popup.setScene(new Scene(FXMLLoader.load(Main.class.getResource("../alerts/wrong_credentials.fxml"))));
        popup.initStyle(StageStyle.UNDECORATED);
        popup.setX(380);
        popup.setY(260);
        popup.show();

    }
    public static void showInvalidEntryPopUp() throws IOException {

        Stage popup = new Stage();
        popup.setScene(new Scene(FXMLLoader.load(Main.class.getResource("../alerts/invalid_entry.fxml"))));
        popup.initStyle(StageStyle.UNDECORATED);
        popup.setX(380);
        popup.setY(260);
        popup.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        loadFiles();

        Parent root = FXMLLoader.load(Main.class.getResource("../login/login_scene.fxml"));
        stage.setTitle("Login");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();

    }

    private void loadFiles() {

        File directory = new File(DataStorageService.PATH);

        if (!directory.exists()){
            directory.mkdirs();
        }

        try{
            BufferedReader reader = new BufferedReader(new FileReader(DataStorageService.AUTHENTICATION_PATH));
            BufferedReader reader2 = new BufferedReader(new FileReader(DataStorageService.FLEET_PATH));
            BufferedReader reader3 = new BufferedReader(new FileReader(DataStorageService.TRANSACTIONS_PATH));
            reader.close();
            reader2.close();
            reader3.close();
        }
        catch(FileNotFoundException e){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(DataStorageService.AUTHENTICATION_PATH, true));
                BufferedWriter writer2 = new BufferedWriter(new FileWriter(DataStorageService.FLEET_PATH, true));
                BufferedWriter writer3 = new BufferedWriter(new FileWriter(DataStorageService.TRANSACTIONS_PATH, true));
                writer.close();
                writer2.close();
                writer3.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reloadUser(){
        Main.USER.setUsername("");
        Main.USER.setPassword("");
        Main.USER.setPhone("");
    }
}
