package pdk.chart.axis;

import pdk.chart.TestUtils;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the {@link NumberTickUnit} class.
 */
public class NumberTickUnitTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        NumberTickUnit t1 = new NumberTickUnit(1.23, new DecimalFormat("0.00"));
        NumberTickUnit t2 = new NumberTickUnit(1.23, new DecimalFormat("0.00"));
        assertEquals(t1, t2);
        assertEquals(t2, t1);

        t1 = new NumberTickUnit(3.21, new DecimalFormat("0.00"));
        assertNotEquals(t1, t2);
        t2 = new NumberTickUnit(3.21, new DecimalFormat("0.00"));
        assertEquals(t1, t2);

        t1 = new NumberTickUnit(3.21, new DecimalFormat("0.000"));
        assertNotEquals(t1, t2);
        t2 = new NumberTickUnit(3.21, new DecimalFormat("0.000"));
        assertEquals(t1, t2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        NumberTickUnit t1 = new NumberTickUnit(1.23, new DecimalFormat("0.00"));
        NumberTickUnit t2 = new NumberTickUnit(1.23, new DecimalFormat("0.00"));
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * This is an immutable class so it doesn't need to be cloneable.
     */
    @Test
    public void testCloning() {
        NumberTickUnit t1 = new NumberTickUnit(1.23, new DecimalFormat("0.00"));
        assertFalse(t1 instanceof Cloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        NumberTickUnit t1 = new NumberTickUnit(1.23, new DecimalFormat("0.00"));
        NumberTickUnit t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
