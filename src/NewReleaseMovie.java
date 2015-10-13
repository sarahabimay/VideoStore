public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title);
    }

    public double rentalAmount(int daysRented){
        return daysRented * 3;
    }

    public int frequentRenterPoints(int daysRented){
        int frequentRenterPoints = 1;
        if (daysRented > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
