package pdk.chart.fluent;

import pdk.chart.Chart;
import pdk.chart.data.flow.FlowDataset;
import pdk.chart.plot.flow.FlowPlot;

/**
 * A flow chart is a visualisation technique that allows to display flows.
 * <p>
 * Several entities (nodes) are represented by rectangles or text. Their links are represented with
 * arrow or arcs that have a width proportional to the importance of the flow.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:38 AM
 */
public class FlowChart extends Chart {

    private FlowPlot plot_;

    public FlowChart() {
        super(null, DEFAULT_TITLE_FONT, new FlowPlot<>(null), false);
        plot_ = (FlowPlot) getPlot();
    }

    /**
     * Sets the dataset for the plot and sends a change notification to all
     * registered listeners.
     *
     * @param dataset the dataset ({@code null} permitted).
     */
    public FlowChart dataset(FlowDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }




}
