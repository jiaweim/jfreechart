package pdk.chart.fluent;

import pdk.chart.renderer.xy.*;

import java.awt.geom.Ellipse2D;

/**
 * XYChart types.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 12:45 PM
 */
public enum XYChartType {

    AREA,
    LINE,
    SPLINE,
    SCATTER,
    HISTOGRAM,
    BAR,
    PEAK;

    /**
     * Create {@link XYItemRenderer} for the given chart type.
     *
     * @return {@link XYItemRenderer}.
     */
    public XYItemRenderer getRenderer() {
        if (this == XYChartType.LINE) {
            return new XYLineAndShapeRenderer(true, false);
        } else if (this == XYChartType.SPLINE) {
            return new XYSplineRenderer();
        } else if (this == XYChartType.AREA) {
            return new XYAreaRenderer(XYAreaRenderer.AREA);
        } else if (this == XYChartType.SCATTER) {
            return new XYLineAndShapeRenderer(false, true);
        } else if (this == XYChartType.BAR || this == XYChartType.HISTOGRAM) {
            XYBarRenderer renderer = new XYBarRenderer();
            renderer.setShadowVisible(false);
            return renderer;
        } else if (this == XYChartType.PEAK) {
            YIntervalRenderer renderer = new YIntervalRenderer();
            Ellipse2D.Double shape = new Ellipse2D.Double(-0.5, 0.5, 1, 1);
            renderer.setSeriesShape(0, shape);
            renderer.setSeriesShape(1, shape);
            return renderer;
        } else {
            throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
