package pdk.chart.examples;

import pdk.chart.LineChart;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import java.awt.*;

public class ScatterPlotDemo1 {

    static void main() {
        SampleXYDataset2 dataset = new SampleXYDataset2();
        LineChart chart = new LineChart();
        chart.dataset(dataset)
                .noDataMessage("NO DATA")
                .title("Scatter Plot Demo 1")
                .orientation(PlotOrientation.VERTICAL)
                .showLegend(true)
                .addTooltips(true)
                .domainAxisName("X")
                .rangeAxisName("Y")
                .domainPannable(true)
                .rangePannable(true)
                .domainZeroBaselineVisible(true)
                .rangeZeroBaselineVisible(true)

                .domainGridlinesStroke(new BasicStroke(0.0F))
                .domainGridlinePaint(Color.BLUE)
                .domainMinorGridlineStroke(new BasicStroke(0.0F))
                .domainMinorGridlinePaint(Color.GRAY)
                .domainMinorGridlinesVisible(true)

                .rangeGridlinesStroke(new BasicStroke(0.0F))
                .rangeGridlinePaint(Color.BLUE)
                .rangeMinorGridlineStroke(new BasicStroke(0.0F))
                .rangeMinorGridlinePaint(Color.GRAY)
                .rangeMinorGridlinesVisible(true)

                .seriesOutlinePaint(0, Color.BLACK)
                .useOutlinePaint(true)
                .defaultShapesVisible(true)
                .defaultLinesVisible(false)

                .domainAxisAutoRangeIncludesZero(false)
                .domainAxisTickMarkInsideLength(2.0f)
                .domainAxisTickMarkOutsideLength(2.0f)
                .domainAxisMinorTickCount(2)
                .domainAxisMinorTickMarksVisible(true)
                .rangeAxisTickMarkInsideLength(2.0f)
                .rangeAxisTickMarkOutsideLength(2.0f)
                .rangeAxisMinorTickCount(2)
                .rangeAxisMinorTickMarksVisible(true)
        ;

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseWheelEnabled(true);
        ApplicationFrame frame = new ApplicationFrame("ScatterPlotDemo1.java");
        frame.setContentPane(chartPanel);
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}
