package pdk.chart.block;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.RectangleInsets;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link LineBorder} class.
 */
public class LineBorderTest {

    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        LineBorder b1 = new LineBorder(Color.RED, new BasicStroke(1.0f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        LineBorder b2 = new LineBorder(Color.RED, new BasicStroke(1.0f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        assertEquals(b1, b2);
        assertEquals(b2, b2);

        b1 = new LineBorder(Color.BLUE, new BasicStroke(1.0f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        assertNotEquals(b1, b2);
        b2 = new LineBorder(Color.BLUE, new BasicStroke(1.0f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        assertEquals(b1, b2);

        b1 = new LineBorder(Color.BLUE, new BasicStroke(1.1f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        assertNotEquals(b1, b2);
        b2 = new LineBorder(Color.BLUE, new BasicStroke(1.1f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        assertEquals(b1, b2);

        b1 = new LineBorder(Color.BLUE, new BasicStroke(1.1f),
                new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(b1, b2);
        b2 = new LineBorder(Color.BLUE, new BasicStroke(1.1f),
                new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertEquals(b1, b2);

    }

    /**
     * Immutable - cloning not necessary.
     */
    @Test
    public void testCloning() {
        LineBorder b1 = new LineBorder();
        assertFalse(b1 instanceof Cloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LineBorder b1 = new LineBorder(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(1.0f),
                new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        LineBorder b2 = TestUtils.serialised(b1);
        assertEquals(b1, b2);
    }

}
