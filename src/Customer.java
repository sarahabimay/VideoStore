import java.util.Enumeration;
import java.util.Vector;

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
            result += createRentalEntryForStatement(thisRental);
        }
        return result;
    }

    private String createRentalEntryForStatement(Rental rental) {
        String movieTitle = rental.getMovieTitle();
        return "\t" + movieTitle + "\t" + String.valueOf(rental.getRentalAmount()) + "\n";
    }

    private String createTotalHistoryStatement() {
        String result = "";
        result += "You owed " + String.valueOf(allRentalAmounts()) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints()) + " frequent renter points\n";
        return result;
    }

    private int frequentRenterPoints() {
        int totalFrequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental thisRental = (Rental) rentals.nextElement();
            totalFrequentRenterPoints += thisRental.getFrequentRenterPoints();
        }
        return totalFrequentRenterPoints;
    }

    private double allRentalAmounts() {
        double totalOfAllRentalAmounts = 0;
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental thisRental = (Rental) rentals.nextElement();
            totalOfAllRentalAmounts += thisRental.getRentalAmount();
        }
        return totalOfAllRentalAmounts;
    }
}
