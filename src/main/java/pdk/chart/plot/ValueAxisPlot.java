package pdk.chart.plot;

import pdk.chart.axis.ValueAxis;
import pdk.chart.data.Range;

/**
 * An interface that is implemented by plots that use a {@link ValueAxis},
 * providing a standard method to find the current data range.
 */
public interface ValueAxisPlot {

    /**
     * Returns the data range that should apply for the specified axis.
     *
     * @param axis the axis.
     * @return The data range.
     */
    Range getDataRange(ValueAxis axis);

}
