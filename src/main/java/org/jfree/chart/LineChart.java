package org.jfree.chart;

import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.api.RectangleInsets;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.legend.LegendTitle;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
        super(new XYPlot<>());
        this.plot_ = (XYPlot) getPlot();
        domainAxis_ = new NumberAxis();
        rangeAxis_ = new NumberAxis();
        renderer_ = new XYLineAndShapeRenderer(true, false);

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer_);
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
}
