package pdk.chart.data.xy;

/**
 * The interface through which JFreeChart obtains data in the form of (x, y, z)
 * items - used for XY and XYZ plots.
 *
 * @param <S> the series key type.
 */
public interface XYZDataset<S extends Comparable<S>> extends XYDataset<S> {

    /**
     * Returns the z-value for the specified series and item.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The z-value (possibly {@code null}).
     */
    Number getZ(int series, int item);

    /**
     * Returns the z-value (as a double primitive) for an item within a series.
     *
     * @param series the series (zero-based index).
     * @param item   the item (zero-based index).
     * @return The z-value.
     */
    double getZValue(int series, int item);

}
