package pdk.chart.block;

import pdk.chart.entity.EntityCollection;

/**
 * Provides access to the {@link EntityCollection} generated when a block is
 * drawn.
 */
public interface EntityBlockResult {

    /**
     * Returns the entity collection.
     *
     * @return An entity collection (possibly {@code null}).
     */
    EntityCollection getEntityCollection();

}
