package pdk.chart.fluent.prop;

import pdk.chart.event.RendererChangeEvent;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.AreaRenderer;

/**
 * This class is used to set the properties of {@link AreaRenderer}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:55 AM
 */
public class CategoryAreaProps extends CategoryXYRendererProps {

    private final AreaRenderer renderer_;

    public CategoryAreaProps(CategoryXYChart chart, AreaRenderer renderer) {
        super(chart, renderer);
        this.renderer_ = renderer;
    }

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public CategoryAreaProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        return this;
    }

    /**
     * Sets a token that controls how the renderer draws the end points, and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param type the end type ({@code null} not permitted).
     */
    public CategoryAreaProps endType(AreaRendererEndType type) {
        renderer_.setEndType(type);
        return this;
    }

}
