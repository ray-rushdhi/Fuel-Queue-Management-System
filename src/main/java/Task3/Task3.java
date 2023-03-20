package Task3;

import java.io.*;
import java.util.*;

public class Task3 {

    static FuelQueue[] fuelQueueArray = new FuelQueue[5]; // Create an array of FuelQueue objects

    static int fuelStock = 6600; // Variable for the Fuel Stock
    static int litrePrice = 430; // Variable for the price of a litre

    // Please note that the income is calculated according to the passengers who are served using the program

    public static ArrayList<Passenger> waitingList =new ArrayList<Passenger>(); // Create an arraylist of passenger objects for the waiting list


    public static void main(String[] args) throws IOException {

        // Initialize all the FuelQueue objects in the object array
        for (int i = 0; i < fuelQueueArray.length; i++) {
            fuelQueueArray[i] = new FuelQueue(6);
        }

        Scanner sc = new Scanner(System.in); // Creating a Scanner object

        initial:
        // Naming the loop
        while (true) { // Creating a while loop to continuously run the program

            System.out.println(" ");
            System.out.println("----------FUEL CENTER----------");
            System.out.println(" ");
            System.out.println("Menu Options");
            System.out.println(" ");
            System.out.println("100 or VFQ: View all Fuel Queues.");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add passenger to a Queue.");
            System.out.println("103 or RCQ: Remove a passenger from a Queue. (From a specific location)");
            System.out.println("104 or PCQ: Remove a served passenger.");
            System.out.println("105 or VCS: View Passengers Sorted in alphabetical order");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining Fuel Stock.");
            System.out.println("109 or AFS: Add Fuel Stock.");
            System.out.println("110 or IFQ: Show the Income of Each Fuel Queue");
            System.out.println("999 or EXT: Exit the Program.");
            System.out.println(" ");
            System.out.print("Enter an option : ");
            String option = sc.nextLine().toUpperCase();
            System.out.println(" ");

            switch (option) { // Using a switch case to loop through all the functions

                case "100":
                case "VFQ":
                    System.out.println("View all Fuel Queues ");
                    System.out.println(" ");
                    viewAllQueues();
                    break;
                case "101":
                case "VEQ":
                    System.out.println("View all Empty Queues ");
                    System.out.println(" ");
                    viewEmptyQueues();
                    break;
                case "102":
                case "ACQ":
                    System.out.println("Add a Passenger to a Queue ");
                    System.out.println(" ");
                    addPassenger();
                    break;
                case "103":
                case "RCQ":
                    System.out.println("Remove a Passenger from a specific location");
                    System.out.println(" ");
                    removeSpecific();
                    break;
                case "104":
                case "PCQ":
                    System.out.println("Remove a Served Passenger ");
                    System.out.println(" ");
                    removeServedPassenger();
                    break;
                case "105":
                case "VCS":
                    System.out.println("View Passengers Sorted in alphabetical order");
                    System.out.println(" ");
                    sortPassengers();
                    break;
                case "106":
                case "SPD":
                    System.out.println("Store Program Data into file");
                    System.out.println(" ");
                    storeData();
                    break;
                case "107":
                case "LPD":
                    System.out.println("Load Program Data from file");
                    System.out.println(" ");
                    loadData();
                    break;
                case "108":
                case "STK":
                    System.out.println(fuelStock + " litres of fuel remaining");
                    System.out.println(" ");
                    break;
                case "109":
                case "AFS":
                    System.out.println("Add fuel stock");
                    System.out.println(" ");
                    addFuelStock();
                    break;
                case "110":
                case "IFQ":
                    System.out.println("The income of each fuel queue");
                    System.out.println(" ");
                    income();
                    break;
                case "999":
                case "EXT":
                    System.out.println("Exit the program");
                    System.out.println(" ");
                    break initial;
                default:
                    System.out.println("Please enter a valid input");
            }
        }

    }

