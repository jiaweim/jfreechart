package pdk.chart.data;

/**
 * An interface through which (single-dimension) data values can be accessed.
 */
public interface Values {

    /**
     * Returns the number of items (values) in the collection.
     *
     * @return The item count (possibly zero).
     */
    int getItemCount();

    /**
     * Returns the value with the specified index.
     *
     * @param index the item index (in the range {@code 0} to
     *              {@code getItemCount() -1}).
     * @return The value (possibly {@code null}).
     * @throws IndexOutOfBoundsException if {@code index} is not in the
     *                                   specified range.
     */
    Number getValue(int index);

}
