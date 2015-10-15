import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VideoStoreTest {

    private final double DELTA = 0.001;
    private Movie newRelease1;
    private Movie newRelease2;
    private Movie childrens;
    private Movie regular1;
    private Movie regular2;
    private Movie regular3;

    @Before
    public void setUp() {
        statement = new Statement("Customer");
        newRelease1 = new Movie("New Release 1", Movie.NEW_RELEASE);
        newRelease2 = new Movie("New Release 2", Movie.NEW_RELEASE);
        childrens = new Movie("Childrens", Movie.CHILDRENS);
        regular1 = new Movie("Regular 1", Movie.REGULAR);
        regular2 = new Movie("Regular 2", Movie.REGULAR);
        regular3 = new Movie("Regular 3", Movie.REGULAR);
    }

    @Test
    public void singleNewReleaseStatementData() {
        statement.addRental(new Rental(newRelease1, 3));
        statement.statement();
        assertEquals(9.0, statement.getTotalRentalsAmount(), DELTA);
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void dualNewReleaseStatementData() {
        statement.addRental(new Rental(newRelease1, 3));
        statement.addRental(new Rental(newRelease2, 3));
        statement.statement();
        assertEquals(18.0, statement.getTotalRentalsAmount(), DELTA);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void singleChildrensStatementData() {
        statement.addRental(new Rental(childrens, 3));
        statement.statement();
        assertEquals(1.5, statement.getTotalRentalsAmount(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void singleRegularStatementData() {
        statement.addRental(new Rental(regular1, 2));
        statement.statement();
        assertEquals(2.0, statement.getTotalRentalsAmount(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatement() {
        statement.addRental(new Rental(regular1, 1));
        statement.addRental(new Rental(regular2, 2));
        statement.addRental(new Rental(regular3, 3));

        assertEquals("Rental Record for Customer\n\t" +
                        "Regular 1\t2.0\n\t" +
                        "Regular 2\t2.0\n\tRegular 3\t3.5\n" +
                        "You owed 7.5\nYou earned 3 frequent renter points\n",
                statement.statement());
    }

    private Statement statement;
}
