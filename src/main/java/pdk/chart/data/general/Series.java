package pdk.chart.data.general;

import pdk.chart.internal.Args;

import javax.swing.event.EventListenerList;
import java.io.Serializable;

/**
 * Base class representing a data series.  Subclasses are left to implement the
 * actual data structures.
 * <p>
 * The series has two properties ("Key" and "Description") for which you can
 * register a {@code PropertyChangeListener}.
 * <p>
 * You can also register a {@link SeriesChangeListener} to receive notification
 * of changes to the series data.
 *
 * @param <K> the key type.
 */
public abstract class Series<K extends Comparable<K>>
        implements Cloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -6906561437538683581L;

    /**
     * The key for the series.
     */
    private final K key;

    /**
     * Storage for registered change listeners.
     */
    private EventListenerList listeners;

    /**
     * A flag that controls whether changes are notified.
     */
    private boolean notify;

    /**
     * Creates a new series with the specified key and description.
     *
     * @param key the series key ({@code null} not permitted).
     */
    protected Series(K key) {
        Args.nullNotPermitted(key, "key");
        this.key = key;
        this.listeners = new EventListenerList();
        this.notify = true;
    }

    /**
     * Returns the key for the series.
     *
     * @return The series key (never {@code null}).
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Returns the flag that controls whether change events are sent to
     * registered listeners.
     *
     * @return A boolean.
     * @see #setNotify(boolean)
     */
    public boolean getNotify() {
        return this.notify;
    }

    /**
     * Sets the flag that controls whether change events are sent to
     * registered listeners.
     *
     * @param notify the new value of the flag.
     * @see #getNotify()
     */
    public void setNotify(boolean notify) {
        if (this.notify != notify) {
            this.notify = notify;
            fireSeriesChanged();
        }
    }

    /**
     * Returns {@code true} if the series contains no data items, and
     * {@code false} otherwise.
     *
     * @return A boolean.
     * @since 1.0.7
     */
    public boolean isEmpty() {
        return (getItemCount() == 0);
    }

    /**
     * Returns the number of data items in the series.
     *
     * @return The number of data items in the series.
     */
    public abstract int getItemCount();

    /**
     * Returns a clone of the series.
     * <p>
     * Notes:
     * <ul>
     * <li>No need to clone the name or description, since String object is
     * immutable.</li>
     * <li>We set the listener list to empty, since the listeners did not
     * register with the clone.</li>
     * <li>Same applies to the PropertyChangeSupport instance.</li>
     * </ul>
     *
     * @return A clone of the series.
     * @throws CloneNotSupportedException not thrown by this class, but
     *                                    subclasses may differ.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        @SuppressWarnings("unchecked")
        Series<K> clone = (Series) super.clone();
        clone.listeners = new EventListenerList();
        return clone;
    }

    /**
     * Tests the series for equality with another object.
     *
     * @param obj the object ({@code null} permitted).
     * @return {@code true} or {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Series)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Series<K> that = (Series) obj;
        if (!getKey().equals(that.getKey())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    /**
     * Registers an object with this series, to receive notification whenever
     * the series changes.
     * <p>
     * Objects being registered must implement the {@link SeriesChangeListener}
     * interface.
     *
     * @param listener the listener to register.
     */
    public void addChangeListener(SeriesChangeListener listener) {
        this.listeners.add(SeriesChangeListener.class, listener);
    }

    /**
     * Deregisters an object, so that it no longer receives notification
     * whenever the series changes.
     *
     * @param listener the listener to deregister.
     */
    public void removeChangeListener(SeriesChangeListener listener) {
        this.listeners.remove(SeriesChangeListener.class, listener);
    }

    /**
     * General method for signalling to registered listeners that the series
     * has been changed.
     */
    public void fireSeriesChanged() {
        if (this.notify) {
            notifyListeners(new SeriesChangeEvent(this));
        }
    }

    /**
     * Sends a change event to all registered listeners.
     *
     * @param event contains information about the event that triggered the
     *              notification.
     */
    protected void notifyListeners(SeriesChangeEvent event) {
        Object[] listenerList = this.listeners.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == SeriesChangeListener.class) {
                ((SeriesChangeListener) listenerList[i + 1]).seriesChanged(
                        event);
            }
        }
    }

}
