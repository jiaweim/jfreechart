package pdk.chart.data;

import pdk.chart.api.PublicCloneable;

import java.io.Serializable;
import java.util.Objects;

/**
 * A (key, object) pair.
 *
 * @param <K> the key type.
 */
public class KeyedObject<K extends Comparable<K>> implements Cloneable,
        PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 2677930479256885863L;

    /**
     * The key.
     */
    private final K key;

    /**
     * The object.
     */
    private Object object;

    /**
     * Creates a new (key, object) pair.
     *
     * @param key    the key.
     * @param object the object ({@code null} permitted).
     */
    public KeyedObject(K key, Object object) {
        this.key = key;
        this.object = object;
    }

    /**
     * Returns the key.
     *
     * @return The key.
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Returns the object.
     *
     * @return The object (possibly {@code null}).
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * Sets the object.
     *
     * @param object the object ({@code null} permitted).
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Returns a clone of this object.  It is assumed that the key is an
     * immutable object, so it is not deep-cloned.  The object is deep-cloned
     * if it implements {@link PublicCloneable}, otherwise a shallow clone is
     * made.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        KeyedObject clone = (KeyedObject) super.clone();
        if (this.object instanceof PublicCloneable) {
            PublicCloneable pc = (PublicCloneable) this.object;
            clone.object = pc.clone();
        }
        return clone;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj the other object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof KeyedObject)) {
            return false;
        }
        KeyedObject that = (KeyedObject) obj;
        if (!Objects.equals(this.key, that.key)) {
            return false;
        }

        if (!Objects.equals(this.object, that.object)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.key);
        hash = 47 * hash + Objects.hashCode(this.object);
        return hash;
    }

}
