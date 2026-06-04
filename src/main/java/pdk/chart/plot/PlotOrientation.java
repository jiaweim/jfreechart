package pdk.chart.plot;

/**
 * Used to indicate the orientation (horizontal or vertical) of a 2D plot.
 * It is the direction of the y-axis that is the determinant (a conventional
 * plot has a vertical y-axis).
 */
public enum PlotOrientation {

    /**
     * For a plot where the range axis is horizontal.
     */
    HORIZONTAL,

    /**
     * For a plot where the range axis is vertical.
     */
    VERTICAL;

    /**
     * Returns {@code true} if this orientation is {@code HORIZONTAL},
     * and {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isHorizontal() {
        return this.equals(PlotOrientation.HORIZONTAL);
    }

    /**
     * Returns {@code true} if this orientation is {@code VERTICAL},
     * and {@code false} otherwise.
     *
     * @return A boolean.
     */
    public boolean isVertical() {
        return this.equals(PlotOrientation.VERTICAL);
    }

}
