package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.CategoryDataset;

/**
 * A <i>category tool tip generator</i> is an object that can be assigned to a
 * {@link pdk.chart.renderer.category.CategoryItemRenderer} and that
 * assumes responsibility for creating text items to be used as tooltips for the
 * items in a {@link pdk.chart.plot.CategoryPlot}.
 * <p>
 * To assist with cloning charts, classes that implement this interface should
 * also implement the {@link PublicCloneable} interface.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface CategoryToolTipGenerator<R extends Comparable<R>, C extends Comparable<C>> {

    /**
     * Generates the tool tip text for an item in a dataset.  Note: in the
     * current dataset implementation, each row is a series, and each column
     * contains values for a particular category.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param row     the row index (zero-based).
     * @param column  the column index (zero-based).
     * @return The tooltip text (possibly {@code null}).
     */
    String generateToolTip(CategoryDataset<R, C> dataset, int row, int column);

}
