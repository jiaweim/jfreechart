package org.jfree.data.xy;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.AbstractSeriesDataset;

/**
 * An base class that you can use to create new implementations of the
 * {@link XYDataset} interface.
 *
 * @param <S> type for the series keys.
 */
public abstract class AbstractXYDataset<S extends Comparable<S>>
        extends AbstractSeriesDataset<S> implements XYDataset<S> {

    /**
     * Creates a new empty dataset.
     */
    protected AbstractXYDataset() {
        super();
    }

    /**
     * Returns the order of the domain (X) values.
     *
     * @return The domain order.
     */
    @Override
    public DomainOrder getDomainOrder() {
        return DomainOrder.NONE;
    }

    /**
     * Returns the x-value (as a double primitive) for an item within a series.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The value.
     */
    @Override
    public double getXValue(int series, int item) {
        double result = Double.NaN;
        Number x = getX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }

    /**
     * Returns the y-value (as a double primitive) for an item within a series.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The value.
     */
    @Override
    public double getYValue(int series, int item) {
        double result = Double.NaN;
        Number y = getY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }

}
