import java.util.Vector;
import java.util.Enumeration;

public class Customer {
    private String name;
    private Vector rentals = new Vector();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        result += rentalHistoryStatement();
        return result;
    }

    private String rentalHistoryStatement() {
        String result = "";
        result += createEachRentalEntryForStatement();
        result += createTotalHistoryStatement();
        return result;
    }

    private String createEachRentalEntryForStatement() {
        String result = "";
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental thisRental = (Rental) rentals.nextElement();
            result += thisRental.createStatement();
        }
        return result;
    }

    private String createTotalHistoryStatement() {
        String result = "";
        int totalFrequentRenterPoints = calculateFrequentRenterPoints();
        double totalOfAllRentalAmounts = calculateAllRentalAmounts();
        result += "You owed " + String.valueOf(totalOfAllRentalAmounts) + "\n";
        result += "You earned " + String.valueOf(totalFrequentRenterPoints) + " frequent renter points\n";
        return result;
    }

    private double calculateAllRentalAmounts() {
        double totalOfAllRentalAmounts = 0;
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental thisRental = (Rental) rentals.nextElement();
            totalOfAllRentalAmounts += thisRental.getRentalAmount();
        }
        return totalOfAllRentalAmounts;
    }

    private int calculateFrequentRenterPoints() {
        int totalFrequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental thisRental = (Rental) rentals.nextElement();
            totalFrequentRenterPoints += thisRental.getFrequentRenterPoints();
        }
        return totalFrequentRenterPoints;
    }

}
