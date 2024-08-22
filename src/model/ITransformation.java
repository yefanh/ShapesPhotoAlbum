package model;

/**
 * Represents a transformation.
 */
public interface ITransformation {
    /**
     * Applies the transformation to a shape.
     * 
     * @param IShape the shape to apply the transformation to
     */
    void apply(IShape IShape);
}