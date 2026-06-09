package pdk.chart.fluent;

import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.plot.ThermometerPlot;

import java.awt.*;

/**
 * Thermometer.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:20 AM
 */
public class ThermometerChart extends Chart {

    private final ThermometerPlot plot_;

    public ThermometerChart() {
        super(null, DEFAULT_TITLE_FONT, new ThermometerPlot(), false);
        plot_ = (ThermometerPlot) getPlot();
    }

    /**
     * Sets the dataset for the plot, replacing the existing dataset if there
     * is one, and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param dataset the dataset ({@code null} permitted).
     */
    public ThermometerChart dataset(ValueDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the Chart title.
     *
     * @param title new title
     * @return this
     */
    public ThermometerChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Set the plot background paint.
     *
     * @param paint {@link Paint}
     * @return this.
     */
    public ThermometerChart plotBackgroundPaint(Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the padding for the thermometer and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param padding the padding ({@code null} not permitted).
     */
    public ThermometerChart padding(RectangleInsets padding) {
        plot_.setPadding(padding);
        return this;
    }

    /**
     * Set the radius (in Java2D units) of the bulb of the thermometer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param r the new radius (in Java2D units).
     */
    public void bulbRadius(int r) {
        plot_.setBulbRadius(r);
    }
}
