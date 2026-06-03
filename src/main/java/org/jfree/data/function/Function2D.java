package org.jfree.data.function;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

/**
 * A function of the form {@code y = f(x)}.
 */
public interface Function2D {

    /**
     * Returns the value of the function ('y') for a given input ('x').
     *
     * @param x the x-value.
     * @return The function value.
     */
    double getValue(double x);

    /**
     * Creates an {@link XYDataset} by sampling the specified function over a
     * fixed range.
     *
     * @param start     the start value for the range.
     * @param end       the end value for the range.
     * @param samples   the number of sample points (must be &gt; 1).
     * @param seriesKey the key to give the resulting series.
     * @param <S>       the type for the series keys.
     * @return A dataset.
     */
    default <S extends Comparable<S>> XYDataset<S> sample(double start, double end, int samples, @NonNull S seriesKey) {
        XYSeries<S> series = sampleSeries(start, end, samples, seriesKey);
        return new XYSeriesCollection<>(series);
    }

    /**
     * Create aqn {@link XYSeries} by sampling this function over a fixed range.
     *
     * @param start     the start value for the range.
     * @param end       the end value for the range.
     * @param samples   the number of sample points (must be &gt; 1).
     * @param seriesKey the key to give the resulting series.
     * @param <S>       the type for the series keys.
     * @return A series.
     */
    default <S extends Comparable<S>> XYSeries<S> sampleSeries(double start, double end, int samples, @NonNull S seriesKey) {
        Objects.requireNonNull(seriesKey);

        if (start >= end) {
            throw new IllegalArgumentException("Requires 'start' < 'end'.");
        }
        if (samples < 2) {
            throw new IllegalArgumentException("Requires 'samples' > 1");
        }

        XYSeries<S> series = new XYSeries<>(seriesKey);
        double step = (end - start) / (samples - 1);
        for (int i = 0; i < samples; i++) {
            double x = start + (step * i);
            series.add(x, getValue(x));
        }
        return series;
    }
}
