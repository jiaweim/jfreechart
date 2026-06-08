package pdk.chart.fluent.prop;

import pdk.chart.fluent.XYChart;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.renderer.xy.XYBarRenderer;

/**
 * A class for configuring properties of {@link pdk.chart.renderer.xy.XYBarRenderer}, designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 9:31 AM
 */
public class XYBarProps extends XYRendererProps {

    private final XYBarRenderer renderer_;

    public XYBarProps(XYChart chart, XYBarRenderer renderer) {
        super(chart, renderer);
        renderer_ = renderer;
    }

    /**
     * Configure chart to generate tool tips.
     *
     * @param addTooltip true if generate tool tips.
     * @return this.
     */
    public XYBarProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        return this;
    }

    /**
     * Sets whether bar outlines are drawn.
     *
     * @param draw the flag.
     */
    public XYBarProps drawBarOutline(boolean draw) {
        renderer_.setDrawBarOutline(draw);
        return this;
    }
}
