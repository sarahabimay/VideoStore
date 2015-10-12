public class Movie {

    public enum PriceCode {
        CHILDRENS, REGULAR, NEW_RELEASE
    }

    private String title;
    private PriceCode priceCode;

    public Movie(String title, PriceCode code) {
        this.title = title;
        this.priceCode = code;
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(PriceCode code) {
        priceCode = code;
    }

    public String getTitle() {
        return title;
    }
}
