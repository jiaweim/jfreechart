package pdk.chart.axis;

import org.jspecify.annotations.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * A numerical tick unit.
 */
public class NumberTickUnit extends TickUnit implements Serializable {

    @Serial
    private static final long serialVersionUID = 3849459506627654442L;

    /**
     * A formatter for the tick unit.
     */
    private final NumberFormat formatter;

    /**
     * Creates a new number tick unit.
     *
     * @param size the size of the tick unit.
     */
    public NumberTickUnit(double size) {
        this(size, NumberFormat.getNumberInstance());
    }

    /**
     * Creates a new number tick unit.
     *
     * @param size      the size of the tick unit.
     * @param formatter a number formatter for the tick unit.
     */
    public NumberTickUnit(double size, @NonNull NumberFormat formatter) {
        super(size);
        this.formatter = Objects.requireNonNull(formatter, "formatter");
    }

    /**
     * Creates a new number tick unit.
     *
     * @param size           the size of the tick unit.
     * @param formatter      a number formatter for the tick unit.
     * @param minorTickCount the number of minor ticks.
     */
    public NumberTickUnit(double size, @NonNull NumberFormat formatter,
            int minorTickCount) {
        super(size, minorTickCount);
        this.formatter = Objects.requireNonNull(formatter, "formatter");
    }

    /**
     * Converts a value to a string.
     *
     * @param value the value.
     * @return The formatted string.
     */
    @Override
    public String valueToString(double value) {
        return this.formatter.format(value);
    }

    /**
     * Tests this formatter for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberTickUnit that)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!this.formatter.equals(that.formatter)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representing this unit.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[NumberTickUnit: size=" + this.valueToString(this.getSize())
                + ", formatter=" + this.formatter + "]";
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + (this.formatter != null
                ? this.formatter.hashCode() : 0);
        return result;
    }

}
