public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public double generateAmount() {
        double rentalAmount = 0.0;
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount += 2;
                if (daysRented > 2)
                    rentalAmount += (daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                rentalAmount += daysRented * 3;
                break;
            case Movie.CHILDRENS:
                rentalAmount += 1.5;
                if (daysRented > 3)
                    rentalAmount += (daysRented - 3) * 1.5;
                break;
        }
        return rentalAmount;
    }

    public int generateFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        boolean bonusIsEarned = movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1;
        if (bonusIsEarned) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
