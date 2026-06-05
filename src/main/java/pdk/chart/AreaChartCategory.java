package pdk.chart;

import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.AreaRenderer;

import java.awt.*;

/**
 * Area Chart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:22 AM
 */
public class AreaChartCategory extends Chart {

    private final CategoryPlot plot_;
    private final CategoryAxis xAxis_;
    private final NumberAxis yAxis_;
    private final AreaRenderer renderer_;

    public AreaChartCategory() {
        super(null, DEFAULT_TITLE_FONT, new CategoryPlot<>(), false);
        plot_ = getCategoryPlot();

        xAxis_ = new CategoryAxis();
        yAxis_ = new NumberAxis();

        renderer_ = new AreaRenderer();
        renderer_.setEndType(AreaRendererEndType.LEVEL);

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);

        xAxis_.setCategoryMargin(0.0);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Set the chart title.
     *
     * @param title new title
     * @return this
     */
    public AreaChartCategory title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param showLegend true if add legend.
     * @return this
     */
    public AreaChartCategory showLegend(boolean showLegend) {
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
     * Sets the paint used to fill the chart background.
     *
     * @param paint the paint.
     */
    public AreaChartCategory backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }

    /**
     * Set the dataset to plot
     *
     * @param dataset {@link CategoryDataset}
     * @return this
     */
    public AreaChartCategory dataset(CategoryDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public AreaChartCategory orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }

    /**
     * Sets the alpha-transparency for the plot.
     *
     * @param alpha the new alpha transparency.
     */
    public AreaChartCategory foregroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
        return this;
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes).
     *
     * @param offset the offset ({@code null} not permitted).
     */
    public AreaChartCategory axisOffset(RectangleInsets offset) {
        plot_.setAxisOffset(offset);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public AreaChartCategory domainAxisName(String xAxisTitle) {
        xAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set Y Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public AreaChartCategory rangeAxisName(String yAxisTitle) {
        yAxis_.setLabel(yAxisTitle);
        return this;
    }

    public AreaChartCategory axisNames(String xTitle, String yTitle) {
        xAxis_.setLabel(xTitle);
        yAxis_.setLabel(yTitle);
        return this;
    }

    /**
     * Sets the lower margin for the axis.
     *
     * @param margin the margin as a percentage of the axis length (for
     *               example, 0.05 is five percent).
     */
    public AreaChartCategory domainAxisLowerMargin(double margin) {
        xAxis_.setLowerMargin(margin);
        return this;
    }

    /**
     * Sets the upper margin for the axis.
     *
     * @param margin the margin as a percentage of the axis length (for
     *               example, 0.05 is five percent).
     */
    public AreaChartCategory domainAxisUpperMargin(double margin) {
        xAxis_.setUpperMargin(margin);
        return this;
    }

    //region Renderer Properties

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public AreaChartCategory addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        return this;
    }
}
