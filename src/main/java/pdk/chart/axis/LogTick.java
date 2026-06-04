package pdk.chart.axis;

import pdk.chart.text.TextAnchor;
import java.text.AttributedString;

/**
 * A tick from a {@link LogAxis}.
 */
public class LogTick extends ValueTick {

    /**
     * The attributed string for the tick label.
     */
    AttributedString attributedLabel;

    /**
     * Creates a new instance.
     *
     * @param type       the type (major or minor tick, {@code null} not
     *                   permitted).
     * @param value      the value.
     * @param label      the label ({@code null} permitted).
     * @param textAnchor the text anchor.
     */
    public LogTick(TickType type, double value, AttributedString label,
            TextAnchor textAnchor) {
        super(type, value, null, textAnchor, textAnchor, 0.0);
        this.attributedLabel = label;
    }

    /**
     * Returns the attributed string for the tick label, or {@code null}
     * if there is no label.
     *
     * @return The attributed string or {@code null}.
     */
    public AttributedString getAttributedLabel() {
        return this.attributedLabel;
    }
}
