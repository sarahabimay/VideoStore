import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Enumeration;

public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<Rental>();

    public double getTotalRentalsAmount() {
        return totalRentalsAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    private double totalRentalsAmount = 0;
    private int frequentRenterPoints = 0;

    public Statement(String name) {
        this.customerName = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String statement() {
        String statementText = header();
        statementText += generate();
        statementText += footer();
        return statementText;
    }

    private String header() {
        return "Rental Record for " + getCustomerName() + "\n";
    }

    private String footer() {
        String result = "";
        result += "You owed " + String.valueOf(totalRentalsAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";
        return result;
    }

    private String generate() {
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
