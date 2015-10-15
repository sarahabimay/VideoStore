package videoStore;

public class ChildrensMovie extends Movie {
    public ChildrensMovie(String title) {
        super(title);
    }

    public double determineAmount(int daysRented) {
        double rentalAmount = 1.5;
        if (daysRented > 3)
            rentalAmount += (daysRented - 3) * 1.5;
        return rentalAmount;
    }

    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
