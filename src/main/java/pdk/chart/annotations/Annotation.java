package pdk.chart.annotations;

import pdk.chart.event.AnnotationChangeEvent;
import pdk.chart.event.AnnotationChangeListener;

/**
 * The base interface for annotations.  All annotations should support the
 * {@link AnnotationChangeEvent} mechanism by allowing listeners to register
 * and receive notification of any changes to the annotation.
 */
public interface Annotation {

    /**
     * Registers an object for notification of changes to the annotation.
     *
     * @param listener the object to register.
     */
    void addChangeListener(AnnotationChangeListener listener);

    /**
     * Deregisters an object for notification of changes to the annotation.
     *
     * @param listener the object to deregister.
     */
    void removeChangeListener(AnnotationChangeListener listener);

}
