package pdk.chart.plot.pep;

import pdk.chart.internal.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * An annotation for a {@link PeptidePlot} that marks an identified fragment.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026
 */
public class PeptideAnnotation implements Serializable, Cloneable {

    private boolean nTerminal;
    /**
     * size of the fragment.
     */
    private int size;
    /**
     * The annotation label.
     */
    private String label;

    /**
     * Create a new annotation.
     *
     * @param isNTerminal true if it is an N-terminal fragment annotation.
     * @param size        size of fragment.
     * @param label       label text.
     */
    public PeptideAnnotation(boolean isNTerminal, int size, String label) {
        Args.requireNonNegative(size, "size");
        Objects.requireNonNull(label, "label");

        this.nTerminal = isNTerminal;
        this.size = size;
        this.label = label;
    }

    /**
     * Return true if it is an N-terminal fragment annotation.
     *
     * @return true if it is an n-TERMINAL FRAGMENT.
     */
    public boolean isNTerminal() {
        return nTerminal;
    }

    /**
     * Set whether it is an N-terminal fragment annotation.
     *
     * @param nTerminal flag.
     */
    public void setNTerminal(boolean nTerminal) {
        this.nTerminal = nTerminal;
    }

    /**
     * Return the size of the fragment.
     *
     * @return size of the fragment.
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the fragment size.
     *
     * @param size fragment size.
     */
    public void setSize(int size) {
        Args.requireNonNegative(size, "size");
        this.size = size;
    }

    /**
     * Returns the annotation label.
     *
     * @return the annotation label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the annotation label.
     *
     * @param label the label ({@code null} not permitted).
     */
    public void setLabel(String label) {
        Objects.requireNonNull(label, "label");
        this.label = label;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PeptideAnnotation that)) return false;
        return nTerminal == that.nTerminal && size == that.size
                && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nTerminal, size, label);
    }

    @Override
    public PeptideAnnotation clone() {
        try {
            return (PeptideAnnotation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
