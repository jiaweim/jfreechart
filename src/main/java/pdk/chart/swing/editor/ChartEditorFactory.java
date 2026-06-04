package pdk.chart.swing.editor;

import pdk.chart.Chart;

/**
 * A factory for creating new {@link ChartEditor} instances.
 */
public interface ChartEditorFactory {

    /**
     * Creates an editor for the given chart.
     *
     * @param chart the chart.
     * @return A chart editor.
     */
    ChartEditor createEditor(Chart chart);

}
