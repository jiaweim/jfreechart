package pdk.chart.entity;

import java.util.Collection;
import java.util.Iterator;

/**
 * This interface defines the methods used to access an ordered list of
 * {@link ChartEntity} objects.
 */
public interface EntityCollection {

    /**
     * Clears all entities.
     */
    void clear();

    /**
     * Adds an entity to the collection.
     *
     * @param entity the entity ({@code null} not permitted).
     */
    void add(ChartEntity entity);

    /**
     * Adds the entities from another collection to this collection.
     *
     * @param collection the other collection.
     */
    void addAll(EntityCollection collection);

    /**
     * Returns an entity whose area contains the specified point.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return The entity.
     */
    ChartEntity getEntity(double x, double y);

    /**
     * Returns an entity from the collection.
     *
     * @param index the index (zero-based).
     * @return An entity.
     */
    ChartEntity getEntity(int index);

    /**
     * Returns the entity count.
     *
     * @return The entity count.
     */
    int getEntityCount();

    /**
     * Returns the entities in an unmodifiable collection.
     *
     * @return The entities.
     */
    Collection<ChartEntity> getEntities();

    /**
     * Returns an iterator for the entities in the collection.
     *
     * @return An iterator.
     */
    Iterator<ChartEntity> iterator();

}
