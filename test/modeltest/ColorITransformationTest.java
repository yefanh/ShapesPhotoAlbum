package modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Color;
import model.ColorITransformation;
import model.IShape;
import model.ITransformation;
import model.Oval;
import model.Point;
import model.Rectangle;
import org.junit.jupiter.api.Test;

/**
 * This class tests the ColorITransformation class.
 */
public class ColorITransformationTest {
  /**
   * Tests the apply method in the ColorITransformation class.
   */
  @Test
  public void testApplyColorTransformationToRectangle() {
    // Create a rectangle shape
    IShape IShape = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0, new Color(255, 0, 0));

    // Apply color transformation
    Color newColor = new Color(0, 0, 255);
    ITransformation colorITransformation = new ColorITransformation(newColor);
    colorITransformation.apply(IShape);

    // Check if the color of the rectangle has changed to the new color
    assertEquals(newColor, IShape.getColor());
  }

  /**
   * Tests the apply method in the ColorITransformation class with an oval shape.
   */
  @Test
  public void testApplyColorTransformationToOval() {
    // Create an oval shape
    IShape IShape = new Oval("TestOval", new Point(10, 10), 20.0, 30.0,
      new Color(0, 255, 0));

    // Apply color transformation
    Color newColor = new Color(255, 0, 0);
    ITransformation colorITransformation = new ColorITransformation(newColor);
    colorITransformation.apply(IShape);

    // Check if the color of the oval has changed to the new color
    assertEquals(newColor, IShape.getColor());
  }
}