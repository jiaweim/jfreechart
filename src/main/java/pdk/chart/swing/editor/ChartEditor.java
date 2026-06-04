package pdk.chart.swing.editor;

import pdk.chart.Chart;

import javax.swing.*;

/**
 * A chart editor is typically a {@link JComponent} containing a user interface
 * for modifying the properties of a chart.
 *
 * @see ChartEditorManager#getChartEditor(Chart)
 */
public interface ChartEditor {

    /**
     * Applies the changes to the specified chart.
     *
     * @param chart the chart.
     */
    void updateChart(Chart chart);

}
