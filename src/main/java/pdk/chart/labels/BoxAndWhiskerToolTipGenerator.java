package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.NumberFormat;

/**
 * An item label generator for plots that use data from a
 * {@link BoxAndWhiskerCategoryDataset}.
 * <p>
 * The tooltip text and item label text are composed using a
 * {@link java.text.MessageFormat} object, that can aggregate some or all of
 * the following string values into a message.
 * <ul>
 * <li>0 : Series Name</li>
 * <li>1 : X (value or date)</li>
 * <li>2 : Mean</li>
 * <li>3 : Median</li>
 * <li>4 : Minimum</li>
 * <li>5 : Maximum</li>
 * <li>6 : Quartile 1</li>
 * <li>7 : Quartile 3</li>
 * </ul>
 */
public class BoxAndWhiskerToolTipGenerator
        extends StandardCategoryToolTipGenerator
        implements CategoryToolTipGenerator, Cloneable, PublicCloneable,
        Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -6076837753823076334L;

    /**
     * The default tooltip format string.
     */
    public static final String DEFAULT_TOOL_TIP_FORMAT
            = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";

    /**
     * Creates a default tool tip generator.
     */
    public BoxAndWhiskerToolTipGenerator() {
        super(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getInstance());
    }

    /**
     * Creates a tool tip formatter.
     *
     * @param format    the tool tip format string.
     * @param formatter the formatter.
     */
    public BoxAndWhiskerToolTipGenerator(String format,
            NumberFormat formatter) {
        super(format, formatter);
    }

    /**
     * Creates the array of items that can be passed to the
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series (zero-based index).
     * @param item    the item (zero-based index).
     * @return The items (never {@code null}).
     */
    @Override
    protected Object[] createItemArray(CategoryDataset dataset, int series,
            int item) {
        Object[] result = new Object[8];
        result[0] = dataset.getRowKey(series);
        Number y = dataset.getValue(series, item);
        NumberFormat formatter = getNumberFormat();
        result[1] = formatter.format(y);
        if (dataset instanceof BoxAndWhiskerCategoryDataset) {
            BoxAndWhiskerCategoryDataset d
                    = (BoxAndWhiskerCategoryDataset) dataset;
            result[2] = formatter.format(d.getMeanValue(series, item));
            result[3] = formatter.format(d.getMedianValue(series, item));
            result[4] = formatter.format(d.getMinRegularValue(series, item));
            result[5] = formatter.format(d.getMaxRegularValue(series, item));
            result[6] = formatter.format(d.getQ1Value(series, item));
            result[7] = formatter.format(d.getQ3Value(series, item));
        }
        return result;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj the other object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BoxAndWhiskerToolTipGenerator) {
            return super.equals(obj);
        }
        return false;
    }

}
