package modeltest;

import model.Color;
import model.Point;
import model.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

  /**
   * Tests the creation of a rectangle.
   */
  @Test
  public void testRectangleCreation() {
    // Test with different colors
    Rectangle rectangle1 = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0, new Color(255, 0, 0));
    assertEquals(new Color(255, 0, 0), rectangle1.getColor());

    Rectangle rectangle2 = new Rectangle("TestRect", new Point(10, 10), 20.0, 30.0, new Color(0, 0, 255));
    assertEquals(new Color(0, 0, 255), rectangle2.getColor());

    // Test with negative width or height values
    Rectangle rectangle3 = new Rectangle("TestRect", new Point(10, 10), -20.0, 30.0, new Color(255, 0, 0));
    assertEquals(-20.0, rectangle3.getWidth());

    Rectangle rectangle4 = new Rectangle("TestRect", new Point(10, 10), 20.0, -30.0, new Color(255, 0, 0));
    assertEquals(-30.0, rectangle4.getHeight());

    // Test with null or empty name
    Rectangle rectangle5 = new Rectangle(null, new Point(10, 10), 20.0, 30.0, new Color(255, 0, 0));
    assertNull(rectangle5.getName());

    Rectangle rectangle6 = new Rectangle("", new Point(10, 10), 20.0, 30.0, new Color(255, 0, 0));
    assertEquals("", rectangle6.getName());

    // Test with null location
    Rectangle rectangle7 = new Rectangle("TestRect", null, 20.0, 30.0, new Color(255, 0, 0));
    assertNull(rectangle7.getLocation());
  }

  /**
   * Tests moving a rectangle to a new location.
   */
  @Test
  public void testMoveToNewLocation() {
    // Test moving to a new positive location
    Rectangle rectangle1 = new Rectangle("MovableRect1", new Point(50, 50), 100.0, 200.0,
      new Color(0, 0, 255));
    rectangle1.moveTo(new Point(100, 200));
    assertEquals(new Point(100, 200), rectangle1.getLocation());

    // Test moving to a new negative location
    Rectangle rectangle2 = new Rectangle("MovableRect2", new Point(50, 50), 100.0, 200.0,
      new Color(0, 0, 255));
    rectangle2.moveTo(new Point(-50, -100));
    assertEquals(new Point(-50, -100), rectangle2.getLocation());

    // Test moving to a new location with zero coordinates
    Rectangle rectangle3 = new Rectangle("MovableRect3", new Point(50, 50), 100.0, 200.0,
      new Color(0, 0, 255));
    rectangle3.moveTo(new Point(0, 0));
    assertEquals(new Point(0, 0), rectangle3.getLocation());

    // Test moving to maximum coordinates
    Rectangle rectangle4 = new Rectangle("MovableRect4", new Point(50, 50), 100.0, 200.0,
      new Color(0, 0, 255));
    rectangle4.moveTo(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE));
    assertEquals(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), rectangle4.getLocation());

    // Test moving to a new location multiple times
    Rectangle rectangle5 = new Rectangle("MovableRect5", new Point(50, 50), 100.0, 200.0,
      new Color(0, 0, 255));
    rectangle5.moveTo(new Point(100, 200));
    assertEquals(new Point(100, 200), rectangle5.getLocation());
    rectangle5.moveTo(new Point(150, 250));
    assertEquals(new Point(150, 250), rectangle5.getLocation());
  }

  /**
   * Tests moving a rectangle by a certain distance.
   */
  @Test
  public void testChangeColor() {
    // Test changing to green color
    Rectangle rectangle1 = new Rectangle("ColorfulRect1", new Point(20, 20), 40.0, 80.0, new Color(255, 0, 0));
    rectangle1.changeColor(new Color(0, 255, 0));
    assertEquals(new Color(0, 255, 0), rectangle1.getColor());

    // Test changing to blue color
    Rectangle rectangle2 = new Rectangle("ColorfulRect2", new Point(20, 20), 40.0, 80.0, new Color(0, 255, 0));
    rectangle2.changeColor(new Color(0,0, 255));
    assertEquals(new Color(0,0, 255), rectangle2.getColor());

    // Test changing to red color
    Rectangle rectangle3 = new Rectangle("ColorfulRect3", new Point(20, 20), 40.0, 80.0, new Color(0, 255, 0));
    rectangle3.changeColor(new Color(255, 0, 0));
    assertEquals(new Color(255, 0, 0), rectangle3.getColor());
  }

  /**
   * Tests scaling a rectangle by a certain factor.
   */
  @Test
  public void testScaleRectangle() {
    // Test scaling with a positive factor
    Rectangle rectangle1 = new Rectangle("ScalableRect1", new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    rectangle1.scale(2.0);
    assertEquals("Name: ScalableRect1\nType: rectangle\nMin corner: (30.0,30.0), Width: 120.0, Height: 240.0, Color: (0,255,0)", rectangle1.getDescription());

    // Test scaling with a negative factor
    Rectangle rectangle2 = new Rectangle("ScalableRect2", new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    rectangle2.scale(-2.0);
    assertEquals("Name: ScalableRect2\nType: rectangle\nMin corner: (30.0,30.0), Width: -120.0, Height: -240.0, Color: (0,255,0)", rectangle2.getDescription());

    // Test scaling to very small sizes
    Rectangle rectangle3 = new Rectangle("ScalableRect3", new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    rectangle3.scale(0.01);
    assertEquals("Name: ScalableRect3\nType: rectangle\nMin corner: (30.0,30.0), Width: 0.6, Height: 1.2, Color: (0,255,0)", rectangle3.getDescription());

    // Test scaling to very large sizes
    Rectangle rectangle4 = new Rectangle("ScalableRect4", new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    rectangle4.scale(100.0);
    assertEquals("Name: ScalableRect4\nType: rectangle\nMin corner: (30.0,30.0), Width: 6000.0, Height: 12000.0, Color: (0,255,0)", rectangle4.getDescription());

    // Test scaling with zero factor
    Rectangle rectangle5 = new Rectangle("ScalableRect5", new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    rectangle5.scale(0.0);
    assertEquals("Name: ScalableRect5\nType: rectangle\nMin corner: (30.0,30.0), Width: 0.0, Height: 0.0, Color: (0,255,0)", rectangle5.getDescription());
  }

  /**
   * Tests scaling a rectangle by a certain factor.
   */
  @Test
  public void testValidRectangleDescription() {
    Rectangle rectangle = new Rectangle("DescriptiveRect", new Point(40, 40), 80.0, 160.0, new Color(255, 0, 0));
    String expectedDescription = "Name: DescriptiveRect\nType: rectangle\nMin corner: (40.0,40.0), Width: 80.0, Height: 160.0, Color: (255,0,0)";
    assertEquals(expectedDescription, rectangle.getDescription());
  }

  /**
   * Tests scaling a rectangle by a certain factor.
   */
  @Test
  public void testRectangleDescriptionWithZeroDimensions() {
    Rectangle rectangle = new Rectangle("DescriptiveRect", new Point(40, 40), 0.0, 160.0, new Color(0, 255, 0));
    String expectedDescription = "Name: DescriptiveRect\nType: rectangle\nMin corner: (40.0,40.0), Width: 0.0, Height: 160.0, Color: (0,255,0)";
    assertEquals(expectedDescription, rectangle.getDescription());
  }

  /**
   * Tests scaling a rectangle by a certain factor.
   */
  @Test
  public void testRectangleDescriptionWithNullColor() {
    Rectangle rectangle = new Rectangle("DescriptiveRect", new Point(40, 40), 80.0, 160.0, null);

    assertThrows(NullPointerException.class, () -> {
      rectangle.getDescription();
    }, "Expected getDescription() to throw NullPointerException due to null color");
  }

  /**
   * Tests scaling a rectangle by a certain factor.
   */
  @Test
  public void testRectangleDescriptionWithNullName() {
    Rectangle rectangle = new Rectangle(null, new Point(40, 40), 80.0, 160.0, new Color(0, 255, 0));
    String expectedDescription = "Name: null\nType: rectangle\nMin corner: (40.0,40.0), Width: 80.0, Height: 160.0, Color: (0,255,0)";
    assertEquals(expectedDescription, rectangle.getDescription());
  }

  /**
   * Tests scaling a rectangle by a certain factor.
   */
  @Test
  public void testRectangleDescriptionWithEmptyName() {
    Rectangle rectangle = new Rectangle("", new Point(40, 40), 80.0, 160.0, new Color(0, 255, 0));
    String expectedDescription = "Name: \nType: rectangle\nMin corner: (40.0,40.0), Width: 80.0, Height: 160.0, Color: (0,255,0)";
    assertEquals(expectedDescription, rectangle.getDescription());
  }
}