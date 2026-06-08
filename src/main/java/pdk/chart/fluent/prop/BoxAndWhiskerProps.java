package pdk.chart.fluent.prop;

import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.labels.BoxAndWhiskerToolTipGenerator;
import pdk.chart.renderer.category.BoxAndWhiskerRenderer;

/**
 * This class is used to set the properties of {@link BoxAndWhiskerRenderer}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:40 AM
 */
public class BoxAndWhiskerProps extends CategoryXYRendererProps {

    private final BoxAndWhiskerRenderer renderer_;

    public BoxAndWhiskerProps(CategoryXYChart chart, BoxAndWhiskerRenderer renderer) {
        super(chart, renderer);
        this.renderer_ = renderer;
    }

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public BoxAndWhiskerProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        }
        return this;
    }

    /**
     * Sets the flag that controls whether the minimum outlier is drawn
     * for each item.
     *
     * @param visible the new flag value. default true.
     */
    public BoxAndWhiskerProps minOutlierVisible(boolean visible) {
        renderer_.setMinOutlierVisible(visible);
        return this;
    }

    /**
     * Sets the flag that controls whether the maximum outlier is drawn
     * for each item.
     *
     * @param visible the new flag value. default true.
     */
    public BoxAndWhiskerProps maxOutlierVisible(boolean visible) {
        renderer_.setMaxOutlierVisible(visible);
        return this;
    }

}
