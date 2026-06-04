package pdk.chart.api;

import java.awt.geom.Rectangle2D;

/**
 * Used to indicate the edge of a rectangle.
 */
public enum RectangleEdge {

    /**
     * Top.
     */
    TOP,

    /**
     * Bottom.
     */
    BOTTOM,

    /**
     * Left.
     */
    LEFT,

    /**
     * Right.
     */
    RIGHT;

    /**
     * Returns {@code true} if the edge is {@code TOP} or
     * {@code BOTTOM}, and {@code false} otherwise.
     *
     * @param edge the edge.
     * @return A boolean.
     */
    public static boolean isTopOrBottom(RectangleEdge edge) {
        return (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM);
    }

    /**
     * Returns {@code true} if the edge is {@code LEFT} or
     * {@code RIGHT}, and {@code false} otherwise.
     *
     * @param edge the edge.
     * @return A boolean.
     */
    public static boolean isLeftOrRight(RectangleEdge edge) {
        return (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT);
    }

    /**
     * Returns the opposite edge.
     *
     * @param edge an edge.
     * @return The opposite edge.
     */
    public static RectangleEdge opposite(RectangleEdge edge) {
        return switch (edge) {
            case TOP -> RectangleEdge.BOTTOM;
            case BOTTOM -> RectangleEdge.TOP;
            case LEFT -> RectangleEdge.RIGHT;
            case RIGHT -> RectangleEdge.LEFT;
        };
    }

    /**
     * Returns the x or y coordinate of the specified edge.
     *
     * @param rectangle the rectangle.
     * @param edge      the edge.
     * @return The coordinate.
     */
    public static double coordinate(Rectangle2D rectangle, RectangleEdge edge) {
        return switch (edge) {
            case TOP -> rectangle.getMinY();
            case BOTTOM -> rectangle.getMaxY();
            case LEFT -> rectangle.getMinX();
            case RIGHT -> rectangle.getMaxX();
        };
    }

}
