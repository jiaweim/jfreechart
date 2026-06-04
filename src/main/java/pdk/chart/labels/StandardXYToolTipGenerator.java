package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.xy.XYDataset;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

/**
 * A standard tool tip generator for use with an
 * {@link pdk.chart.renderer.xy.XYItemRenderer}.
 */
public class StandardXYToolTipGenerator extends AbstractXYItemLabelGenerator
        implements XYToolTipGenerator, Cloneable, PublicCloneable,
        Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -3564164459039540784L;

    /**
     * The default tooltip format.
     */
    public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2})";

    /**
     * Returns a tool tip generator that formats the x-values as dates and the
     * y-values as numbers.
     *
     * @return A tool tip generator (never {@code null}).
     */
    public static StandardXYToolTipGenerator getTimeSeriesInstance() {
        return new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT,
                DateFormat.getInstance(), NumberFormat.getInstance());
    }

    /**
     * Creates a tool tip generator using default number formatters.
     */
    public StandardXYToolTipGenerator() {
        this(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getNumberInstance(),
                NumberFormat.getNumberInstance());
    }

    /**
     * Creates a tool tip generator using the specified number formatters.
     *
     * @param formatString the item label format string ({@code null} not
     *                     permitted).
     * @param xFormat      the format object for the x values ({@code null}
     *                     not permitted).
     * @param yFormat      the format object for the y values ({@code null}
     *                     not permitted).
     */
    public StandardXYToolTipGenerator(String formatString,
            NumberFormat xFormat, NumberFormat yFormat) {

        super(formatString, xFormat, yFormat);

    }

    /**
     * Creates a tool tip generator using the specified number formatters.
     *
     * @param formatString the label format string ({@code null} not
     *                     permitted).
     * @param xFormat      the format object for the x values ({@code null}
     *                     not permitted).
     * @param yFormat      the format object for the y values ({@code null}
     *                     not permitted).
     */
    public StandardXYToolTipGenerator(String formatString, DateFormat xFormat,
            NumberFormat yFormat) {

        super(formatString, xFormat, yFormat);

    }

    /**
     * Creates a tool tip generator using the specified formatters (a
     * number formatter for the x-values and a date formatter for the
     * y-values).
     *
     * @param formatString the item label format string ({@code null}
     *                     not permitted).
     * @param xFormat      the format object for the x values ({@code null}
     *                     permitted).
     * @param yFormat      the format object for the y values ({@code null}
     *                     not permitted).
     */
    public StandardXYToolTipGenerator(String formatString,
            NumberFormat xFormat, DateFormat yFormat) {

        super(formatString, xFormat, yFormat);
    }

    /**
     * Creates a tool tip generator using the specified date formatters.
     *
     * @param formatString the label format string ({@code null} not
     *                     permitted).
     * @param xFormat      the format object for the x values ({@code null}
     *                     not permitted).
     * @param yFormat      the format object for the y values ({@code null}
     *                     not permitted).
     */
    public StandardXYToolTipGenerator(String formatString,
            DateFormat xFormat, DateFormat yFormat) {

        super(formatString, xFormat, yFormat);

    }

    /**
     * Generates the tool tip text for an item in a dataset.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series index (zero-based).
     * @param item    the item index (zero-based).
     * @return The tooltip text (possibly {@code null}).
     */
    @Override
    public String generateToolTip(XYDataset dataset, int series, int item) {
        return generateLabelString(dataset, series, item);
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj the other object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardXYToolTipGenerator)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns an independent copy of the generator.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

}
