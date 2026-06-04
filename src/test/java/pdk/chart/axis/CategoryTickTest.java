package pdk.chart.axis;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.text.TextBlock;
import pdk.chart.text.TextBlockAnchor;
import pdk.chart.text.TextLine;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CategoryTick} class.
 */
public class CategoryTickTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        Comparable<String> c1 = "C1";
        Comparable<String> c2 = "C2";
        TextBlock tb1 = new TextBlock();
        tb1.addLine(new TextLine("Block 1"));
        TextBlock tb2 = new TextBlock();
        tb1.addLine(new TextLine("Block 2"));
        TextBlockAnchor tba1 = TextBlockAnchor.CENTER;
        TextBlockAnchor tba2 = TextBlockAnchor.BOTTOM_CENTER;
        TextAnchor ta1 = TextAnchor.CENTER;
        TextAnchor ta2 = TextAnchor.BASELINE_LEFT;

        CategoryTick t1 = new CategoryTick(c1, tb1, tba1, ta1, 1.0f);
        CategoryTick t2 = new CategoryTick(c1, tb1, tba1, ta1, 1.0f);
        assertEquals(t1, t2);

        t1 = new CategoryTick(c2, tb1, tba1, ta1, 1.0f);
        assertNotEquals(t1, t2);
        t2 = new CategoryTick(c2, tb1, tba1, ta1, 1.0f);
        assertEquals(t1, t2);

        t1 = new CategoryTick(c2, tb2, tba1, ta1, 1.0f);
        assertNotEquals(t1, t2);
        t2 = new CategoryTick(c2, tb2, tba1, ta1, 1.0f);
        assertEquals(t1, t2);

        t1 = new CategoryTick(c2, tb2, tba2, ta1, 1.0f);
        assertNotEquals(t1, t2);
        t2 = new CategoryTick(c2, tb2, tba2, ta1, 1.0f);
        assertEquals(t1, t2);

        t1 = new CategoryTick(c2, tb2, tba2, ta2, 1.0f);
        assertNotEquals(t1, t2);
        t2 = new CategoryTick(c2, tb2, tba2, ta2, 1.0f);
        assertEquals(t1, t2);

        t1 = new CategoryTick(c2, tb2, tba2, ta2, 2.0f);
        assertNotEquals(t1, t2);
        t2 = new CategoryTick(c2, tb2, tba2, ta2, 2.0f);
        assertEquals(t1, t2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        Comparable<String> c1 = "C1";
        TextBlock tb1 = new TextBlock();
        tb1.addLine(new TextLine("Block 1"));
        tb1.addLine(new TextLine("Block 2"));
        TextBlockAnchor tba1 = TextBlockAnchor.CENTER;
        TextAnchor ta1 = TextAnchor.CENTER;

        CategoryTick t1 = new CategoryTick(c1, tb1, tba1, ta1, 1.0f);
        CategoryTick t2 = new CategoryTick(c1, tb1, tba1, ta1, 1.0f);
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CategoryTick t1 = new CategoryTick("C1", new TextBlock(),
                TextBlockAnchor.CENTER, TextAnchor.CENTER, 1.5f);
        CategoryTick t2 = (CategoryTick) t1.clone();
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CategoryTick t1 = new CategoryTick("C1", new TextBlock(),
                TextBlockAnchor.CENTER, TextAnchor.CENTER, 1.5f);
        CategoryTick t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
