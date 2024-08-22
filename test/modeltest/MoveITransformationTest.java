package modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Color;
import model.IShape;
import model.ITransformation;
import model.MoveITransformation;
import model.Oval;
import model.Point;
import model.Rectangle;
import org.junit.jupiter.api.Test;

/**
 * This class tests the MoveITransformation class.
 */
public class MoveITransformationTest {

  /**
   * Tests the apply method in the MoveITransformation class.
   */
  @Test
  public void testApplyMoveTransformationToRectangle() {
    // Create a rectangle shape
    IShape IShape = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0, new model.Color(255, 0, 0));

    // Create a new position for the transformation
    Point newPosition = new Point(50, 50);

    // Apply move transformation
    ITransformation moveITransformation = new MoveITransformation(newPosition);
    moveITransformation.apply(IShape);

    // Check if the shape has moved to the new position
    assertEquals(newPosition, IShape.getLocation());
  }

  /**
   * Tests the apply method in the MoveITransformation class with an oval shape.
   */
  @Test
  public void testApplyMoveTransformationToOval() {
    // Create an oval shape
    IShape IShape = new Oval("TestOval", new Point(20, 20), 40.0, 50.0, new Color(255, 0, 0));

    // Create a new position for the transformation
    Point newPosition = new Point(100, 100);

    // Apply move transformation
    ITransformation moveITransformation = new MoveITransformation(newPosition);
    moveITransformation.apply(IShape);

    // Check if the shape has moved to the new position
    assertEquals(newPosition, IShape.getLocation());
  }

  /**
   * Tests the apply method in the MoveITransformation class with negative coordinates.
   */
  @Test
  public void testApplyMoveTransformationWithNegativeCoordinates() {
    // Create a shape
    IShape IShape = new Rectangle("TestShape", new Point(30, 30), 60.0, 70.0, new Color(255, 0, 0));

    // Create a new position with negative coordinates for the transformation
    Point newPosition = new Point(-50, -50);

    // Apply move transformation
    ITransformation moveITransformation = new MoveITransformation(newPosition);
    moveITransformation.apply(IShape);

    // Check if the shape has moved to the new position with negative coordinates
    assertEquals(newPosition, IShape.getLocation());
  }

  /**
   * Tests the apply method in the MoveITransformation class with the same position.
   */
  @Test
  public void testApplyMoveTransformationToSamePosition() {
    // Create a shape
    IShape IShape = new Oval("TestShape", new Point(40, 40), 80.0, 90.0, new Color(255, 0, 0));

    // Create the same position for the transformation
    Point samePosition = new Point(40, 40);

    // Apply move transformation
    ITransformation moveITransformation = new MoveITransformation(samePosition);
    moveITransformation.apply(IShape);

    // Check if the shape remains at the same position
    assertEquals(samePosition, IShape.getLocation());
  }
}
