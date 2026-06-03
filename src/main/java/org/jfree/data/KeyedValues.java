package org.jfree.data;

import java.util.List;

/**
 * An ordered list of (key, value) items where the keys are unique and
 * non-{@code null}.
 *
 * @param <K> the key type
 * @see Values
 * @see DefaultKeyedValues
 */
public interface KeyedValues<K extends Comparable<K>> extends Values {

    /**
     * Returns the key associated with the item at a given position.  Note
     * that some implementations allow re-ordering of the data items, so the
     * result may be transient.
     *
     * @param index the item index (in the range {@code 0} to
     *              {@code getItemCount() - 1}).
     * @return The key (never {@code null}).
     * @throws IndexOutOfBoundsException if {@code index} is not in the
     *                                   specified range.
     */
    K getKey(int index);

    /**
     * Returns the index for a given key.
     *
     * @param key the key ({@code null} not permitted).
     * @return The index, or {@code -1} if the key is unrecognised.
     * @throws IllegalArgumentException if {@code key} is {@code null}.
     */
    int getIndex(K key);

    /**
     * Returns the keys for the values in the collection.  Note that you can
     * access the values in this collection by key or by index.  For this
     * reason, the key order is important - this method should return the keys
     * in order.  The returned list may be unmodifiable.
     *
     * @return The keys (never {@code null}).
     */
    List<K> getKeys();

    /**
     * Returns the value for a given key.
     *
     * @param key the key.
     * @return The value (possibly {@code null}).
     * @throws UnknownKeyException if the key is not recognised.
     */
    Number getValue(K key);

}
