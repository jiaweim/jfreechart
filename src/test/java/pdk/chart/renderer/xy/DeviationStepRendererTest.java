package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DeviationStepRenderer} class.
 */
public class DeviationStepRendererTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        // default instances
        DeviationStepRenderer r1 = new DeviationStepRenderer();
        DeviationStepRenderer r2 = new DeviationStepRenderer();
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1.setAlpha(0.1f);
        assertNotEquals(r1, r2);
        r2.setAlpha(0.1f);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        DeviationStepRenderer r1 = new DeviationStepRenderer();
        DeviationStepRenderer r2 = new DeviationStepRenderer();
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
        DeviationStepRenderer r1 = new DeviationStepRenderer();
        DeviationStepRenderer r2 = (DeviationStepRenderer) r1.clone();
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        DeviationStepRenderer r1 = new DeviationStepRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DeviationStepRenderer r1 = new DeviationStepRenderer();
        DeviationStepRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

}
