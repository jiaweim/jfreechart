package pdk.chart.text;

/**
 * Used to indicate the position of an anchor point for a text string.  This is
 * frequently used to align a string to a fixed point in some coordinate space.
 */
public enum TextAnchor {

    /**
     * Top/left.
     */
    TOP_LEFT,

    /**
     * Top/center.
     */
    TOP_CENTER,

    /**
     * Top/right.
     */
    TOP_RIGHT,

    /**
     * Half-ascent/left.
     */
    HALF_ASCENT_LEFT,

    /**
     * Half-ascent/center.
     */
    HALF_ASCENT_CENTER,

    /**
     * Half-ascent/right.
     */
    HALF_ASCENT_RIGHT,

    /**
     * Middle/left.
     */
    CENTER_LEFT,

    /**
     * Middle/center.
     */
    CENTER,

    /**
     * Middle/right.
     */
    CENTER_RIGHT,

    /**
     * Baseline/left.
     */
    BASELINE_LEFT,

    /**
     * Baseline/center.
     */
    BASELINE_CENTER,

    /**
     * Baseline/right.
     */
    BASELINE_RIGHT,

    /**
     * Bottom/left.
     */
    BOTTOM_LEFT,

    /**
     * Bottom/center.
     */
    BOTTOM_CENTER,

    /**
     * Bottom/right.
     */
    BOTTOM_RIGHT;

    /**
     * Returns {@code true} if the anchor is a left-side anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isLeft() {
        return this == BASELINE_LEFT || this == BOTTOM_LEFT
                || this == CENTER_LEFT || this == HALF_ASCENT_LEFT
                || this == TOP_LEFT;
    }

    /**
     * Returns {@code true} if the anchor is a right-side anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isRight() {
        return this == BASELINE_RIGHT || this == BOTTOM_RIGHT
                || this == CENTER_RIGHT || this == HALF_ASCENT_RIGHT
                || this == TOP_RIGHT;
    }

    /**
     * Returns {@code true} if the anchor is a center anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isHorizontalCenter() {
        return this == BASELINE_CENTER || this == BOTTOM_CENTER
                || this == CENTER || this == HALF_ASCENT_CENTER
                || this == TOP_CENTER;
    }

    /**
     * Returns {@code true} if the anchor is a top anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isTop() {
        return this == TOP_LEFT || this == TOP_CENTER || this == TOP_RIGHT;
    }

    /**
     * Returns {@code true} if the anchor is a bottom anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isBottom() {
        return this == BOTTOM_LEFT || this == BOTTOM_CENTER
                || this == BOTTOM_RIGHT;
    }

    /**
     * Returns {@code true} if the anchor is a baseline anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isBaseline() {
        return this == BASELINE_LEFT || this == BASELINE_CENTER
                || this == BASELINE_RIGHT;
    }

    /**
     * Returns {@code true} if the anchor is a half-ascent anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isHalfAscent() {
        return this == HALF_ASCENT_LEFT || this == HALF_ASCENT_CENTER
                || this == HALF_ASCENT_RIGHT;
    }

    /**
     * Returns {@code true} if the anchor is a half-ascent anchor, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isVerticalCenter() {
        return this == CENTER_LEFT || this == CENTER || this == CENTER_RIGHT;
    }

}
