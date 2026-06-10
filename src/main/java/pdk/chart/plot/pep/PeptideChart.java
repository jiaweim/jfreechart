package pdk.chart.plot.pep;

import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.event.PlotChangeEvent;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 12:21 PM
 */
public class PeptideChart extends Chart {

    private final PeptidePlot plot_;

    public PeptideChart() {
        super(null, DEFAULT_TITLE_FONT, new PeptidePlot(), false);
        plot_ = (PeptidePlot) getPlot();
    }

    /**
     * Set the dataset.
     *
     * @param dataset {@link PeptideDataset}
     * @return this.
     */
    public PeptideChart dataset(PeptideDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the dataset.
     *
     * @param peptide peptide sequence.
     * @return this.
     */
    public PeptideChart dataset(String peptide) {
        plot_.setDataset(new PeptideDataset(peptide.toCharArray()));
        return this;
    }

    /**
     * Set the Chart title.
     *
     * @param title new title.
     * @return this.
     */
    public PeptideChart title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets the background color of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @see #getBackgroundPaint()
     */
    public PeptideChart plotBackgroundPaint(Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the flag that controls whether the plot's outline is
     * drawn, and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param visible the new flag value.
     */
    public PeptideChart outlineVisible(boolean visible) {
        plot_.setOutlineVisible(visible);
        return this;
    }

    /**
     * Sets the stroke used to outline the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners. If you set this
     * attribute to {@code null}, no outline will be drawn.
     *
     * @param stroke the stroke ({@code null} permitted).
     */
    public PeptideChart outlineStroke(Stroke stroke) {
        plot_.setOutlineStroke(stroke);
        return this;
    }

    /**
     * Sets the paint used to draw the outline of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * <p>
     * If you set this attribute to {@code null}, no outline will be drawn.
     *
     * @param paint the paint ({@code null} permitted).
     */
    public PeptideChart outlinePaint(Paint paint) {
        plot_.setOutlinePaint(paint);
        return this;
    }

    /**
     * Sets the alpha-transparency for the plot and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param alpha the new alpha transparency.
     */
    public PeptideChart foregroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
        return this;
    }

    /**
     * Sets the message that is displayed when the dataset is empty or
     * {@code null}, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param message the message.
     */
    public PeptideChart noDataMessage(@Nullable String message) {
        plot_.setNoDataMessage(message);
        return this;
    }


    static void main() {
        PeptideDataset dataset = new PeptideDataset("VQGGALEDSQLVAGVAFKK".toCharArray());
        for (int i = 2; i <= 17; i++) {
            dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, i, "b" + i));
        }
        for (int i = 1; i <= 18; i++) {
            dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, i, "y" + i));
        }

        PeptideChart chart = new PeptideChart();
        chart.dataset(dataset);
        chart.show();
    }
}
