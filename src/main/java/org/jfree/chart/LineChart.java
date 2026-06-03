package org.jfree.chart;

import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.api.RectangleInsets;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.legend.LegendTitle;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.text.TextAnchor;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jspecify.annotations.Nullable;

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
        super(null, null, new XYPlot<>(), false);
        this.plot_ = (XYPlot) getPlot();
        domainAxis_ = new NumberAxis();
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
            legend.addChangeListener(this);
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
