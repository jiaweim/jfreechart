package pdk.chart.fluent.prop;

import pdk.chart.fluent.XYChart;
import pdk.chart.renderer.xy.XYItemRenderer;

/**
 * A class used to set the renderer properties of XYChart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 9:37 AM
 */
public class XYRendererProps {

    protected XYChart chart_;
    protected XYItemRenderer renderer_;

    public XYRendererProps(XYChart chart, XYItemRenderer renderer) {
        this.chart_ = chart;
        this.renderer_ = renderer;
    }

    /**
     * Return the XYChart that holds this renderer.
     *
     * @return {@link XYChart}.
     */
    public XYChart chart() {
        return this.chart_;
    }

    /**
     * Return the XYChart that holds this renderer.
     *
     * @return {@link XYChart}.
     */
    public XYChart done() {
        return this.chart_;
    }
}
