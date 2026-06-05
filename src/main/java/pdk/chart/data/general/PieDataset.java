package pdk.chart.data.general;

import pdk.chart.data.KeyedValues;

/**
 * A general purpose dataset where values are associated with keys.  As the
 * name suggests, you can use this dataset to supply data for pie charts (refer
 * to the {@link pdk.chart.plot.pie.PiePlot} class).
 *
 * @param <K> Key type for KeyedValues
 */
public interface PieDataset<K extends Comparable<K>> extends KeyedValues<K>,
        Dataset {
    // no new methods added.
}
