package pdk.chart.plot;

/**
 * Defines the tokens that indicate the rendering order for series in a
 * {@link pdk.chart.plot.XYPlot}.
 */
public enum SeriesRenderingOrder {

    /**
     * Render series in the order 0, 1, 2, ..., N-1, where N is the number
     * of series.
     */
    FORWARD,

    /**
     * Render series in the order N-1, N-2, ..., 2, 1, 0, where N is the
     * number of series.
     */
    REVERSE

}
