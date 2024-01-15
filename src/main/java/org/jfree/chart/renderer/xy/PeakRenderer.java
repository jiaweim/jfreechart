package org.jfree.chart.renderer.xy;

import org.jfree.chart.api.PublicCloneable;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.internal.Args;
import org.jfree.chart.internal.CloneUtils;
import org.jfree.chart.internal.SerialUtils;
import org.jfree.chart.internal.ShapeUtils;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import org.jfree.chart.legend.LegendItem;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.text.TextUtils;
import org.jfree.chart.util.GradientPaintTransformer;
import org.jfree.chart.util.StandardGradientPaintTransformer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A renderer that draws bars on an {@link XYPlot} (requires an {@link XYDataset}).
 */
public class PeakRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{

    /**
     * The state class used by this renderer.
     */
    protected class XYBarRendererState extends XYItemRendererState
    {

        /**
         * Base for bars against the range axis, in Java 2D space.
         */
        private double g2Base;

        /**
         * Creates a new state object.
         *
         * @param info the plot rendering info.
         */
        public XYBarRendererState(PlotRenderingInfo info) {
            super(info);
        }

        /**
         * Returns the base (range) value in Java 2D space.
         *
         * @return The base value.
         */
        public double getG2Base() {
            return this.g2Base;
        }

        /**
         * Sets the range axis base in Java2D space.
         *
         * @param value the value.
         */
        public void setG2Base(double value) {
            this.g2Base = value;
        }
    }

    /**
     * The default base value for the bars.
     */
    private double base;

    /**
     * width of the peak
     */
    private double defaultPeakWidth;
    private transient Map<Integer, Double> peakWidths = new HashMap<>();

    /**
     * A flag that controls whether or not bar outlines are drawn.
     */
    private boolean drawBarOutline;

    /**
     * An optional class used to transform gradient paint objects to fit each bar.
     */
    private GradientPaintTransformer gradientPaintTransformer;

    /**
     * The shape used to represent a bar in each legend item (this should never be {@code null}).
     */
    private transient Shape legendBar;

    /**
     * The fallback position if a positive item label doesn't fit inside the bar.
     */
    private ItemLabelPosition positiveItemLabelPositionFallback;

    /**
     * The fallback position if a negative item label doesn't fit inside the bar.
     */
    private ItemLabelPosition negativeItemLabelPositionFallback;

    /**
     * The default constructor.
     */
    public PeakRenderer() {
        this(1.0);
    }

    /**
     * Constructs a new renderer.
     *
     * @param defaultPeakWidth the width of each peak
     */
    public PeakRenderer(double defaultPeakWidth) {
        super();
        this.defaultPeakWidth = defaultPeakWidth;
        this.base = 0.0;
        this.gradientPaintTransformer = new StandardGradientPaintTransformer();
        this.drawBarOutline = false;
        this.legendBar = new Rectangle2D.Double(-3.0, -5.0, 6.0, 10.0);
    }

    /**
     * REturn the peak width  used to draw peaks by the renderer
     *
     * @param series series index (0-based)
     */
    public Double getSeriesPeakWidth(int series) {
        return this.peakWidths.get(series);
    }

    public void setSeriesPeakWidth(int series, double width) {
        setSeriesPeakWidth(series, width, true);
    }

    public void setSeriesPeakWidth(int series, double width, boolean notify) {
        this.peakWidths.put(series, width);
        if (notify) {
            fireChangeEvent();
        }
    }

    /**
     * Returns the base value for the bars.
     *
     * @return The base value for the bars.
     * @see #setBase(double)
     */
    public double getBase() {
        return this.base;
    }

    /**
     * Sets the base value for the bars and sends a {@link RendererChangeEvent} to all registered listeners.  The base
     * value is not used if the dataset's y-interval is being used to determine the bar length.
     *
     * @param base the new base value.
     * @see #getBase()
     */
    public void setBase(double base) {
        this.base = base;
        fireChangeEvent();
    }

