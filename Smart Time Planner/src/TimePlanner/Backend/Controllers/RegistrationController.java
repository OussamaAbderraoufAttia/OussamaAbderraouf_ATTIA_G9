package TimePlanner.Backend.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import TimePlanner.Backend.Models.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class RegistrationController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Button SignInButton;
    @FXML
    private Button SignUpButton;

    @FXML
    private void addUser() {
        String nom = nomField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        Utilisateur user = new Utilisateur(nom, email, password, telephone);
        createSerializedProfile(user);
    }

    private void createSerializedProfile(Utilisateur user) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Smart Time Planner/src/TimePlanner/UsersInformation/" + user.getProfile().getNom() + ".ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            // Write the user object to the file
            objectOutputStream.writeObject(user);
            System.out.println("User profile serialized and saved to " + user.getProfile().getNom() + ".ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SignIn() {

        String nextPage = "/TimePlanner/Frontend/Pages/Login/Login.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nextPage));
            Parent root = loader.load();

            // Get the current scene
            Scene currentScene = SignInButton.getScene();

            // Set the root of the current scene to the registration page root
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.getCause().printStackTrace();
        }
    }
}
