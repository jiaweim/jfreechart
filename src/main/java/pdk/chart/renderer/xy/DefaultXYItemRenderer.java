package pdk.chart.renderer.xy;

import java.io.Serializable;

/**
 * A default renderer for the {@link pdk.chart.plot.XYPlot} class.  This
 * is an alias for the {@link XYLineAndShapeRenderer} class.
 */
public class DefaultXYItemRenderer extends XYLineAndShapeRenderer
        implements Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 3450423530996888074L;

    /**
     * Creates a new default renderer.
     */
    public DefaultXYItemRenderer() {
        super();
    }

}
