package videoStore;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title);
    }

    public double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    public int determineFrequentRenterPoints(int daysRented) {
        boolean bonusIsEarned = daysRented > 1;
        if (bonusIsEarned) {
            return 2;
        }
        return 1;
    }
}
