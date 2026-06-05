package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.TickUnitSource;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.labels.BoxAndWhiskerToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BoxAndWhiskerRenderer;

import java.awt.*;

/**
 * Box-Whisker chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:08 AM
 */
public class BoxWhiskerChart extends Chart {

    private final CategoryPlot plot_;
    private final NumberAxis yAxis_;
    private final CategoryAxis xAxis_;
    private final BoxAndWhiskerRenderer renderer_;

    public BoxWhiskerChart() {
        super(null, DEFAULT_TITLE_FONT, new CategoryPlot<>(), false);

        plot_ = getCategoryPlot();
        renderer_ = new BoxAndWhiskerRenderer();
        xAxis_ = new CategoryAxis();
        yAxis_ = new NumberAxis();

        plot_.setRenderer(renderer_);
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Set the chart title.
     *
     * @param title new title
     * @return this
     */
    public BoxWhiskerChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets the paint used to fill the chart background.
     *
     * @param paint the paint.
     */
    public BoxWhiskerChart backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param createLegend true if add legend
     * @return this
     */
    public BoxWhiskerChart showLegend(boolean createLegend) {
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
     * Sets the dataset for the plot, replacing the existing dataset, if there
     * is one.
     *
     * @param dataset the dataset.
     */
    public BoxWhiskerChart dataset(@Nullable BoxAndWhiskerCategoryDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Sets the background color of the plot area.
     *
     * @param paint the paint.
     */
    public BoxWhiskerChart plotBackgroundPaint(@Nullable Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public BoxWhiskerChart domainGridlinesVisible(boolean showGridlines) {
        plot_.setDomainGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public BoxWhiskerChart rangeGridlinesVisible(boolean showGridlines) {
        plot_.setRangeGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain
     * axis.
     *
     * @param paint the paint.
     */
    public BoxWhiskerChart domainGridlinePaint(@NonNull Paint paint) {
        plot_.setDomainGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis.
     *
     * @param paint the paint.
     */
    public BoxWhiskerChart rangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public BoxWhiskerChart domainAxisName(String xAxisTitle) {
        xAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set Y Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public BoxWhiskerChart rangeAxisName(String yAxisTitle) {
        yAxis_.setLabel(yAxisTitle);
        return this;
    }

    public BoxWhiskerChart axisName(String xTitle, String yTitle) {
        xAxis_.setLabel(xTitle);
        yAxis_.setLabel(yTitle);
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
    public BoxWhiskerChart rangeAxisAutoRangeIncludesZero(boolean yAxisIncludesZero) {
        yAxis_.setAutoRangeIncludesZero(yAxisIncludesZero);
        return this;
    }

    /**
     * Sets the source for obtaining standard tick units for the axis.  The axis will
     * try to select the smallest tick unit from the source that does not cause
     * the tick labels to overlap.
     *
     * @param source the source for standard tick units.
     */
    public BoxWhiskerChart rangeAxisStandardTickUnits(@Nullable TickUnitSource source) {
        yAxis_.setStandardTickUnits(source);
        return this;
    }

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public BoxWhiskerChart addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        }
        return this;
    }

    /**
     * Sets the flag that controls whether the minimum outlier is drawn
     * for each item.
     *
     * @param visible the new flag value. default true.
     */
    public BoxWhiskerChart minOutlierVisible(boolean visible) {
        renderer_.setMinOutlierVisible(visible);
        return this;
    }

    /**
     * Sets the flag that controls whether the maximum outlier is drawn
     * for each item.
     *
     * @param visible the new flag value. default true.
     */
    public BoxWhiskerChart maxOutlierVisible(boolean visible) {
        renderer_.setMaxOutlierVisible(visible);
        return this;
    }

}
