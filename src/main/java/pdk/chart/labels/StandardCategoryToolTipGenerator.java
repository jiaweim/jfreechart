package pdk.chart.labels;

import pdk.chart.data.category.CategoryDataset;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

/**
 * A standard tool tip generator that can be used with a
 * {@link pdk.chart.renderer.category.CategoryItemRenderer}.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public class StandardCategoryToolTipGenerator<R extends Comparable<R>, C extends Comparable<C>>
        extends AbstractCategoryItemLabelGenerator<R, C>
        implements CategoryToolTipGenerator<R, C>, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -6768806592218710764L;

    /**
     * The default format string.
     */
    public static final String DEFAULT_TOOL_TIP_FORMAT_STRING
            = "({0}, {1}) = {2}";

    /**
     * Creates a new generator with a default number formatter.
     */
    public StandardCategoryToolTipGenerator() {
        super(DEFAULT_TOOL_TIP_FORMAT_STRING, NumberFormat.getInstance());
    }

    /**
     * Creates a new generator with the specified number formatter.
     *
     * @param labelFormat the label format string ({@code null} not
     *                    permitted).
     * @param formatter   the number formatter ({@code null} not permitted).
     */
    public StandardCategoryToolTipGenerator(String labelFormat,
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
    public StandardCategoryToolTipGenerator(String labelFormat,
            DateFormat formatter) {
        super(labelFormat, formatter);
    }

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
    @Override
    public String generateToolTip(CategoryDataset<R, C> dataset,
            int row, int column) {
        return generateLabelString(dataset, row, column);
    }

    /**
     * Tests this generator for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardCategoryToolTipGenerator)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
