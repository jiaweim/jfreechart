package pdk.chart.labels;

/**
 * An enumeration of the positions that a value label can take, relative to an
 * item in a {@link pdk.chart.plot.CategoryPlot}.
 */
public enum ItemLabelAnchor {

    /**
     * Center anchor point.
     */
    CENTER,

    /**
     * 1 o'clock anchor point.
     */
    INSIDE1,

    /**
     * 2 o'clock  anchor point.
     */
    INSIDE2,

    /**
     * 3 o'clock  anchor point.
     */
    INSIDE3,

    /**
     * 4 o'clock  anchor point.
     */
    INSIDE4,

    /**
     * 5 o'clock  anchor point.
     */
    INSIDE5,

    /**
     * 6 o'clock  anchor point.
     */
    INSIDE6,

    /**
     * 7 o'clock  anchor point.
     */
    INSIDE7,

    /**
     * 8 o'clock  anchor point.
     */
    INSIDE8,

    /**
     * 9 o'clock  anchor point.
     */
    INSIDE9,

    /**
     * 10 o'clock  anchor point.
     */
    INSIDE10,

    /**
     * 11 o'clock  anchor point.
     */
    INSIDE11,

    /**
     * 12 o'clock  anchor point.
     */
    INSIDE12,

    /**
     * 1 o'clock  anchor point.
     */
    OUTSIDE1,

    /**
     * 2 o'clock  anchor point.
     */
    OUTSIDE2,

    /**
     * 3 o'clock  anchor point.
     */
    OUTSIDE3,

    /**
     * 4 o'clock  anchor point.
     */
    OUTSIDE4,

    /**
     * 5 o'clock  anchor point.
     */
    OUTSIDE5,

    /**
     * 6 o'clock  anchor point.
     */
    OUTSIDE6,

    /**
     * 7 o'clock  anchor point.
     */
    OUTSIDE7,

    /**
     * 8 o'clock  anchor point.
     */
    OUTSIDE8,

    /**
     * 9 o'clock  anchor point.
     */
    OUTSIDE9,

    /**
     * 10 o'clock  anchor point.
     */
    OUTSIDE10,

    /**
     * 11 o'clock  anchor point.
     */
    OUTSIDE11,

    /**
     * 12 o'clock  anchor point.
     */
    OUTSIDE12;

    /**
     * Returns {@code true} if this anchor point is inside an area.
     *
     * @return {@code true} if this anchor point is inside an area,
     * {@code false} otherwise.
     */
    public boolean isInternal() {
        return this == CENTER
                || this == INSIDE1
                || this == INSIDE2
                || this == INSIDE3
                || this == INSIDE4
                || this == INSIDE5
                || this == INSIDE6
                || this == INSIDE7
                || this == INSIDE8
                || this == INSIDE9
                || this == INSIDE10
                || this == INSIDE11
                || this == INSIDE12;
    }
}
