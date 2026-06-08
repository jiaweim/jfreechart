package pdk.chart.fluent.prop;

import pdk.chart.event.RendererChangeEvent;
import pdk.chart.fluent.XYChart;
import pdk.chart.labels.XYItemLabelGenerator;
import pdk.chart.renderer.xy.YIntervalRenderer;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 7:02 PM
 */
public class YIntervalProps extends XYRendererProps {

    private final YIntervalRenderer renderer_;

    public YIntervalProps(XYChart chart, YIntervalRenderer renderer) {
        super(chart, renderer);
        this.renderer_ = renderer;
    }

    /**
     * Sets the paint used for a series fill and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public YIntervalProps seriesFillPaint(int series, Paint paint) {
        renderer_.setSeriesFillPaint(series, paint, false);
        return this;
    }

    /**
     * Sets the base flag that controls whether item labels are visible,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible the flag.
     */
    public YIntervalProps defaultItemLabelsVisible(boolean visible) {
        renderer_.setDefaultItemLabelsVisible(visible, false);
        return this;
    }

    /**
     * Sets the default item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public YIntervalProps setDefaultItemLabelGenerator(XYItemLabelGenerator generator) {
        renderer_.setDefaultItemLabelGenerator(generator);
        return this;
    }
}

