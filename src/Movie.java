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

    public String getTitle() {
        return title;
    }

    public boolean isChildrens() {
        return priceCode == PriceCode.CHILDRENS;
    }

    public boolean isRegular() {
        return priceCode == PriceCode.REGULAR;
    }

    public boolean isNewRelease() {
        return priceCode == PriceCode.NEW_RELEASE;
    }
}
