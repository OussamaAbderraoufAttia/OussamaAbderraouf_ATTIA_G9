package TimePlanner.Backend.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javafx.geometry.Insets;
import TimePlanner.Backend.Models.Creneau;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import java.time.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class CreneauxLibresController implements Initializable {

    @FXML
    private ComboBox<String> StarthourComboBox;

    @FXML
    private ComboBox<String> StartMinuteComboBox;

    @FXML
    private ComboBox<String> EndHourComboBox;

    @FXML
    private ComboBox<String> EndMinuteComboBox;

    @FXML
    private Label CurrentPeriodDay;
    @FXML
    private Button nextbutton;

    private List<Creneau> creneauxList = new ArrayList<Creneau>();




    @FXML
    private Button nextButton;

    @FXML
    private Label ErrorMessageAjouter;

    @FXML
    private FlowPane creneauContainer= new FlowPane();





    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    LocalDate firstday = utilisateur.getPeriode().getStartDate();
    LocalDate endday = utilisateur.getPeriode().getEndDate();
    LocalDate currentDay = firstday;

    ArrayList<Creneau> creneaujour = new ArrayList<>();
    int nbcreneaux = 0;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeHourComboBox();
        initializeMinuteComboBox();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH);
        String formattedDate = currentDay.format(outputFormatter);
        CurrentPeriodDay.setText(formattedDate);
    }

    private void initializeHourComboBox() {
        ObservableList<String> hours = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            String hour = String.format("%02d", i);
            hours.add(hour);
        }
        StarthourComboBox.setItems(hours);
        EndHourComboBox.setItems(hours);
    }

    private void initializeMinuteComboBox() {

        ObservableList<String> minutes = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            String minute = String.format("%02d", i);
            minutes.add(minute);
        }
        StartMinuteComboBox.setItems(minutes);
        EndMinuteComboBox.setItems(minutes);

    }
    public class CreneauCard extends VBox {
        final String firstHour;
        final String firstMinute;
        final String lastHour;
        final String lastMinute;
        final Label timeLabel;

        public CreneauCard(String firstHour, String firstMinute, String lastHour, String lastMinute, Pane container) {
            this.firstHour = firstHour;
            this.firstMinute = firstMinute;
            this.lastHour = lastHour;
            this.lastMinute = lastMinute;

            // Create the card layout
            setSpacing(10);
            setPadding(new Insets(10));
            setStyle("-fx-border-radius: 10px; -fx-start-margin: 10px; -fx-end-margin: 10px; -fx-top-margin: 10px; -fx-bottom-margin: 10px;" +
                    "-fx-background-color: #FFE57A; -fx-background-insets: 0px; -fx-start-margin: 10px;");

            // Create the time label
            timeLabel = new Label();
            updateTimeLabel();

            // Add the time label to the card
            getChildren().add(timeLabel);

            // Add the card to the container
            container.getChildren().add(this);
        }

        private void updateTimeLabel() {
            timeLabel.setText(firstHour + ":" + firstMinute + " - " + lastHour + ":" + lastMinute);
        }
    }

    @FXML
    private void handleAjouterCreneauController() {
        String FirstHour = StarthourComboBox.getValue();
        String LastHour = EndHourComboBox.getValue();
        String FirstMinute = StartMinuteComboBox.getValue();
        String LastMinute = EndMinuteComboBox.getValue();
        String firstHour = StarthourComboBox.getValue();
        String firstMinute = StartMinuteComboBox.getValue();
        String lastHour = EndHourComboBox.getValue();
        String lastMinute = EndMinuteComboBox.getValue();

        boolean conditiona = FirstHour != null && FirstHour != "";
        boolean conditionb = LastHour != null && LastHour != "";
        boolean conditionc = FirstMinute != null && FirstMinute != "";
        boolean conditiond = LastMinute != null && LastMinute != "";

        if (conditiona && conditionb && conditionc && conditiond) {
            ErrorMessageAjouter.setText(null);
            nbcreneaux++;

            if (nbcreneaux <= 12) {
                String startTime = firstHour + ":" + firstMinute + ":00";
                String endTime = lastHour + ":" + lastMinute + ":00";
                LocalTime debut = LocalTime.parse(startTime);
                LocalTime fin = LocalTime.parse(endTime);
                creneauxList.add(new Creneau(debut, fin));
                CreneauCard creneauCard = new CreneauCard(firstHour, firstMinute, lastHour, lastMinute, creneauContainer);
                String creneauCardId = "creneau" + nbcreneaux;
                creneauCard.setId(creneauCardId);
                Insets cardMargin = new Insets(10, 10, 10, 0);
                for (Node child : creneauContainer.getChildren()) {
                    FlowPane.setMargin(child, cardMargin);
                }
                creneauContainer.setOrientation(Orientation.HORIZONTAL);
                creneauContainer.getChildren().add(creneauCard);
            } else {
                ErrorMessageAjouter.setText("You can't add more than 12 creneaux");
            }
            StarthourComboBox.setValue(null);
            StartMinuteComboBox.setValue(null);
            EndHourComboBox.setValue(null);
            EndMinuteComboBox.setValue(null);


        }

        else

        {
            ErrorMessageAjouter.setText("Missing values");
        }
    }


    @FXML
    private void handleNextButton() {
        nbcreneaux = 0;
        creneaujour.clear();
        utilisateur.getPeriode().addCreneaux(currentDay, creneauxList);
        creneauxList = new ArrayList<>();
        creneauContainer.getChildren().clear();

        currentDay = currentDay.plus(1, ChronoUnit.DAYS);
        if (!currentDay.isBefore(endday.plusDays(1))) {
            Map<LocalDate, List<Creneau>> creneauxPerDay = utilisateur.getPeriode().getCreneaux();
            for (Map.Entry<LocalDate, List<Creneau>> entry : creneauxPerDay.entrySet()) {
                LocalDate key = entry.getKey();
                List<Creneau> value = entry.getValue();
                System.out.println("--------------------");
                System.out.println("key: " + key);
                // printing values in a loop
                for (Creneau creneau : value) {
                    System.out.println("Debut: " + creneau.getDebut());
                    System.out.println("Fin: " + creneau.getFin());
                }
                System.out.println("--------------------");
            }

            DataManager.getInstance().setUtilisateur(utilisateur);
            serializeUser(utilisateur);
            String nextPage = "/TimePlanner/Frontend/Pages/Profile/Profile.fxml";
            try {
                // Load the next page
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(nextPage));
                Parent next = fxmlLoader.load();

                // Get the current scene
                Scene currentScene = nextbutton.getScene();

                // Set the root of the current scene to the next root
                currentScene.setRoot(next);
            } catch (IOException e) {
                System.out.println("Error while loading the next page");
                e.printStackTrace();
            }
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