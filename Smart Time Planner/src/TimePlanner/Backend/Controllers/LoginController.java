package TimePlanner.Backend.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameErrorMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;
    @FXML
    private Label errormessage;

    /*
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * EVENT LISTENER FOR THE SIGNIN BUTTON CLICK
     */

    @FXML
    public void SignIn() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform login authentication and retrieve the Utilisateur object
        Utilisateur utilisateur = authenticate(username, password);

        if (utilisateur != null) {
            // Set the Utilisateur in the DataManager
            DataManager.getInstance().setUtilisateur(utilisateur);

            // Load the next page
            loadNextPage(utilisateur);
        }
    }

    /*
     *
     *
     *
     *
     *
     *
     *
     * AUTHENTIFICATION
     */

    @FXML
    public void SignUp() {
        System.out.println("Sign up is being clicked.");

        String nextPage = "/TimePlanner/Frontend/Pages/Registration/Registration.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nextPage));
            Parent root = loader.load();

            // Get the current scene
            Scene currentScene = SignUpButton.getScene();

            // Set the root of the current scene to the registration page root
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * AUTHENTIFICATION
     */

    private Utilisateur authenticate(String username, String password) {

        String pseudo = username.replaceAll(" ", "");
        System.out.print("The pseudo is "+ pseudo);
        String filename = "Smart Time Planner/src/TimePlanner/UsersInformation/" + pseudo + ".ser";

        File file = new File(filename);

        if (file.exists()) {
            System.out.println("File is found");
            Utilisateur utilisateur = null;
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                utilisateur = (Utilisateur) objectInputStream.readObject();
                String storedUsername = utilisateur.getProfile().getNom();
                String storedPassword = utilisateur.getProfile().getPassword();

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return utilisateur;
                } else {
                    errormessage.setText("Invalid password!");
                }
            } catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
            }
        } else {
            errormessage.setText("User does no exist!");
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            System.out.println("File Path: " + file.getAbsolutePath());
            System.out.println("File is not found");
        }
        return null;
    }

    /*
     *
     *
     *
     *
     *
     *
     *
     * GO TO THE NEXT APPROPRIATE PAGE
     */

    private void loadNextPage(Utilisateur utilisateur) {
        String nextPage;

        if (utilisateur.getPeriode() == null ){
            nextPage = "/TimePlanner/Frontend/Pages/PeriodChoice/PeriodChoice.fxml";
        } else {
            nextPage = "/TimePlanner/Frontend/Pages/Profile/Profile.fxml";
        }

        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = SignInButton.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }


}
