package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.data.Range;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.IntervalCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link AbstractCategoryItemRenderer} class.
 */
public class AbstractCategoryItemRendererTest {

    /**
     * Checks that all fields are distinguished.
     */
    @Test
    public void testEquals() {
        BarRenderer r1 = new BarRenderer();
        BarRenderer r2 = new BarRenderer();
        assertEquals(r1, r2);

        // the plot field is NOT tested

        // toolTipGeneratorList
        r1.setSeriesToolTipGenerator(1, new StandardCategoryToolTipGenerator());
        assertNotEquals(r1, r2);
        r2.setSeriesToolTipGenerator(1, new StandardCategoryToolTipGenerator());
        assertEquals(r1, r2);

        // defaultToolTipGenerator
        r1.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("{2}",
                NumberFormat.getInstance()));
        assertNotEquals(r1, r2);
        r2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("{2}",
                NumberFormat.getInstance()));
        assertEquals(r1, r2);

        // itemLabelGeneratorList
        r1.setSeriesItemLabelGenerator(1,
                new StandardCategoryItemLabelGenerator());
        assertNotEquals(r1, r2);
        r2.setSeriesItemLabelGenerator(1,
                new StandardCategoryItemLabelGenerator());
        assertEquals(r1, r2);

        // defaultItemLabelGenerator
        r1.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator(
                "{2}", NumberFormat.getInstance()));
        assertNotEquals(r1, r2);
        r2.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator(
                "{2}", NumberFormat.getInstance()));
        assertEquals(r1, r2);

        // urlGeneratorList
        r1.setSeriesItemURLGenerator(1, new StandardCategoryURLGenerator());
        assertNotEquals(r1, r2);
        r2.setSeriesItemURLGenerator(1, new StandardCategoryURLGenerator());
        assertEquals(r1, r2);

        // defaultItemURLGenerator
        r1.setDefaultItemURLGenerator(new StandardCategoryURLGenerator(
                "abc.html"));
        assertNotEquals(r1, r2);
        r2.setDefaultItemURLGenerator(new StandardCategoryURLGenerator(
                "abc.html"));
        assertEquals(r1, r2);

        // legendItemLabelGenerator
        r1.setLegendItemLabelGenerator(new StandardCategorySeriesLabelGenerator(
                "XYZ"));
        assertNotEquals(r1, r2);
        r2.setLegendItemLabelGenerator(new StandardCategorySeriesLabelGenerator(
                "XYZ"));
        assertEquals(r1, r2);

        // legendItemToolTipGenerator
        r1.setLegendItemToolTipGenerator(
                new StandardCategorySeriesLabelGenerator("ToolTip"));
        assertNotEquals(r1, r2);
        r2.setLegendItemToolTipGenerator(
                new StandardCategorySeriesLabelGenerator("ToolTip"));
        assertEquals(r1, r2);

        // legendItemURLGenerator
        r1.setLegendItemURLGenerator(
                new StandardCategorySeriesLabelGenerator("URL"));
        assertNotEquals(r1, r2);
        r2.setLegendItemURLGenerator(
                new StandardCategorySeriesLabelGenerator("URL"));
        assertEquals(r1, r2);
    }

    @Test
    public void testEquals_ObjectList() {
        BarRenderer r1 = new BarRenderer();
        r1.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
        BarRenderer r2 = new BarRenderer();
        r2.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
        assertEquals(r1, r2);
        r2.setSeriesItemLabelGenerator(1, new StandardCategoryItemLabelGenerator("X", new DecimalFormat("0.0")));
        assertNotEquals(r1, r2);
    }

    @Test
    public void testEquals_ObjectList2() {
        BarRenderer r1 = new BarRenderer();
        r1.setSeriesToolTipGenerator(0, new StandardCategoryToolTipGenerator());
        BarRenderer r2 = new BarRenderer();
        r2.setSeriesToolTipGenerator(0, new StandardCategoryToolTipGenerator());
        assertEquals(r1, r2);
        r2.setSeriesToolTipGenerator(1, new StandardCategoryToolTipGenerator("X", new DecimalFormat("0.0")));
        assertNotEquals(r1, r2);
    }

    @Test
    public void testEquals_ObjectList3() {
        BarRenderer r1 = new BarRenderer();
        r1.setSeriesItemURLGenerator(0, new StandardCategoryURLGenerator());
        BarRenderer r2 = new BarRenderer();
        r2.setSeriesItemURLGenerator(0, new StandardCategoryURLGenerator());
        assertEquals(r1, r2);
        r2.setSeriesItemURLGenerator(1, new StandardCategoryURLGenerator());
        assertNotEquals(r1, r2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning1() throws CloneNotSupportedException {
        AbstractCategoryItemRenderer r1 = new BarRenderer();
        AbstractCategoryItemRenderer r2 = (BarRenderer) r1.clone();
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        r1 = new BarRenderer();
        r1.setSeriesItemLabelGenerator(0,
                new StandardCategoryItemLabelGenerator());
        r2 = (BarRenderer) r1.clone();

        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        r1 = new BarRenderer();
        r1.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        r2 = (BarRenderer) r1.clone();
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning2() throws CloneNotSupportedException {
        BarRenderer r1 = new BarRenderer();
        r1.setDefaultItemLabelGenerator(new IntervalCategoryItemLabelGenerator());
        BarRenderer r2 = (BarRenderer) r1.clone();

        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        r1 = new BarRenderer();
        r1.setSeriesItemLabelGenerator(0,
                new IntervalCategoryItemLabelGenerator());
        r2 = (BarRenderer) r1.clone();
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        r1 = new BarRenderer();
        r1.setDefaultItemLabelGenerator(new IntervalCategoryItemLabelGenerator());
        r2 = (BarRenderer) r1.clone();

        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Check that the legendItemLabelGenerator is cloned.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning_LegendItemLabelGenerator() throws CloneNotSupportedException {
        StandardCategorySeriesLabelGenerator generator
                = new StandardCategorySeriesLabelGenerator("Series {0}");
        BarRenderer r1 = new BarRenderer();
        r1.setLegendItemLabelGenerator(generator);
        BarRenderer r2 = (BarRenderer) r1.clone();

        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        // check that the generator has been cloned
        assertNotSame(r1.getLegendItemLabelGenerator(), r2.getLegendItemLabelGenerator());
    }

    /**
     * Check that the legendItemToolTipGenerator is cloned.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning_LegendItemToolTipGenerator() throws CloneNotSupportedException {
        StandardCategorySeriesLabelGenerator generator
                = new StandardCategorySeriesLabelGenerator("Series {0}");
        BarRenderer r1 = new BarRenderer();
        r1.setLegendItemToolTipGenerator(generator);
        BarRenderer r2 = (BarRenderer) r1.clone();

        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        // check that the generator has been cloned
        assertNotSame(r1.getLegendItemToolTipGenerator(), r2.getLegendItemToolTipGenerator());
    }

    /**
     * Check that the legendItemURLGenerator is cloned.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning_LegendItemURLGenerator() throws CloneNotSupportedException {
        StandardCategorySeriesLabelGenerator generator
                = new StandardCategorySeriesLabelGenerator("Series {0}");
        BarRenderer r1 = new BarRenderer();
        r1.setLegendItemURLGenerator(generator);
        BarRenderer r2 = (BarRenderer) r1.clone();
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        // check that the generator has been cloned
        assertNotSame(r1.getLegendItemURLGenerator(), r2.getLegendItemURLGenerator());
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        AbstractCategoryItemRenderer r = new LineAndShapeRenderer();
        assertNull(r.findRangeBounds(null));

        // an empty dataset should return a null range
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        assertNull(r.findRangeBounds(dataset));

        dataset.addValue(1.0, "R1", "C1");
        assertEquals(new Range(1.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(-2.0, "R1", "C2");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(null, "R1", "C3");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));
    }

    /**
     * A test that reproduces the problem reported in bug 2947660.
     */
    @Test
    public void test2947660() {
        AbstractCategoryItemRenderer r = new LineAndShapeRenderer();
        assertNotNull(r.getLegendItems());
        assertEquals(0, r.getLegendItems().getItemCount());

        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        CategoryPlot<String, String> plot = new CategoryPlot<>();
        plot.setDataset(dataset);
        plot.setRenderer(r);
        assertEquals(0, r.getLegendItems().getItemCount());

        dataset.addValue(1.0, "S1", "C1");
        LegendItemCollection lic = r.getLegendItems();
        assertEquals(1, lic.getItemCount());
        assertEquals("S1", lic.get(0).getLabel());
    }

}

