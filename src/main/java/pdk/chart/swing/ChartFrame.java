package pdk.chart.swing;

import pdk.chart.Chart;

import javax.swing.*;

/**
 * A frame for displaying a chart.
 */
public class ChartFrame extends JFrame {

    /**
     * The chart panel.
     */
    private final ChartPanel chartPanel;

    /**
     * Constructs a frame for a chart.
     *
     * @param title the frame title.
     * @param chart the chart.
     */
    public ChartFrame(String title, Chart chart) {
        this(title, chart, false);
    }

    /**
     * Constructs a frame for a chart.
     *
     * @param title      the frame title.
     * @param chart      the chart.
     * @param scrollPane if {@code true}, put the Chart(Panel) into a
     *                   JScrollPane.
     */
    public ChartFrame(String title, Chart chart, boolean scrollPane) {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.chartPanel = new ChartPanel(chart, false);
        if (scrollPane) {
            setContentPane(new JScrollPane(this.chartPanel));
        } else {
            setContentPane(this.chartPanel);
        }
    }

    /**
     * Returns the chart panel for the frame.
     *
     * @return The chart panel.
     */
    public ChartPanel getChartPanel() {
        return this.chartPanel;
    }

}
