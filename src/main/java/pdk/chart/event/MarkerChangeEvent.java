package pdk.chart.event;

import pdk.chart.plot.Marker;

/**
 * An event that can be forwarded to any {@link MarkerChangeListener} to
 * signal a change to a {@link Marker}.
 */
public class MarkerChangeEvent extends ChartChangeEvent {

    /**
     * The plot that generated the event.
     */
    private final Marker marker;

    /**
     * Creates a new {@code MarkerChangeEvent} instance.
     *
     * @param marker the marker that triggered the event ({@code null}
     *               not permitted).
     */
    public MarkerChangeEvent(Marker marker) {
        super(marker); // null check is in here
        this.marker = marker;
    }

    /**
     * Returns the marker that triggered the event.
     *
     * @return The marker that triggered the event (never {@code null}).
     */
    public Marker getMarker() {
        return this.marker;
    }

}
