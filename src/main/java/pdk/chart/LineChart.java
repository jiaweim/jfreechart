package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.TickUnitSource;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.Marker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.renderer.xy.XYSplineRenderer;
import pdk.chart.text.TextAnchor;

import java.awt.*;

/**
 * Line Chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 10:47 AM
 */
public class LineChart extends Chart {

    private final NumberAxis domainAxis_;
    private final NumberAxis rangeAxis_;
    private final XYLineAndShapeRenderer renderer_;
    private final XYPlot plot_;

    public LineChart() {
        super(null, DEFAULT_TITLE_FONT, new XYPlot<>(), false);
        this.plot_ = (XYPlot) getPlot();
        domainAxis_ = new NumberAxis();
        domainAxis_.setAutoRangeIncludesZero(false);

        rangeAxis_ = new NumberAxis();
        renderer_ = new XYLineAndShapeRenderer(true, false);

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer_);


        DEFAULT_THEME.apply(this);
    }

    /**
     * Set the Chart title.
     *
     * @param title new title
     * @return this
     */
    public LineChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets the message that is displayed when the dataset is empty or
     * {@code null}.
     *
     * @param message the message ({@code null} permitted).
     */
    public LineChart noDataMessage(String message) {
        plot_.setNoDataMessage(message);
        return this;
    }

    /**
     * Whether to create and display the legend.
     *
     * @param showLegend true if add legend.
     * @return this
     */
    public LineChart showLegend(boolean showLegend) {
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
     * @see #getBackgroundPaint()
     */
    public LineChart backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the default 'lines visible' flag.
     *
     * @param showLine whether show lines between data points.
     * @return this
     */
    public LineChart defaultLinesVisible(boolean showLine) {
        renderer_.setDefaultLinesVisible(showLine);
        return this;
    }

    /**
     * Set true to create scatter chart.
     *
     * @param showShape whether show shapes of data points
     * @return this
     */
    public LineChart defaultShapesVisible(boolean showShape) {
        renderer_.setDefaultShapesVisible(showShape);
        return this;
    }

    /**
     * Set true to create scatter chart.
     *
     * @param showShape whether show shapes of data points
     * @return this
     */
    public LineChart defaultShapesVisible(int dataset, boolean showShape) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer instanceof XYLineAndShapeRenderer lineAndShapeRenderer) {
            lineAndShapeRenderer.setDefaultShapesVisible(showShape);
        }
        return this;
    }

    /**
     * Sets the shape used for a series.
     *
     * @param series the series index (zero-based).
     * @param shape  the shape.
     */
    public LineChart seriesShape(int series, @Nullable Shape shape) {
        renderer_.setSeriesShape(series, shape);
        return this;
    }

    /**
     * Sets the shape used for a series.
     *
     * @param dataset dataset index
     * @param series  the series index (zero-based).
     * @param shape   the shape.
     */
    public LineChart seriesShape(int dataset, int series, @Nullable Shape shape) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        renderer.setSeriesShape(series, shape);
        return this;
    }

    /**
     * Sets the paint used for a series.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public LineChart seriesPaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesPaint(series, paint);
        return this;
    }

    /**
     * Sets the paint used for a series.
     *
     * @param dataset dataset index
     * @param series  the series index (zero-based).
     * @param paint   the paint ({@code null} permitted).
     */
    public LineChart seriesPaint(int dataset, int series, Paint paint) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        renderer.setSeriesPaint(series, paint);
        return this;
    }

    /**
     * Sets the paint used for a series fill.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public LineChart seriesFillPaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesFillPaint(series, paint);
        return this;
    }

    /**
     * Sets the paint used for a series fill.
     *
     * @param dataset dataset index
     * @param series  the series index (zero-based).
     * @param paint   the paint ({@code null} permitted).
     */
    public LineChart seriesFillPaint(int dataset, int series, Paint paint) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        renderer.setSeriesFillPaint(series, paint);
        return this;
    }

    /**
     * Sets the paint used for a series outline.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public LineChart seriesOutlinePaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesOutlinePaint(series, paint, false);
        return this;
    }

    /**
     * Sets the paint used for a series outline.
     *
     * @param dataset dataset index
     * @param series  the series index (zero-based).
     * @param paint   the paint ({@code null} permitted).
     */
    public LineChart seriesOutlinePaint(int dataset, int series, Paint paint) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        renderer.setSeriesOutlinePaint(series, paint);
        return this;
    }

    /**
     * Sets the flag that controls whether outlines are drawn for shapes.
     * <p>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag the flag.
     */
    public LineChart drawOutlines(boolean flag) {
        renderer_.setDrawOutlines(flag);
        return this;
    }

    /**
     * Sets the flag that controls whether outlines are drawn for shapes.
     * <p>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param dataset dataset index
     * @param flag    the flag.
     */
    public LineChart drawOutlines(int dataset, boolean flag) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        if (renderer instanceof XYLineAndShapeRenderer lineShapeRenderer) {
            lineShapeRenderer.setDrawOutlines(flag);
        }
        return this;
    }

    /**
     * Set the dataset to plot
     *
     * @param dataset {@link XYDataset}
     * @return this
     */
    public LineChart dataset(XYDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Add a new dataset to the plot
     *
     * @param dataset {@link XYDataset} instance
     * @return this
     */
    public LineChart addDataset(XYDataset dataset) {
        int datasetCount = plot_.getDatasetCount();
        return addDataset(datasetCount, dataset);
    }

    /**
     * Add a new dataset to the plot
     *
     * @param index   index of the dataset
     * @param dataset {@link XYDataset} instance
     * @return this
     */
    public LineChart addDataset(int index, XYDataset dataset) {
        XYDataset preDataset = plot_.getDataset(index);
        if (preDataset != null) {
            throw new IllegalStateException("Dataset with index " + index + " already exists!");
        }
        plot_.setDataset(index, dataset);
        plot_.setRenderer(index, new XYLineAndShapeRenderer(true, false));
        return this;
    }

    /**
     * Add a new dataset to the plot
     *
     * @param index   index of the dataset
     * @param dataset {@link XYDataset} instance
     * @return this
     */
    public LineChart addDataset(int index, XYDataset dataset, boolean smooth) {
        XYDataset preDataset = plot_.getDataset(index);
        if (preDataset != null) {
            throw new IllegalStateException("Dataset with index " + index + " already exists!");
        }
        plot_.setDataset(index, dataset);
        if (smooth) {
            plot_.setRenderer(index, new XYSplineRenderer());
        } else {
            plot_.setRenderer(index, new XYLineAndShapeRenderer(true, false));
        }
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public LineChart orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
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
    public LineChart addRangeMarker(Marker marker, Layer layer) {
        plot_.addRangeMarker(marker, layer);
        return this;
    }

    /**
     * Configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public LineChart addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        return this;
    }

    /**
     * Add a XYPointer Annotation
     *
     * @param label           annotation text
     * @param x               the x-coordinate (measured against the chart's domain axis).
     * @param y               the y-coordinate (measured against the chart's range axis).
     * @param angle           the angle of the arrow's line (in radians).
     * @param labelOffset     the label offset (distance between arrows and annotation text)
     * @param textAnchor      the text anchor (the point on the text bounding rectangle that is aligned to the (x, y) coordinate of the annotation)
     * @param backgroundColor the background paint for the annotation
     * @return this
     */
    public LineChart addPointerAnnotation(String label, double x, double y,
            double angle, double labelOffset,
            TextAnchor textAnchor, Color backgroundColor) {

        XYPointerAnnotation annotation = new XYPointerAnnotation(label, x, y, angle);
        annotation.setLabelOffset(labelOffset);
        annotation.setTextAnchor(textAnchor);
        annotation.setBackgroundPaint(backgroundColor);
        plot_.addAnnotation(annotation);
        return this;
    }

    /**
     * Sets the stroke used for a series.
     *
     * @param series series index
     * @param stroke {@link Stroke}
     * @return this
     */
    public LineChart seriesStroke(int series, @Nullable Stroke stroke) {
        renderer_.setSeriesStroke(series, stroke);
        return this;
    }

    /**
     * Set the line width of a given series
     *
     * @param series series index
     * @param width  line width
     * @return this
     */
    public LineChart seriesLineWidth(int series, float width) {
        renderer_.setSeriesStroke(series, new BasicStroke(width));
        return this;
    }

    /**
     * Sets the 'lines visible' flag for a series.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
     */
    public LineChart seriesLinesVisible(int series, boolean visible) {
        renderer_.setSeriesLinesVisible(series, visible);
        return this;
    }

    /**
     * Sets the 'shapes visible' flag for a series.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
     */
    public LineChart seriesShapesVisible(int series, boolean visible) {
        renderer_.setSeriesShapesVisible(series, visible);
        return this;
    }

    /**
     * Whether the outline paint is used to draw shape outlines.
     *
     * @param flag the flag.
     */
    public LineChart useOutlinePaint(boolean flag) {
        renderer_.setUseOutlinePaint(flag);
        return this;
    }

    /**
     * Sets the flag that controls whether the outline paint is used to draw
     * shape outlines.
     *
     * @param dataset dataset index
     * @param flag    the flag
     */
    public LineChart useOutlinePaint(int dataset, boolean flag) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        if (renderer instanceof XYLineAndShapeRenderer lineShapeRenderer) {
            lineShapeRenderer.setUseOutlinePaint(flag);
        }
        return this;
    }

    /**
     * Whether the fill paint is used to fill shapes.
     *
     * @param flag the flag.
     */
    public LineChart useFillPaint(boolean flag) {
        renderer_.setUseFillPaint(flag);
        return this;
    }

    /**
     * Sets the flag that controls whether the fill paint is used to fill
     * shapes.
     *
     * @param dataset dataset index.
     * @param flag    the flag.
     */
    public LineChart useFillPaint(int dataset, boolean flag) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer == null) {
            throw new IllegalArgumentException("Dataset of index " + dataset + " not exist!");
        }
        if (renderer instanceof XYLineAndShapeRenderer lineShapeRenderer) {
            lineShapeRenderer.setUseFillPaint(flag);
        }
        return this;
    }

    /**
     * Set X Axis title
     *
     * @param xAxisTitle x axis title
     * @return this
     */
    public LineChart domainAxisName(String xAxisTitle) {
        domainAxis_.setLabel(xAxisTitle);
        return this;
    }

    /**
     * Set the domain and range axis names.
     *
     * @param domainName domain axis title.
     * @param rangeName  range axis title.
     * @return this
     */
    public LineChart axisName(String domainName, String rangeName) {
        domainAxis_.setLabel(domainName);
        rangeAxis_.setLabel(rangeName);
        return this;
    }

    /**
     * Sets the flag that indicates whether the axis range, if
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to {@code true}, the axis range is
     * recalculated.
     *
     * @param xAxisIncludesZero the new value of the flag.
     */
    public LineChart domainAxisAutoRangeIncludesZero(boolean xAxisIncludesZero) {
        if (domainAxis_ instanceof NumberAxis na) {
            na.setAutoRangeIncludesZero(xAxisIncludesZero);
        }
        return this;
    }

    /**
     * Set Y Axis title
     *
     * @param yAxisTitle y axis title
     * @return this
     */
    public LineChart rangeAxisName(String yAxisTitle) {
        rangeAxis_.setLabel(yAxisTitle);
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
    public LineChart rangeAxisAutoRangeIncludesZero(boolean yAxisIncludesZero) {
        rangeAxis_.setAutoRangeIncludesZero(yAxisIncludesZero);
        return this;
    }

    /**
     * Sets the source for obtaining standard tick units for the axis.  The axis will
     * try to select the smallest tick unit from the source that does not cause
     * the tick labels to overlap.
     *
     * @param source the source for standard tick units.
     */
    public LineChart rangeAxisStandardTickUnits(@Nullable TickUnitSource source) {
        rangeAxis_.setStandardTickUnits(source);
        return this;
    }

    /**
     * Sets the range for the axis and sends a change event to all registered
     * listeners.  As a side-effect, the auto-range flag is set to
     * {@code false}.
     *
     * @param lower the lower axis limit.
     * @param upper the upper axis limit.
     */
    public LineChart domainAxisRange(double lower, double upper) {
        domainAxis_.setRange(lower, upper);
        return this;
    }

    /**
     * Sets the range for the axis and sends a change event to all registered
     * listeners. As a side-effect, the auto-range flag is set to
     * {@code false}.
     *
     * @param lower the lower axis limit.
     * @param upper the upper axis limit.
     */
    public LineChart rangeAxisRange(double lower, double upper) {
        rangeAxis_.setRange(lower, upper);
        return this;
    }

    /**
     * Enables or disables panning of the plot along the domain axes.
     *
     * @param pannable the new flag value.
     */
    public LineChart domainPannable(boolean pannable) {
        plot_.setDomainPannable(pannable);
        return this;
    }

    /**
     * Enables or disables panning of the plot along the range axis/axes.
     *
     * @param pannable the new flag value.
     */
    public LineChart rangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
        return this;
    }

    /**
     * Sets the background color of the plot area.
     *
     * @param paint the paint.
     */
    public LineChart plotBackgroundPaint(@Nullable Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showDomainGridlines true if show grid lines
     * @return this
     */
    public LineChart domainGridlinesVisible(boolean showDomainGridlines) {
        plot_.setDomainGridlinesVisible(showDomainGridlines);
        return this;
    }

    /**
     * Sets the stroke for the grid lines plotted against the domain axis.
     *
     * @param stroke the stroke.
     */
    public LineChart domainGridlinesStroke(@NonNull Stroke stroke) {
        plot_.setDomainGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain
     * axis.
     *
     * @param paint the paint.
     */
    public LineChart domainGridlinePaint(@NonNull Paint paint) {
        plot_.setDomainGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the stroke for the minor grid lines plotted against the domain axis.
     *
     * @param stroke the stroke.
     */
    public LineChart domainMinorGridlineStroke(@NonNull Stroke stroke) {
        plot_.setDomainMinorGridlineStroke(stroke);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showRangeGridlines true if show grid lines
     * @return this
     */
    public LineChart rangeGridlinesVisible(boolean showRangeGridlines) {
        plot_.setRangeGridlinesVisible(showRangeGridlines);
        return this;
    }

    /**
     * Sets the stroke for the grid lines plotted against the range axis.
     *
     * @param stroke the stroke ({@code null} not permitted).
     */
    public LineChart rangeGridlinesStroke(@NonNull Stroke stroke) {
        plot_.setRangeGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the stroke for the minor grid lines plotted against the range axis.
     *
     * @param stroke the stroke.
     */
    public LineChart rangeMinorGridlineStroke(@NonNull Stroke stroke) {
        plot_.setRangeMinorGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis.
     *
     * @param paint the paint.
     */
    public LineChart rangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint for the minor grid lines plotted against the range axis.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public LineChart rangeMinorGridlinePaint(Paint paint) {
        plot_.setRangeMinorGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint for the minor grid lines plotted against the domain axis.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public LineChart domainMinorGridlinePaint(Paint paint) {
        plot_.setDomainMinorGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the flag that controls whether the domain minor grid-lines
     * are visible.
     *
     * @param visible the new value of the flag.
     */
    public LineChart domainMinorGridlinesVisible(boolean visible) {
        plot_.setDomainMinorGridlinesVisible(visible);
        return this;
    }


    /**
     * Sets the flag that controls whether the range axis minor grid
     * lines are visible.
     *
     * @param visible the new value of the flag.
     */
    public LineChart rangeMinorGridlinesVisible(boolean visible) {
        plot_.setRangeMinorGridlinesVisible(visible);
        return this;
    }

    /**
     * Sets the auto range minimum size.
     * <p>
     * The minimum display range of the axis values.
     *
     * @param size the size.
     */
    public LineChart rangeAxisAutoRangeMinimumSize(double size) {
        rangeAxis_.setAutoRangeMinimumSize(size, false);
        return this;
    }


    /**
     * Sets the flag that controls whether the zero baseline is
     * displayed for the domain axis.
     *
     * @param visible the flag.
     */
    public LineChart domainZeroBaselineVisible(boolean visible) {
        plot_.setDomainZeroBaselineVisible(visible);
        return this;
    }

    /**
     * Sets the flag that controls whether the zero baseline is
     * displayed for the range axis.
     *
     * @param visible the flag.
     */
    public LineChart rangeZeroBaselineVisible(boolean visible) {
        plot_.setRangeZeroBaselineVisible(visible);
        return this;
    }

    /**
     * Sets the inside length of the tick marks.
     *
     * @param length the new length.
     */
    public LineChart domainAxisTickMarkInsideLength(float length) {
        domainAxis_.setTickMarkInsideLength(length);
        return this;
    }

    /**
     * Sets the inside length of the tick marks.
     *
     * @param length the new length.
     */
    public LineChart rangeAxisTickMarkInsideLength(float length) {
        rangeAxis_.setTickMarkInsideLength(length);
        return this;
    }

    /**
     * Sets the outside length of the tick marks.
     *
     * @param length the new length.
     */
    public LineChart domainAxisTickMarkOutsideLength(float length) {
        domainAxis_.setTickMarkOutsideLength(length);
        return this;
    }

    /**
     * Sets the outside length of the tick marks.
     *
     * @param length the new length.
     */
    public LineChart rangeAxisTickMarkOutsideLength(float length) {
        rangeAxis_.setTickMarkOutsideLength(length);
        return this;
    }


    /**
     * Sets the number of minor tick marks to display.
     *
     * @param count the count.
     */
    public LineChart domainAxisMinorTickCount(int count) {
        domainAxis_.setMinorTickCount(count);
        return this;
    }

    /**
     * Sets the number of minor tick marks to display.
     * <p>
     * 2 means dividing the interval between two adjacent major ticks into two segments and inserting one minor tick.
     *
     * @param count the count.
     */
    public LineChart rangeAxisMinorTickCount(int count) {
        rangeAxis_.setMinorTickCount(count);
        return this;
    }

    /**
     * Sets the flag that indicates whether the minor tick marks are
     * showing.
     *
     * @param flag the flag.
     */
    public LineChart domainAxisMinorTickMarksVisible(boolean flag) {
        domainAxis_.setMinorTickMarksVisible(flag);
        return this;
    }

    /**
     * Sets the flag that indicates whether the minor tick marks are
     * showing.
     *
     * @param flag the flag.
     */
    public LineChart rangeAxisMinorTickMarksVisible(boolean flag) {
        rangeAxis_.setMinorTickMarksVisible(flag);
        return this;
    }

    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        XYSeries<String> series = new XYSeries<>("TIC");
        double[] x = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        double[] y = {100.0, 250.0, 500.0, 800.0, 600.0, 300.0, 150.0, 50.0};
        series.add(x, y);
        dataset.addSeries(series);

        LineChart chart = new LineChart();
        chart.dataset(dataset)
                .title("Chromatogram")
                .domainAxisName("Time (min)")
                .rangeAxisName("Intensity");
        chart.show();
    }
}
