package pdk.chart.block;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.UnitType;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BlockBorder} class.
 */
public class BlockBorderTest {

    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        BlockBorder b1 = new BlockBorder(new RectangleInsets(1.0, 2.0, 3.0,
                4.0), Color.RED);
        BlockBorder b2 = new BlockBorder(new RectangleInsets(1.0, 2.0, 3.0,
                4.0), Color.RED);
        assertEquals(b1, b2);
        assertEquals(b2, b2);

        // insets
        b1 = new BlockBorder(new RectangleInsets(UnitType.RELATIVE, 1.0, 2.0,
                3.0, 4.0), Color.RED);
        assertNotEquals(b1, b2);
        b2 = new BlockBorder(new RectangleInsets(UnitType.RELATIVE, 1.0, 2.0,
                3.0, 4.0), Color.RED);
        assertEquals(b1, b2);

        // paint
        b1 = new BlockBorder(new RectangleInsets(1.0, 2.0, 3.0, 4.0),
                Color.BLUE);
        assertNotEquals(b1, b2);
        b2 = new BlockBorder(new RectangleInsets(1.0, 2.0, 3.0, 4.0),
                Color.BLUE);
        assertEquals(b1, b2);
    }

    /**
     * Immutable - cloning not necessary.
     */
    @Test
    public void testCloning() {
        BlockBorder b1 = new BlockBorder();
        assertFalse(b1 instanceof Cloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        BlockBorder b1 = new BlockBorder(new RectangleInsets(1.0, 2.0, 3.0,
                4.0), new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.YELLOW));
        BlockBorder b2 = TestUtils.serialised(b1);
        assertEquals(b1, b2);
    }

}
