package pdk.chart.plot;

import pdk.chart.axis.Axis;
import pdk.chart.axis.AxisState;

import java.util.HashMap;
import java.util.Map;

/**
 * Records information about the state of a plot during the drawing process.
 */
public class PlotState {

    /**
     * The shared axis states.
     */
    private final Map<Axis, AxisState> sharedAxisStates;

    /**
     * Creates a new state object.
     */
    public PlotState() {
        this.sharedAxisStates = new HashMap<>();
    }

    /**
     * Returns a map containing the shared axis states.
     *
     * @return A map.
     */
    public Map<Axis, AxisState> getSharedAxisStates() {
        return this.sharedAxisStates;
    }

}
