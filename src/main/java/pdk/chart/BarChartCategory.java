package pdk.chart;

import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.text.TextAnchor;

import java.awt.*;

/**
 * Bar Chart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 9:28 AM
 */
public class BarChartCategory extends Chart {

    private final CategoryAxis xAxis_;
    private final NumberAxis yAxis_;
    private final BarRenderer renderer_;
    private final CategoryPlot plot_;

    public BarChartCategory() {
        this(PlotOrientation.VERTICAL);
    }

    public BarChartCategory(PlotOrientation orientation) {
        super(null, DEFAULT_TITLE_FONT, new CategoryPlot<>(), false);

        xAxis_ = new CategoryAxis();
        yAxis_ = new NumberAxis();
        renderer_ = new BarRenderer();
        plot_ = getCategoryPlot();
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);

        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer_.setDefaultNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer_.setDefaultNegativeItemLabelPosition(position2);
        }


        DEFAULT_THEME.apply(this);
    }

    /**
     * Sets the dataset for the plot, replacing the existing dataset, if there
     * is one.  This method also calls the
     * {@link #plot_.datasetChanged(DatasetChangeEvent)} method, which adjusts the
     * axis ranges if necessary and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param dataset the dataset.
     */
    public BarChartCategory dataset(@Nullable CategoryDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the chart title.
     *
     * @param title new title.
     * @return this
     */
    public BarChartCategory title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public BarChartCategory orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public BarChartCategory domainAxisName(String xAxisTitle) {
        xAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public BarChartCategory rangeAxisName(String yAxisTitle) {
        yAxis_.setLabel(yAxisTitle);
        return this;
    }

    /**
     * Set axis names.
     *
     * @param xAxisTitle x axis name.
     * @param yAxisTitle y axis name.
     * @return this.
     */
    public BarChartCategory axisName(String xAxisTitle, String yAxisTitle) {
        xAxis_.setLabel(xAxisTitle);
        yAxis_.setLabel(yAxisTitle);
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param showLegend true if add legend.
     * @return this
     */
    public BarChartCategory showLegend(boolean showLegend) {
        if (showLegend) {
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
    public BarChartCategory addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public BarChartCategory domainGridlinesVisible(boolean showGridlines) {
        plot_.setDomainGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public BarChartCategory rangeGridlinesVisible(boolean showGridlines) {
        plot_.setRangeGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Set the amount of space reserved between categories.
     *
     * @param categoryMargin a percentage of the axis length, default to be 0.2
     * @return this
     */
    public BarChartCategory categoryMargin(double categoryMargin) {
        xAxis_.setCategoryMargin(categoryMargin);
        return this;
    }

    /**
     * Set the margin between items (bars) within a category.
     *
     * @param itemMargin a percentage of the available space for all bars.
     * @return this
     */
    public BarChartCategory itemMargin(double itemMargin) {
        renderer_.setItemMargin(itemMargin);
        return this;
    }

    /**
     * Sets the paint used for a series.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public BarChartCategory seriesPaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesPaint(series, paint, false);
        return this;
    }
}
