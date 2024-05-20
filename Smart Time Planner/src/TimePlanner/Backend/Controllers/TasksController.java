package TimePlanner.Backend.Controllers;
import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import java.util.ArrayList;
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
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Modality;
//improting all the tasks classes
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.TacheSimple;
import TimePlanner.Backend.Models.TacheSimplePeriodique;
import TimePlanner.Backend.Models.TacheDecomposable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.geometry.Orientation;
public class TasksController {
    @FXML
    private Label nomField;

    @FXML
    private Button simple;
    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
            private FlowPane tachesContainer= new FlowPane();
    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();
    ArrayList<Tache> Addedtaches= new ArrayList<Tache>();
    //push the new array list to data manager

    public class TaskCard extends HBox {
        private String title;
        private String cardColor;

        public TaskCard(String title, String cardColor) {
            this.title = title;
            this.cardColor = cardColor;

            // Set spacing and padding for the card
            setSpacing(10);
            setPadding(new Insets(10));
            setBackground(createBackground(cardColor));

            // Create and configure the title label
            Label titleLabel = new Label(title);
            // Customize the appearance of the title label, if needed

            // Add the title label to the card
            getChildren().add(titleLabel);
        }

        private Background createBackground(String backgroundColor) {
            Color color = Color.web(backgroundColor);
            BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
            return new Background(fill);
        }
    }


    @FXML
    private void simple(ActionEvent event) {
        Addedtaches = new ArrayList<Tache>();
        DataManager.getInstance().setTaches(Addedtaches);

        // Create a new Stage object for the pop-up
        Stage popupStage = new Stage();

        try {
            // Load the FXML file for the pop-up content
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TimePlanner/Frontend/Pages/Tasks/simple.fxml"));
            Parent popupContent = fxmlLoader.load();

            // Set the content of the pop-up window
            popupStage.setScene(new Scene(popupContent));

            // Customize the appearance and behavior of the pop-up window
            popupStage.setTitle("Add a simple task");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setResizable(false);

            // Show the pop-up window
            popupStage.show();
            popupStage.setOnHidden(e -> {
                //Check if the list is empty
                if(Addedtaches.isEmpty())
                    System.out.println("The list is empty");
                else
                    System.out.println("The list is not empty");
                System.out.println("Pop-up window closed");
                tachesContainer.setOrientation(Orientation.HORIZONTAL);
                Insets cardMargin = new Insets(10, 10, 10, 0);
                for (Tache addedTache : Addedtaches) {

                    String title = addedTache.getTitre();
                    TaskCard taskCard = new TaskCard(title, "#9d58fa");


                    // Apply card margins
                    for (Node child : tachesContainer.getChildren()) {
                        FlowPane.setMargin(child, cardMargin);
                    }
                    tachesContainer.getChildren().add(taskCard);
                }
                if(utilisateur.getTaches().isEmpty()){
                    utilisateur.setTaches(Addedtaches);
                }else{
                    utilisateur.mergeTaches(Addedtaches);
                }

                Addedtaches=new ArrayList<Tache>();
                DataManager.getInstance().setTaches(Addedtaches);
                DataManager.getInstance().setUtilisateur(utilisateur);
                serializeUser(utilisateur);

            });
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

    private void serializeUser(Utilisateur utilisateur) {

        String username = utilisateur.getProfile().getNom().replaceAll(" ", "");

        try {
            FileOutputStream fileOut = new FileOutputStream("Smart Time Planner/src/TimePlanner/UsersInformation/" + username + ".ser",
                    false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(utilisateur);
            out.close();
            fileOut.close();
            System.out.println("Profile serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
