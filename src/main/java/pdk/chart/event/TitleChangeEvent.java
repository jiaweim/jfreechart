package pdk.chart.event;

import pdk.chart.title.Title;

/**
 * A change event that encapsulates information about a change to a chart title.
 */
public class TitleChangeEvent extends ChartChangeEvent {

    /**
     * The chart title that generated the event.
     */
    private final Title title;

    /**
     * Default constructor.
     *
     * @param title the chart title that generated the event.
     */
    public TitleChangeEvent(Title title) {
        super(title);
        this.title = title;
    }

    /**
     * Returns the title that generated the event.
     *
     * @return The title that generated the event.
     */
    public Title getTitle() {
        return this.title;
    }

}
