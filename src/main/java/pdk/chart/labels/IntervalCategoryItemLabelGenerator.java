package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.IntervalCategoryDataset;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

/**
 * A label generator for plots that use data from an
 * {@link IntervalCategoryDataset}.
 */
public class IntervalCategoryItemLabelGenerator
        extends StandardCategoryItemLabelGenerator
        implements CategoryItemLabelGenerator, PublicCloneable, Cloneable,
        Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 5056909225610630529L;

    /**
     * The default format string.
     */
    public static final String DEFAULT_LABEL_FORMAT_STRING
            = "({0}, {1}) = {3} - {4}";

    /**
     * Creates a new generator with a default number formatter.
     */
    public IntervalCategoryItemLabelGenerator() {
        super(DEFAULT_LABEL_FORMAT_STRING, NumberFormat.getInstance());
    }

    /**
     * Creates a new generator with the specified number formatter.
     *
     * @param labelFormat the label format string ({@code null} not
     *                    permitted).
     * @param formatter   the number formatter ({@code null} not permitted).
     */
    public IntervalCategoryItemLabelGenerator(String labelFormat,
            NumberFormat formatter) {
        super(labelFormat, formatter);
    }

    /**
     * Creates a new generator with the specified date formatter.
     *
     * @param labelFormat the label format string ({@code null} not
     *                    permitted).
     * @param formatter   the date formatter ({@code null} not permitted).
     */
    public IntervalCategoryItemLabelGenerator(String labelFormat,
            DateFormat formatter) {
        super(labelFormat, formatter);
    }

    /**
     * Creates the array of items that can be passed to the
     * {@code MessageFormat} class for creating labels.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param row     the row index (zero-based).
     * @param column  the column index (zero-based).
     * @return The items (never {@code null}).
     */
    @Override
    protected Object[] createItemArray(CategoryDataset dataset,
            int row, int column) {
        Object[] result = new Object[5];
        result[0] = dataset.getRowKey(row).toString();
        result[1] = dataset.getColumnKey(column).toString();
        Number value = dataset.getValue(row, column);
        if (getNumberFormat() != null) {
            result[2] = getNumberFormat().format(value);
        } else if (getDateFormat() != null) {
            result[2] = getDateFormat().format(value);
        }

        if (dataset instanceof IntervalCategoryDataset) {
            IntervalCategoryDataset icd = (IntervalCategoryDataset) dataset;
            Number start = icd.getStartValue(row, column);
            Number end = icd.getEndValue(row, column);
            if (getNumberFormat() != null) {
                result[3] = getNumberFormat().format(start);
                result[4] = getNumberFormat().format(end);
            } else if (getDateFormat() != null) {
                result[3] = getDateFormat().format(start);
                result[4] = getDateFormat().format(end);
            }
        }
        return result;
    }

}
