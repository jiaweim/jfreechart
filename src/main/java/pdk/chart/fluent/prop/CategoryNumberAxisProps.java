package pdk.chart.fluent.prop;

import org.jspecify.annotations.Nullable;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.TickUnitSource;
import pdk.chart.event.AxisChangeEvent;
import pdk.chart.fluent.CategoryXYChart;

/**
 * A class for configuring properties of NumberAxis, designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 9:12 AM
 */
public class CategoryNumberAxisProps {

    private final CategoryXYChart chart_;
    private final NumberAxis axis_;

    public CategoryNumberAxisProps(CategoryXYChart chart, NumberAxis axis) {
        this.chart_ = chart;
        this.axis_ = axis;
    }

    /**
     *
     * @return
     */
    public CategoryXYChart done() {
        return chart_;
    }

    /**
     * Set Axis title.
     *
     * @param axisTitle axis title.
     * @return this.
     */
    public CategoryNumberAxisProps name(String axisTitle) {
        axis_.setLabel(axisTitle);
        return this;
    }

    /**
     * Sets the flag that indicates whether the axis range, if
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to {@code true}, the axis range is
     * recalculated.
     *
     * @param axisIncludesZero the new value of the flag.
     */
    public CategoryNumberAxisProps autoRangeIncludesZero(boolean axisIncludesZero) {
        axis_.setAutoRangeIncludesZero(axisIncludesZero);
        return this;
    }

    /**
     * Sets the source for obtaining standard tick units for the axis.
     * <p>
     * The axis will try to select the smallest tick unit from the source that does not cause
     * the tick labels to overlap.
     *
     * @param source the source for standard tick units.
     */
    public CategoryNumberAxisProps standardTickUnits(@Nullable TickUnitSource source) {
        axis_.setStandardTickUnits(source);
        return this;
    }

    /**
     * Sets the range for the axis and sends a change event to all registered
     * listeners.
     * <p>
     * As a side effect, the auto-range flag is set to {@code false}.
     *
     * @param lower the lower axis limit.
     * @param upper the upper axis limit.
     */
    public CategoryNumberAxisProps range(double lower, double upper) {
        axis_.setRange(lower, upper);
        return this;
    }


    /**
     * Sets the flag that indicates whether the minor tick marks are
     * showing.
     *
     * @param flag the flag.
     */
    public CategoryNumberAxisProps minorTickMarksVisible(boolean flag) {
        axis_.setMinorTickMarksVisible(flag);
        return this;
    }

    /**
     * Sets the number of minor tick marks to display.
     * <p>
     * 2 means dividing the interval between two adjacent major ticks into two segments and inserting one minor tick.
     *
     * @param count the count.
     */
    public CategoryNumberAxisProps minorTickCount(int count) {
        axis_.setMinorTickCount(count);
        return this;
    }

    /**
     * Sets the inside length of the tick marks.
     *
     * @param length the new length.
     */
    public CategoryNumberAxisProps tickMarkInsideLength(float length) {
        axis_.setTickMarkInsideLength(length);
        return this;
    }

    /**
     * Sets the outside length of the tick marks.
     *
     * @param length the new length.
     */
    public CategoryNumberAxisProps tickMarkOutsideLength(float length) {
        axis_.setTickMarkOutsideLength(length);
        return this;
    }

    /**
     * Sets the auto range minimum size.
     * <p>
     * The minimum display range of the axis values.
     *
     * @param size the size.
     */
    public CategoryNumberAxisProps autoRangeMinimumSize(double size) {
        axis_.setAutoRangeMinimumSize(size, false);
        return this;
    }

    /**
     * Sets the lower margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners.  This
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin the margin percentage (for example, 0.05 is five percent).
     */
    public CategoryNumberAxisProps lowerMargin(double margin) {
        axis_.setLowerMargin(margin);
        return this;
    }

    /**
     * Sets the upper margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners.
     * <p>
     * This margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin the margin percentage (for example, 0.05 is five percent).
     */
    public CategoryNumberAxisProps upperMargin(double margin) {
        axis_.setUpperMargin(margin);
        return this;
    }
}
