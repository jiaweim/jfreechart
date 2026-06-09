package pdk.chart.plot.pep;

import pdk.chart.Chart;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:30 PM
 */
public class PSMChart extends Chart {

    private final PSMPlot plot_;

    public PSMChart() {
        super(null, DEFAULT_TITLE_FONT, new PSMPlot(), false);
        plot_ = (PSMPlot) getPlot();
    }

    static void main() {

    }
}
