package pdk.chart.block;

import pdk.chart.api.PublicCloneable;
import pdk.chart.api.RectangleInsets;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A block frame is a type of border that can be drawn around the outside of
 * any {@link AbstractBlock}.  Classes that implement this interface should
 * implement {@link PublicCloneable} OR be immutable.
 */
public interface BlockFrame {

    /**
     * Returns the space reserved for the border.
     *
     * @return The space (never {@code null}).
     */
    RectangleInsets getInsets();

    /**
     * Draws the border by filling in the reserved space (in black).
     *
     * @param g2   the graphics device.
     * @param area the area.
     */
    void draw(Graphics2D g2, Rectangle2D area);

}
