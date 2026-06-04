package pdk.chart.renderer.category;

import pdk.chart.api.RectangleEdge;

import java.awt.*;
import java.awt.geom.RectangularShape;

/**
 * The interface for plugin painter for the {@link BarRenderer} class.  When
 * developing a class that implements this interface, bear in mind the
 * following:
 * <ul>
 * <li>the {@code equals(Object)} method should be overridden;</li>
 * <li>instances of the class should be immutable OR implement the
 *     {@code PublicCloneable} interface, so that a renderer using the
 *     painter can be cloned reliably;
 * <li>the class should be {@code Serializable}, otherwise chart
 *     serialization will not be supported.</li>
 * </ul>
 */
public interface BarPainter {

    /**
     * Paints a single bar on behalf of a renderer.
     *
     * @param g2       the graphics target.
     * @param renderer the renderer.
     * @param row      the row index for the item.
     * @param column   the column index for the item.
     * @param bar      the bounds for the bar.
     * @param base     the base of the bar.
     */
    void paintBar(Graphics2D g2, BarRenderer renderer,
            int row, int column, RectangularShape bar, RectangleEdge base);

    /**
     * Paints the shadow for a single bar on behalf of a renderer.
     *
     * @param g2        the graphics target.
     * @param renderer  the renderer.
     * @param row       the row index for the item.
     * @param column    the column index for the item.
     * @param bar       the bounds for the bar.
     * @param base      the base of the bar.
     * @param pegShadow peg the shadow to the base of the bar?
     */
    void paintBarShadow(Graphics2D g2, BarRenderer renderer,
            int row, int column, RectangularShape bar, RectangleEdge base,
            boolean pegShadow);

}
