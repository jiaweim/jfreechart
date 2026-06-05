package pdk.chart.examples;

import pdk.chart.LineChartCategory;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.PlotOrientation;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 11:08 AM
 */
public class LineChartDemo1 {
    static void main() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addSeries("Classes",
                new String[]{"JDK 1.0", "JDK 1.1", "JDK 1.2", "JDK 1.3", "JDK 1.4",
                        "JDK 1.5", "JDK 1.6", "JDK 1.7", "JDK 8", "JDK 9",
                        "JDK 10", "JDK 11", "JDK 12", "JDK 13", "JDK 14"},
                new double[]{212.0, 504.0, 1520.0, 1842.0, 2991.0,
                        3500.0, 3793.0, 4024.0, 4240.0, 6005.0,
                        6002.0, 4411.0, 4433.0, 4545.0, 4569.0});
        LineChartCategory chart = new LineChartCategory();
        chart.dataset(dataset)
                .title("Java Standard Class Library")
                .domainAxisName(null)
                .rangeAxisName("Class Count")
                .categoryLabelPositions(CategoryLabelPositions.UP_90)
                .showLegend(false)
                .addTooltips(true)
                .orientation(PlotOrientation.VERTICAL);
        chart.show();
    }
}
