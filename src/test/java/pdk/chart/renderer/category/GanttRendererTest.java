package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link GanttRenderer} class.
 */
public class GanttRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        GanttRenderer r1 = new GanttRenderer();
        GanttRenderer r2 = new GanttRenderer();
        assertEquals(r1, r2);

        r1.setCompletePaint(Color.YELLOW);
        assertNotEquals(r1, r2);
        r2.setCompletePaint(Color.YELLOW);
        assertEquals(r1, r2);

        r1.setIncompletePaint(Color.GREEN);
        assertNotEquals(r1, r2);
        r2.setIncompletePaint(Color.GREEN);
        assertEquals(r1, r2);

        r1.setStartPercent(0.11);
        assertNotEquals(r1, r2);
        r2.setStartPercent(0.11);
        assertEquals(r1, r2);

        r1.setEndPercent(0.88);
        assertNotEquals(r1, r2);
        r2.setEndPercent(0.88);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        GanttRenderer r1 = new GanttRenderer();
        GanttRenderer r2 = new GanttRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        GanttRenderer r1 = new GanttRenderer();
        r1.setCompletePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE, 4.0f, 3.0f, Color.CYAN));
        r1.setIncompletePaint(new GradientPaint(1.0f, 2.0f, Color.RED, 4.0f, 3.0f, Color.GREEN));
        GanttRenderer r2 = (GanttRenderer) r1.clone();
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        GanttRenderer r1 = new GanttRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        GanttRenderer r1 = new GanttRenderer();
        r1.setCompletePaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f,
                4.0f, Color.BLUE));
        r1.setIncompletePaint(new GradientPaint(4.0f, 3.0f, Color.RED, 2.0f,
                1.0f, Color.BLUE));
        GanttRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

}
