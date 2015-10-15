package videoStore;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title);
    }

    public double determineAmount(int daysRented) {
        double rentalAmount = 2;
        if (daysRented > 2)
            rentalAmount += (daysRented - 2) * 1.5;
        return rentalAmount;
    }

    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
