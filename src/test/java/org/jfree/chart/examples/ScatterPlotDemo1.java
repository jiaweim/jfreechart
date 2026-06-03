package org.jfree.chart.examples;

import org.jfree.chart.Chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 8:59 AM
 */
public class ScatterPlotDemo1 extends ApplicationFrame {

    public ScatterPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();

        plot.setNoDataMessage("NO DATA");
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainGridlineStroke(new BasicStroke(0.0F));
        plot.setDomainMinorGridlineStroke(new BasicStroke(0.0F));
        plot.setDomainGridlinePaint(Color.BLUE);
        plot.setRangeGridlineStroke(new BasicStroke(0.0F));
        plot.setRangeMinorGridlineStroke(new BasicStroke(0.0F));
        plot.setRangeGridlinePaint(Color.BLUE);
        plot.setDomainMinorGridlinesVisible(true);
        plot.setRangeMinorGridlinesVisible(true);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setUseOutlinePaint(true);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setTickMarkInsideLength(2.0F);
        domainAxis.setTickMarkOutsideLength(2.0F);
        domainAxis.setMinorTickCount(2);
        domainAxis.setMinorTickMarksVisible(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickMarkInsideLength(2.0F);
        rangeAxis.setTickMarkOutsideLength(2.0F);
        rangeAxis.setMinorTickCount(2);
        rangeAxis.setMinorTickMarksVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(new SampleXYDataset2());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    static void main(String[] args) {
        ScatterPlotDemo1 demo = new ScatterPlotDemo1("JFreeChart: ScatterPlotDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
