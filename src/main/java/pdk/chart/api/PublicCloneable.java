package pdk.chart.api;

import pdk.chart.Chart;

/**
 * An interface that exposes the clone() method.  In order to support the
 * cloning of {@link Chart} instances, it is advisable to implement this
 * interface for custom plots, renderers and other chart components.  If
 * this interface is not implemented, cloning will still be attempted via
 * reflection.
 */
public interface PublicCloneable extends Cloneable {

    /**
     * Returns a clone of the object.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if cloning is not supported for some reason.
     */
    Object clone() throws CloneNotSupportedException;

}

