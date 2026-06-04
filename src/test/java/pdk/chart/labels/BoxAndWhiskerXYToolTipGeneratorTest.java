package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BoxAndWhiskerXYToolTipGenerator} class.
 */
public class BoxAndWhiskerXYToolTipGeneratorTest {

    /**
     * A series of tests for the equals() method.
     */
    @Test
    public void testEquals() {

        // standard test
        BoxAndWhiskerXYToolTipGenerator g1
                = new BoxAndWhiskerXYToolTipGenerator();
        BoxAndWhiskerXYToolTipGenerator g2
                = new BoxAndWhiskerXYToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        // tooltip format
        g1 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        g2 = new BoxAndWhiskerXYToolTipGenerator("{1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        assertNotEquals(g1, g2);
        g2 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        assertEquals(g1, g2);

        // date format
        g1 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        g2 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("MMM-yyyy"), new DecimalFormat("0.0"));
        assertNotEquals(g1, g2);
        g2 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        assertEquals(g1, g2);

        // Y format
        g1 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        g2 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.00"));
        assertNotEquals(g1, g2);
        g2 = new BoxAndWhiskerXYToolTipGenerator("{0} --> {1} {2}",
                new SimpleDateFormat("yyyy"), new DecimalFormat("0.0"));
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        BoxAndWhiskerXYToolTipGenerator g1
                = new BoxAndWhiskerXYToolTipGenerator();
        BoxAndWhiskerXYToolTipGenerator g2
                = new BoxAndWhiskerXYToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        BoxAndWhiskerXYToolTipGenerator g1
                = new BoxAndWhiskerXYToolTipGenerator();
        BoxAndWhiskerXYToolTipGenerator g2 = (BoxAndWhiskerXYToolTipGenerator)
                g1.clone();
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        BoxAndWhiskerXYToolTipGenerator g1
                = new BoxAndWhiskerXYToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        BoxAndWhiskerXYToolTipGenerator g1
                = new BoxAndWhiskerXYToolTipGenerator();
        BoxAndWhiskerXYToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
