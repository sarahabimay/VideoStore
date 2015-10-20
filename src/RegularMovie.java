public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title);
    }

    public double generateAmount(int daysRented) {
        double rentalAmount = 2.0;
        if (daysRented > 2)
            rentalAmount += (daysRented - 2) * 1.5;
        return rentalAmount;
    }

    public int generateFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
