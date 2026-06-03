package org.jfree.chart.examples;

import org.jfree.chart.LineChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 2:56 PM
 */
public class XYSeriesDemo2 {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        XYSeries<String> series = new XYSeries<>("Flat Data");
        series.add(
                new double[]{1.0, 5.0, 4.0, 12.5, 17.3, 21.2, 21.9, 25.6, 30.0},
                new double[]{100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0}
        );
        dataset.addSeries(series);
        LineChart chart = new LineChart();
        chart.dataset(dataset)
                .title("XY Series Demo")
                .domainAxisName("X")
                .rangeAxisName("Y")
                .rangeAxisAutoRangeIncludesZero(false)
                .showLegend(true)
                .addTooltips(true);
        chart.show();
    }
}
