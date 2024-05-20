package TimePlanner.Backend.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
//import data management
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.TacheSimple;
import TimePlanner.Backend.Services.DataManager;
import javafx.scene.control.TextField;

public class PeriodiqueController implements Initializable {
    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();
    ArrayList<Tache> taches = DataManager.getInstance().getTaches();
    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private Label errormessage;
    @FXML
    private TextField titleTask;
    @FXML
    private TextField descriptionTask;
    @FXML
    private DatePicker firstday;
    @FXML
    private DatePicker lastday;

    @FXML
    private ComboBox<String> StarthourComboBox;
    @FXML
    private ComboBox<String> StartMinuteComboBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializePopup();
    }

    public void initializePopup() {
        // Initialize priorityComboBox
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("High", "Medium", "Low");
        priorityComboBox.setItems(items);
        priorityComboBox.setValue("Medium"); // Set the default value

        // Get the keys from the HashMap and convert it to a List
        List<String> categoryKeys = new ArrayList<>(utilisateur.getCategoryList().keySet());

        // Initialize categoryComboBox
        categoryComboBox.getItems().addAll(categoryKeys);

        String firstCategory = categoryKeys.get(0);
        categoryComboBox.setValue(firstCategory); // Set the default value
        initializeHourComboBox();
        initializeMinuteComboBox();

    }
    private void initializeHourComboBox() {
        ObservableList<String> hours = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            String hour = String.format("%02d", i);
            hours.add(hour);
        }
        StarthourComboBox.setItems(hours);
    }

    private void initializeMinuteComboBox() {

        ObservableList<String> minutes = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            String minute = String.format("%02d", i);
            minutes.add(minute);
        }
        StartMinuteComboBox.setItems(minutes);

    }

    public void Add() {
        String titre = this.titleTask.getText();
        String description = this.descriptionTask.getText();
        String priority = this.priorityComboBox.getValue();
        String category = this.categoryComboBox.getValue();
        LocalDate firstday = this.firstday.getValue();
        LocalDate lastday = this.lastday.getValue(); // Can be null if not selected
        int starthour = Integer.parseInt(this.StarthourComboBox.getValue());
        int startminute = Integer.parseInt(this.StartMinuteComboBox.getValue());

        // Printing all the inputs
        System.out.println(titre);
        System.out.println(description);
        System.out.println(priority);
        System.out.println(category);
        System.out.println(firstday);
        System.out.println(lastday);
        System.out.println(starthour);
        System.out.println(startminute);

        if (titre.isEmpty() && description.isEmpty() && priority.isEmpty() && category.isEmpty() && firstday == null && starthour == 0 && startminute == 0 && lastday == null && lastday.isBefore(firstday)) {
            errormessage.setText("Please fill in the required fields");
            System.out.println("Please fill in the required fields");
        } else {
            if(taches==null)
                taches=new ArrayList<Tache>();

            TacheSimple tacheSimple = new TacheSimple(titre, description, priority, firstday, lastday, category, starthour, startminute);
            taches.add(tacheSimple);
            errormessage.setText("Task added successfully");
            this.titleTask.setText("");
            this.descriptionTask.setText("");
            this.priorityComboBox.setValue("Medium");
            this.categoryComboBox.setValue(utilisateur.getCategorie_taches().get(0));
            this.firstday.setValue(null);
            this.lastday.setValue(null);
            this.StarthourComboBox.setValue("0");
            this.StartMinuteComboBox.setValue("0");
        }
    }



}
