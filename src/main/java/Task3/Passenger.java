package Task3;

public class Passenger { // Create an object for passengers

    // Initialize attributes

    private String passengerFirstName;
    private String passengerLastName;
    private String vehicleNumber;
    private int noOfLiters;

    // Create a constructor

    public Passenger(String passengerFirstName, String passengerLastName, String vehicleNumber, int noOfLiters) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.vehicleNumber = vehicleNumber;
        this.noOfLiters = noOfLiters;
    }

    //Create getters and setters to access the attributes

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getNoOfLiters() {
        return noOfLiters;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setNoOfLiters(int noOfLiters) {
        this.noOfLiters = noOfLiters;
    }

}



