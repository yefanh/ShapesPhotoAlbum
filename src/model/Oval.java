package model;

/**
 * Represents an oval in a 2D plane.
 */
public class Oval extends AbstractShape {
    private double radiusX;
    private double radiusY;

    /**
     * Creates a new oval with the given name, center, radius, and color.
     * 
     * @param name    the name of the oval
     * @param center  the center of the oval
     * @param radiusX the x radius of the oval
     * @param radiusY the y radius of the oval
     * @param color   the color of the oval
     */
    public Oval(String name, Point center, double radiusX, double radiusY, Color color) {
        super(name, center, color);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void scale(double factor) {
        this.radiusX *= factor;
        this.radiusY *= factor;
    }

    @Override
    public String getDescription() {
        return String.format("Name: %s\nType: oval\nCenter: (%.1f,%.1f), "
                + "X radius: %.1f, Y radius: %.1f, Color: (%d,%d,%d)",
                getName(), getLocation().getX(), getLocation().getY(), getRadiusX(), getRadiusY(),
                color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setDimensions(double radiusX, double radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

}