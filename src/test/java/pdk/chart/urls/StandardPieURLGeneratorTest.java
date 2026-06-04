package pdk.chart.urls;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.general.DefaultPieDataset;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardPieURLGenerator} class.
 */
public class StandardPieURLGeneratorTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        StandardPieURLGenerator g1 = new StandardPieURLGenerator();
        StandardPieURLGenerator g2 = new StandardPieURLGenerator();
        assertEquals(g1, g2);

        g1 = new StandardPieURLGenerator("prefix", "category", "index");
        assertNotEquals(g1, g2);
        g2 = new StandardPieURLGenerator("prefix", "category", "index");
        assertEquals(g1, g2);

        g1 = new StandardPieURLGenerator("prefix2", "category", "index");
        assertNotEquals(g1, g2);
        g2 = new StandardPieURLGenerator("prefix2", "category", "index");
        assertEquals(g1, g2);

        g1 = new StandardPieURLGenerator("prefix2", "category2", "index");
        assertNotEquals(g1, g2);
        g2 = new StandardPieURLGenerator("prefix2", "category2", "index");
        assertEquals(g1, g2);

        g1 = new StandardPieURLGenerator("prefix2", "category2", "index2");
        assertNotEquals(g1, g2);
        g2 = new StandardPieURLGenerator("prefix2", "category2", "index2");
        assertEquals(g1, g2);

        g1 = new StandardPieURLGenerator("prefix2", "category2", null);
        assertNotEquals(g1, g2);
        g2 = new StandardPieURLGenerator("prefix2", "category2", null);
        assertEquals(g1, g2);
    }

    /**
     * Checks that the class does not implement PublicCloneable (the generator
     * is immutable).
     */
    @Test
    public void testPublicCloneable() {
        StandardPieURLGenerator g1 = new StandardPieURLGenerator(
                "index.html?", "cat");
        assertFalse(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardPieURLGenerator g1 = new StandardPieURLGenerator(
                "index.html?", "cat");
        StandardPieURLGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * Test that the generated URL is as expected.
     */
    @Test
    public void testURL() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("Alpha '1'", 5.0);
        dataset.setValue("Beta", 5.5);
        StandardPieURLGenerator g1 = new StandardPieURLGenerator(
                "chart.jsp", "category");
        String url = g1.generateURL(dataset, "Beta", 0);
        assertEquals("chart.jsp?category=Beta&amp;pieIndex=0", url);
        url = g1.generateURL(dataset, "Alpha '1'", 0);
        assertEquals("chart.jsp?category=Alpha+%271%27&amp;pieIndex=0", url);
    }

}
