package pdk.chart.examples;

import pdk.chart.LineChart;
import pdk.chart.XYChartType;
import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 12:40 PM
 */
public class NormalDistributionDemo {
    static void main() {
        NormalDistributionFunction2D func = new NormalDistributionFunction2D(20.6, 1.62);
        XYSeries<String> lineSeries = func.sampleSeries(20.6 - 10, 20.6 + 10, 500, "Line");
        XYSeries<String> areaSeries = func.sampleSeries(10.6, 18, 100, "Area");

        XYSeriesCollection<String> dataset1 = new XYSeriesCollection<>(lineSeries);
        XYSeriesCollection<String> dataset2 = new XYSeriesCollection<>(areaSeries);

        LineChart chart = new LineChart();
        chart.axisNames("X", "Probability Density")
                .addDataset(0, dataset1, XYChartType.LINE)
                .addDataset(1, dataset2, XYChartType.AREA)
                .showLegend(false);
        chart.show();
    }
}
