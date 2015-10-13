public class Movie {

    public enum PriceCode {
        CHILDRENS, REGULAR, NEW_RELEASE
    }

    private String title;
    private PriceCode priceCode;

    public Movie(String title) {
        this.title = title;
//        this.priceCode = code;
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public double rentalAmount(int daysRented) {
        return 0;
    }

    public int frequentRenterPoints(int daysRented){
        return 0;
    }
}
