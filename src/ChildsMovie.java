public class ChildsMovie extends Movie {
    public ChildsMovie(String title) {
        super(title);
    }

    public double generateAmount(int daysRented) {
        double rentalAmount = 1.5;
        if (daysRented > 3)
            rentalAmount += (daysRented - 3) * 1.5;
        return rentalAmount;
    }

    public int generateFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
