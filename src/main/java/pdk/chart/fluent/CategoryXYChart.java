package pdk.chart.fluent;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.fluent.prop.*;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.AreaRenderer;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;
import pdk.chart.title.Title;

import java.awt.*;

/**
 * XYChart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:10 AM
 */
public class CategoryXYChart extends Chart {

    /**
     * Create a {@link CategoryXYChart}
     *
     * @return {@link CategoryXYChart}.
     */
    public static CategoryXYChart create() {
        return new CategoryXYChart();
    }

    private final NumberAxis rangeAxis_ = new NumberAxis();
    private final CategoryAxis domainAxis_ = new CategoryAxis();
    private final CategoryPlot plot_;

    public CategoryXYChart() {
        super(null, DEFAULT_TITLE_FONT, new CategoryPlot<>(), false);
        plot_ = getCategoryPlot();

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Whether to create and display the legend.
     *
     * @param showLegend true if add legend.
     * @return this
     */
    public CategoryXYChart showLegend(boolean showLegend) {
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
    public CategoryXYChart title(@Nullable String title) {
        setTitle(title);
        return this;
    }

    public CategoryXYChart addTitle(Title title) {
        addSubtitle(title);
        return this;
    }

    /**
     * Set the axis labels.
     *
     * @param xName name for domain axis.
     * @param yName name for range axis.
     * @return this.
     */
    public CategoryXYChart axisNames(String xName, String yName) {
        domainAxis_.setLabel(xName);
        rangeAxis_.setLabel(yName);
        return this;
    }

    /**
     * Return the configuration class for range axis properties.
     *
     * @return {@link NumberAxisProps}.
     */
    public NumberAxisProps rangeAxis() {
        return new NumberAxisProps(this, rangeAxis_);
    }

    public CategoryAxisProps domainAxis() {
        return new CategoryAxisProps(this, domainAxis_);
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset   {@link CategoryDataset} to show.
     * @param chartType {@link CategoryXYChartType} to render the dataset.
     * @return this.
     */
    public CategoryXYChart dataset(CategoryDataset dataset, CategoryXYChartType chartType) {
        return addDataset(0, dataset, chartType);
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset   {@link CategoryDataset} to show.
     * @param chartType {@link CategoryXYChartType} to render the dataset.
     * @return this.
     */
    public CategoryXYChart addDataset(CategoryDataset dataset, CategoryXYChartType chartType) {
        int datasetCount = plot_.getDatasetCount();
        return addDataset(datasetCount, dataset, chartType);
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset   {@link CategoryDataset} to show.
     * @param chartType {@link CategoryXYChartType} to render the dataset.
     * @return this.
     */
    public CategoryXYChart addDataset(int index, CategoryDataset dataset, CategoryXYChartType chartType) {
        CategoryDataset exitingDataset = plot_.getDataset(index);
        if (exitingDataset != null) {
            throw new IllegalStateException("Dataset with index " + index + " already exists!");
        }

        CategoryItemRenderer renderer = chartType.getRenderer();
        if (chartType == CategoryXYChartType.BAR) {
            PlotOrientation orientation = plot_.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                ItemLabelPosition position1 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
                renderer.setDefaultPositiveItemLabelPosition(position1);
                ItemLabelPosition position2 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
                renderer.setDefaultNegativeItemLabelPosition(position2);
            } else if (orientation == PlotOrientation.VERTICAL) {
                ItemLabelPosition position1 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
                renderer.setDefaultPositiveItemLabelPosition(position1);
                ItemLabelPosition position2 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
                renderer.setDefaultNegativeItemLabelPosition(position2);
            }
        }

        plot_.setDataset(index, dataset);
        plot_.setRenderer(index, renderer);
        return this;
    }

    /**
     * Set properties for the {@link CategoryXYChartType#AREA}.
     * <p>
     * Throw an {@link IllegalStateException} if the renderer type of the specified dataset is not AREA
     *
     * @param index index of the dataset.
     * @return {@link CategoryAreaProps}.
     */
    public CategoryAreaProps areaRenderer(int index) {
        CategoryItemRenderer renderer = plot_.getRenderer(index);
        if (renderer instanceof AreaRenderer areaRenderer) {
            return new CategoryAreaProps(this, areaRenderer);
        }
        throw new IllegalStateException("The specified chart type is not area.");
    }

    /**
     * Set properties for the {@link CategoryXYChartType#BAR}.
     * <p>
     * Throw an {@link IllegalStateException} if the renderer type of the specified dataset is not BAR.
     *
     * @param index index of the dataset.
     * @return {@link CategoryBarProps}.
     */
    public CategoryBarProps barRenderer(int index) {
        CategoryItemRenderer renderer = plot_.getRenderer(index);
        if (renderer instanceof BarRenderer barRenderer) {
            return new CategoryBarProps(this, barRenderer);
        }
        throw new IllegalStateException("The specified chart type is not bar.");
    }

    /**
     * Set properties for the {@link CategoryXYChartType#LINE}.
     * <p>
     * Throw an {@link IllegalStateException} if the renderer type of the specified dataset is not LINE.
     *
     * @param index index of the dataset.
     * @return {@link CategoryBarProps}.
     */
    public CategoryLineProps lineRenderer(int index) {
        CategoryItemRenderer renderer = plot_.getRenderer(index);
        if (renderer instanceof LineAndShapeRenderer lineAndShapeRenderer) {
            return new CategoryLineProps(this, lineAndShapeRenderer);
        }
        throw new IllegalStateException("The specified chart type is not line.");
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public CategoryXYChart orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }

    /**
     * Sets the paint used to fill the chart background.
     *
     * @param paint the paint.
     */
    public CategoryXYChart backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the background color of the plot area.
     *
     * @param paint the paint.
     */
    public CategoryXYChart plotBackgroundPaint(@Nullable Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the alpha-transparency for the plot.
     *
     * @param alpha the new alpha transparency.
     */
    public CategoryXYChart foregroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
        return this;
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes).
     *
     * @param offset the offset ({@code null} not permitted).
     */
    public CategoryXYChart axisOffset(RectangleInsets offset) {
        plot_.setAxisOffset(offset);
        return this;
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain
     * axis.
     *
     * @param paint the paint.
     */
    public CategoryXYChart domainGridlinePaint(@NonNull Paint paint) {
        plot_.setDomainGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis.
     *
     * @param paint the paint.
     */
    public CategoryXYChart rangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public CategoryXYChart domainGridlinesVisible(boolean showGridlines) {
        plot_.setDomainGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public CategoryXYChart rangeGridlinesVisible(boolean showGridlines) {
        plot_.setRangeGridlinesVisible(showGridlines);
        return this;
    }
}
