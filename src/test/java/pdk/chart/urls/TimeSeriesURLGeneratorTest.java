package pdk.chart.urls;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.xy.DefaultXYDataset;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link TimeSeriesURLGenerator} class.
 */
public class TimeSeriesURLGeneratorTest {

    /**
     * A basic check for the generateURL() method.
     */
    @Test
    public void testGenerateURL() {
        TimeSeriesURLGenerator g = new TimeSeriesURLGenerator();
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        dataset.addSeries("Series '1'", new double[][]{{1.0, 2.0},
                {3.0, 4.0}});
        String s = g.generateURL(dataset, 0, 0);
        assertTrue(s.startsWith("index.html?series=Series+%271%27&amp;item="));
    }

    /**
     * Check that the equals() method can distinguish all fields.
     */
    @Test
    public void testEquals() {
        TimeSeriesURLGenerator g1 = new TimeSeriesURLGenerator();
        TimeSeriesURLGenerator g2 = new TimeSeriesURLGenerator();
        assertEquals(g1, g2);

        g1 = new TimeSeriesURLGenerator(new SimpleDateFormat("yyyy"), "prefix",
                "series", "item");
        assertNotEquals(g1, g2);
        g2 = new TimeSeriesURLGenerator(new SimpleDateFormat("yyyy"), "prefix",
                "series", "item");
        assertEquals(g1, g2);

        g1 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix",
                "series", "item");
        assertNotEquals(g1, g2);
        g2 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix",
                "series", "item");
        assertEquals(g1, g2);

        g1 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix1",
                "series", "item");
        assertNotEquals(g1, g2);
        g2 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix1",
                "series", "item");
        assertEquals(g1, g2);

        g1 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix1",
                "series1", "item");
        assertNotEquals(g1, g2);
        g2 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix1",
                "series1", "item");
        assertEquals(g1, g2);

        g1 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix1",
                "series1", "item1");
        assertNotEquals(g1, g2);
        g2 = new TimeSeriesURLGenerator(new SimpleDateFormat("yy"), "prefix1",
                "series1", "item1");
        assertEquals(g1, g2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TimeSeriesURLGenerator g1 = new TimeSeriesURLGenerator();
        TimeSeriesURLGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * Checks that the class does not implement PublicCloneable (the generator
     * is immutable).
     */
    @Test
    public void testPublicCloneable() {
        TimeSeriesURLGenerator g1 = new TimeSeriesURLGenerator();
        assertFalse(g1 instanceof PublicCloneable);
    }

}
