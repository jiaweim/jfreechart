package pdk.chart.axis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.RectangleEdge;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests for the {@link LogarithmicAxis} class.
 */
public class LogarithmicAxisTest {

    static class MyLogarithmicAxis extends LogarithmicAxis {

        /**
         * Creates a new instance.
         *
         * @param label the label.
         */
        public MyLogarithmicAxis(String label) {
            super(label);
        }

        /* (non-Javadoc)
         * @see pdk.chart.axis.LogarithmicAxis#switchedLog10(double)
         */
        @Override
        protected double switchedLog10(double val) {
            return super.switchedLog10(val);
        }

    }

    /**
     * Tolerance for floating point comparisons
     */
    public static double EPSILON = 0.000001;

    MyLogarithmicAxis axis = null;

    /**
     * Sets up a new axis.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        this.axis = new MyLogarithmicAxis("Value (log)");
        this.axis.setAllowNegativesFlag(false);
        this.axis.setLog10TickLabelsFlag(false);
        this.axis.setLowerMargin(0.0);
        this.axis.setUpperMargin(0.0);

        this.axis.setLowerBound(0.2);
        this.axis.setUpperBound(100.0);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LogarithmicAxis a1 = new LogarithmicAxis("Test Axis");
        LogarithmicAxis a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);
    }

    /**
     * Test if adjustedLog10 and adjustedPow10 are inverses of each other
     */
    @Test
    public void testAdjustedLog10() {
        checkLogPowRoundTrip(20);
        checkLogPowRoundTrip(10);
        checkLogPowRoundTrip(5);
        checkLogPowRoundTrip(2);
        checkLogPowRoundTrip(1);
        checkLogPowRoundTrip(0.5);
        checkLogPowRoundTrip(0.2);
        checkLogPowRoundTrip(0.0001);
    }

    private void checkLogPowRoundTrip(double value) {
        assertEquals(value, this.axis.adjustedLog10(this.axis.adjustedPow10(value)),
                EPSILON, "log(pow(x)) = x");
        assertEquals(value, this.axis.adjustedPow10(this.axis.adjustedLog10(value)),
                EPSILON, "pow(log(x)) = x");
    }

    /**
     * Test if switchedLog10 and switchedPow10 are inverses of each other
     */
    @Test
    public void testSwitchedLog10() {
        assertFalse(this.axis.getAllowNegativesFlag(), "Axis should not allow negative values");

        assertEquals(Math.log(0.5) / LogarithmicAxis.LOG10_VALUE,
                this.axis.switchedLog10(0.5), EPSILON);
        checkSwitchedLogPowRoundTrip(20);
        checkSwitchedLogPowRoundTrip(10);
        checkSwitchedLogPowRoundTrip(5);
        checkSwitchedLogPowRoundTrip(2);
        checkSwitchedLogPowRoundTrip(1);
        checkSwitchedLogPowRoundTrip(0.5);
        checkSwitchedLogPowRoundTrip(0.2);
        checkSwitchedLogPowRoundTrip(0.0001);
    }

    private void checkSwitchedLogPowRoundTrip(double value) {
        assertEquals(value, this.axis.switchedLog10(
                this.axis.switchedPow10(value)), EPSILON, "log(pow(x)) = x");
        assertEquals(value, this.axis.switchedPow10(
                this.axis.switchedLog10(value)), EPSILON, "pow(log(x)) = x");
    }

    /**
     * Test of java2DToValue method.
     */
    @Test
    public void testJava2DToValue() {
        Rectangle2D plotArea = new Rectangle2D.Double(22, 33, 500, 500);
        RectangleEdge edge = RectangleEdge.BOTTOM;

        // set axis bounds to be both greater than 1
        this.axis.setRange(10, 20);
        checkPointsToValue(edge, plotArea);

        // check for bounds interval that includes 1
        this.axis.setRange(0.5, 10);
        checkPointsToValue(edge, plotArea);

        // check for bounds interval that includes 1
        this.axis.setRange(0.2, 20);
        checkPointsToValue(edge, plotArea);

        // check for both bounds smaller than 1
        this.axis.setRange(0.2, 0.7);
        checkPointsToValue(edge, plotArea);
    }

    /**
     * Test of valueToJava2D method.
     */
    @Test
    public void testValueToJava2D() {
        Rectangle2D plotArea = new Rectangle2D.Double(22, 33, 500, 500);
        RectangleEdge edge = RectangleEdge.BOTTOM;

        // set axis bounds to be both greater than 1
        this.axis.setRange(10, 20);
        checkPointsToJava2D(edge, plotArea);

        // check for bounds interval that includes 1
        this.axis.setRange(0.5, 10);
        checkPointsToJava2D(edge, plotArea);

        // check for bounds interval that includes 1
        this.axis.setRange(0.2, 20);
        checkPointsToJava2D(edge, plotArea);

        // check for both bounds smaller than 1
        this.axis.setRange(0.2, 0.7);
        checkPointsToJava2D(edge, plotArea);
    }

    private void checkPointsToJava2D(RectangleEdge edge, Rectangle2D plotArea) {
        assertEquals(plotArea.getX(), this.axis.valueToJava2D(
                        this.axis.getLowerBound(), plotArea, edge), EPSILON,
                "Left most point on the axis should be beginning of range.");
        assertEquals(plotArea.getX() + plotArea.getWidth(),
                this.axis.valueToJava2D(this.axis.getUpperBound(),
                        plotArea, edge), EPSILON, "Right most point on the axis should be end of range.");
        assertEquals(plotArea.getX() + (plotArea.getWidth() / 2),
                this.axis.valueToJava2D(Math.sqrt(this.axis.getLowerBound()
                        * this.axis.getUpperBound()), plotArea, edge), EPSILON,
                "Center point on the axis should geometric mean of the bounds.");
    }

    /**
     * Check the translation java2D to value for left, right, and center point.
     *
     * @param edge     the edge.
     * @param plotArea the plot area.
     */
    private void checkPointsToValue(RectangleEdge edge, Rectangle2D plotArea) {
        assertEquals(this.axis.getUpperBound(), this.axis.java2DToValue(
                        plotArea.getX() + plotArea.getWidth(), plotArea, edge),
                EPSILON, "Right most point on the axis should be end of range.");

        assertEquals(this.axis.getLowerBound(),
                this.axis.java2DToValue(plotArea.getX(), plotArea, edge),
                EPSILON, "Left most point on the axis should be beginning of range.");

        assertEquals(Math.sqrt(this.axis.getUpperBound()
                        * this.axis.getLowerBound()), this.axis.java2DToValue(
                        plotArea.getX() + (plotArea.getWidth() / 2), plotArea, edge),
                EPSILON, "Center point on the axis should geometric mean of the bounds.");
    }

}
