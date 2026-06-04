package pdk.chart.event;

import java.util.EventListener;

/**
 * The interface that must be supported by classes that wish to receive
 * notification of chart events.
 * <p>
 * The {@link pdk.chart.swing.ChartPanel} class registers itself with the
 * chart it displays, and whenever the chart changes, the panel redraws itself.
 */
public interface ChartChangeListener extends EventListener {

    /**
     * Receives notification of a chart change event.
     *
     * @param event the event.
     */
    void chartChanged(ChartChangeEvent event);

}
