package org.jfree.data;

/**
 * A (key, value) pair.
 *
 * @param <K> the key type.
 * @see DefaultKeyedValue
 */
public interface KeyedValue<K extends Comparable<K>> extends Value {

    /**
     * Returns the key associated with the value.  The key returned by this
     * method should be immutable.
     *
     * @return The key (never {@code null}).
     */
    K getKey();

}
