package view;

import model.IShape;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.List;;

/**
 * A panel that displays shapes.
 */
public class ShapesPanel extends JPanel {
    private List<IShape> shapes;

    /**
     * Constructs a ShapesPanel.
     */
    public ShapesPanel() {
    }

    /**
     * Gets the shapes.
     *
     * @return the shapes
     */
    public void setShapes(List<IShape> shapes) {
        this.shapes = shapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.shapes.forEach(shape -> ShapeRenderer.drawShape(g, shape));
    }
}
