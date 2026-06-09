package pdk.chart.fluent;

import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.general.PieDataset;
import pdk.chart.event.ChartChangeEvent;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.labels.PieSectionLabelGenerator;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.labels.StandardPieToolTipGenerator;
import pdk.chart.plot.CenterTextMode;
import pdk.chart.plot.RingPlot;

import java.awt.*;
import java.text.Format;

/**
 * Ring chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 9:31 AM
 */
public class RingChart extends Chart {

    private final RingPlot plot_;

    public RingChart() {
        super(null, DEFAULT_TITLE_FONT, new RingPlot(), false);
        plot_ = (RingPlot) getPlot();

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
    public RingChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets the paint used to fill the chart background and sends a
     * {@link ChartChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @return this.
     */
    public RingChart backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }

    /**
     * Set up and add tooltips.
     *
     * @param addTooltips true if add tooltips.
     * @return this.
     */
    public RingChart addTooltips(boolean addTooltips) {
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
    public RingChart dataset(PieDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Sets the section label font and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param font the font ({@code null} not permitted).
     * @return this.
     */
    public RingChart labelFont(Font font) {
        plot_.setLabelFont(font);
        return this;
    }

    /**
     * Sets the message that is displayed when the dataset is empty or
     * {@code null}, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param message the message ({@code null} permitted).
     */
    public RingChart noDataMessage(String message) {
        plot_.setNoDataMessage(message);
        return this;
    }

    /**
     * The section depth is given as proportion of the plot radius.
     * Specifying 1.0 results in a straightforward pie chart.
     *
     * @param sectionDepth the section depth.
     */
    public RingChart sectionDepth(double sectionDepth) {
        plot_.setSectionDepth(sectionDepth);
        return this;
    }

    /**
     * A flag indicating whether the pie chart is circular, or stretched into
     * an elliptical shape.
     *
     * @param flag the new value.
     */
    public RingChart circular(boolean flag) {
        plot_.setCircular(flag);
        return this;
    }

    /**
     * Sets the gap between the edge of the pie and the labels (expressed as a
     * percentage of the plot width) and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param gap the gap (a percentage, where 0.05 = five percent).
     */
    public RingChart labelGap(double gap) {
        plot_.setLabelGap(gap);
        return this;
    }


    /**
     * Sets the mode for displaying text in the center of the plot and sends
     * a change event to all registered listeners.  For
     * {@link CenterTextMode#FIXED}, the display text will come from the
     * {@code centerText} attribute.
     * <p>
     * For {@link CenterTextMode#VALUE}, the center text will be the value from
     * the first section in the dataset.
     *
     * @param mode the mode ({@code null} not permitted).
     */
    public RingChart centerTextMode(CenterTextMode mode) {
        plot_.setCenterTextMode(mode);
        return this;
    }


    /**
     * Sets the font used to display the center text and sends a change event
     * to all registered listeners.
     *
     * @param font the font ({@code null} not permitted).
     */
    public RingChart centerTextFont(Font font) {
        plot_.setCenterTextFont(font);
        return this;
    }

    /**
     * Sets the color for the center text and sends a change event to all
     * registered listeners.
     *
     * @param color the color ({@code null} not permitted).
     */
    public RingChart centerTextColor(Color color) {
        plot_.setCenterTextColor(color);
        return this;
    }

    /**
     * Sets the formatter used to format the center text value and sends a
     * change event to all registered listeners.
     *
     * @param formatter the formatter ({@code null} not permitted).
     */
    public RingChart centerTextFormatter(Format formatter) {
        plot_.setCenterTextFormatter(formatter);
        return this;
    }

    /**
     * Sets the background color of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @see #getBackgroundPaint()
     */
    public RingChart plotBackgroundPaint(Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the flag that controls whether the plot's outline is
     * drawn, and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param visible the new flag value.
     */
    public RingChart outlineVisible(boolean visible) {
        plot_.setOutlineVisible(visible);
        return this;
    }

    /**
     * Sets the section label generator and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public RingChart labelGenerator(PieSectionLabelGenerator generator) {
        plot_.setLabelGenerator(generator);
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
    public RingChart sectionPaint(String key, Paint paint) {
        plot_.setSectionPaint(key, paint);
        return this;
    }

    /**
     * Sets the flag that controls whether the outline is drawn for
     * each pie section, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param visible the flag.
     */
    public RingChart sectionOutlinesVisible(boolean visible) {
        plot_.setSectionOutlinesVisible(visible);
        return this;
    }

    /**
     * Sets the shadow paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     */
    public RingChart shadowPaint(Paint paint) {
        plot_.setShadowPaint(paint);
        return this;
    }
}
