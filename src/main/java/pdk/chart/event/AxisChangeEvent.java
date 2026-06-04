package pdk.chart.event;

import pdk.chart.axis.Axis;

/**
 * A change event that encapsulates information about a change to an axis.
 */
public class AxisChangeEvent extends ChartChangeEvent {

    /**
     * The axis that generated the change event.
     */
    private final Axis axis;

    /**
     * Creates a new AxisChangeEvent.
     *
     * @param axis the axis that generated the event ({@code null} not
     *             permitted).
     */
    public AxisChangeEvent(Axis axis) {
        super(axis); // null is checked in this call
        this.axis = axis;
    }

    /**
     * Returns the axis that generated the event.
     *
     * @return The axis that generated the event (never {@code null}).
     */
    public Axis getAxis() {
        return this.axis;
    }

}
