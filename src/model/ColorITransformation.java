package model;

/**
 * Represents a color transformation.
 */
public class ColorITransformation implements ITransformation {
    private Color newColor;

    /**
     * Creates a new color transformation with the given color.
     * 
     * @param newColor the new color
     */
    public ColorITransformation(Color newColor) {
        this.newColor = newColor;
    }

    @Override
    public void apply(IShape IShape) {
        IShape.changeColor(newColor);
    }
}
