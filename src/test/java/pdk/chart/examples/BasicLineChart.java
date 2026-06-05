package pdk.chart.examples;

import pdk.chart.LineChartCategory;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.internal.ShapeUtils;

import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=line-simple
 * <p>
 * 不是完全实现，无法避免直线从圆中穿过。
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 11:05 AM
 */
public class BasicLineChart {

    static void main() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("Category",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{150, 230, 224, 218, 135, 147, 260});
        LineChartCategory chart = new LineChartCategory();
        chart.dataset(dataset)
                .seriesShape(0, ShapeUtils.createCircle(6))
                .seriesShapesFilled(0, false)
                .seriesLinesWidth(0, 2F)
                .seriesOutlineStroke(0, new BasicStroke(2F))
                .showLegend(true);
        chart.show();
    }
}
