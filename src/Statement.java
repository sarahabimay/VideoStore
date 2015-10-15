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
        String statementText = "";
        double rentalAmount = generateAmount(rental);
        totalRentalsAmount += rentalAmount;
        frequentRenterPoints += generateFrequentRenterPoints(rental);

        statementText += "\t" + rental.getMovie().getTitle() + "\t"
                + String.valueOf(rentalAmount) + "\n";
        return statementText;
    }

    private int generateFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = 1;

        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private double generateAmount(Rental rental) {
        double rentalAmount = 0.0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount += 2;
                if (rental.getDaysRented() > 2)
                    rentalAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                rentalAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                rentalAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    rentalAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return rentalAmount;
    }


}
