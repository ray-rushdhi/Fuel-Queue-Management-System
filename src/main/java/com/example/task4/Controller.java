package com.example.task4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller { // Code reference - https://www.youtube.com/watch?v=FeTrcNBVWtg

    @FXML
    private TextField filterField;

    @FXML
    private TableView<PassengerFX> table;

    @FXML
    private TableColumn<PassengerFX, String> firstName;

    @FXML
    private TableColumn<PassengerFX, String> lastName;

    @FXML
    private TableColumn<PassengerFX, String> vehicleNo;

    @FXML
    private TableColumn<PassengerFX, Integer> litres;

    @FXML
    private TableColumn<PassengerFX, String> queue;


    static ObservableList<PassengerFX> list = FXCollections.observableArrayList(


    );


    public Controller() throws FileNotFoundException {
    }


    public void initialize() {

        firstName.setCellValueFactory(new PropertyValueFactory<PassengerFX, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<PassengerFX, String>("lastName"));
        vehicleNo.setCellValueFactory(new PropertyValueFactory<PassengerFX, String>("vehicleNo"));
        litres.setCellValueFactory(new PropertyValueFactory<PassengerFX, Integer>("litres"));
        queue.setCellValueFactory(new PropertyValueFactory<PassengerFX, String>("queue"));

        // Code reference - https://www.youtube.com/watch?v=-Aud0cDh-J8
        File dataFile = new File("data.txt"); // Create a File object

        // Please enter the proper path of the task 3 data.txt save file to run the program

        Scanner read = null; // Create a Scanner object to read the file
        try {
            read = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);


        String passengerDetails;
        for (int x = 0; x < 5; x++) { // Loop for 30 times to get all the passenger details in the CSV file
            for (int y = 0; y < 6; y++) {
                passengerDetails = read.nextLine();
                // Store each line into an array and separate the data using commas
                String[] parts = passengerDetails.split(",");
                String count = "QueueNo."+(x+1);
                // Check if the line is an empty space
                if (passengerDetails.length() > 2) {
                    // Add passenger using the comma separated values in the array above
                    String pasFirstName = parts[0];
                    String pasLastName = parts[1];
                    String vehicleNo = parts[2];
                    int litresNeeded = Integer.parseInt(parts[3]);

                    list.add(new PassengerFX(pasFirstName, pasLastName, vehicleNo, litresNeeded, count));
                }
            }
        }

        String unimportant;
        for (int y=0; y<6; y++){
            unimportant = read.nextLine();
        }

        String waitingListDetails;
        // Use a while loop until there is no data
        while (read.hasNextLine()){
            waitingListDetails = read.nextLine();
            // Store each line into an array and separate the data using commas
            String[] sections = waitingListDetails.split(",");
            // Check if the line is an empty space
            // Add passenger using the comma separated values in the array above
            if (waitingListDetails.length()>2){
                String waitFirstName = sections[0];
                String waitLastName = sections[1];
                String waitVehicleNo = sections[2];
                int waitLitresNeeded = Integer.parseInt(sections[3]);
                list.add(new PassengerFX(waitFirstName,waitLastName,waitVehicleNo,waitLitresNeeded,"Waiting list"));
            }
        }

        // Code reference - https://www.youtube.com/watch?v=FeTrcNBVWtg

        FilteredList<PassengerFX> filteredData = new FilteredList<>(list, b-> true);

            filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate(passenger -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (passenger.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (passenger.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else if (passenger.getVehicleNo().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                        else
                            return false;

                });
            });

            SortedList<PassengerFX> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(table.comparatorProperty());

            table.setItems(sortedData);
    }
}
