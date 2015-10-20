public abstract class Movie {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    abstract double generateAmount(int daysRented);

    abstract int generateFrequentRenterPoints(int daysRented);
}
