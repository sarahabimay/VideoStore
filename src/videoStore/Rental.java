package videoStore;

public class Rental
{
	public Rental (Movie movie, int daysRented) {
		this.movie 		= movie;
		this.daysRented = daysRented;
	}

	Movie movie;
	private int daysRented;

	public String getTitle() {
		return movie.getTitle();
	}
    public double determineAmount(){
        return movie.determineAmount(daysRented);
    }

	public int determineFrequentRenterPoints() {
		return movie.determineFrequentRenterPoints(daysRented);
	}

}
