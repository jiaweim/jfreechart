package pdk.chart.title;

import org.junit.jupiter.api.Test;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.VerticalAlignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for the abstract {@link Title} class.
 */
public class TitleTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        // use the TextTitle class because it is a concrete subclass
        Title t1 = new TextTitle();
        Title t2 = new TextTitle();
        assertEquals(t1, t2);

        t1.setPosition(RectangleEdge.LEFT);
        assertNotEquals(t1, t2);
        t2.setPosition(RectangleEdge.LEFT);
        assertEquals(t1, t2);

        t1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        assertNotEquals(t1, t2);
        t2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        assertEquals(t1, t2);

        t1.setVerticalAlignment(VerticalAlignment.BOTTOM);
        assertNotEquals(t1, t2);
        t2.setVerticalAlignment(VerticalAlignment.BOTTOM);
        assertEquals(t1, t2);

        t1.setVisible(false);
        assertNotEquals(t1, t2);
        t2.setVisible(false);
        assertEquals(t1, t2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

}
