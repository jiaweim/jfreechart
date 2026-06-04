package pdk.chart.urls;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.general.PieDataset;

/**
 * Interface for a URL generator for plots that use data from a
 * {@link PieDataset}.  Classes that implement this interface:
 * <ul>
 * <li>are responsible for correctly escaping any text that is derived from the
 *     dataset, as this may be user-specified and could pose a security
 *     risk;</li>
 * <li>should be either (a) immutable, or (b) cloneable via the
 *     {@link PublicCloneable} interface. This provides a mechanism for the referring plot to clone
 *     the generator if necessary.</li>
 * </ul>
 *
 * @param <K> the dataset key type
 */
public interface PieURLGenerator<K extends Comparable<K>> {

    /**
     * Generates a URL for one item in a {@link PieDataset}. As a guideline,
     * the URL should be valid within the context of an XHTML 1.0 document.
     *
     * @param dataset  the dataset ({@code null} not permitted).
     * @param key      the item key ({@code null} not permitted).
     * @param pieIndex the pie index (differentiates between pies in a
     *                 'multi' pie chart).
     * @return A string containing the URL.
     */
    String generateURL(PieDataset<K> dataset, K key, int pieIndex);

}
