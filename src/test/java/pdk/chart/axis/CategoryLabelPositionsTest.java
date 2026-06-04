package pdk.chart.axis;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.text.TextBlockAnchor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests for the {@link CategoryLabelPositions} class.
 */
public class CategoryLabelPositionsTest {

    private static final RectangleAnchor RA_TOP = RectangleAnchor.TOP;
    private static final RectangleAnchor RA_BOTTOM = RectangleAnchor.BOTTOM;

    /**
     * Check that the equals method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        CategoryLabelPositions p1 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        CategoryLabelPositions p2 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertEquals(p1, p2);

        p1 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertFalse(p1.equals(p2));
        p2 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertEquals(p1, p2);

        p1 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM, TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertFalse(p1.equals(p2));
        p2 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertEquals(p1, p2);

        p1 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertFalse(p1.equals(p2));
        p2 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertEquals(p1, p2);

        p1 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER));
        assertFalse(p1.equals(p2));
        p2 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER),
                new CategoryLabelPosition(RA_BOTTOM,
                        TextBlockAnchor.TOP_CENTER));
        assertEquals(p1, p2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        CategoryLabelPositions p1 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        CategoryLabelPositions p2 = new CategoryLabelPositions(
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER),
                new CategoryLabelPosition(RA_TOP, TextBlockAnchor.CENTER));
        assertEquals(p1, p2);
        int h1 = p1.hashCode();
        int h2 = p2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CategoryLabelPositions p1 = CategoryLabelPositions.STANDARD;
        CategoryLabelPositions p2 = TestUtils.serialised(p1);
        assertEquals(p1, p2);
    }

}
