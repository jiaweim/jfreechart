package pdk.chart.labels;

import pdk.chart.plot.Crosshair;

/**
 * A label generator for crosshairs.
 */
public interface CrosshairLabelGenerator {

    /**
     * Returns a string that can be used as the label for a crosshair.
     *
     * @param crosshair the crosshair ({@code null} not permitted).
     * @return The label (possibly {@code null}).
     */
    String generateLabel(Crosshair crosshair);

}