    public static void viewAllQueues() { // Method to view all the fuel queues

        for (int x = 0; x < 5; x++) { // Loop through all 5 fuel queues
            int count = 1;
            System.out.println("----- Fuel Queue " + (x + 1) + " -----");
            int sizeOfList = fuelQueueArray[x].getPassengersSize();
            for (int i = 0; i < sizeOfList; i++) { // Loop through the passengers list in each fuel queue object
                System.out.println(count + ". " + fuelQueueArray[x].getFirstName(i) + " " + fuelQueueArray[x].getLastName(i) + " - " + fuelQueueArray[x].getVehicleNo(i));
                count++;
            }
            int emptySpaces = fuelQueueArray[x].getQueueLength() - sizeOfList;
            for (int j = 0; j < emptySpaces; j++) { // Print "empty" if theres an empty space
                System.out.println(count + ". " + "empty");
                count++;
            }
            System.out.println(" ");
        }
    }

    public static void viewEmptyQueues() { // Method to view the number of empty slots in a Fuel Queue

        for (int x = 0; x < 5; x++) { // Using the "getEmptySpaces" method in the FuelQueue object
            System.out.println("Fuel Queue " + (x + 1) + " has " + fuelQueueArray[x].getEmptySpaces() + " empty slots");
        }
    }

