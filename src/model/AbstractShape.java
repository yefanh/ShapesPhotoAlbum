package model;

/**
 * Represents a shape in a 2D plane.
 */
public abstract class AbstractShape implements IShape {
    protected Point location;
    protected Color color;
    protected String name;

    /**
     * Creates a new shape with the given name, location, and color.
     * 
     * @param name     the name of the shape
     * @param location the location of the shape
     * @param color    the color of the shape
     */
    public AbstractShape(String name, Point location, Color color) {
        this.name = name;
        this.location = location;
        this.color = color;
    }

    @Override
    public void moveTo(Point newLocation) {
        this.location = newLocation;
    }

    @Override
    public void changeColor(Color newColor) {
        this.color = newColor;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Point getLocation() {
        return this.location;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public abstract void scale(double factor);

    @Override
    public abstract String getDescription();

    @Override
    public AbstractShape clone() {
        try {
            AbstractShape cloned = (AbstractShape) super.clone();
            // Point is mutable, so we need to clone it
            cloned.location = (Point) this.location.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            // This should never happen because model.AbstractShape implements Cloneable
            throw new RuntimeException("Clone not supported", e);
        }
    }
}