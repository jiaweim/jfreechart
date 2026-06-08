package pdk.chart.examples;

import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.Crosshair;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 10:27 AM
 */
public class CrosshairOverlayDemo2 {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        for (int i = 0; i < 4; ++i) {
            XYSeries<String> series = new XYSeries<>("S" + i);
            for (int x = 0; x < 10; ++x) {
                series.add(x, x + Math.random() * 4.0);
            }
            dataset.addSeries(series);
        }

        XYChart chart = XYChart.create()
                .title("CrosshairOverlayDemo2")
                .dataset(dataset, XYChartType.LINE)
                .axisNames("X", "Y");

        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        Crosshair crosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5f));
        crosshair.setVisible(true);
        crosshairOverlay.addDomainCrosshair(crosshair);

        for (int i = 0; i < 4; i++) {
            Crosshair rangeCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5f));
            rangeCrosshair.setVisible(true);
            if (i % 2 == 0) {
                rangeCrosshair.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
            }
            crosshairOverlay.addRangeCrosshair(rangeCrosshair);
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.addOverlay(crosshairOverlay);
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {}

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
                Rectangle2D dataArea = chartPanel.getScreenDataArea();
                XYPlot xyPlot = chart.getXYPlot();
                ValueAxis xAxis = xyPlot.getDomainAxis();
                double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
                crosshairOverlay.getDomainCrosshairs().getFirst().setValue(x);

                for (int i = 0; i < 4; i++) {
                    double y = DatasetUtils.findYValue(xyPlot.getDataset(), i, x);
                    crosshairOverlay.getRangeCrosshairs().get(i).setValue(y);
                }
            }
        });
        ApplicationFrame frame = new ApplicationFrame("");
        frame.setContentPane(chartPanel);
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}
