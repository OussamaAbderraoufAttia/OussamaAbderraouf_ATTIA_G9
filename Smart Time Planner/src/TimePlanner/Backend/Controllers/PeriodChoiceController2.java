package TimePlanner.Backend.Controllers;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.Periode;
import TimePlanner.Backend.Models.Projet;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.scene.paint.Paint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.Map;
public class PeriodChoiceController2  implements Initializable{
        @FXML
    private DatePicker firstday;

    @FXML
    private DatePicker lastday;

    @FXML
    private Label errormessage;

    @FXML
    private TextField categoryname;
    @FXML
    private Button nextbutton;

    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    @FXML
    private ComboBox<String> colorcategoryteComboBox;
    @FXML
    private FlowPane Cat1;
    @FXML
    private FlowPane Cat11;
    @FXML
    private FlowPane Cat111;
    @FXML
    private FlowPane Cat1111;
    @FXML
    private FlowPane Cat11111;
    @FXML
    private FlowPane Cat111111;
    @FXML
    private FlowPane Cat1111111;

    int nbcategory=0;
    private HashMap<String, String> categoryColor = new HashMap<String, String>();
    String idCategory= "category";
    String idCat = "cat";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColorCategoryteComboBox();

    }
    @FXML
    private void initializeColorCategoryteComboBox() {

        ObservableList<String> color = FXCollections.observableArrayList();
        color.add("red"); //
        color.add("blue");
        color.add("green");
        color.add("yellow");
        color.add("orange");
        color.add("purple");
        color.add("grey");

        colorcategoryteComboBox.setItems(color);

    }
    public class CategoryCard extends VBox {
        private final String category;
        private final Label categoryLabel;

        public CategoryCard(String category, String backgroundColor) {
            this.category = category;

            // Create the card layout
            setSpacing(10);
            setPadding(new Insets(10));
            setBackground(createBackground(backgroundColor));

            // Create the category label
            categoryLabel = new Label(category);

            // Add the category label to the card
            getChildren().add(categoryLabel);
        }

        private Background createBackground(String backgroundColor) {
            Color color = Color.web(backgroundColor);
            BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
            return new Background(fill);
        }
    }







    @FXML
    private void CheckandSet() {

        LocalDate currentDate = LocalDate.now();
        LocalDate firstDate = firstday.getValue();
        LocalDate lastDate = lastday.getValue();
        ObservableList<String> category = FXCollections.observableArrayList();

        boolean FirstMessageconditions = firstDate != null && firstDate.isAfter(currentDate);
        boolean SecondMessageConditions = lastDate != null && lastDate.isAfter(firstDate);
        //printing the first date and the last date
        System.out.println(firstDate);
        System.out.println(lastDate);



            if (FirstMessageconditions) {

                if (SecondMessageConditions) {


                    Periode periode = new Periode(firstDate, lastDate);
                    //set the Category hashmap
                    utilisateur.setCategoryList(categoryColor);
                    utilisateur.setPeriode(periode);
                    DataManager.getInstance().setUtilisateur(utilisateur);
                    serializeUser(utilisateur);

                    try {
                        Parent step2Root = FXMLLoader.load(getClass().getResource("/TimePlanner/Frontend/Pages/CreneauxLibres/CreneauxLibres.fxml"));

                        // Get the current scene
                        Scene currentScene = nextbutton.getScene();

                        // Set the root of the current scene to the Step2 root
                        currentScene.setRoot(step2Root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    errormessage.setText("Chose a valid last date");
                    errormessage.setVisible(true);
                }
            } else {
                errormessage.setText("Chose a valid start date");
                errormessage.setVisible(true);
            }


    }

    @FXML
    //Develop method to handle category and color addition
    // Declare categoryColor HashMap at the class level

    private void addCategory() {
        nbcategory++;
        idCategory = idCategory + "1";
        idCat = idCat + "1";
        Paint paint = null;
        String category = categoryname.getText();
        String color = colorcategoryteComboBox.getValue();
        String hexColor = null;

        if (category != null && color != null) {
            switch (color) {
                case "red":
                    hexColor = "#FDCBCB";
                    break;
                case "blue":
                    hexColor = "#D4ECFF";
                    break;
                case "green":
                    hexColor = "#CFFDCB";
                    break;
                case "yellow":
                    hexColor = "#FFE57A";
                    break;
                case "orange":
                    hexColor = "#FFA500";
                    break;
                case "purple":
                    hexColor = "#800080";
                    break;
                case "grey":
                    hexColor = "#808080";
                    break;
                default:
                    hexColor = "#000000"; // Default color if the input is unexpected
                    break;
            }

            // Add category and color to the hashmap
            categoryColor.put(category, hexColor);

            // Add category card dynamically based on nbcategory
            CategoryCard categoryCard = new CategoryCard(category, hexColor);
            switch (nbcategory) {
                case 1:
                    Cat1.getChildren().add(categoryCard);
                    break;
                case 2:
                    Cat11.getChildren().add(categoryCard);
                    break;
                case 3:
                    Cat111.getChildren().add(categoryCard);
                    break;
                case 4:
                    Cat1111.getChildren().add(categoryCard);
                    break;
                case 5:
                    Cat11111.getChildren().add(categoryCard);
                    break;
                case 6:
                    Cat111111.getChildren().add(categoryCard);
                    break;
                case 7:
                    Cat1111111.getChildren().add(categoryCard);
                    break;
                default:
                    // You can only add 7 categories
                    errormessage.setText("You can only add 7 categories");
                    errormessage.setVisible(true);
                    break;
            }
        } else {
            // Display error message if the user didn't enter a category or a color
            errormessage.setText("Please enter a category and a color");
            errormessage.setVisible(true);
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
