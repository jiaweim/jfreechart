package pdk.chart.examples;

import pdk.chart.HistogramChart;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.plot.PlotOrientation;

import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:52 AM
 */
public class HistogramGaussian {
    static void main() {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = new double[1000];
        Random random = new Random(12345678L);
        for (int i = 0; i < 1000; i++) {
            values[i] = random.nextGaussian() + 5;
        }
        dataset.addSeries("H1", values, 100, 2.0, 8.0);

        values = new double[1000];
        for (int i = 0; i < 1000; i++) {
            values[i] = random.nextGaussian() + 7;
        }
        dataset.addSeries("H2", values, 100, 4.0, 10.0);

        HistogramChart chart = new HistogramChart();
        chart.dataset(dataset)
                .title("Histogram Demo")
                .orientation(PlotOrientation.VERTICAL)
                .showLegend(true)
                .addTooltips(true)
                .drawBarOutline(false);
        chart.show();
    }
}
