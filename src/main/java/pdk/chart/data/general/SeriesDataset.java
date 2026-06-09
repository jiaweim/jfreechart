package pdk.chart.data.general;

import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.IntervalXYZDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYZDataset;

/**
 * The interface for a dataset consisting of one or many series of data.
 *
 * @param <S> The type for the series keys.
 * @see CategoryDataset
 * @see IntervalXYDataset
 * @see IntervalXYZDataset
 * @see XYDataset
 * @see XYZDataset
 */
public interface SeriesDataset<S extends Comparable<S>> extends Dataset {

    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    int getSeriesCount();

    /**
     * Returns the key for a series.
     *
     * @param series the series index (in the range {@code 0} to
     *               {@code getSeriesCount() - 1}).
     * @return The key for the series.
     */
    S getSeriesKey(int series);

    /**
     * Returns the index of the series with the specified key, or -1 if there
     * is no such series in the dataset.
     *
     * @param seriesKey the series key ({@code null} permitted).
     * @return The index, or -1.
     */
    int indexOf(S seriesKey);

}
