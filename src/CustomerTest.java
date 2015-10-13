import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Statement statementForCustomer;
    private Movie newRelease1;
    private Movie childrens1;
    private Movie regular1;

    @Before
    public void setUp() {
        statementForCustomer = new Statement("Customer");
        newRelease1 = new NewReleaseMovie("New Release");
        childrens1 = new ChildrensMovie("Childrens");
        regular1 = new RegularMovie("Regular");
    }

    @Test
    public void children1DayRentalAmount() {
        assertEquals(1.5, childrens1.rentalAmount(1), 0.001);
    }

    @Test
    public void childrenOver3DayRentalAmount() {
        assertEquals(3.0, childrens1.rentalAmount(4), 0.001);
    }

    @Test
    public void childrenFrequentRenterPoints() {
        assertEquals(1, childrens1.frequentRenterPoints(2));
    }

    @Test
    public void newReleaseRentalAmount() {
        assertEquals(3.0, newRelease1.rentalAmount(1), 0.001);
    }

    @Test
    public void upTo1DayNewReleaseFrequentRenterPoints() {
        assertEquals(1, newRelease1.frequentRenterPoints(1));
    }

    @Test
    public void over2DaysNewReleaseFrequentRenterPoints() {
        assertEquals(2, newRelease1.frequentRenterPoints(2));
    }

    @Test
    public void upTo2DaysRegularRentalAmount() {
        assertEquals(2.0, regular1.rentalAmount(2), 0.001);
    }

    @Test
    public void over2DaysRegularRentalAmount() {
        assertEquals(3.5, regular1.rentalAmount(3), 0.001);
    }

    @Test
    public void regularFrequentRenterPoints() {
        assertEquals(1, regular1.frequentRenterPoints(2));
    }

    @Test
    public void standardAmountAndFrequentRenter() {
        statementForCustomer.addRental(new Rental(newRelease1, 1));
        statementForCustomer.addRental(new Rental(childrens1, 1));
        statementForCustomer.addRental(new Rental(regular1, 1));
        assertEquals("Rental Record for Customer\n\tNew Release\t3.0\n\tChildrens\t1.5\n\tRegular\t2.0\nYou owed 6.5\nYou earned 3 frequent renter points\n", statementForCustomer.statement());
    }

    @Test
    public void nonStandardAmountAndFrequentRenter() {
        statementForCustomer.addRental(new Rental(newRelease1, 3));
        statementForCustomer.addRental(new Rental(childrens1, 4));
        statementForCustomer.addRental(new Rental(regular1, 3));
        assertEquals("Rental Record for Customer\n\tNew Release\t9.0\n\tChildrens\t3.0\n\tRegular\t3.5\nYou owed 15.5\nYou earned 4 frequent renter points\n", statementForCustomer.statement());
    }
}