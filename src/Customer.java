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
        double totalOfAllRentalAmounts = 0;
        int totalFrequentRenterPoints = 0;
        String result = "";
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            double thisRentalAmount = 0;
            Rental thisRental = (Rental) rentals.nextElement();

            totalFrequentRenterPoints += frequentRenterPointsFor(thisRental);
            thisRentalAmount = rentalAmountFor(thisRental);
            result += createStatementEntryForThisRental(thisRental.getMovie().getTitle(), thisRentalAmount);

            totalOfAllRentalAmounts += thisRentalAmount;
        }
        result += createTotalHistoryStatement(totalOfAllRentalAmounts, totalFrequentRenterPoints);
        return result;
    }

    private int frequentRenterPointsFor(Rental rental) {
        int frequentRenterPoints = 1;

        if (rental.getMovie().getPriceCode() == Movie.PriceCode.NEW_RELEASE && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private double rentalAmountFor(Rental rental) {
        Movie.PriceCode priceCode = rental.getMovie().getPriceCode();
        int daysRented = rental.getDaysRented();
        double thisAmount = 0;

        thisAmount += totalAmountOnRegularPriceCodes(priceCode, daysRented);
        thisAmount += totalAmountOnNewReleasePriceCodes(priceCode, daysRented);
        thisAmount += totalAmountOnChildrensPriceCodes(priceCode, daysRented);
        return thisAmount;
    }

    private double totalAmountOnChildrensPriceCodes(Movie.PriceCode priceCode, int daysRented) {
        double thisAmount = 0;
        if (priceCode == Movie.PriceCode.CHILDRENS) {
            thisAmount += 1.5;
            if (daysRented > 3) {
                thisAmount += (daysRented - 3) * 1.5;
            }
        }
        return thisAmount;
    }

    private double totalAmountOnNewReleasePriceCodes(Movie.PriceCode priceCode, int daysRented) {
        double thisAmount = 0;
        if (priceCode == Movie.PriceCode.NEW_RELEASE) {
            thisAmount += daysRented * 3;
        }
        return thisAmount;
    }

    private double totalAmountOnRegularPriceCodes(Movie.PriceCode priceCode, int daysRented) {
        double thisAmount = 0;
        if (priceCode == Movie.PriceCode.REGULAR) {
            thisAmount += 2;
            if (daysRented > 2) {
                thisAmount += (daysRented - 2) * 1.5;
            }
        }
        return thisAmount;
    }

    private String createStatementEntryForThisRental(String movieTitle, double thisAmount) {
        return "\t" + movieTitle + "\t" + String.valueOf(thisAmount) + "\n";
    }

    private String createTotalHistoryStatement(double totalAmount, int frequentRenterPoints) {
        String result = "";
        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";
        return result;
    }
}
