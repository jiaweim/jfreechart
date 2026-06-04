package pdk.chart.renderer;

import pdk.chart.renderer.xy.XYBlockRenderer;

import java.awt.*;

/**
 * A source for {@code Paint} instances, used by the
 * {@link XYBlockRenderer}.
 * <br><br>
 * NOTE: Classes that implement this interface should also implement
 * {@code PublicCloneable} and {@code Serializable}, so
 * that any renderer (or other object instance) that references an instance of
 * this interface can still be cloned or serialized.
 */
public interface PaintScale {

    /**
     * Returns the lower bound for the scale.
     *
     * @return The lower bound.
     * @see #getUpperBound()
     */
    double getLowerBound();

    /**
     * Returns the upper bound for the scale.
     *
     * @return The upper bound.
     * @see #getLowerBound()
     */
    double getUpperBound();

    /**
     * Returns a {@code Paint} instance for the specified value.
     *
     * @param value the value.
     * @return A {@code Paint} instance (never {@code null}).
     */
    Paint getPaint(double value);

}
