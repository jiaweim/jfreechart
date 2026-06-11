package pdk.chart.plot.pep;

import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.XYItemLabelGenerator;
import pdk.chart.renderer.xy.YIntervalRenderer;

import java.awt.*;

/**
 * Chart to display peak list.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 10 Jun 2026, 2:51 PM
 */
public class SpectrumChart extends Chart {

    private final SpectrumPlot plot_;
    private final YIntervalRenderer renderer_;
    private final NumberAxis xAxis_;
    private final NumberAxis yAxis_;

    public SpectrumChart() {
        super(null, DEFAULT_TITLE_FONT, new SpectrumPlot(), false);
        this.plot_ = (SpectrumPlot) getPlot();
        this.renderer_ = (YIntervalRenderer) plot_.getRenderer();
        this.xAxis_ = (NumberAxis) plot_.getDomainAxis();
        this.yAxis_ = (NumberAxis) plot_.getRangeAxis();
        DEFAULT_THEME.apply(this);
    }

    /**
     * Set the title.
     *
     * @param title chart title.
     * @return this.
     */
    public SpectrumChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Set the peaks to be displayed.
     *
     * @param dataset {@link SpectrumDataset}.
     * @return this.
     */
    public SpectrumChart dataset(SpectrumDataset dataset) {
        this.plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the title of the x-axis.
     *
     * @param xAxisLabel x axis title.
     * @return this.
     */
    public SpectrumChart xAxisLabel(String xAxisLabel) {
        this.xAxis_.setLabel(xAxisLabel);
        return this;
    }

    /**
     * Set the range of the x-axis.
     *
     * @param min minimum value to display.
     * @param max maximum value to diaplay.
     * @return this.
     */
    public SpectrumChart xAxisRange(double min, double max) {
        this.xAxis_.setRange(min, max);
        return this;
    }

    /**
     * Set the title of the y-axis.
     *
     * @param label y axis title.
     * @return this.
     */
    public SpectrumChart yAxisLabel(String label) {
        this.yAxis_.setLabel(label);
        return this;
    }

    /**
     * Set the range of the y-axis.
     *
     * @param min minimum value to display.
     * @param max maximum value to display.
     * @return this.
     */
    public SpectrumChart yAxisRange(double min, double max) {
        this.yAxis_.setRange(min, max);
        return this;
    }

    /**
     * Sets  whether item labels are visible,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible the flag.
     */
    public SpectrumChart defaultItemLabelsVisible(boolean visible) {
        this.renderer_.setDefaultItemLabelsVisible(visible);
        return this;
    }

    /**
     * Sets the default item label font and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param font the font ({@code null} not permitted).
     */
    public SpectrumChart defaultItemLabelFont(Font font) {
        this.renderer_.setDefaultItemLabelFont(font);
        return this;
    }

    /**
     * Sets the default item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param labelGenerator the generator ({@code null} permitted).
     */
    public SpectrumChart defaultItemLabelGenerator(XYItemLabelGenerator labelGenerator) {
        this.renderer_.setDefaultItemLabelGenerator(labelGenerator);
        return this;
    }

    /**
     * Sets the item label generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series         the series index (zero based).
     * @param labelGenerator the generator ({@code null} permitted).
     */
    public SpectrumChart seriesItemLabelGenerator(int series, XYItemLabelGenerator labelGenerator) {
        renderer_.setSeriesItemLabelGenerator(series, labelGenerator);
        return this;
    }

    /**
     * Sets the item label paint for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series (zero based index).
     * @param paint  the paint ({@code null} permitted).
     */
    public SpectrumChart seriesItemLabelPaint(int series, Paint paint) {
        renderer_.setSeriesItemLabelPaint(series, paint);
        return this;
    }

    /**
     * Sets the paint used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public SpectrumChart seriesPaint(int series, Paint paint) {
        this.renderer_.setSeriesPaint(series, paint);
        return this;
    }

    /**
     * Sets the stroke used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public SpectrumChart seriesStroke(int series, Stroke stroke) {
        renderer_.setSeriesStroke(series, stroke);
        return this;
    }
}
