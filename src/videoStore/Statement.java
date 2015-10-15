package videoStore;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<Rental>();
    private double totalAmount = 0;
    private int frequentRenterPoints = 0;

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public String generate() {
        clearTotals();
        String statementText = header();
        statementText += rentalLines();
        statementText += footer();
        return statementText;
    }

    private void clearTotals() {
        totalAmount = 0.0;
        frequentRenterPoints = 0;
    }
    private String header() {
        return String.format("videoStore.Rental Record for %s\n", customerName);
    }

    private String footer() {
        return String.format("You owed %.1f\nYou earned %d frequent renter points\n",
                totalAmount,
                frequentRenterPoints);
    }

    private String rentalLines() {
        String rentalLines = "";
        for (Rental rental : rentals) {
            rentalLines += rentalLine(rental);
        }
        return rentalLines;
    }

    private String rentalLine(Rental rental) {
        double rentalAmount = rental.determineAmount();
        frequentRenterPoints += rental.determineFrequentRenterPoints();
        totalAmount += rentalAmount;

        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(Rental rental, double rentalAmount) {
        return String.format("\t%s\t%.1f\n", rental.getTitle(), rentalAmount);
    }

}
