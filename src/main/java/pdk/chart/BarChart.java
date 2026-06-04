package pdk.chart;

import org.jspecify.annotations.Nullable;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.AxisChangeEvent;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.Marker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.title.TextTitle;

import java.awt.*;

/**
 * XY BarChart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 2:37 PM
 */
public class BarChart extends Chart {

    private final XYPlot plot_;
    private final NumberAxis domainAxis_;
    private final NumberAxis rangeAxis_;
    private final XYBarRenderer renderer_;

    public BarChart() {
        super(null, DEFAULT_TITLE_FONT, new XYPlot<>(), false);

        plot_ = getXYPlot();
        domainAxis_ = new NumberAxis();
        rangeAxis_ = new NumberAxis();
        renderer_ = new XYBarRenderer();

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer_);

        domainAxis_.setAutoRangeIncludesZero(false);

        DEFAULT_THEME.apply(this);
    }


    /**
     * Sets the chart title.
     * <p>
     * This is a convenience method that ends up calling
     * the {@link #setTitle(TextTitle)} method.  If there is an existing title,
     * its text is updated, otherwise a new title using the default font is
     * added to the chart.  If {@code text} is {@code null} the chart
     * title is set to {@code null}.
     *
     * @param title the title text.
     */
    public BarChart title(@Nullable String title) {
        setTitle(title);
        return this;
    }

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public BarChart addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        return this;
    }


    /**
     * Adds a marker for the range axis in the specified layer.
     * <p>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     * @param layer  the layer (foreground or background).
     */
    public BarChart addRangeMarker(Marker marker, Layer layer) {
        plot_.addRangeMarker(marker, layer);
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param createLegend true if add legend
     * @return this
     */
    public BarChart showLegend(boolean createLegend) {
        if (createLegend) {
            LegendTitle legend = new LegendTitle(this.plot_);
            legend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
            legend.setBackgroundPaint(Color.WHITE);
            legend.setPosition(RectangleEdge.BOTTOM);
            addSubtitle(legend);
        }
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public BarChart domainAxisName(String xAxisTitle) {
        domainAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public BarChart rangeAxisName(String yAxisTitle) {
        rangeAxis_.setLabel(yAxisTitle);
        return this;
    }

    /**
     * Sets the flag that indicates whether the axis range, if
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to {@code true}, the axis range is
     * recalculated.
     * <p>
     * Any change to the flag will trigger an {@link AxisChangeEvent}.
     *
     * @param xAxisIncludesZero the new value of the flag.
     */
    public BarChart domainAxisAutoRangeIncludesZero(boolean xAxisIncludesZero) {
        domainAxis_.setAutoRangeIncludesZero(xAxisIncludesZero);
        return this;
    }

    /**
     * Sets the flag that indicates whether the axis range, if
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to {@code true}, the axis range is
     * recalculated.
     *
     * @param yAxisIncludesZero the new value of the flag.
     */
    public BarChart rangeAxisAutoRangeIncludesZero(boolean yAxisIncludesZero) {
        rangeAxis_.setAutoRangeIncludesZero(yAxisIncludesZero);
        return this;
    }

    /**
     * Set the dataset to plot
     *
     * @param dataset {@link XYDataset}
     * @return this
     */
    public BarChart dataset(XYDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public BarChart orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }


}
