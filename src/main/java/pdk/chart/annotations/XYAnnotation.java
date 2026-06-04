package pdk.chart.annotations;

import pdk.chart.axis.ValueAxis;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.XYPlot;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The interface that must be supported by annotations that are to be added to
 * an {@link XYPlot}.  Note that, in JFreeChart 1.0.14, a non-compatible
 * change has been made to this interface (it now extends the Annotation
 * interface to support change notifications).
 */
public interface XYAnnotation extends Annotation {

    /**
     * Draws the annotation.
     *
     * @param g2            the graphics device.
     * @param plot          the plot.
     * @param dataArea      the data area.
     * @param domainAxis    the domain axis.
     * @param rangeAxis     the range axis.
     * @param rendererIndex the renderer index.
     * @param info          an optional info object that will be populated with
     *                      entity information.
     */
    void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
            ValueAxis domainAxis, ValueAxis rangeAxis,
            int rendererIndex, PlotRenderingInfo info);

}
