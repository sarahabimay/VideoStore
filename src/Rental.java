public class Rental {
    private Movie movie;
    private int daysRented;
    private double rentalAmount;
    private int frequentRenterPoints;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
        this.rentalAmount = rentalAmount();
        this.frequentRenterPoints = frequentRenterPoints();
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

    private int frequentRenterPoints() {
        int frequentRenterPoints = 1;

        if (this.movie.isNewRelease() && getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private double rentalAmount() {
        double thisAmount = 0;

        thisAmount += rentalAmountForRegularMovies();
        thisAmount += rentalAmountForNewReleaseMovies();
        thisAmount += rentalAmountForChildrensMovies();
        return thisAmount;
    }

    private double rentalAmountForChildrensMovies() {
        double thisAmount = 0;
        if (movie.isChildrens()) {
            thisAmount += 1.5;
            if (daysRented > 3) {
                thisAmount += (daysRented - 3) * 1.5;
            }
        }
        return thisAmount;
    }

    private double rentalAmountForNewReleaseMovies() {
        double thisAmount = 0;
        if (movie.isNewRelease()) {
            thisAmount += daysRented * 3;
        }
        return thisAmount;
    }

    private double rentalAmountForRegularMovies() {
        double thisAmount = 0;
        if (movie.isRegular()) {
            thisAmount += 2;
            if (daysRented > 2) {
                thisAmount += (daysRented - 2) * 1.5;
            }
        }
        return thisAmount;
    }
}
