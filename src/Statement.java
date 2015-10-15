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
        String result = "";
        for (Rental rental : rentals) {
            double thisAmount = 0;

            // determines the amount for each line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2)
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3)
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }

            frequentRenterPoints++;

            if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                    && rental.getDaysRented() > 1)
                frequentRenterPoints++;

            result += "\t" + rental.getMovie().getTitle() + "\t"
                    + String.valueOf(thisAmount) + "\n";
            totalRentalsAmount += thisAmount;

        }
        return result;
    }


}
