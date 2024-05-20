package TimePlanner.Backend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.Profile;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;

public class ProfileController implements Initializable {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField telephoneField;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Label pseudo;





    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    /*
     * 
     * 
     * 
     * 
     * 
     */
    // Deserialize the profile object from the file and populate the text fields

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeProfile();
    }

    private void initializeProfile() {
        emailField.setText(null);
        nomField.setText(null);
        passwordField.setText(null);
        telephoneField.setText(null);

        if (utilisateur != null) {
            pseudo.setText(utilisateur.getProfile().getNom());
            email.setText(utilisateur.getProfile().getEmail());
            telephone.setText(utilisateur.getProfile().getTelephone());
        }
    }


    @FXML
    private void saveChanges() {
        // Retrieve the entered values from the text fields
        String nameNew = nomField.getText();
        String emailNew = emailField.getText();
        String passwordNew = passwordField.getText();
        String telephoneNew = telephoneField.getText();
        if(nameNew==null){
            nameNew = pseudo.getText();
        }
        if(emailNew==null){
            emailNew = email.getText();
        }
        if(telephoneNew==null){
            telephoneNew = telephone.getText();
        }
        if(passwordNew==null){
            passwordNew = utilisateur.getProfile().getPassword();
        }
        //check the values of nameNew, emailNew, passwordNew and telephoneNew before updating the profile object
        System.out.println("-------------");
        System.out.println(nameNew);
        System.out.println(emailNew);
        System.out.println(passwordNew);
        System.out.println(telephoneNew);
        System.out.println("-------------");
        Profile profile = new Profile(nameNew, emailNew, passwordNew, telephoneNew);
        //priint email new password new and telephone new and name new
        pseudo.setText(nameNew);
        email.setText(emailNew);
        telephone.setText(telephoneNew);
        // Update the profile object with the new values
        utilisateur.setProfile(profile);

        // Update the data manager as well
        DataManager.getInstance().setUtilisateur(utilisateur);

        // Serialize the new utilisateur object to a file
        serializeProfile(utilisateur);
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    private void serializeProfile(Utilisateur utilisateur) {

        String username = utilisateur.getProfile().getNom().replaceAll(" ", "");
        System.out.println("-------------------");
        System.out.println(username);
        System.out.println("-------------------");
        try {
            FileOutputStream fileOut = new FileOutputStream("Smart Time Planner/src/TimePlanner/UsersInformation/" + username + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(utilisateur);
            out.close();
            fileOut.close();
            System.out.println("Profile serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void Calendar(){
        String nextPage = "/TimePlanner/Frontend/Pages/Calendar/Calendar.fxml";
        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }
    @FXML
    public void Period(){
        String nextPage = "/TimePlanner/Frontend/Pages/Period/Period.fxml";
        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }

    @FXML
    public void Project(){
        String nextPage = "/TimePlanner/Frontend/Pages/Project/Project.fxml";
        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }
    @FXML
    public void Tasks(){
        String nextPage = "/TimePlanner/Frontend/Pages/Tasks/Tasks.fxml";
        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }
    @FXML
    public void Statistics(){
        String nextPage = "/TimePlanner/Frontend/Pages/Statistics/Statistics.fxml";
        try{
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }
    @FXML
    public void History(){
        String nextPage = "/TimePlanner/Frontend/Pages/History/History.fxml";
        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
            e.printStackTrace();
        }
    }
    @FXML
    public void Profile(){
        String nextPage = "/TimePlanner/Frontend/Pages/Profile/Profile.fxml";
        try {
            // Load the next page
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nextPage));
            Parent next = fxmlLoader.load();

            // Get the current scene
            Scene currentScene = nomField.getScene();

            // Set the root of the current scene to the next root
            currentScene.setRoot(next);
        } catch (IOException e) {
            System.out.println("Error while loading the next page");
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
     */
    /*
     * private Profile deserializeProfile(String username) {
     * try {
     * FileInputStream fileIn = new
     * FileInputStream("./src/TimePlanner/UsersInformation/" + username + ".ser");
     * ObjectInputStream in = new ObjectInputStream(fileIn);
     * Profile profile = (Profile) in.readObject();
     * in.close();
     * fileIn.close();
     * System.out.println("Profile deserialized successfully.");
     * return profile;
     * } catch (IOException | ClassNotFoundException e) {
     * System.out.println("Error deserializing the profile");
     * e.printStackTrace();
     * }
     * return null;
     * }
     */

}
