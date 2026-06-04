package pdk.chart.util;

import java.awt.*;

/**
 * The interface for a class that can transform a {@code GradientPaint} to
 * fit an arbitrary shape.
 */
public interface GradientPaintTransformer {

    /**
     * Transforms a {@code GradientPaint} instance to fit some target
     * shape.  Classes that implement this method typically return a new
     * instance of {@code GradientPaint}.
     *
     * @param paint  the original paint (not {@code null}).
     * @param target the reference area (not {@code null}).
     * @return A transformed paint.
     */
    GradientPaint transform(GradientPaint paint, Shape target);

}

