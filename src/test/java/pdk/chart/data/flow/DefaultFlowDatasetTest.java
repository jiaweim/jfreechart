package pdk.chart.data.flow;


import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DefaultFlowDataset} class.
 */
public class DefaultFlowDatasetTest {

    /**
     * Some checks for the getValue() method.
     */
    @Test
    public void testGetFlow() {
        DefaultFlowDataset<String> d = new DefaultFlowDataset<>();
        d.setFlow(0, "A", "Z", 1.5);
        assertEquals(1.5, d.getFlow(0, "A", "Z"));
    }

    /**
     * Some tests for the getStageCount() method.
     */
    @Test
    public void testGetStageCount() {
        DefaultFlowDataset<String> d = new DefaultFlowDataset<>();
        assertEquals(1, d.getStageCount());

        d.setFlow(0, "A", "Z", 11.1);
        assertEquals(1, d.getStageCount());

        // a row of all null values is still counted...
        d.setFlow(1, "Z", "P", 5.0);
        assertEquals(2, d.getStageCount());
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultFlowDataset<String> d1 = new DefaultFlowDataset<>();
        DefaultFlowDataset<String> d2 = new DefaultFlowDataset<>();
        assertEquals(d1, d2);

        d1.setFlow(0, "A", "Z", 1.0);
        assertNotEquals(d1, d2);
        d2.setFlow(0, "A", "Z", 1.0);
        assertEquals(d1, d2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultFlowDataset<String> d1 = new DefaultFlowDataset<>();
        d1.setFlow(0, "A", "Z", 1.0);
        DefaultFlowDataset<String> d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultFlowDataset<String> d1 = new DefaultFlowDataset<>();
        d1.setFlow(0, "A", "Z", 1.0);
        DefaultFlowDataset<String> d2 = (DefaultFlowDataset<String>) d1.clone();

        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);

        // check that the clone doesn't share the same underlying arrays.
        d1.setFlow(0, "A", "Y", 8.0);
        assertNotEquals(d1, d2);
        d2.setFlow(0, "A", "Y", 8.0);
        assertEquals(d1, d2);
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        DefaultFlowDataset<String> d = new DefaultFlowDataset<>();
        assertTrue(d instanceof PublicCloneable);
    }

}

