package pdk.chart.examples;

import pdk.chart.LineChart;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 1:46 PM
 */
public class XYLineAndShapeRendererDemo2 {
    static void main() {
        XYSeriesCollection<String> dataset1 = new XYSeriesCollection<>(new XYSeries<>("Series 1", new double[]{1, 2, 3}, new double[]{1, 1, 1}));
        XYSeriesCollection<String> dataset2 = new XYSeriesCollection<>(new XYSeries<>("Series 2", new double[]{1, 2, 3}, new double[]{2, 2, 2}));
        XYSeriesCollection<String> dataset3 = new XYSeriesCollection<>(new XYSeries<>("Series 3", new double[]{1, 2, 3}, new double[]{3, 3, 3}));
        XYSeriesCollection<String> dataset4 = new XYSeriesCollection<>(new XYSeries<>("Series 4", new double[]{1, 2, 3}, new double[]{4, 4, 4}));
        XYSeriesCollection<String> dataset5 = new XYSeriesCollection<>(new XYSeries<>("Series 5", new double[]{1, 2, 3}, new double[]{5, 5, 5}));

        Shape shape = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
        LineChart chart = new LineChart();
        chart.title("XYLineAndShapeRendererDemo2")
                .dataset(dataset1)
                .addDataset(dataset2)
                .addDataset(dataset3)
                .addDataset(dataset4)
                .addDataset(dataset5)
                .domainAxisName("X")
                .rangeAxisName("Y")

                .defaultShapesVisible(true)
                .defaultLinesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)

                .defaultShapesVisible(1, true)
                .seriesShape(1, 0, shape)
                .seriesPaint(1, 0, Color.RED)
                .seriesFillPaint(1, 0, Color.YELLOW)
                .seriesOutlinePaint(1, 0, Color.GRAY)
                .useFillPaint(1, true)

                .defaultShapesVisible(2, true)
                .seriesShape(2, 0, shape)
                .seriesPaint(2, 0, Color.RED)
                .seriesFillPaint(2, 0, Color.YELLOW)
                .seriesOutlinePaint(2, 0, Color.GRAY)
                .useOutlinePaint(2, true)

                .defaultShapesVisible(3, true)
                .seriesShape(3, 0, shape)
                .seriesPaint(3, 0, Color.RED)
                .seriesFillPaint(3, 0, Color.YELLOW)
                .seriesOutlinePaint(3, 0, Color.GRAY)
                .useFillPaint(3, true)
                .useOutlinePaint(3, true)

                .defaultShapesVisible(4, true)
                .seriesShape(4, 0, shape)
                .seriesPaint(4, 0, Color.RED)
                .seriesFillPaint(4, 0, Color.YELLOW)
                .seriesOutlinePaint(4, 0, Color.GRAY)
                .useOutlinePaint(4, true)
                .useFillPaint(4, true)
                .drawOutlines(4, false)

                .showLegend(true)
                .addTooltips(true)
                .domainAxisAutoRangeIncludesZero(false)
                .rangeAxisAutoRangeIncludesZero(false);
        chart.show();
    }
}
