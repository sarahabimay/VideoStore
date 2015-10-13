public class Rental {
    private Movie movie;
    private int daysRented;
    private double rentalAmount;
    private int frequentRenterPoints;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
        this.rentalAmount = movie.rentalAmount(daysRented);
        this.frequentRenterPoints = movie.frequentRenterPoints(daysRented);
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double getRentalAmount() {
        return rentalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }
}
