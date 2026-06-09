package pdk.chart.data.general;

/**
 * The base interface for data sets.
 * <p>
 * All datasets are required to support the {@link DatasetChangeEvent}
 * mechanism by allowing listeners to register and receive notification of any
 * changes to the dataset.
 */
public interface Dataset {

    /**
     * Registers an object for notification of changes to the dataset.
     *
     * @param listener the object to register.
     */
    void addChangeListener(DatasetChangeListener listener);

    /**
     * Deregisters an object for notification of changes to the dataset.
     *
     * @param listener the object to deregister.
     */
    void removeChangeListener(DatasetChangeListener listener);

}
