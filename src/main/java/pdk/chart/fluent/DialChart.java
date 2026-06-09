package pdk.chart.fluent;

import pdk.chart.Chart;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.plot.dial.DialFrame;
import pdk.chart.plot.dial.DialLayer;
import pdk.chart.plot.dial.DialPlot;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 11:01 AM
 */
public class DialChart extends Chart {

    private final DialPlot plot_;

    public DialChart() {
        super(null, DEFAULT_TITLE_FONT, new DialPlot(), false);
        plot_ = (DialPlot) getPlot();
    }

    /**
     * Sets the dataset for the plot, replacing the existing dataset, if there
     * is one, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param dataset the dataset ({@code null} permitted).
     */
    public DialChart dataset(ValueDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Sets the dial's frame and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param frame the frame ({@code null} not permitted).
     */
    public DialChart dialFrame(DialFrame frame) {
        plot_.setDialFrame(frame);
        return this;
    }

    /**
     * Sets the background layer and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param background the background layer ({@code null} permitted).
     */
    public DialChart plotBackground(DialLayer background) {
        plot_.setBackground(background);
        return this;
    }

}