    /**
     * set the width of the peak
     *
     * @param width new peak width
     */
    public void setDefaultPeakWidth(double width) {
        this.defaultPeakWidth = width;
        fireChangeEvent();
    }

    /**
     * Returns a flag that controls whether or not bar outlines are drawn.
     *
     * @return A boolean.
     * @see #setDrawBarOutline(boolean)
     */
    public boolean isDrawBarOutline() {
        return this.drawBarOutline;
    }

    /**
     * Sets the flag that controls whether or not bar outlines are drawn and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param draw the flag.
     * @see #isDrawBarOutline()
     */
    public void setDrawBarOutline(boolean draw) {
        this.drawBarOutline = draw;
        fireChangeEvent();
    }

    /**
     * Returns the gradient paint transformer (an object used to transform gradient paint objects to fit each bar).
     *
     * @return A transformer ({@code null} possible).
     * @see #setGradientPaintTransformer(GradientPaintTransformer)
     */
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;
    }

    /**
     * Sets the gradient paint transformer and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param transformer the transformer ({@code null} permitted).
     * @see #getGradientPaintTransformer()
     */
    public void setGradientPaintTransformer(GradientPaintTransformer transformer) {
        this.gradientPaintTransformer = transformer;
        fireChangeEvent();
    }

    /**
     * Returns the shape used to represent bars in each legend item.
     *
     * @return The shape used to represent bars in each legend item (never {@code null}).
     * @see #setLegendBar(Shape)
     */
    public Shape getLegendBar() {
        return this.legendBar;
    }

    /**
     * Sets the shape used to represent bars in each legend item and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param bar the bar shape ({@code null} not permitted).
     * @see #getLegendBar()
     */
    public void setLegendBar(Shape bar) {
        Args.nullNotPermitted(bar, "bar");
        this.legendBar = bar;
        fireChangeEvent();
    }

    /**
     * Returns the fallback position for positive item labels that don't fit within a bar.
     *
     * @return The fallback position ({@code null} possible).
     * @see #setPositiveItemLabelPositionFallback(ItemLabelPosition)
     */
    public ItemLabelPosition getPositiveItemLabelPositionFallback() {
        return this.positiveItemLabelPositionFallback;
    }

    /**
     * Sets the fallback position for positive item labels that don't fit within a bar, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param position the position ({@code null} permitted).
     * @see #getPositiveItemLabelPositionFallback()
     */
    public void setPositiveItemLabelPositionFallback(ItemLabelPosition position) {
        this.positiveItemLabelPositionFallback = position;
        fireChangeEvent();
    }

    /**
     * Returns the fallback position for negative item labels that don't fit within a bar.
     *
     * @return The fallback position ({@code null} possible).
     * @see #setNegativeItemLabelPositionFallback(ItemLabelPosition)
     */
    public ItemLabelPosition getNegativeItemLabelPositionFallback() {
        return this.negativeItemLabelPositionFallback;
    }

    /**
     * Sets the fallback position for negative item labels that don't fit within a bar, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param position the position ({@code null} permitted).
     * @see #getNegativeItemLabelPositionFallback()
     */
    public void setNegativeItemLabelPositionFallback(
            ItemLabelPosition position) {
        this.negativeItemLabelPositionFallback = position;
        fireChangeEvent();
    }

    /**
     * Initialises the renderer and returns a state object that should be passed to all subsequent calls to the
     * drawItem() method.  Here we calculate the Java2D y-coordinate for zero, since all the bars have their bases fixed
     * at zero.
     *
     * @param g2       the graphics device.
     * @param dataArea the area inside the axes.
     * @param plot     the plot.
     * @param dataset  the data.
     * @param info     an optional info collection object to return data back to the caller.
     * @return A state object.
     */
    @Override
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea,
            XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {

        XYBarRendererState state = new XYBarRendererState(info);
        ValueAxis rangeAxis = plot.getRangeAxisForDataset(plot.indexOf(
                dataset));
        state.setG2Base(rangeAxis.valueToJava2D(this.base, dataArea,
                plot.getRangeAxisEdge()));
        return state;

    }

    /**
     * Returns a default legend item for the specified series.  Subclasses should override this method to generate
     * customised items.
     *
     * @param datasetIndex the dataset index (zero-based).
     * @param series       the series index (zero-based).
     * @return A legend item for the series.
     */
    @Override
    public LegendItem getLegendItem(int datasetIndex, int series) {
        XYPlot xyplot = getPlot();
        if (xyplot == null) {
            return null;
        }
        XYDataset dataset = xyplot.getDataset(datasetIndex);
        if (dataset == null) {
            return null;
        }
        LegendItem result;
        XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
        String label = lg.generateLabel(dataset, series);
        String description = label;
        String toolTipText = null;
        if (getLegendItemToolTipGenerator() != null) {
            toolTipText = getLegendItemToolTipGenerator().generateLabel(
                    dataset, series);
        }
        String urlText = null;
        if (getLegendItemURLGenerator() != null) {
            urlText = getLegendItemURLGenerator().generateLabel(dataset,
                    series);
        }
        Shape shape = this.legendBar;
        Paint paint = lookupSeriesPaint(series);
        Paint outlinePaint = lookupSeriesOutlinePaint(series);
        Stroke outlineStroke = lookupSeriesOutlineStroke(series);
        if (this.drawBarOutline) {
            result = new LegendItem(label, description, toolTipText,
                    urlText, shape, paint, outlineStroke, outlinePaint);
        } else {
            result = new LegendItem(label, description, toolTipText, urlText,
                    shape, paint);
        }
        result.setLabelFont(lookupLegendTextFont(series));
        Paint labelPaint = lookupLegendTextPaint(series);
        if (labelPaint != null) {
            result.setLabelPaint(labelPaint);
        }
        result.setDataset(dataset);
        result.setDatasetIndex(datasetIndex);
        result.setSeriesKey(dataset.getSeriesKey(series));
        result.setSeriesIndex(series);
        if (getGradientPaintTransformer() != null) {
            result.setFillPaintTransformer(getGradientPaintTransformer());
        }
        return result;
    }

    public double lookupSeriesPeakWidth(int series) {
        Double seriesPeakWidth = getSeriesPeakWidth(series);
        if (seriesPeakWidth == null)
            seriesPeakWidth = defaultPeakWidth;
        return seriesPeakWidth;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2             the graphics device.
     * @param state          the renderer state.
     * @param dataArea       the area within which the plot is being drawn.
     * @param info           collects information about the drawing.
     * @param plot           the plot (can be used to obtain standard color information etc).
     * @param domainAxis     the domain axis.
     * @param rangeAxis      the range axis.
     * @param dataset        the dataset.
     * @param series         the series index (zero-based).
     * @param item           the item index (zero-based).
     * @param crosshairState crosshair information for the plot ({@code null} permitted).
     * @param pass           the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
            int series, int item, CrosshairState crosshairState, int pass) {

        if (!getItemVisible(series, item)) {
            return;
        }

        double value0 = this.base;
        double value1 = dataset.getYValue(series, item);
        if (Double.isNaN(value0) || Double.isNaN(value1)) {
            return;
        }

        double translatedValue0 = rangeAxis.valueToJava2D(value0, dataArea, plot.getRangeAxisEdge());
        double translatedValue1 = rangeAxis.valueToJava2D(value1, dataArea, plot.getRangeAxisEdge());
        double bottom = Math.min(translatedValue0, translatedValue1);
        double top = Math.max(translatedValue0, translatedValue1);

        double startX = dataset.getXValue(series, item);
        if (Double.isNaN(startX)) {
            return;
        }

        RectangleEdge location = plot.getDomainAxisEdge();
        double translatedStartX = domainAxis.valueToJava2D(startX, dataArea, location);

        double translatedWidth = Math.max(lookupSeriesPeakWidth(series), 1);
        double left = translatedStartX - translatedWidth / 2;

        // clip top and bottom bounds to data area
        bottom = Math.max(bottom, dataArea.getMinY());
        top = Math.min(top, dataArea.getMaxY());
        Rectangle2D bar = new Rectangle2D.Double(left, bottom, translatedWidth, top - bottom);

        RectangleEdge barBase = RectangleEdge.BOTTOM;

        if (state.getElementHinting()) {
            beginElementGroup(g2, dataset.getSeriesKey(series), item);
        }
        paintBar(g2, series, item, bar, barBase);
        if (state.getElementHinting()) {
            endElementGroup(g2);
        }

        if (isItemLabelVisible(series, item)) {
            XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
            drawItemLabel(g2, dataset, series, item, plot, generator, bar,
                    value1 < 0.0);
        }

        // update the crosshair point
        double x1 = startX;
        double y1 = dataset.getYValue(series, item);
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, location);
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
        int datasetIndex = plot.indexOf(dataset);
        updateCrosshairValues(crosshairState, x1, y1, datasetIndex, transX1, transY1, plot.getOrientation());

        EntityCollection entities = state.getEntityCollection();
        if (entities != null) {
            addEntity(entities, bar, dataset, series, item, 0.0, 0.0);
        }
    }

    /**
     * Paints a single bar instance.
     *
     * @param g2     the graphics target.
     * @param row    the row index.
     * @param column the column index.
     * @param bar    the bar
     * @param base   indicates which side of the rectangle is the base of the bar.
     */
    public void paintBar(Graphics2D g2, int row, int column, RectangularShape bar, RectangleEdge base) {

        Paint itemPaint = getItemPaint(row, column);
        GradientPaintTransformer t = getGradientPaintTransformer();
        if (t != null && itemPaint instanceof GradientPaint) {
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
        }
        g2.setPaint(itemPaint);
        g2.fill(bar);

        // draw the outline...
        if (isDrawBarOutline()) {
            // && state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
            Stroke stroke = getItemOutlineStroke(row, column);
            Paint paint = getItemOutlinePaint(row, column);
            if (stroke != null && paint != null) {
                g2.setStroke(stroke);
                g2.setPaint(paint);
                g2.draw(bar);
            }
        }
    }

    /**
     * Draws an item label.  This method is provided as an alternative to
     * {@link #drawItemLabel(Graphics2D, PlotOrientation, XYDataset, int, int, double, double, boolean)} so that the bar
     * can be used to calculate the label anchor point.
     *
     * @param g2        the graphics device.
     * @param dataset   the dataset.
     * @param series    the series index.
     * @param item      the item index.
     * @param plot      the plot.
     * @param generator the label generator ({@code null} permitted, in which case the method does nothing, just
     *                  returns).
     * @param bar       the bar.
     * @param negative  a flag indicating a negative value.
     */
    protected void drawItemLabel(Graphics2D g2, XYDataset dataset,
            int series, int item, XYPlot plot, XYItemLabelGenerator generator,
            Rectangle2D bar, boolean negative) {

        if (generator == null) {
            return;  // nothing to do
        }
        String label = generator.generateLabel(dataset, series, item);
        if (label == null) {
            return;  // nothing to do
        }

        Font labelFont = getItemLabelFont(series, item);
        g2.setFont(labelFont);
        Paint paint = getItemLabelPaint(series, item);
        g2.setPaint(paint);

        // find out where to place the label...
        ItemLabelPosition position;
        if (!negative) {
            position = getPositiveItemLabelPosition(series, item);
        } else {
            position = getNegativeItemLabelPosition(series, item);
        }

        // work out the label anchor point...
        Point2D anchorPoint = calculateLabelAnchorPoint(
                position.getItemLabelAnchor(), bar, plot.getOrientation());

        if (isInternalAnchor(position.getItemLabelAnchor())) {
            Shape bounds = TextUtils.calculateRotatedStringBounds(label,
                    g2, (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(),
                    position.getRotationAnchor());

            if (bounds != null) {
                if (!bar.contains(bounds.getBounds2D())) {
                    if (!negative) {
                        position = getPositiveItemLabelPositionFallback();
                    } else {
                        position = getNegativeItemLabelPositionFallback();
                    }
                    if (position != null) {
                        anchorPoint = calculateLabelAnchorPoint(
                                position.getItemLabelAnchor(), bar,
                                plot.getOrientation());
                    }
                }
            }

        }

        if (position != null) {
            TextUtils.drawRotatedString(label, g2,
                    (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(),
                    position.getRotationAnchor());
        }
    }

    /**
     * Calculates the item label anchor point.
     *
     * @param anchor      the anchor.
     * @param bar         the bar.
     * @param orientation the plot orientation.
     * @return The anchor point.
     */
    private Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor,
            Rectangle2D bar, PlotOrientation orientation) {

        Point2D result = null;
        double offset = getItemLabelAnchorOffset();
        double x0 = bar.getX() - offset;
        double x1 = bar.getX();
        double x2 = bar.getX() + offset;
        double x3 = bar.getCenterX();
        double x4 = bar.getMaxX() - offset;
        double x5 = bar.getMaxX();
        double x6 = bar.getMaxX() + offset;

        double y0 = bar.getMaxY() + offset;
        double y1 = bar.getMaxY();
        double y2 = bar.getMaxY() - offset;
        double y3 = bar.getCenterY();
        double y4 = bar.getMinY() + offset;
        double y5 = bar.getMinY();
        double y6 = bar.getMinY() - offset;

        if (anchor == ItemLabelAnchor.CENTER) {
            result = new Point2D.Double(x3, y3);
        } else if (anchor == ItemLabelAnchor.INSIDE1) {
            result = new Point2D.Double(x4, y4);
        } else if (anchor == ItemLabelAnchor.INSIDE2) {
            result = new Point2D.Double(x4, y4);
        } else if (anchor == ItemLabelAnchor.INSIDE3) {
            result = new Point2D.Double(x4, y3);
        } else if (anchor == ItemLabelAnchor.INSIDE4) {
            result = new Point2D.Double(x4, y2);
        } else if (anchor == ItemLabelAnchor.INSIDE5) {
            result = new Point2D.Double(x4, y2);
        } else if (anchor == ItemLabelAnchor.INSIDE6) {
            result = new Point2D.Double(x3, y2);
        } else if (anchor == ItemLabelAnchor.INSIDE7) {
            result = new Point2D.Double(x2, y2);
        } else if (anchor == ItemLabelAnchor.INSIDE8) {
            result = new Point2D.Double(x2, y2);
        } else if (anchor == ItemLabelAnchor.INSIDE9) {
            result = new Point2D.Double(x2, y3);
        } else if (anchor == ItemLabelAnchor.INSIDE10) {
            result = new Point2D.Double(x2, y4);
        } else if (anchor == ItemLabelAnchor.INSIDE11) {
            result = new Point2D.Double(x2, y4);
        } else if (anchor == ItemLabelAnchor.INSIDE12) {
            result = new Point2D.Double(x3, y4);
        } else if (anchor == ItemLabelAnchor.OUTSIDE1) {
            result = new Point2D.Double(x5, y6);
        } else if (anchor == ItemLabelAnchor.OUTSIDE2) {
            result = new Point2D.Double(x6, y5);
        } else if (anchor == ItemLabelAnchor.OUTSIDE3) {
            result = new Point2D.Double(x6, y3);
        } else if (anchor == ItemLabelAnchor.OUTSIDE4) {
            result = new Point2D.Double(x6, y1);
        } else if (anchor == ItemLabelAnchor.OUTSIDE5) {
            result = new Point2D.Double(x5, y0);
        } else if (anchor == ItemLabelAnchor.OUTSIDE6) {
            result = new Point2D.Double(x3, y0);
        } else if (anchor == ItemLabelAnchor.OUTSIDE7) {
            result = new Point2D.Double(x1, y0);
        } else if (anchor == ItemLabelAnchor.OUTSIDE8) {
            result = new Point2D.Double(x0, y1);
        } else if (anchor == ItemLabelAnchor.OUTSIDE9) {
            result = new Point2D.Double(x0, y3);
        } else if (anchor == ItemLabelAnchor.OUTSIDE10) {
            result = new Point2D.Double(x0, y5);
        } else if (anchor == ItemLabelAnchor.OUTSIDE11) {
            result = new Point2D.Double(x1, y6);
        } else if (anchor == ItemLabelAnchor.OUTSIDE12) {
            result = new Point2D.Double(x3, y6);
        }

        return result;

    }

    /**
     * Returns {@code true} if the specified anchor point is inside a bar.
     *
     * @param anchor the anchor point.
     * @return A boolean.
     */
    private boolean isInternalAnchor(ItemLabelAnchor anchor) {
        return anchor == ItemLabelAnchor.CENTER
                || anchor == ItemLabelAnchor.INSIDE1
                || anchor == ItemLabelAnchor.INSIDE2
                || anchor == ItemLabelAnchor.INSIDE3
                || anchor == ItemLabelAnchor.INSIDE4
                || anchor == ItemLabelAnchor.INSIDE5
                || anchor == ItemLabelAnchor.INSIDE6
                || anchor == ItemLabelAnchor.INSIDE7
                || anchor == ItemLabelAnchor.INSIDE8
                || anchor == ItemLabelAnchor.INSIDE9
                || anchor == ItemLabelAnchor.INSIDE10
                || anchor == ItemLabelAnchor.INSIDE11
                || anchor == ItemLabelAnchor.INSIDE12;
    }

    /**
     * Returns the lower and upper bounds (range) of the x-values in the specified dataset.  Since this renderer uses
     * the x-interval in the dataset, this is taken into account for the range.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return The range ({@code null} if the dataset is {@code null} or empty).
     */
    @Override
    public Range findDomainBounds(XYDataset dataset) {
        return findDomainBounds(dataset, true);
    }

    /**
     * Returns the lower and upper bounds (range) of the y-values in the specified dataset.  If the renderer is plotting
     * the y-interval from the dataset, this is taken into account for the range.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return The range ({@code null} if the dataset is {@code null} or empty).
     */
    @Override
    public Range findRangeBounds(XYDataset dataset) {
        return findRangeBounds(dataset, false);
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if the renderer cannot be cloned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        PeakRenderer result = (PeakRenderer) super.clone();
        if (this.gradientPaintTransformer != null) {
            result.gradientPaintTransformer = CloneUtils.clone(this.gradientPaintTransformer);
        }
        result.legendBar = CloneUtils.clone(this.legendBar);
        return result;
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj the object to test against ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PeakRenderer)) {
            return false;
        }
        PeakRenderer that = (PeakRenderer) obj;
        if (this.base != that.base) {
            return false;
        }
        if (this.drawBarOutline != that.drawBarOutline) {
            return false;
        }
        if (!Objects.equals(this.gradientPaintTransformer, that.gradientPaintTransformer)) {
            return false;
        }
        if (!ShapeUtils.equal(this.legendBar, that.legendBar)) {
            return false;
        }
        if (!Objects.equals(this.positiveItemLabelPositionFallback, that.positiveItemLabelPositionFallback)) {
            return false;
        }
        if (!Objects.equals(this.negativeItemLabelPositionFallback, that.negativeItemLabelPositionFallback)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Provides serialization support.
     *
     * @param stream the input stream.
     * @throws IOException            if there is an I/O error.
     * @throws ClassNotFoundException if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.legendBar = SerialUtils.readShape(stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream the output stream.
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtils.writeShape(this.legendBar, stream);
    }
}
