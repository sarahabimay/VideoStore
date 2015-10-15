import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VideoStoreTest {

    private Movie newRelease1;
    private Movie newRelease2;
    private Movie childrens;
    private Movie regular1;
    private Movie regular2;
    private Movie regular3;

    @Before
    public void setUp() {
        customer = new Customer("Customer");
        newRelease1 = new Movie("New Release 1", Movie.NEW_RELEASE);
        newRelease2 = new Movie("New Release 2", Movie.NEW_RELEASE);
        childrens = new Movie("Childrens", Movie.CHILDRENS);
        regular1 = new Movie("Regular 1", Movie.REGULAR);
        regular2 = new Movie("Regular 2", Movie.REGULAR);
        regular3 = new Movie("Regular 3", Movie.REGULAR);
    }

    @Test
    public void singleNewReleaseStatementData() {
        customer.addRental(new Rental(newRelease1, 3));
        customer.statement();
        assertEquals(9.0, customer.getTotalRentalsAmount(), 0.001);
        assertEquals(2, customer.getFrequentRenterPoints());
    }

    @Test
    public void dualNewReleaseStatementData() {
        customer.addRental(new Rental(newRelease1, 3));
        customer.addRental(new Rental(newRelease2, 3));
        customer.statement();
        assertEquals(18.0, customer.getTotalRentalsAmount(), 0.001);
        assertEquals(4, customer.getFrequentRenterPoints());
    }

    @Test
    public void singleChildrensStatementData() {
        customer.addRental(new Rental(childrens, 3));
        customer.statement();
        assertEquals(1.5, customer.getTotalRentalsAmount(), 0.001);
        assertEquals(1, customer.getFrequentRenterPoints());
    }

    @Test
    public void singleRegularStatementData() {
        customer.addRental(new Rental(regular1, 2));
        customer.statement();
        assertEquals(2.0, customer.getTotalRentalsAmount(), 0.001);
        assertEquals(1, customer.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(regular1, 1));
        customer.addRental(new Rental(regular2, 2));
        customer.addRental(new Rental(regular3, 3));

        assertEquals("Rental Record for Customer\n\t" +
                        "Regular 1\t2.0\n\t" +
                        "Regular 2\t2.0\n\tRegular 3\t3.5\n" +
                        "You owed 7.5\nYou earned 3 frequent renter points\n",
                customer.statement());
    }

    private Customer customer;
}
