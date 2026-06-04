package pdk.chart.urls;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.DefaultCategoryDataset;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardCategoryURLGenerator} class.
 */
public class StandardCategoryURLGeneratorTest {

    /**
     * Some tests for the generateURL() method.
     */
    @Test
    public void testGenerateURL() {
        StandardCategoryURLGenerator g1 = new StandardCategoryURLGenerator();
        DefaultCategoryDataset<String, String> dataset
                = new DefaultCategoryDataset<>();
        dataset.addValue(1.0, "R1", "C1");
        dataset.addValue(2.0, "R2", "C2");
        dataset.addValue(3.0, "R&", "C&");
        assertEquals("index.html?series=R1&amp;category=C1",
                g1.generateURL(dataset, 0, 0));
        assertEquals("index.html?series=R1&amp;category=C2",
                g1.generateURL(dataset, 0, 1));
        assertEquals("index.html?series=R2&amp;category=C2",
                g1.generateURL(dataset, 1, 1));
        assertEquals("index.html?series=R%26&amp;category=C%26",
                g1.generateURL(dataset, 2, 2));
    }

    /**
     * Checks that the class does not implement PublicCloneable (the generator
     * is immutable, so cloning is not necessary).
     */
    @Test
    public void testPublicCloneable() {
        StandardCategoryURLGenerator g1 = new StandardCategoryURLGenerator();
        assertFalse(g1 instanceof PublicCloneable);
    }

    /**
     * Some tests for the equals() method.
     */
    @Test
    public void testEquals() {
        StandardCategoryURLGenerator g1 = new StandardCategoryURLGenerator();
        StandardCategoryURLGenerator g2 = new StandardCategoryURLGenerator();
        assertEquals(g1, g2);

        g1 = new StandardCategoryURLGenerator("index2.html?");
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryURLGenerator("index2.html?");
        assertEquals(g1, g2);

        g1 = new StandardCategoryURLGenerator("index2.html?", "A", "B");
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryURLGenerator("index2.html?", "A", "B");
        assertEquals(g1, g2);

        g1 = new StandardCategoryURLGenerator("index2.html?", "A", "C");
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryURLGenerator("index2.html?", "A", "C");
        assertEquals(g1, g2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardCategoryURLGenerator g1 = new StandardCategoryURLGenerator(
                "index.html?");
        StandardCategoryURLGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