    public static void addPassenger(){ // Method to add a passenger to the queue

        boolean available = checkAvailability();
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the first name of the passenger : ");
            if (sc.hasNextLine()) { // Input validation
                String pasFirstName = sc.nextLine();
                while (!pasFirstName.matches("[a-zA-Z ]+")) { // Check if user has input any number
                    System.out.print("Please enter a valid name! : ");
                    pasFirstName = sc.nextLine();
                }
                System.out.print("Enter the second name of the passenger : ");
                if (sc.hasNextLine()) {
                    String pasSecondName = sc.nextLine();
                    while (!pasSecondName.matches("[a-zA-Z ]+")) {
                        System.out.print("Please enter a valid name! : ");
                        pasSecondName = sc.nextLine();
                    }
                    System.out.print("Enter the vehicle number of the passenger : ");
                    if (sc.hasNextLine()) {
                        String pasVehicleNo = sc.nextLine();
                        System.out.print("Enter the number of litres needed : ");
                        if (sc.hasNextInt()) {
                            int pasLitresNeeded = sc.nextInt();
                            if (available) { // Check if there are available slots to add a passenger
                                int smallestQueue = selectSmallestQueue(); // Check for the smallest queue in the FuelQueue array
                                // Add passenger to the smallest queue
                                fuelQueueArray[smallestQueue].addPassenger(pasFirstName, pasSecondName, pasVehicleNo, pasLitresNeeded);
                                System.out.println(" ");
                                System.out.println(pasFirstName + " " + pasSecondName + " has been added to Queue No.0" + (smallestQueue + 1));
                                fuelStock = fuelStock - pasLitresNeeded;  // Deduct the fuel needed by the passenger from the fuel stock
                                // Warning message for low fuel
                                if (fuelStock<=500){
                                    System.out.println("WARNING ! Only "+fuelStock+" litres of fuel remaining");
                                }
                                break;
                            } else { // If no available slots add passenger to the waiting queue
                                System.out.println("----------------------------------------");
                                System.out.println("All the fuel queues are full");
                                System.out.println("Passenger will be added to the waiting queue");
                                // Add passenger to the waiting queue
                                waitingList.add(new Passenger(pasFirstName,pasSecondName,pasVehicleNo,pasLitresNeeded));
                                System.out.println(" ");
                                System.out.println(pasFirstName + " " + pasSecondName + " has been added to the waiting queue");
                                fuelStock = fuelStock - pasLitresNeeded;

                                if (fuelStock<=500){
                                    System.out.println("WARNING ! Only "+fuelStock+" litres of fuel remaining");
                                }
                                break;
                            }
                        } else {
                            System.out.println("Please enter a valid amount");
                            continue;
                        }
                    } else {
                        System.out.println("Please enter a valid vehicle number");
                        continue;
                    }
                }else {
                    System.out.println("Please enter a valid second name");
                    continue;
                }
            }else {
                System.out.println("Please enter a valid first name");
                continue;

            }
        }
    }

    public static void removeSpecific(){ // Method to remove a passenger from a specific index

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Fuel Queue number of the passenger : ");
            if (sc.hasNextInt()){ // Input validation
                int indexOfQueue = sc.nextInt();
                if (indexOfQueue>0 && indexOfQueue<6){
                    System.out.println(" ");
                    viewAllQueues(); // Print all the queues for efficiency
                    System.out.print("Enter the index in front of the person you wish to remove : ");
                    if (sc.hasNextInt()){
                        int indexOfPas = sc.nextInt();
                        if (indexOfPas>0 && indexOfPas<7){
                            // Get the size of the list
                            int sizeOfList = fuelQueueArray[indexOfQueue-1].getPassengersSize();
                            if (indexOfPas<=sizeOfList){ // Check if there is a passenger in the given index
                                System.out.println(fuelQueueArray[indexOfQueue-1].getFirstName(indexOfPas-1)+" "+fuelQueueArray[indexOfQueue-1].getLastName(indexOfPas-1)+" has been removed from the queue");
                                // Add the amount of fuel needed by the passenger back to the fuel stock because they weren't served
                                fuelStock = fuelStock + fuelQueueArray[indexOfQueue-1].getLitresNeeded(indexOfPas-1);
                                // Remove the passenger
                                fuelQueueArray[indexOfQueue-1].removePassenger(indexOfPas-1);

                                if (!waitingList.isEmpty()){ // If the waiting list is not empty add 0th index passenger to the queue
                                    fuelQueueArray[indexOfQueue-1].addPassenger(waitingList.get(0).getPassengerFirstName(),waitingList.get(0).getPassengerLastName(),waitingList.get(0).getVehicleNumber(),waitingList.get(0).getNoOfLiters());
                                    waitingList.remove(0);
                                    break;
                                }

                                break;
                            }else {
                                System.out.println("No passenger is in this position. Please try again with a valid index");
                                continue;
                            }
                        }else {
                            System.out.println("Please enter a number between 1 and 6");
                            continue;
                        }
                    }else {
                        System.out.println("Please enter a valid integer");
                        continue;
                    }
                }else {
                    System.out.println("Please enter a number between 1 and 5");
                    continue;
                }
            }else {
                System.out.println("Please enter a valid number");
                continue;
            }
        }
    }

    public static void removeServedPassenger(){ // Method to remove a served passenger

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Fuel Queue number of the passenger : ");
            if (sc.hasNextInt()){
                int indexOfQueue = sc.nextInt();
                if (indexOfQueue>0 && indexOfQueue<6){
                    if (fuelQueueArray[indexOfQueue-1].getPassengersSize()>0){ // Check if there is a passenger to be served
                        // Add the income of the passenger to the income of the fuel pump/queue
                        int incomeOfPas = fuelQueueArray[indexOfQueue-1].getLitresNeeded(0)*litrePrice;
                        fuelQueueArray[indexOfQueue-1].setIncome(incomeOfPas);
                        // Remove the passenger in the 0th index
                        System.out.println(fuelQueueArray[indexOfQueue-1].getFirstName(0)+" "+fuelQueueArray[indexOfQueue-1].getLastName(0)+" has been served");
                        fuelQueueArray[indexOfQueue-1].removePassenger(0);

                        if (!waitingList.isEmpty()){ // If the waiting list is not empty add 0th index passenger to the queue
                            fuelQueueArray[indexOfQueue-1].addPassenger(waitingList.get(0).getPassengerFirstName(),waitingList.get(0).getPassengerLastName(),waitingList.get(0).getVehicleNumber(),waitingList.get(0).getNoOfLiters());
                            waitingList.remove(0);
                            break;
                        }

                        break;
                    }else {
                        System.out.println("There are no passengers in this queue");
                        continue;
                    }
                }else {
                    System.out.println("Please enter a number between 1 and 5");
                    continue;
                }
            }else {
                System.out.println("Please enter a valid number");
                continue;
            }
        }
    }

    public static void sortPassengers(){ // Method to sort the passengers

        // Get the passengers in each list as an arraylist
        ArrayList<String> fuelPump01 = fuelQueueArray[0].getListOfPassengers();
        ArrayList<String> fuelPump02 = fuelQueueArray[1].getListOfPassengers();
        ArrayList<String> fuelPump03 = fuelQueueArray[2].getListOfPassengers();
        ArrayList<String> fuelPump04 = fuelQueueArray[3].getListOfPassengers();
        ArrayList<String> fuelPump05 = fuelQueueArray[4].getListOfPassengers();

        // Print the sorted queues
        System.out.print("Fuel Queue 01 : ");
        sortAndPrint(fuelPump01);
        System.out.println(" ");
        System.out.print("Fuel Queue 02 : ");
        sortAndPrint(fuelPump02);
        System.out.println(" ");
        System.out.print("Fuel Queue 03 : ");
        sortAndPrint(fuelPump03);
        System.out.println(" ");
        System.out.print("Fuel Queue 04 : ");
        sortAndPrint(fuelPump04);
        System.out.println(" ");
        System.out.print("Fuel Queue 05 : ");
        sortAndPrint(fuelPump05);
        System.out.println(" ");
    }

    public static void sortAndPrint(ArrayList<String> fuelPump) { // Method to sort an arraylist

        // Code reference - https://stackoverflow.com/questions/2839137/how-to-use-comparator-in-java-to-sort
        Collections.sort(fuelPump, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) { // Using a comparator to sort based on first and last names
                String[] split1 = o1.split(" ");
                String[] split2 = o2.split(" ");
                String firstName1 = split1[0];
                String lastName1 = split1[1];
                String firstName2 = split2[0];
                String lastName2 = split2[1];
                if (firstName1.compareToIgnoreCase(firstName2) > 0) {
                    return 1;
                } else if (firstName1.compareToIgnoreCase(firstName2) < 0){
                    return -1;
                }else {
                    if (lastName1.compareToIgnoreCase(lastName2) > 0){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            }
        });
        System.out.println(fuelPump);
    }

    public static void storeData() throws IOException { // Method to save data in a text file (CSV)

        Scanner sc = new Scanner(System.in);
        FileWriter myWriter = new FileWriter("data.txt"); // Create a FileWriter object
        System.out.println("Are you sure you want to overwrite the saved data if data is already stored?");
        System.out.print("Enter Y / N : ");
        if (sc.hasNextLine()){ // Input validation
            String ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y")){
                // This loop will run for 30 times and print empty spaces for easy accessibility when loading
                for (int x=0; x<5; x++){ // Loop through all 5 fuel queues
                    for (int y=0; y<fuelQueueArray[x].getPassengersSize(); y++){ // Write passenger details to the file in a specific format
                        myWriter.write(fuelQueueArray[x].getPassengerString(y));
                    }
                    if (fuelQueueArray[x].getPassengersSize()<fuelQueueArray[x].getQueueLength()){// Write empty spaces if no passenger
                        for (int z=0; z<fuelQueueArray[x].getQueueLength()-fuelQueueArray[x].getPassengersSize();z++){
                            myWriter.write("\n");
                        }
                    }
                }
                // Save the income of each fuel queue
                for (int z=0; z<5; z++){
                    myWriter.write(fuelQueueArray[z].getIncome()+"\n");
                }
                // Save the fuel stock
                myWriter.write(String.valueOf(fuelStock+"\n"));

                // Write all the passenger list data
                for (int i=0; i< waitingList.size(); i++){
                    myWriter.write(getWaitingList(i));
                }

                myWriter.close(); // Close the writer object
                System.out.println("The data has been successfully stored");
            }else {
                System.out.println("The saved data won't be overwritten");
            }
        }else{
            System.out.println("Please enter a valid input. Try again");
        }
    }

    public static void loadData() throws FileNotFoundException { // Method to load data from a CSV file

        // Code reference - https://www.youtube.com/watch?v=-Aud0cDh-J8
        File dataFile = new File("data.txt"); // Create a File object
        Scanner read = new Scanner(dataFile); // Create a Scanner object to read the file
        Scanner sc = new Scanner(System.in);

        System.out.println("Are you sure you want to load data and overwrite the current data?");
        System.out.print("Enter Y/N : ");
        if (sc.hasNextLine()){ // Input validation
            String ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y")){
                // Loop through all the queues and clear already existing data
                for (int x=0; x<5; x++){
                    fuelQueueArray[x].clearData();
                }

                String passengerDetails;
                for (int x=0; x<5; x++){ // Loop for 30 times to get all the passenger details in the CSV file
                    for (int y=0; y<6; y++){
                        passengerDetails = read.nextLine();
                        // Store each line into an array and separate the data using commas
                        String[] parts = passengerDetails.split(",");
                        // Check if the line is an empty space
                        if (passengerDetails.length()>2){
                            // Add passenger using the comma separated values in the array above
                            String pasFirstName = parts[0];
                            String pasLastName = parts[1];
                            String vehicleNo = parts[2];
                            int litresNeeded = Integer.parseInt(parts[3]);

                            fuelQueueArray[x].addPassenger(pasFirstName, pasLastName, vehicleNo, litresNeeded);
                            // Deduct the fuel queue as per the amount needed by the passenger
                            fuelStock = fuelStock - litresNeeded;
                        }
                    }
                }

                String income;
                for (int z=0; z<5; z++){ // Get the stored income data of each queue and set it to the queue
                    income = read.nextLine();
                    fuelQueueArray[z].setIncome(Integer.parseInt(income));
                }
                int newFuelStock = read.nextInt(); // Replace the fuel stock with the new stock
                fuelStock = newFuelStock;

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
                        waitingList.add(new Passenger(waitFirstName,waitLastName,waitVehicleNo,waitLitresNeeded));
                        // Deduct the fuel queue as per the amount needed by the passenger
                        fuelStock = fuelStock - waitLitresNeeded;
                    }
                }

                read.close();
                System.out.println("The data has been successfully loaded");

            } else {
                System.out.println("The data won't be overwritten");
            }


        }else {
            System.out.println("Please enter a valid input. Try again");
        }
    }

    public static void addFuelStock(){ // Method to add new fuel to the stock

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the amount of fuel you need to add in litres : ");
            if (sc.hasNextInt()){
                int newStock = sc.nextInt();
                fuelStock = fuelStock + newStock;
                System.out.println(newStock+" litres added to the stock");
                break;
            }else {
                System.out.println("Enter a valid amount");
                continue;
            }
        }
    }

    public static void income(){ // Method to get the income of each queue

        for (int x=0; x<5; x++){
            int income = fuelQueueArray[x].getIncome();
            System.out.println("Income of Fuel Queue "+(x+1)+" = Rs."+income);
        }

    }

    public static boolean checkAvailability() { // Method to check if at least one fuel queue has an empty space

        boolean available = false;
        for (int x=0; x<5; x++){
            if (fuelQueueArray[x].getPassengersSize()<6){
                available = true;
            }
        }
        return available;
    }

    public static int selectSmallestQueue() { // Method to select the smallest queue out of all the queues

        int noOfEmptySpaces = 0;
        int indexSmallestQueue = 0;
        for (int x = 0; x < 5; x++) {
            if (fuelQueueArray[x].getEmptySpaces() > noOfEmptySpaces) {
                noOfEmptySpaces = fuelQueueArray[x].getEmptySpaces();
                indexSmallestQueue = x;
            }
        }
        return indexSmallestQueue;
    }

    public static String getWaitingList(int x){ // Method to write the waiting list data to the data file
        String data = null;
        String firstName = waitingList.get(x).getPassengerFirstName();
        String lastName = waitingList.get(x).getPassengerLastName();
        String vehicleNo = waitingList.get(x).getVehicleNumber();
        String litresNeeded = String.valueOf((waitingList.get(x).getNoOfLiters()));
        data = firstName+","+lastName+","+vehicleNo+","+litresNeeded+"\n";
        return data;
    }

}
