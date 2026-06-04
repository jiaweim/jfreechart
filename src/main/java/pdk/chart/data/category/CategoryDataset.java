package pdk.chart.data.category;

import pdk.chart.data.KeyedValues2D;
import pdk.chart.data.general.Dataset;

/**
 * The interface for a dataset with one or more series, and values associated
 * with categories.
 * <p>
 * The categories are represented by {@code Comparable} instance, with the
 * category label being provided by the {@code toString()} method.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface CategoryDataset<R extends Comparable<R>, C extends Comparable<C>>
        extends KeyedValues2D<R, C>, Dataset {

    // no additional methods required

}
