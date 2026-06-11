package pdk.chart.plot.pep;

import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.YIntervalRenderer;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Plot for spectrum.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:04 PM
 */
public class SpectrumPlot extends XYPlot<SeriesType> {

    private final YIntervalRenderer renderer = new YIntervalRenderer();

    public SpectrumPlot() {
        super();
        NumberAxis xAxis = new NumberAxis("m/z");
        NumberAxis yAxis = new NumberAxis("Relative Abundance");
        xAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRangeIncludesZero(true);

        setDomainAxis(xAxis);
        setRangeAxis(yAxis);
        setRenderer(renderer);

        setDomainGridlinesVisible(false);
        setRangeGridlinesVisible(false);

        renderer.setDefaultItemLabelGenerator((dataset, series, item) -> {
            SpectrumDataset seriesDataset = (SpectrumDataset) dataset;
            SeriesType seriesKey = seriesDataset.getSeriesKey(series);
            String[] labels = seriesDataset.getLabels(seriesKey);
            if (labels == null || labels.length == 0) {
                return "";
            }
            return labels[item];
        });
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
    }

    /**
     * Set the dataset to display.
     *
     * @param spectrumDataset {@link SpectrumDataset}.
     */
    public void setDataset(SpectrumDataset spectrumDataset) {
        spectrumDataset.sort();
        setDataset(0, spectrumDataset);

        for (int i = 0; i < spectrumDataset.getSeriesCount(); i++) {
            SeriesType seriesKey = spectrumDataset.getSeriesKey(i);
            renderer.setSeriesShape(i, new Ellipse2D.Double(-0.5, 0.5, 1, 1)); // without ending shape
            renderer.setSeriesPaint(i, seriesKey.getColor());
            renderer.setSeriesStroke(i, new BasicStroke(seriesKey.getStokeWidth()));
            renderer.setSeriesItemLabelPaint(i, seriesKey.getColor());
        }
    }

    public void setSeriesPaint(int series, Paint paint) {
        renderer.setSeriesPaint(series, paint);
    }
}
