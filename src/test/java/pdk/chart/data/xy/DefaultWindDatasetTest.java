package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link DefaultWindDataset}.
 */
public class DefaultWindDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultWindDataset d1 = new DefaultWindDataset();
        DefaultWindDataset d2 = new DefaultWindDataset();
        assertEquals(d1, d2);
        assertEquals(d2, d1);

        d1 = createSampleDataset1();
        assertNotEquals(d1, d2);
        d2 = createSampleDataset1();
        assertEquals(d1, d2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultWindDataset d1 = new DefaultWindDataset();
        DefaultWindDataset d2 = (DefaultWindDataset) d1.clone();
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);

        // try a dataset with some content...
        d1 = createSampleDataset1();
        d2 = (DefaultWindDataset) d1.clone();
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        DefaultWindDataset d1 = new DefaultWindDataset();
        assertTrue(d1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultWindDataset d1 = new DefaultWindDataset();
        DefaultWindDataset d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);

        // try a dataset with some content...
        d1 = createSampleDataset1();
        d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

    /**
     * Some checks for the getSeriesKey(int) method.
     */
    @Test
    public void testGetSeriesKey() {
        DefaultWindDataset d = createSampleDataset1();
        assertEquals("Series 1", d.getSeriesKey(0));
        assertEquals("Series 2", d.getSeriesKey(1));

        // check for series key out of bounds
        boolean pass = false;
        try {
            /*Comparable k =*/
            d.getSeriesKey(-1);
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            /*Comparable k =*/
            d.getSeriesKey(2);
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Some checks for the indexOf(Comparable) method.
     */
    @Test
    public void testIndexOf() {
        DefaultWindDataset d = createSampleDataset1();
        assertEquals(0, d.indexOf("Series 1"));
        assertEquals(1, d.indexOf("Series 2"));
        assertEquals(-1, d.indexOf("Green Eggs and Ham"));
        assertEquals(-1, d.indexOf(null));
    }

    /**
     * Creates a sample dataset for testing.
     *
     * @return A sample dataset.
     */
    public DefaultWindDataset createSampleDataset1() {
        Day t = new Day(1, 4, 2006);
        Object[] item1 = createItem(t, 3, 7);
        Object[] item2 = createItem(t.next(), 4, 8);
        Object[] item3 = createItem(t.next(), 5, 9);
        Object[][] series1 = new Object[][]{item1, item2, item3};
        Object[] item1b = createItem(t, 6, 10);
        Object[] item2b = createItem(t.next(), 7, 11);
        Object[] item3b = createItem(t.next(), 8, 12);
        Object[][] series2 = new Object[][]{item1b, item2b, item3b};
        Object[][][] data = new Object[][][]{series1, series2};
        return new DefaultWindDataset(data);
    }

    /**
     * Creates an array representing one item in a series.
     *
     * @param t     the time period.
     * @param dir   the wind direction.
     * @param force the wind force.
     * @return An array containing the specified items.
     */
    private Object[] createItem(RegularTimePeriod t, int dir, int force) {
        return new Object[]{t.getMiddleMillisecond(), dir, force};
    }
}
