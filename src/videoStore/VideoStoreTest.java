package videoStore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private final double DELTA = 0.001;
    private Statement statement;
    private Movie release1;
    private Movie release2;
    private Movie release3;
    private Movie childrens1;
    private Movie regular1;
    private Movie regular2;
    private Movie regular3;

    @Before
    public void setUp() {
        statement = new Statement("Statement");
        release1 = new NewReleaseMovie("New Release1");
        release2 = new NewReleaseMovie("New Release2");
        release3 = new NewReleaseMovie("New Release3");
        childrens1 = new ChildrensMovie("Children1");
        regular1 = new RegularMovie("Regular1");
        regular2 = new RegularMovie("Regular2");
        regular3 = new RegularMovie("Regular3");
    }

    @Test
    public void singleNewReleaseMovie() {
        statement.addRental(new Rental(release1, 3));
        statement.generate();
        assertEquals(9.0, statement.getTotalAmount(), DELTA);
    }

    @Test
    public void dualNewReleaseMovie() {
        statement.addRental(new Rental(release2, 3));
        statement.addRental(new Rental(release3, 3));
        statement.generate();
        assertEquals(18.0, statement.getTotalAmount(), DELTA);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void singleChildrensMovie() {
        statement.addRental(new Rental(childrens1, 3));
        statement.generate();
        assertEquals(1.5, statement.getTotalAmount(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void tripleChildrenMovies() {
        statement.addRental(new Rental(regular1, 1));
        statement.addRental(new Rental(regular2, 2));
        statement.addRental(new Rental(regular3, 3));
        statement.generate();
        assertEquals(7.5, statement.getTotalAmount(), DELTA);
        assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void formatOfStatement() {
        statement.addRental(new Rental(childrens1, 2));
        assertEquals("videoStore.Rental Record for Statement\n\t" +
                        "Children1\t1.5\nYou owed 1.5\n" +
                        "You earned 1 frequent renter points\n",
                statement.generate());
    }

}
