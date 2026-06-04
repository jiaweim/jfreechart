package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;

import java.io.Serializable;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 10:41 AM
 */
public class ScatterChart extends Chart implements Serializable {

    public ScatterChart(String title) {
        super(title, new XYPlot<>(null,
                new NumberAxis(""),
                new NumberAxis(""),
                new XYLineAndShapeRenderer(false, true)));
    }


}
