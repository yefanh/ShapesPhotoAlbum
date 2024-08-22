package model;

/**
 * Represents a rectangle in a 2D plane.
 */
public class Rectangle extends AbstractShape {
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the given name, top-left corner, width, height,
     * and color.
     * 
     * @param name    the name of the rectangle
     * @param topLeft the top-left corner of the rectangle
     * @param width   the width of the rectangle
     * @param height  the height of the rectangle
     * @param color   the color of the rectangle
     */
    public Rectangle(String name, Point topLeft, double width, double height, Color color) {
        super(name, topLeft, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void scale(double factor) {
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public String getDescription() {
        return String.format("Name: %s\nType: rectangle\nMin corner: (%.1f,%.1f), "
                + "Width: %.1f, Height: %.1f, Color: (%d,%d,%d)",
                getName(), getLocation().getX(), getLocation().getY(), getWidth(), getHeight(),
                color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}