package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link GradientXYBarPainter} class.
 */
public class GradientXYBarPainterTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        GradientXYBarPainter p1 = new GradientXYBarPainter(0.1, 0.2, 0.3);
        GradientXYBarPainter p2 = new GradientXYBarPainter(0.1, 0.2, 0.3);
        assertEquals(p1, p2);

        p1 = new GradientXYBarPainter(0.11, 0.2, 0.3);
        assertNotEquals(p1, p2);
        p2 = new GradientXYBarPainter(0.11, 0.2, 0.3);
        assertEquals(p1, p2);

        p1 = new GradientXYBarPainter(0.11, 0.22, 0.3);
        assertNotEquals(p1, p2);
        p2 = new GradientXYBarPainter(0.11, 0.22, 0.3);
        assertEquals(p1, p2);

        p1 = new GradientXYBarPainter(0.11, 0.22, 0.33);
        assertNotEquals(p1, p2);
        p2 = new GradientXYBarPainter(0.11, 0.22, 0.33);
        assertEquals(p1, p2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        GradientXYBarPainter p1 = new GradientXYBarPainter(0.1, 0.2, 0.3);
        GradientXYBarPainter p2 = new GradientXYBarPainter(0.1, 0.2, 0.3);
        assertEquals(p1, p2);
        int h1 = p1.hashCode();
        int h2 = p2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning isn't implemented (it isn't required, because
     * instances of the class are immutable).
     */
    @Test
    public void testCloning() {
        GradientXYBarPainter p1 = new GradientXYBarPainter(0.1, 0.2, 0.3);
        assertFalse(p1 instanceof Cloneable);
        assertFalse(p1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        GradientXYBarPainter p1 = new GradientXYBarPainter(0.1, 0.2, 0.3);
        GradientXYBarPainter p2 = TestUtils.serialised(p1);
        assertEquals(p1, p2);
    }

}
