
package model;

/**
 * Represents a move transformation.
 */
public class MoveITransformation implements ITransformation {
    private Point newPosition;

    /**
     * Creates a new move transformation with the given new position.
     * 
     * @param newPosition the new position
     */
    public MoveITransformation(Point newPosition) {
        this.newPosition = newPosition;
    }

    @Override
    public void apply(IShape IShape) {
        IShape.moveTo(newPosition);
    }
}