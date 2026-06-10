package pdk.chart.plot.pep;

import org.jspecify.annotations.NonNull;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Plot for peptide spectrum match.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:09 PM
 */
public class PSMPlot extends Plot {

    private final PeptidePlot peptidePlot = new PeptidePlot();
    private final SpectrumPlot spectrumPlot = new SpectrumPlot();

    private PSMDataset dataset;
    private double peptideHeight = 100;
    private double gap = 0;

    public PSMPlot() {
        peptidePlot.setOutlineVisible(false);
    }

    public SpectrumPlot getSpectrumPlot() {
        return spectrumPlot;
    }

    /**
     * Set the paint used to draw amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public void setAminoAcidPaint(@NonNull Paint paint) {
        peptidePlot.setAminoAcidPaint(paint);
    }

    /**
     * Set the paint used to draw marked amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public void setMarkAminoAcidPaint(@NonNull Paint paint) {
        peptidePlot.setMarkAminoAcidPaint(paint);
    }

    /**
     * Set the {@link Font} used to draw amino acid letters.
     *
     * @param font {@link Font}
     */
    public void setAminoAcidFont(Font font) {
        peptidePlot.setAminoAcidFont(font);
    }

    /**
     * Set the gap between adjacent residues and sends a {@link pdk.chart.event.PlotChangeEvent}
     * to all registered listeners.
     *
     * @param aminoAcidSpacing the gap in pixels.
     */
    public void setAminoAcidSpacing(double aminoAcidSpacing) {
        peptidePlot.setAminoAcidSpacing(aminoAcidSpacing);
    }

    /**
     * Set the font used to draw annotation text.
     *
     * @param font {@link Font}.
     */
    public void setAminoAcidLabelFont(@NonNull Font font) {
        peptidePlot.setLabelFont(font);
    }

    /**
     * Set the stroke used to draw annotation line.
     *
     * @param stroke {@link Stroke}
     */
    public void setAminoAcidAnnotationLineStroke(Stroke stroke) {
        peptidePlot.setAnnotationLineStroke(stroke);
    }

    /**
     * Set the vertical distance between amino acid residues and annotation lines.
     *
     * @param gap the gap in pixels.
     */
    public void setAminoAcidAnnotationLineGap(double gap) {
        peptidePlot.setAminoAcidAnnotationLineGap(gap);
    }

    /**
     * Set the vertical distance between the annotation label and annotation line.
     *
     * @param gap gap in pixel.
     */
    public void setAminoAcidLabelLineGap(double gap) {
        peptidePlot.setLabelLineGap(gap);
    }

    /**
     * Set the gap between peptide and spectrum plot.
     *
     * @param gap gap in pixel.
     */
    public void setPeptideSpectrumGap(double gap) {
        if (this.gap != gap) {
            this.gap = gap;
            fireChangeEvent();
        }
    }

    /**
     * Set the height of the area for rendering peptide sequences.
     *
     * @param height height in pixels.
     */
    public void setPeptideHeight(double height) {
        if (this.peptideHeight != height) {
            this.peptideHeight = height;
            fireChangeEvent();
        }
    }

    /**
     * Set the dataset to renderer.
     *
     * @param psmDataset {@link PSMDataset}.
     */
    public void setDataset(PSMDataset psmDataset) {
        PSMDataset existing = this.dataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }

        this.dataset = psmDataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        DatasetChangeEvent event = new DatasetChangeEvent(this, psmDataset);
        datasetChanged(event);
    }

    @Override
    public String getPlotType() {
        return "PSMPlot";
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
        if (area.getWidth() <= 0 || area.getHeight() <= 0)
            return;

        RectangleInsets insets = getInsets();
        insets.trim(area);

        if (area.isEmpty())
            return;

        if (info != null) {
            info.setPlotArea(area);
            info.setDataArea(area);
        }

        drawBackground(g2, area);
        drawOutline(g2, area);

        double totalHeight = area.getHeight();
        double usableHeight = totalHeight - gap;

        double spectrumHeight = usableHeight - peptideHeight;

        Rectangle2D peptideArea = new Rectangle2D.Double(
                area.getX(), area.getY(),
                area.getWidth(), peptideHeight
        );

        Rectangle2D spectrumArea = new Rectangle2D.Double(
                area.getX(), peptideArea.getMaxY() + gap,
                area.getWidth(), spectrumHeight
        );

        PlotRenderingInfo spectrumInfo = null;
        if (info != null) {
            spectrumInfo = new PlotRenderingInfo(info.getOwner());
        }
        spectrumPlot.setDataset(dataset.getSpectrumDataset());
        spectrumPlot.draw(g2, spectrumArea, anchor, parentState, spectrumInfo);
        PlotRenderingInfo peptideInfo = null;
        if (info != null) {
            peptideInfo = new PlotRenderingInfo(info.getOwner());
        }
        peptidePlot.setDataset(dataset.getPeptideDataset());
        peptidePlot.draw(g2, peptideArea, anchor, parentState, peptideInfo);

        if (info != null) {
            info.addSubplotInfo(spectrumInfo);
            info.addSubplotInfo(peptideInfo);
        }

        drawOutline(g2, area);
    }
}
