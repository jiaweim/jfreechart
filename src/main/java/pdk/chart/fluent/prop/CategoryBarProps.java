package pdk.chart.fluent.prop;

import org.jspecify.annotations.Nullable;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.renderer.category.BarRenderer;

import java.awt.*;

/**
 * This class is used to set the properties of BarRenderer.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 11:29 AM
 */
public class CategoryBarProps extends CategoryXYRendererProps {

    private final BarRenderer renderer_;

    public CategoryBarProps(CategoryXYChart chart, BarRenderer renderer) {
        super(chart, renderer);
        this.renderer_ = renderer;
    }

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public CategoryBarProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        return this;
    }

    /**
     * Set the margin between items (bars) within a category.
     *
     * @param itemMargin a percentage of the available space for all bars.
     * @return this
     */
    public CategoryBarProps itemMargin(double itemMargin) {
        renderer_.setItemMargin(itemMargin);
        return this;
    }

    /**
     * Sets the paint used for a series.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public CategoryBarProps seriesPaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesPaint(series, paint, false);
        return this;
    }
}
