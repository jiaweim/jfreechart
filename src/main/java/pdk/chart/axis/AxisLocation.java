package pdk.chart.axis;

import java.util.Objects;

/**
 * Used to indicate the location of an axis on a 2D plot, prior to knowing the
 * orientation of the plot.
 */
public enum AxisLocation {

    /**
     * Axis at the top or left.
     */
    TOP_OR_LEFT,

    /**
     * Axis at the top or right.
     */
    TOP_OR_RIGHT,

    /**
     * Axis at the bottom or left.
     */
    BOTTOM_OR_LEFT,

    /**
     * Axis at the bottom or right.
     */
    BOTTOM_OR_RIGHT;

    /**
     * Returns the location that is opposite to this location.
     *
     * @return The opposite location.
     */
    public AxisLocation getOpposite() {
        return getOpposite(this);
    }

    /**
     * Returns the location that is opposite to the supplied location.
     *
     * @param location the location ({@code null} not permitted).
     * @return The opposite location.
     */
    public static AxisLocation getOpposite(AxisLocation location) {
        Objects.requireNonNull(location, "location");
        return switch (location) {
            case TOP_OR_LEFT -> AxisLocation.BOTTOM_OR_RIGHT;
            case TOP_OR_RIGHT -> AxisLocation.BOTTOM_OR_LEFT;
            case BOTTOM_OR_LEFT -> AxisLocation.TOP_OR_RIGHT;
            case BOTTOM_OR_RIGHT -> AxisLocation.TOP_OR_LEFT;
        };
    }

}
