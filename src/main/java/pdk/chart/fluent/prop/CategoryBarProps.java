package pdk.chart.fluent.prop;

import org.jspecify.annotations.Nullable;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.CategorySeriesLabelGenerator;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.renderer.category.BarPainter;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.util.GradientPaintTransformer;

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

    /**
     * Sets the flag that controls whether bar outlines are drawn and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param draw the flag.
     */
    public CategoryBarProps drawBarOutline(boolean draw) {
        renderer_.setDrawBarOutline(draw);
        return this;
    }

    /**
     * Sets the bar painter for this renderer and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param painter the painter ({@code null} not permitted).
     */
    public CategoryBarProps barPainter(BarPainter painter) {
        renderer_.setBarPainter(painter);
        return this;
    }

    /**
     * Sets the legend item tool tip generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * <p>
     * Legend tool tips are text displayed when the mouse hovers over the legend.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public CategoryBarProps legendItemToolTipGenerator(@Nullable CategorySeriesLabelGenerator generator) {
        renderer_.setLegendItemToolTipGenerator(generator);
        return this;
    }

    /**
     * Sets the gradient paint transformer and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param transformer the transformer ({@code null} permitted).
     */
    public CategoryBarProps gradientPaintTransformer(GradientPaintTransformer transformer) {
        renderer_.setGradientPaintTransformer(transformer);
        return this;
    }

    /**
     * Sets the default item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public CategoryBarProps defaultItemLabelGenerator(CategoryItemLabelGenerator generator) {
        renderer_.setDefaultItemLabelGenerator(generator);
        return this;
    }


    /**
     * Sets the default flag that controls whether item labels are visible
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible the flag.
     */
    public CategoryBarProps defaultItemLabelsVisible(boolean visible) {
        renderer_.setDefaultItemLabelsVisible(visible);
        return this;
    }

    /**
     * Sets the default positive item label position.
     *
     * @param position the position.
     */
    public CategoryBarProps defaultPositiveItemLabelPosition(ItemLabelPosition position) {
        renderer_.setDefaultPositiveItemLabelPosition(position);
        return this;
    }


}
