package pdk.chart.axis;

import pdk.chart.text.TextAnchor;

/**
 * A numerical tick.
 */
public class NumberTick extends ValueTick {

    /**
     * The number.
     */
    private final Number number;

    /**
     * Creates a new tick.
     *
     * @param number         the number ({@code null} not permitted).
     * @param label          the label.
     * @param textAnchor     the part of the label that is aligned with the anchor
     *                       point.
     * @param rotationAnchor defines the rotation point relative to the text.
     * @param angle          the rotation angle (in radians).
     */
    public NumberTick(Number number, String label,
            TextAnchor textAnchor,
            TextAnchor rotationAnchor, double angle) {

        super(number.doubleValue(), label, textAnchor, rotationAnchor, angle);
        this.number = number;

    }

    /**
     * Creates a new tick.
     *
     * @param tickType       the tick type.
     * @param value          the value.
     * @param label          the label.
     * @param textAnchor     the part of the label that is aligned with the anchor
     *                       point.
     * @param rotationAnchor defines the rotation point relative to the text.
     * @param angle          the rotation angle (in radians).
     */
    public NumberTick(TickType tickType, double value, String label,
            TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
        super(tickType, value, label, textAnchor, rotationAnchor, angle);
        this.number = value;
    }

    /**
     * Returns the number.
     *
     * @return The number.
     */
    public Number getNumber() {
        return this.number;
    }

}
