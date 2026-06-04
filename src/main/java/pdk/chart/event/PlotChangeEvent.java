package pdk.chart.event;

import pdk.chart.plot.Plot;

/**
 * An event that can be forwarded to any
 * {@link pdk.chart.event.PlotChangeListener} to signal a change to a
 * plot.
 */
public class PlotChangeEvent extends ChartChangeEvent {

    /**
     * The plot that generated the event.
     */
    private final Plot plot;

    /**
     * Creates a new PlotChangeEvent.
     *
     * @param plot the plot that generated the event ({@code null} not
     *             permitted).
     */
    public PlotChangeEvent(Plot plot) {
        super(plot);
        this.plot = plot;
    }

    /**
     * Returns the plot that generated the event (set in the constructor).
     *
     * @return The plot that generated the event.
     */
    public Plot getPlot() {
        return this.plot;
    }

}
