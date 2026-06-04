package pdk.chart.examples;

import pdk.chart.LineChart;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 12:53 PM
 */
public class ScatterPlotDemo3 {
    static void main() {
        LineChart chart = new LineChart();
        chart.dataset(new SampleXYDataset2())
                .title("Scatter Plot Demo 3")
                .showLegend(true)
                .addTooltips(true)
                .defaultLinesVisible(false)
                .defaultShapesVisible(true)
                .domainAxisName("X")
                .rangeAxisName("Y")
                .domainAxisAutoRangeIncludesZero(false);
        chart.show();
    }
}
