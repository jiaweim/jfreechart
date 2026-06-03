package org.jfree.data;

/**
 * A key that references a single data item in a dataset.
 */
public interface ItemKey {

    /**
     * Returns a JSON formatted string representing the key.
     *
     * @return A JSON formatted string.
     */
    String toJSONString();

}
