package pdk.chart.renderer.category;

import pdk.chart.plot.CategoryPlot;

import java.io.Serializable;

/**
 * A default renderer for the {@link CategoryPlot} class.  This is simply an
 * alias for the {@link LineAndShapeRenderer} class.
 */
public class DefaultCategoryItemRenderer extends LineAndShapeRenderer
        implements Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -7793786349384231896L;

    /**
     * Creates a new default renderer.
     */
    public DefaultCategoryItemRenderer() {
        super();
    }

}
