package pdk.chart.plot;

/**
 * Defines the tokens that indicate the rendering order for datasets in a
 * {@link pdk.chart.plot.CategoryPlot} or an
 * {@link pdk.chart.plot.XYPlot}.
 */
public enum DatasetRenderingOrder {

    /**
     * Render datasets in the order 0, 1, 2, ..., N-1, where N is the number
     * of datasets.
     */
    FORWARD,

    /**
     * Render datasets in the order N-1, N-2, ..., 2, 1, 0, where N is the
     * number of datasets.
     */
    REVERSE

}
