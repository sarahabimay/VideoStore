import java.util.ArrayList;
import java.util.List;

public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<Rental>();
    private double totalRentalsAmount;
    private int frequentRenterPoints;

    public double getTotalRentalsAmount() {
        return totalRentalsAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }


    public Statement(String name) {
        this.customerName = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String generate() {
        clearTotals();
        String statementText = header();
        statementText += rentalLines();
        statementText += footer();
        return statementText;
    }

    private void clearTotals() {
        totalRentalsAmount = 0;
        frequentRenterPoints = 0;
    }

    private String header() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private String footer() {
        return String.format("You owed %.1f\nYou earned %d frequent renter points\n", totalRentalsAmount, frequentRenterPoints);
    }

    private String rentalLines() {
        String statementText = "";
        for (Rental rental : rentals) {
            statementText += rentalLine(rental);
        }
        return statementText;
    }

    private String rentalLine(Rental rental) {
        double rentalAmount = rental.generateAmount();
        totalRentalsAmount += rentalAmount;
        frequentRenterPoints += rental.generateFrequentRenterPoints();

        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(Rental rental, double rentalAmount) {
        return String.format("\t%s\t%.1f\n", rental.getTitle(), rentalAmount);
    }


}
