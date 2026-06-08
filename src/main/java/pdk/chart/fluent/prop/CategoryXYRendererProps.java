package pdk.chart.fluent.prop;

import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.renderer.category.CategoryItemRenderer;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:41 AM
 */
public class CategoryXYRendererProps {

    protected CategoryXYChart chart_;
    protected CategoryItemRenderer renderer_;

    public CategoryXYRendererProps(CategoryXYChart chart, CategoryItemRenderer renderer) {
        this.chart_ = chart;
        this.renderer_ = renderer;
    }

    public CategoryXYChart chart() {
        return this.chart_;
    }

    public CategoryXYChart done() {
        return this.chart_;
    }
}
