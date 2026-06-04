package pdk.chart.event;

import pdk.chart.plot.Marker;

import java.util.EventListener;

/**
 * The interface that must be supported by classes that wish to receive
 * notification of changes to a {@link Marker}.
 */
public interface MarkerChangeListener extends EventListener {

    /**
     * Receives notification of a marker change event.
     *
     * @param event the event.
     */
    void markerChanged(MarkerChangeEvent event);

}
