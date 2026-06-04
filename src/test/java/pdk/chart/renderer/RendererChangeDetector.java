package pdk.chart.renderer;

import pdk.chart.event.RendererChangeEvent;
import pdk.chart.event.RendererChangeListener;

/**
 * A simple class for detecting whether a renderer has generated
 * a {@link RendererChangeEvent}.
 */
public class RendererChangeDetector implements RendererChangeListener {

    /**
     * A flag that records whether a change event has been received.
     */
    private boolean notified;

    /**
     * Creates a new detector.
     */
    public RendererChangeDetector() {
        this.notified = false;
    }

    /**
     * Returns the flag that indicates whether a change event has been
     * received.
     *
     * @return The flag.
     */
    public boolean getNotified() {
        return this.notified;
    }

    /**
     * Sets the flag that indicates whether a change event has been
     * received.
     *
     * @param notified the new value of the flag.
     */
    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    /**
     * Receives a {@link RendererChangeEvent} from a renderer.
     *
     * @param event the event.
     */
    @Override
    public void rendererChanged(RendererChangeEvent event) {
        this.notified = true;
    }

}
