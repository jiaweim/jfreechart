package pdk.chart;

import org.junit.jupiter.api.Test;
import pdk.chart.data.Range;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.plot.MeterInterval;
import pdk.chart.plot.MeterPlot;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Miscellaneous checks for meter charts.
 */
public class MeterChartTest {

    /**
     * Draws the chart with a single range.  At one point, this caused a null
     * pointer exception (fixed now).
     */
    @Test
    public void testDrawWithNullInfo() {
        MeterPlot plot = new MeterPlot(new DefaultValueDataset(60.0));
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 80.0)));
        Chart chart = new Chart(plot);
        BufferedImage image = new BufferedImage(200, 100,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        chart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null, null);
        g2.dispose();
        //FIXME we should really assert a value here
    }

}
