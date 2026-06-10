package pdk.chart.plot.pep;

import org.jspecify.annotations.NonNull;
import pdk.chart.Chart;
import pdk.chart.axis.ValueAxis;

import java.awt.*;

/**
 * Chart for visualizing Peptide spectrum match.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:30 PM
 */
public class PSMChart extends Chart {

    private final PSMPlot plot_;
    private final SpectrumPlot spectrumPlot_;

    public PSMChart() {
        super(null, DEFAULT_TITLE_FONT, new PSMPlot(), false);
        plot_ = (PSMPlot) getPlot();
        DEFAULT_THEME.apply(this);
        plot_.setOutlineVisible(false);
        spectrumPlot_ = plot_.getSpectrumPlot();
    }

    /**
     * Set the dataset to renderer.
     *
     * @param dataset {@link PSMDataset}.
     */
    public PSMChart setDataset(PSMDataset dataset) {
        plot_.setDataset(dataset);
        return this;
    }

    /**
     * Set the axis names
     *
     * @param xAxisLabel x axis name
     * @param yAxisLabel y axis name
     * @return this.
     */
    public PSMChart setAxisNames(String xAxisLabel, String yAxisLabel) {
        ValueAxis domainAxis = spectrumPlot_.getDomainAxis();
        domainAxis.setLabel(xAxisLabel);
        ValueAxis rangeAxis = spectrumPlot_.getRangeAxis();
        rangeAxis.setLabel(yAxisLabel);
        return this;
    }

    /**
     * Set the gap between peptide and spectrum plot.
     *
     * @param gap gap in pixel.
     */
    public PSMChart setPeptideSpectrumGap(double gap) {
        plot_.setPeptideSpectrumGap(gap);
        return this;
    }

    /**
     * Set the height of the area for rendering peptide sequences.
     *
     * @param height height in pixels.
     */
    public PSMChart setPeptideHeight(double height) {
        plot_.setPeptideHeight(height);
        return this;
    }

    /**
     * Set the paint used to draw amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public PSMChart setAminoAcidPaint(Paint paint) {
        plot_.setAminoAcidPaint(paint);
        return this;
    }

    /**
     * Set the paint used to draw marked amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public PSMChart setMarkAminoAcidPaint(@NonNull Paint paint) {
        plot_.setMarkAminoAcidPaint(paint);
        return this;
    }

    /**
     * Set the {@link Font} used to draw amino acid letters.
     *
     * @param font {@link Font}
     */
    public PSMChart setAminoAcidFont(Font font) {
        plot_.setAminoAcidFont(font);
        return this;
    }

    /**
     * Set the gap between adjacent residues and sends a {@link pdk.chart.event.PlotChangeEvent}
     * to all registered listeners.
     *
     * @param aminoAcidSpacing the gap in pixels.
     */
    public PSMChart setAminoAcidSpacing(double aminoAcidSpacing) {
        plot_.setAminoAcidSpacing(aminoAcidSpacing);
        return this;
    }

    /**
     * Set the font used to draw annotation text.
     *
     * @param font {@link Font}.
     */
    public PSMChart setAminoAcidLabelFont(@NonNull Font font) {
        plot_.setAminoAcidLabelFont(font);
        return this;
    }

    /**
     * Set the stroke used to draw annotation line.
     *
     * @param stroke {@link Stroke}
     */
    public PSMChart setAminoAcidAnnotationLineStroke(Stroke stroke) {
        plot_.setAminoAcidAnnotationLineStroke(stroke);
        return this;
    }

    /**
     * Set the vertical distance between amino acid residues and annotation lines.
     *
     * @param gap the gap in pixels.
     */
    public PSMChart setAminoAcidAnnotationLineGap(double gap) {
        plot_.setAminoAcidAnnotationLineGap(gap);
        return this;
    }

    /**
     * Set the vertical distance between the annotation label and annotation line.
     *
     * @param gap gap in pixel.
     */
    public PSMChart setAminoAcidLabelLineGap(double gap) {
        plot_.setAminoAcidLabelLineGap(gap);
        return this;
    }


}
