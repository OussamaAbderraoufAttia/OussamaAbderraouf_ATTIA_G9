package TimePlanner;

import TimePlanner.Backend.Models.Utilisateur;
//import TimePlanner.Backend.Models.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Frontend/Pages/Login/Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    private static void createSerializedProfile() {
        // Create a sample profile object
        Utilisateur user = new Utilisateur("User", "Oussama@example.com",
                "123", "1234567890");

        try (FileOutputStream fileOutputStream = new FileOutputStream("Smart Time Planner/src/TimePlanner/UsersInformation/User.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            // Write the user object to the file
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        createSerializedProfile();
        launch(args);
    }
}