package pdk.chart.data.general;

/**
 * A change event that encapsulates information about a change to a dataset.
 */
public class DatasetChangeEvent extends java.util.EventObject {

    /**
     * The dataset that generated the change event.
     */
    private final Dataset dataset;

    /**
     * Constructs a new event.  The source is either the dataset or the
     * {@link pdk.chart.plot.Plot} class.  The dataset can be
     * {@code null} (in this case the source will be the
     * {@link pdk.chart.plot.Plot} class).
     *
     * @param source  the source of the event.
     * @param dataset the dataset that generated the event ({@code null}
     *                permitted).
     */
    public DatasetChangeEvent(Object source, Dataset dataset) {
        super(source);
        this.dataset = dataset;
    }

    /**
     * Returns the dataset that generated the event.  Note that the dataset
     * may be {@code null} since adding a {@code null} dataset to a
     * plot will generated a change event.
     *
     * @return The dataset (possibly {@code null}).
     */
    public Dataset getDataset() {
        return this.dataset;
    }

}
