package pdk.chart.text;

import pdk.chart.block.Size2D;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A sequence of {@link TextFragment} objects that together form a line of
 * text.  A sequence of text lines is managed by the {@link TextBlock} class.
 */
public class TextLine implements Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 7100085690160465444L;

    /**
     * Storage for the text fragments that make up the line.
     */
    private List<TextFragment> fragments;

    /**
     * Creates a new empty line.
     */
    public TextLine() {
        this.fragments = new ArrayList<>();
    }

    /**
     * Creates a new text line using the default font.
     *
     * @param text the text ({@code null} not permitted).
     */
    public TextLine(String text) {
        this(text, TextFragment.DEFAULT_FONT);
    }

    /**
     * Creates a new text line.
     *
     * @param text the text ({@code null} not permitted).
     * @param font the text font ({@code null} not permitted).
     */
    public TextLine(String text, Font font) {
        this.fragments = new java.util.ArrayList<>();
        final TextFragment fragment = new TextFragment(text, font);
        this.fragments.add(fragment);
    }

    /**
     * Creates a new text line.
     *
     * @param text  the text ({@code null} not permitted).
     * @param font  the text font ({@code null} not permitted).
     * @param paint the text color ({@code null} not permitted).
     */
    public TextLine(String text, Font font, Paint paint) {
        Objects.requireNonNull(text, "text must not be null.");
        Objects.requireNonNull(font, "font must not be null.");
        Objects.requireNonNull(paint, "paint must not be null.");

        this.fragments = new ArrayList<>();
        final TextFragment fragment = new TextFragment(text, font, paint);
        this.fragments.add(fragment);
    }

    /**
     * Adds a text fragment to the text line.
     *
     * @param fragment the text fragment ({@code null} not permitted).
     */
    public void addFragment(TextFragment fragment) {
        Objects.requireNonNull(fragment, "fragment must not be null.");
        this.fragments.add(fragment);
    }

    /**
     * Removes a fragment from the line.
     *
     * @param fragment the fragment to remove.
     */
    public void removeFragment(TextFragment fragment) {
        this.fragments.remove(fragment);
    }

    /**
     * Draws the text line.
     *
     * @param g2      the graphics device.
     * @param anchorX the x-coordinate for the anchor point.
     * @param anchorY the y-coordinate for the anchor point.
     * @param anchor  the point on the text line that is aligned to the anchor
     *                point.
     * @param rotateX the x-coordinate for the rotation point.
     * @param rotateY the y-coordinate for the rotation point.
     * @param angle   the rotation angle (in radians).
     */
    public void draw(Graphics2D g2, float anchorX, float anchorY,
            TextAnchor anchor, float rotateX, float rotateY, double angle) {

        Size2D dim = calculateDimensions(g2);
        float xAdj = 0.0f;
        if (anchor.isHorizontalCenter()) {
            xAdj = (float) -dim.getWidth() / 2.0f;
        } else if (anchor.isRight()) {
            xAdj = (float) -dim.getWidth();
        }
        float x = anchorX + xAdj;
        final float yOffset = calculateBaselineOffset(g2, anchor);
        for (TextFragment fragment : this.fragments) {
            final Size2D d = fragment.calculateDimensions(g2);
            fragment.draw(g2, x, anchorY + yOffset, TextAnchor.BASELINE_LEFT,
                    rotateX, rotateY, angle);
            x = x + (float) d.getWidth();
        }

    }

    /**
     * Calculates the width and height of the text line.
     *
     * @param g2 the graphics device.
     * @return The width and height.
     */
    public Size2D calculateDimensions(Graphics2D g2) {
        double width = 0.0;
        double height = 0.0;
        for (TextFragment fragment : this.fragments) {
            final Size2D dimension = fragment.calculateDimensions(g2);
            width = width + dimension.getWidth();
            height = Math.max(height, dimension.getHeight());
        }
        return new Size2D(width, height);
    }

    /**
     * Returns the first text fragment in the line.
     *
     * @return The first text fragment in the line.
     */
    public TextFragment getFirstTextFragment() {
        TextFragment result = null;
        if (this.fragments.size() > 0) {
            result = this.fragments.get(0);
        }
        return result;
    }

    /**
     * Returns the last text fragment in the line.
     *
     * @return The last text fragment in the line.
     */
    public TextFragment getLastTextFragment() {
        TextFragment result = null;
        if (this.fragments.size() > 0) {
            result = this.fragments.get(this.fragments.size() - 1);
        }
        return result;
    }

    /**
     * Calculate the offsets required to translate from the specified anchor
     * position to the left baseline position.
     *
     * @param g2     the graphics device.
     * @param anchor the anchor position.
     * @return The offsets.
     */
    private float calculateBaselineOffset(Graphics2D g2, TextAnchor anchor) {
        float result = 0.0f;
        for (TextFragment fragment : this.fragments) {
            result = Math.max(result,
                    fragment.calculateBaselineOffset(g2, anchor));
        }
        return result;
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj the object to test against ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextLine) {
            final TextLine line = (TextLine) obj;
            return this.fragments.equals(line.fragments);
        }
        return false;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return (this.fragments != null ? this.fragments.hashCode() : 0);
    }

}
