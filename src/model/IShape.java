package model;

/**
 * Represents a shape in a 2D plane.
 */
public interface IShape extends Cloneable {
    /**
     * Moves the shape to a new location.
     * 
     * @param newLocation the new location
     */
    void moveTo(Point newLocation);

    /**
     * Changes the color of the shape.
     * 
     * @param newColor the new color
     */
    void changeColor(Color newColor);

    /**
     * Scales the shape by a factor.
     * 
     * @param factor the factor to scale by
     */
    void scale(double factor);

    /**
     * Returns the name of the shape.
     * 
     * @return the name of the shape
     */
    String getName();

    /**
     * Returns the location of the shape.
     * 
     * @return the location of the shape
     */
    Point getLocation();

    /**
     * Returns the color of the shape.
     * 
     * @return the color of the shape
     */
    Color getColor();

    /**
     * Returns a description of the shape.
     * 
     * @return a description of the shape
     */
    String getDescription();

    /**
     * Clones the shape.
     * 
     * @return a clone of the shape
     */
    IShape clone();
}
