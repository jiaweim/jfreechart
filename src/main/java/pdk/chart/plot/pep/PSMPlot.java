package pdk.chart.plot.pep;

import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:09 PM
 */
public class PSMPlot extends Plot {

    private PeptidePlot peptidePlot = new PeptidePlot();
    private SpectrumPlot spectrumPlot = new SpectrumPlot();

    private double peptideHeight = 120;

    private double gap = 10;

    public PSMPlot() {}

    @Override
    public String getPlotType() {
        return "PSMPlot";
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
        if (area.getWidth() <= 0 || area.getHeight() <= 0)
            return;

        double totalHeight = area.getHeight();
        double usableHeight = totalHeight - gap;

        double spectrumHeight = usableHeight - peptideHeight;

        Rectangle2D spectrumArea = new Rectangle2D.Double(
                area.getX(), area.getY(), area.getWidth(), spectrumHeight
        );
        Rectangle2D peptideArea = new Rectangle2D.Double(
                area.getX(), spectrumArea.getMaxY() + gap,
                area.getWidth(), peptideHeight
        );

        PlotRenderingInfo spectrumInfo = null;
        if (info != null) {
            spectrumInfo = new PlotRenderingInfo(info.getOwner());
        }
        spectrumPlot.draw(g2, spectrumArea, anchor, parentState, spectrumInfo);
        PlotRenderingInfo peptideInfo = null;
        if (info != null) {
            peptideInfo = new PlotRenderingInfo(info.getOwner());
        }
        peptidePlot.draw(g2, peptideArea, anchor, parentState, peptideInfo);

        if (info != null) {
            info.addSubplotInfo(spectrumInfo);
            info.addSubplotInfo(peptideInfo);
        }
    }
}
