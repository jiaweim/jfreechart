package pdk.chart.fluent;

import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.general.PieDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.labels.PieSectionLabelGenerator;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.labels.StandardPieToolTipGenerator;
import pdk.chart.plot.pie.PiePlot;

import java.awt.*;

/**
 * PieChart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 9:07 AM
 */
public class PieChart extends Chart {

    private final PiePlot plot_;

    public PieChart() {
        super(null, DEFAULT_TITLE_FONT, new PiePlot<>(), true);
        plot_ = (PiePlot) getPlot();

        plot_.setLabelGenerator(new StandardPieSectionLabelGenerator<>());
        plot_.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0), false);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Set the Chart title.
     *
     * @param title new title.
     * @return this.
     */
    public PieChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Set up and add tooltips.
     *
     * @param addTooltips true if add tooltips.
     * @return this.
     */
    public PieChart addTooltips(boolean addTooltips) {
        if (addTooltips) {
            plot_.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        return this;
    }

    /**
     * Sets the dataset and sends a {@link DatasetChangeEvent} to 'this'.
     *
     * @param dataset the dataset ({@code null} permitted).
     */
    public PieChart dataset(PieDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Sets the background color of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @see #getBackgroundPaint()
     */
    public PieChart plotBackgroundPaint(Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the paint associated with the specified key, and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param key   the key ({@code null} not permitted).
     * @param paint the paint.
     * @throws IllegalArgumentException if {@code key} is
     *                                  {@code null}.
     */
    public PieChart sectionPaint(Comparable key, Paint paint) {
        plot_.setSectionPaint(key, paint);
        return this;
    }

    /**
     * Sets the section label generator and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public PieChart labelGenerator(PieSectionLabelGenerator generator) {
        plot_.setLabelGenerator(generator);
        return this;
    }
}
