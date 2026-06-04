package pdk.chart.event;

import pdk.chart.annotations.Annotation;

import java.util.EventListener;

/**
 * The interface that must be supported by classes that wish to receive
 * notification of changes to an {@link Annotation}.
 */
public interface AnnotationChangeListener extends EventListener {

    /**
     * Receives notification of an annotation change event.
     *
     * @param event the event.
     */
    void annotationChanged(AnnotationChangeEvent event);

}
