public class ChildsMovie extends Movie {
    public ChildsMovie(String title, int type) {
        super(title, type);
    }

    public double generateAmount(int daysRented) {
        double rentalAmount = 0.0;
        switch (getPriceCode()) {
            case REGULAR:
                rentalAmount += 2;
                if (daysRented > 2)
                    rentalAmount += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                rentalAmount += daysRented * 3;
                break;
            case CHILDRENS:
                rentalAmount += 1.5;
                if (daysRented > 3)
                    rentalAmount += (daysRented - 3) * 1.5;
                break;
        }
        return rentalAmount;
    }

    public int generateFrequentRenterPoints(int daysRented) {
        int frequentRenterPoints = 1;
        boolean bonusIsEarned = getPriceCode() == NEW_RELEASE && daysRented > 1;
        if (bonusIsEarned) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
