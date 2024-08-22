package view;

import model.Color;
import model.IShape;
import model.Oval;
import model.Point;
import model.Rectangle;
import java.awt.Graphics;

/**
 * This class renders shapes.
 */
public class ShapeRenderer {

    /**
     * Draws the given shape.
     *
     * @param g     the graphics object
     * @param shape the shape
     */
    public static void drawShape(Graphics g, IShape shape) {
        if (shape instanceof Rectangle) {
            drawRectangle(g, (Rectangle) shape);
        } else if (shape instanceof Oval) {
            drawOval(g, (Oval) shape);
        }
    }

    /**
     * Draws the given rectangle.
     *
     * @param g         the graphics object
     * @param rectangle the rectangle
     */
    private static void drawRectangle(Graphics g, Rectangle rectangle) {
        Color color = rectangle.getColor();
        Point location = rectangle.getLocation();
        g.setColor(ColorAdapter.toAwtColor(color));
        g.fillRect((int) location.getX(), (int) location.getY(), (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
    }

    /**
     * Draws the given oval.
     *
     * @param g    the graphics object
     * @param oval the oval
     */
    private static void drawOval(Graphics g, Oval oval) {
        Color color = oval.getColor();
        Point center = oval.getLocation();
        g.setColor(ColorAdapter.toAwtColor(color));
        g.fillOval((int) (center.getX() - oval.getRadiusX()), (int) (center.getY() - oval.getRadiusY()),
                (int) (2 * oval.getRadiusX()), (int) (2 * oval.getRadiusY()));
    }
}
