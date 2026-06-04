package pdk.chart.examples;

import pdk.chart.LineChart;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 1:42 PM
 */
public class XYLineAndShapeRendererDemo1 {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        dataset.addSeries(new XYSeries<>("Series 1", new double[]{1, 2, 3}, new double[]{3.3, 4.4, 1.7}));
        dataset.addSeries(new XYSeries<>("Series 2", new double[]{1, 2, 3, 4}, new double[]{7.3, 6.8, 9.6, 5.6}));
        LineChart chart = new LineChart();
        chart.dataset(dataset)
                .domainAxisName("X")
                .rangeAxisName("Y")
                .title("XYLineAndShapeRenderer Demo 1")
                .addTooltips(true)
                .showLegend(true)
                .seriesLinesVisible(0, true)
                .seriesShapesVisible(0, false)
                .seriesLinesVisible(1, false)
                .seriesShapesVisible(1, true);
        chart.show();
    }
}
