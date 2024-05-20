package TimePlanner.Backend.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;


import javafx.scene.control.Button;

public class AutomaticPlanController {
    @FXML
    private VBox formContainer;

    @FXML
    private Button addTaskButton;

    @FXML
    private void handleAddTaskButtonAction(ActionEvent event) {
        HBox newForm = createNewForm();
        formContainer.getChildren().add(newForm);
    }

    private HBox createNewForm() {
        // Create the new form components
        TextField taskNameField = new TextField();
        DatePicker datePicker = new DatePicker();
        ComboBox<String> timeComboBox = new ComboBox<>();
        timeComboBox.getItems().addAll("Morning", "Afternoon", "Evening");

        // Create the HBox container for the form
        HBox newForm = new HBox();
        newForm.setSpacing(10); // Set the spacing between form elements

        // Add the form components to the newForm HBox
        newForm.getChildren().addAll(taskNameField, datePicker, timeComboBox);

        // Create the button and set its onAction method
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            // Handle delete button action here
            formContainer.getChildren().remove(newForm);
        });

        // Add the delete button to the newForm HBox
        newForm.getChildren().add(deleteButton);
        System.out.println("It is being added");
        return newForm;
    }





}
