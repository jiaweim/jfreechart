package pdk.chart.plot.pep;

import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A plot that displays a peptide sequence.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:28 AM
 */
public class PeptidePlot extends Plot {

    /**
     * Default font for amino acid residues.
     */
    public static final Font DEFAULT_RESIDUE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);
    /**
     * default font for label text.
     */
    public static final Font DEFAULT_LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public static final Paint DEFAULT_RESIDUE_PAINT = Color.BLACK;

    public static final Paint DEFAULT_N_LABEL_PAINT = Color.RED;

    public static final Paint DEFAULT_C_LABEL_PAINT = Color.BLUE;

    public static final double DEFAULT_RESIDUE_SPACING = 16.0;

    public static final Stroke DEFAULT_LINE_STROKE = new BasicStroke(2.0f);

    public static final double DEFAULT_RESIDUE_LINE_SPACING = 2.0;

    public static final double DEFAULT_LABEL_LINE_SPACING = 6.0;

    /**
     * The dataset for the plot.
     */
    private PeptideDataset dataset;

    /**
     * The font to write the residue.
     * Monospace fonts are commonly used to ensure alignment.
     */
    private Font residueFont = DEFAULT_RESIDUE_FONT;
    /**
     * Color that the value is written in.
     */
    private transient Paint residuePaint = DEFAULT_RESIDUE_PAINT;
    /**
     * The space between adjacent residues.
     */
    private double residueSpacing = DEFAULT_RESIDUE_SPACING;

    /**
     * The font used to render annotation label.
     * <p>
     * The annotation font is generally slightly smaller than the amino acid font.
     */
    private Font labelFont = DEFAULT_LABEL_FONT;

    private transient Paint nFragmentPaint = DEFAULT_N_LABEL_PAINT;

    private transient Paint cFragmentPaint = DEFAULT_C_LABEL_PAINT;

    private transient Stroke annotationLineStroke = DEFAULT_LINE_STROKE;

    /**
     * The space between amino acid residue and annotation line.
     */
    private double residueLineSpacing = DEFAULT_RESIDUE_LINE_SPACING;

    private double labelLineSpacing = DEFAULT_LABEL_LINE_SPACING;

    /**
     * Create a new peptide plot.
     */
    public PeptidePlot() {
        this(new PeptideDataset());
    }

    /**
     * Create a new peptide plot.
     *
     * @param dataset {@link PeptideDataset}.
     */
    public PeptidePlot(PeptideDataset dataset) {
        super();
        this.dataset = dataset;
    }

    /**
     * Return the dataset for the plot.
     *
     * @return the dataset.
     */
    public @Nullable PeptideDataset getDataset() {
        return dataset;
    }

    /**
     * Set the dataset and sends a {@link DatasetChangeEvent} to this.
     *
     * @param dataset {@link PeptideDataset}
     */
    public void setDataset(@Nullable PeptideDataset dataset) {
        // if there is an existing dataset, remove the plot from the list of
        // change listeners...
        PeptideDataset existing = this.dataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
    }

    /**
     * Returns the gap width between adjacent residues.
     *
     * @return the gap width in pixels.
     */
    public double getResidueSpacing() {
        return residueSpacing;
    }

    /**
     * Set the gap between adjacent residues and sends a {@link pdk.chart.event.PlotChangeEvent}
     * to all registered listeners.
     *
     * @param residueSpacing the gap in pixels.
     */
    public void setResidueSpacing(double residueSpacing) {
        this.residueSpacing = residueSpacing;
        fireChangeEvent();
    }

    @Override
    public String getPlotType() {
        return "PeptidePlot";
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);

        if (area.isEmpty()) {
            return;
        }

        if (info != null) {
            info.setPlotArea(area);
            info.setDataArea(area);
        }

        drawBackground(g2, area);
        drawOutline(g2, area);

        Shape savedClip = g2.getClip();
        g2.clip(area);

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getForegroundAlpha()));
        if (!DatasetUtils.isEmptyOrNull(this.dataset)) {
            drawPeptide(g2, area, info);
        } else {
            drawNoDataMessage(g2, area);
        }

        g2.setClip(savedClip);
        g2.setComposite(originalComposite);

        drawOutline(g2, area);
    }

    protected void drawPeptide(Graphics2D g2, Rectangle2D plotArea, PlotRenderingInfo info) {
        if (this.dataset.isEmpty()) {
            return;
        }

        // Save original settings
        Paint originalPaint = g2.getPaint();
        Stroke originalStroke = g2.getStroke();
        Font originalFont = g2.getFont();

        // 1. draw amino acid letters
        g2.setFont(residueFont);
        g2.setPaint(residuePaint);

        FontMetrics metrics = g2.getFontMetrics();
        int aaWidth = metrics.stringWidth("M");
        int aaAscent = metrics.getAscent();
        int aaDescent = metrics.getDescent();
        int aaHeight = metrics.getHeight();

        // top=baseline-ascent // 字体框的顶部，不一定是字符顶部
        // bottom=baseline+descent
        // height = ascent+descent

        double aaAndSpacing = residueSpacing + aaWidth;

        int length = dataset.size();
        double totalWidth = length * aaAndSpacing;
        double halfSpacing = residueSpacing * 0.5;
        double halfAAAndSpacing = aaAndSpacing * 0.5;

        double startX = plotArea.getCenterX() - totalWidth / 2;
        double endX = plotArea.getCenterX() + totalWidth / 2;
        double centerY = plotArea.getCenterY();

        float aaY = (float) (centerY + (aaAscent - aaDescent) / 2.0);
        char[] value = dataset.getValue();
        for (int i = 0; i < length; i++) {
            String letter = String.valueOf(value[i]);

            double centerX = startX + halfAAAndSpacing + i * aaAndSpacing;
            float textX = (float) (centerX - metrics.stringWidth(letter) / 2.0); // Horizontally centered
            g2.drawString(letter, textX, aaY); // textX
        }

        // 2. draw annotations
        g2.setFont(labelFont);
        g2.setStroke(annotationLineStroke);
        metrics = g2.getFontMetrics();

        List<PeptideAnnotation> annotations = dataset.getAnnotations();
        List<PeptideAnnotation> nAnnotations = new ArrayList<>();
        List<PeptideAnnotation> cAnnotations = new ArrayList<>();
        for (PeptideAnnotation annotation : annotations) {
            if (annotation.isNTerminal()) {
                nAnnotations.add(annotation);
            } else {
                cAnnotations.add(annotation);
            }
        }

        if (!nAnnotations.isEmpty()) {
            g2.setPaint(nFragmentPaint);

            float labelY = (float) (centerY - aaHeight / 2.0 - residueLineSpacing - labelLineSpacing);

            int[] xPoints = new int[3];
            int[] yPoints = new int[3];
            yPoints[0] = (int) centerY;
            yPoints[1] = (int) (centerY - aaHeight / 2.0 - residueLineSpacing);
            yPoints[2] = yPoints[1];

            for (PeptideAnnotation annotation : nAnnotations) {
                int fragSize = annotation.getSize();
                String label = annotation.getLabel();
                int labelWidth = metrics.stringWidth(label);

                float labelX = (float) (endX - (fragSize - 1) * aaAndSpacing - halfAAAndSpacing - labelWidth / 2.0);
                // draw label
                g2.drawString(label, labelX, labelY);

                // draw annotation line
                xPoints[0] = (int) (endX - fragSize * aaAndSpacing);
                xPoints[1] = xPoints[0];
                xPoints[2] = xPoints[0] + aaWidth + (int) halfSpacing;
                g2.drawPolyline(xPoints, yPoints, 3);
            }
        }
        if (!cAnnotations.isEmpty()) {
            g2.setPaint(cFragmentPaint);
            float labelY = (float) (centerY + aaHeight / 2.0 + residueLineSpacing + labelLineSpacing + (metrics.getAscent() - metrics.getDescent()) / 2.0);

            int[] xPoints = new int[3];
            int[] yPoints = new int[3];
            yPoints[0] = (int) centerY;
            yPoints[1] = (int) (centerY + aaHeight / 2.0 + residueLineSpacing);
            yPoints[2] = yPoints[1];

            for (PeptideAnnotation annotation : cAnnotations) {
                int fragSize = annotation.getSize();
                String label = annotation.getLabel();
                int labelWidth = metrics.stringWidth(label);

                // draw label
                float labelX = (float) (startX + (fragSize - 1) * aaAndSpacing + halfAAAndSpacing - labelWidth / 2.0);
                g2.drawString(label, labelX, labelY);

                // draw line
                xPoints[0] = (int) (startX + fragSize * aaAndSpacing);
                xPoints[1] = xPoints[0];
                xPoints[2] = (int) (xPoints[0] - aaWidth - halfSpacing);
                g2.drawPolyline(xPoints, yPoints, 3);
            }
        }

        // restore settings
        g2.setPaint(originalPaint);
        g2.setStroke(originalStroke);
        g2.setFont(originalFont);
    }

}
