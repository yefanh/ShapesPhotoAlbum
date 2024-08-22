package modeltest;

import model.Color;
import model.Oval;
import model.Point;
import model.Rectangle;
import model.ScaleITransformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleITransformationTest {

  /**
   * Tests the apply method in the ScaleITransformation class.
   */
  @Test
  public void testApplyScaleTransformation() {
    // Test applying scale transformation to a rectangle
    Rectangle rectangle = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0,
      new Color(255, 0, 0));
    ScaleITransformation scaleTransformation = new ScaleITransformation(2.0);
    scaleTransformation.apply(rectangle);
    assertEquals(40.0, rectangle.getWidth());
    assertEquals(60.0, rectangle.getHeight());

    // Test applying scale transformation to an oval
    Oval oval = new Oval("TestOval", new Point(20, 20), 40.0, 50.0,
      new Color(0, 0, 255));
    scaleTransformation.apply(oval);
    assertEquals(80.0, oval.getRadiusX());
    assertEquals(100.0, oval.getRadiusY());
  }

  /**
   * Tests the apply method in the ScaleITransformation class with a negative scale factor.
   */
  @Test
  public void testNegativeScaleFactor() {
    // Test applying scale transformation with a negative scale factor
    Rectangle rectangle = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0,
      new Color(255, 0, 0));
    ScaleITransformation scaleTransformation = new ScaleITransformation(-2.0);
    scaleTransformation.apply(rectangle);
    assertEquals(-40.0, rectangle.getWidth());
    assertEquals(-60.0, rectangle.getHeight());
  }

  /**
   * Tests the apply method in the ScaleITransformation class with a zero scale factor.
   */
  @Test
  public void testZeroScaleFactor() {
    // Test applying scale transformation with a zero scale factor
    Rectangle rectangle = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0,
      new Color(255, 0, 0));
    ScaleITransformation scaleTransformation = new ScaleITransformation(0.0);
    scaleTransformation.apply(rectangle);
    assertEquals(0.0, rectangle.getWidth());
    assertEquals(0.0, rectangle.getHeight());
  }
}
