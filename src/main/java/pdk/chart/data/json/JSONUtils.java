package pdk.chart.data.json;

import pdk.chart.data.KeyedValues;
import pdk.chart.data.KeyedValues2D;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.data.json.impl.JSONValue;
import pdk.chart.internal.Args;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * A utility class that can read and write data in specific JSON formats.
 *
 * @since 1.0.20
 */
public class JSONUtils {

    private JSONUtils() {
        // no requirement for instantiation
    }

    /**
     * Returns a string containing the data in JSON format.  The format is
     * an array of arrays, where each sub-array represents one data value.
     * The sub-array should contain two items, first the item key as a string
     * and second the item value as a number.  For example:
     * {@code [["Key A", 1.0], ["Key B", 2.0]]}
     * <br><br>
     * Note that this method can be used with instances of {@link PieDataset}.
     *
     * @param data the data ({@code null} not permitted).
     * @return A string in JSON format.
     */
    public static String writeKeyedValues(KeyedValues<?> data) {
        Args.nullNotPermitted(data, "data");
        StringWriter sw = new StringWriter();
        try {
            writeKeyedValues(data, sw);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return sw.toString();
    }

    /**
     * Writes the data in JSON format to the supplied writer.
     * <br><br>
     * Note that this method can be used with instances of {@link PieDataset}.
     *
     * @param data   the data ({@code null} not permitted).
     * @param writer the writer ({@code null} not permitted).
     * @throws IOException if there is an I/O problem.
     */
    public static void writeKeyedValues(KeyedValues data, Writer writer)
            throws IOException {
        Args.nullNotPermitted(data, "data");
        Args.nullNotPermitted(writer, "writer");
        writer.write("[");
        boolean first = true;
        for (Object o : data.getKeys()) {
            Comparable key = (Comparable) o;
            if (!first) {
                writer.write(", ");
            } else {
                first = false;
            }
            writer.write("[");
            writer.write(JSONValue.toJSONString(key.toString()));
            writer.write(", ");
            writer.write(JSONValue.toJSONString(data.getValue(key)));
            writer.write("]");
        }
        writer.write("]");
    }

    /**
     * Returns a string containing the data in JSON format.  The format is...
     * <br><br>
     * Note that this method can be used with instances of
     * {@link CategoryDataset}.
     *
     * @param data the data ({@code null} not permitted).
     * @return A string in JSON format.
     */
    public static String writeKeyedValues2D(KeyedValues2D data) {
        Args.nullNotPermitted(data, "data");
        StringWriter sw = new StringWriter();
        try {
            writeKeyedValues2D(data, sw);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return sw.toString();
    }

    /**
     * Writes the data in JSON format to the supplied writer.
     * <br><br>
     * Note that this method can be used with instances of
     * {@link CategoryDataset}.
     *
     * @param data   the data ({@code null} not permitted).
     * @param writer the writer ({@code null} not permitted).
     * @throws IOException if there is an I/O problem.
     */
    public static void writeKeyedValues2D(KeyedValues2D data, Writer writer)
            throws IOException {
        Args.nullNotPermitted(data, "data");
        Args.nullNotPermitted(writer, "writer");
        List<Comparable<?>> columnKeys = data.getColumnKeys();
        List<Comparable<?>> rowKeys = data.getRowKeys();
        writer.write("{");
        if (!columnKeys.isEmpty()) {
            writer.write("\"columnKeys\": [");
            boolean first = true;
            for (Comparable<?> columnKey : columnKeys) {
                if (!first) {
                    writer.write(", ");
                } else {
                    first = false;
                }
                writer.write(JSONValue.toJSONString(columnKey.toString()));
            }
            writer.write("]");
        }
        if (!rowKeys.isEmpty()) {
            writer.write(", \"rows\": [");
            boolean firstRow = true;
            for (Comparable<?> rowKey : rowKeys) {
                if (!firstRow) {
                    writer.write(", [");
                } else {
                    writer.write("[");
                    firstRow = false;
                }
                // write the row data 
                writer.write(JSONValue.toJSONString(rowKey.toString()));
                writer.write(", [");
                boolean first = true;
                for (Comparable<?> columnKey : columnKeys) {
                    if (!first) {
                        writer.write(", ");
                    } else {
                        first = false;
                    }
                    writer.write(JSONValue.toJSONString(data.getValue(rowKey,
                            columnKey)));
                }
                writer.write("]]");
            }
            writer.write("]");
        }
        writer.write("}");
    }

}
