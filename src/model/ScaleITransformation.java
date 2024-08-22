package model;

/**
 * Represents a scale transformation.
 */
public class ScaleITransformation implements ITransformation {
    private double scaleFactor;

    /**
     * Creates a new scale transformation with the given scale factor.
     * 
     * @param scaleFactor the scale factor
     */
    public ScaleITransformation(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void apply(IShape IShape) {
        IShape.scale(scaleFactor);
    }
}