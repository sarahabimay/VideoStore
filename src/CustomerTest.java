import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;
    private Movie newRelease1;
    private Movie newRelease2;
    private Movie childrens1;
    private Movie childrens2;
    private Movie childrens3;
    private Movie regular1;
    private Movie regular2;
    private Movie regular3;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Fred");
        newRelease1 = new NewReleaseMovie("The Cell");
        newRelease2 = new NewReleaseMovie("The Tigger Movie");
        childrens1 = new ChildrensMovie("The Tigger Movie");
        childrens2 = new ChildrensMovie("Cinderella");
        childrens3 = new ChildrensMovie("Bambi");
        regular1 = new RegularMovie("Plan 9 from Outer Space");
        regular2 = new RegularMovie("8 1/2");
        regular3 = new RegularMovie("Eraserhead");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(newRelease1, 3));
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customer.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(newRelease1, 3));
        customer.addRental(new Rental(newRelease2, 3));
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customer.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(childrens1, 3));
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customer.statement());
    }

    @Test
    public void testDualChildrensStatement() {
        customer.addRental(new Rental(childrens1,3));
        customer.addRental(new Rental(childrens2, 5));
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\n\tCinderella\t4.5\nYou owed 6.0\nYou earned 2 frequent renter points\n", customer.statement());
    }

    @Test
    public void testMultipleChildrensStatement() {
        customer.addRental(new Rental(childrens1, 3));
        customer.addRental(new Rental(childrens2, 5));
        customer.addRental(new Rental(childrens3, 1));
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\n\tCinderella\t4.5\n\tBambi\t1.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement());

    }

    @Test
    public void childrensWithMoreThanThreeDaysRentalStatement() {
        customer.addRental(new Rental(childrens1, 4));
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t3.0\nYou owed 3.0\nYou earned 1 frequent renter points\n", customer.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(new RegularMovie("Plan 9 from Outer Space"), 1));
        customer.addRental(new Rental(new RegularMovie("8 1/2"), 2));
        customer.addRental(new Rental(new RegularMovie("Eraserhead"), 3));

        assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement());
    }
}