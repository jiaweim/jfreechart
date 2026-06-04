package pdk.chart.labels;

import pdk.chart.data.category.CategoryDataset;

/**
 * A <i>category item label generator</i> is an object that can be assigned to a
 * {@link pdk.chart.renderer.category.CategoryItemRenderer} and that
 * assumes responsibility for creating text items to be used as labels for the
 * items in a {@link pdk.chart.plot.CategoryPlot}.
 * <p>
 * To assist with cloning charts, classes that implement this interface should
 * also implement the {@link pdk.chart.api.PublicCloneable} interface.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface CategoryItemLabelGenerator<R extends Comparable<R>, C extends Comparable<C>> {

    /**
     * Generates a label for the specified row.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param row     the row index (zero-based).
     * @return The label.
     */
    String generateRowLabel(CategoryDataset<R, C> dataset, int row);

    /**
     * Generates a label for the specified row.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param column  the column index (zero-based).
     * @return The label.
     */
    String generateColumnLabel(CategoryDataset<R, C> dataset, int column);

    /**
     * Generates a label for the specified item. The label is typically a
     * formatted version of the data value, but any text can be used.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param row     the row index (zero-based).
     * @param column  the column index (zero-based).
     * @return The label (possibly {@code null}).
     */
    String generateLabel(CategoryDataset<R, C> dataset, int row, int column);

}
