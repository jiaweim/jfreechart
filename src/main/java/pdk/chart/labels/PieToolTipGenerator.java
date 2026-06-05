package pdk.chart.labels;

import pdk.chart.data.general.PieDataset;

/**
 * A tool tip generator that is used by the
 * {@link pdk.chart.plot.pie.PiePlot} class.
 *
 * @param <K> the dataset key type.
 */
public interface PieToolTipGenerator<K extends Comparable<K>> {

    /**
     * Generates a tool tip text item for the specified item in the dataset.
     * This method can return {@code null} to indicate that no tool tip
     * should be displayed for an item.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param key     the section key ({@code null} not permitted).
     * @return The tool tip text (possibly {@code null}).
     */
    String generateToolTip(PieDataset<K> dataset, K key);

}
