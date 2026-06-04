package pdk.chart.annotations;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.plot.CategoryPlot;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The interface that must be supported by annotations that are to be added to
 * a {@link CategoryPlot}. Note that, in JFreeChart 1.0.14, a non-compatible
 * change has been made to this interface (it now extends the Annotation
 * interface to support change notifications).
 */
public interface CategoryAnnotation extends Annotation {

    /**
     * Draws the annotation.
     *
     * @param g2         the graphics device.
     * @param plot       the plot.
     * @param dataArea   the data area.
     * @param domainAxis the domain axis.
     * @param rangeAxis  the range axis.
     */
    void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea,
            CategoryAxis domainAxis, ValueAxis rangeAxis);

}
