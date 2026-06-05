package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;

import java.awt.*;

/**
 * Line Chart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:57 AM
 */
public class LineChartCategory extends Chart {

    private final CategoryAxis xAxis_ = new CategoryAxis();
    private final NumberAxis yAxis_ = new NumberAxis();
    private final CategoryPlot plot_;
    private final LineAndShapeRenderer renderer_;

    public LineChartCategory() {
        super(null, DEFAULT_TITLE_FONT, new CategoryPlot<>(), false);
        plot_ = getCategoryPlot();
        renderer_ = new LineAndShapeRenderer();

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Set the chart title.
     *
     * @param title new title
     * @return this
     */
    public LineChartCategory title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Set the dataset to plot.
     *
     * @param dataset {@link CategoryDataset}.
     * @return this
     */
    public LineChartCategory dataset(CategoryDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }


    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public LineChartCategory addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        return this;
    }

    /**
     * Sets the default shape.
     *
     * @param shape the shape.
     */
    public LineChartCategory defaultShape(@NonNull Shape shape) {
        renderer_.setDefaultShape(shape, false);
        return this;
    }

    /**
     * Sets the shape of a given series.
     *
     * @param shape the shape.
     */
    public LineChartCategory seriesShape(int series, @NonNull Shape shape) {
        renderer_.setSeriesShape(series, shape, false);
        return this;
    }

    /**
     * Sets the outline stroke used for a series.
     * <p>
     * For example, it can be used to set the border width of each data point.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public LineChartCategory seriesOutlineStroke(int series, @Nullable Stroke stroke) {
        renderer_.setSeriesOutlineStroke(series, stroke);
        return this;
    }

    /**
     * Sets the 'shapes filled' flag for a series.
     *
     * @param series the series index (zero-based).
     * @param filled the flag.
     */
    public LineChartCategory seriesShapesFilled(int series, boolean filled) {
        renderer_.setSeriesShapesFilled(series, filled);
        return this;
    }


    /**
     * Set the line width of a given series
     *
     * @param series series index
     * @param width  line width
     * @return this
     */
    public LineChartCategory seriesLinesWidth(int series, float width) {
        renderer_.setSeriesStroke(series, new BasicStroke(width));
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param createLegend true if add legend
     * @return this
     */
    public LineChartCategory showLegend(boolean createLegend) {
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
     * Sets the category label position specification for the axis.
     *
     * @param positions the positions.
     */
    public LineChartCategory categoryLabelPositions(@NonNull CategoryLabelPositions positions) {
        xAxis_.setCategoryLabelPositions(positions);
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}.
     * @return this
     */
    public LineChartCategory orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public LineChartCategory domainAxisName(String xAxisTitle) {
        xAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set Y Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public LineChartCategory rangeAxisName(String yAxisTitle) {
        yAxis_.setLabel(yAxisTitle);
        return this;
    }

}
