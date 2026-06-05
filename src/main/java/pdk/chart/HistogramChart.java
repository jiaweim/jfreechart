package pdk.chart;

import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;

import java.awt.*;

/**
 * Class to create Histogram.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:45 AM
 */
public class HistogramChart extends Chart {

    private final NumberAxis xAxis_;
    private final NumberAxis yAxis_;
    private final XYPlot plot_;
    private final XYBarRenderer renderer_;

    public HistogramChart() {
        super(null, DEFAULT_TITLE_FONT, new XYPlot<>(), false);
        xAxis_ = new NumberAxis();
        yAxis_ = new NumberAxis();
        renderer_ = new XYBarRenderer();
        plot_ = getXYPlot();

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);
        xAxis_.setAutoRangeIncludesZero(false);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Sets whether bar outlines are drawn.
     *
     * @param draw the flag.
     */
    public HistogramChart drawBarOutline(boolean draw) {
        renderer_.setDrawBarOutline(draw);
        return this;
    }

    /**
     * Set the dataset to plot
     *
     * @param dataset {@link IntervalXYDataset} instance
     * @return this
     */
    public HistogramChart dataset(IntervalXYDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the chart title.
     *
     * @param title new title
     * @return this
     */
    public HistogramChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public HistogramChart orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public HistogramChart domainAxisName(String xAxisTitle) {
        xAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public HistogramChart rangeAxisName(String yAxisTitle) {
        yAxis_.setLabel(yAxisTitle);
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param createLegend true if add legend
     * @return this
     */
    public HistogramChart showLegend(boolean createLegend) {
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
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public HistogramChart addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public HistogramChart domainGridlinesVisible(boolean showGridlines) {
        plot_.setDomainGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public HistogramChart rangeGridlinesVisible(boolean showGridlines) {
        plot_.setRangeGridlinesVisible(showGridlines);
        return this;
    }

}
