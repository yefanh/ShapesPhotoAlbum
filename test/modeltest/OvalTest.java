package modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Color;
import model.Oval;
import model.Point;
import org.junit.jupiter.api.Test;

/**
 * This class tests the Oval class.
 */
public class OvalTest {

  /**
   * Tests the creation of an oval.
   */
  @Test
  public void testOvalCreation() {
    // Test regular creation
    Oval oval = new Oval("TestOval", new Point(10, 10), 20.0, 30.0, new Color(255, 0, 0));
    assertEquals("TestOval", oval.getName());
    assertEquals(new Point(10, 10), oval.getLocation());
    assertEquals(new Color(255, 0, 0), oval.getColor());
    assertEquals("Name: TestOval\nType: oval\nCenter: (10.0,10.0), "
      + "X radius: 20.0, Y radius: 30.0, Color: (255,0,0)", oval.getDescription());

    // Test creation with negative radiusX
    Oval ovalNegativeRadiusX = new Oval("TestOvalNegativeRadiusX",
      new Point(0, 0), -10.0, 20.0, new Color(255, 0, 0));
    assertEquals("TestOvalNegativeRadiusX", ovalNegativeRadiusX.getName());
    assertEquals(new Point(0, 0), ovalNegativeRadiusX.getLocation());
    assertEquals(new Color(255, 0, 0), ovalNegativeRadiusX.getColor());
    assertEquals("Name: TestOvalNegativeRadiusX\nType: oval\nCenter: "
        + "(0.0,0.0), X radius: -10.0, Y radius: 20.0, Color: (255,0,0)",
      ovalNegativeRadiusX.getDescription());

    // Test creation with negative radiusY
    Oval ovalNegativeRadiusY = new Oval("TestOvalNegativeRadiusY",
      new Point(0, 0), 10.0, -20.0, new Color(255, 0, 0));
    assertEquals("TestOvalNegativeRadiusY", ovalNegativeRadiusY.getName());
    assertEquals(new Point(0, 0), ovalNegativeRadiusY.getLocation());
    assertEquals(new Color(255, 0, 0), ovalNegativeRadiusY.getColor());
    assertEquals("Name: TestOvalNegativeRadiusY\nType: oval\nCenter: "
        + "(0.0,0.0), X radius: 10.0, Y radius: -20.0, Color: (255,0,0)",
      ovalNegativeRadiusY.getDescription());

    // Test creation with zero radiusX
    Oval ovalZeroRadiusX = new Oval("TestOvalZeroRadiusX", new Point(0, 0), 0.0, 20.0, new Color(255, 0, 0));
    assertEquals("TestOvalZeroRadiusX", ovalZeroRadiusX.getName());
    assertEquals(new Point(0, 0), ovalZeroRadiusX.getLocation());
    assertEquals(new Color(255, 0, 0), ovalZeroRadiusX.getColor());
    assertEquals("Name: TestOvalZeroRadiusX\nType: oval\nCenter: (0.0,0.0), "
      + "X radius: 0.0, Y radius: 20.0, Color: (255,0,0)", ovalZeroRadiusX.getDescription());

    // Test creation with zero radiusY
    Oval ovalZeroRadiusY = new Oval("TestOvalZeroRadiusY", new Point(0, 0), 10.0, 0.0, new Color(255, 0, 0));
    assertEquals("TestOvalZeroRadiusY", ovalZeroRadiusY.getName());
    assertEquals(new Point(0, 0), ovalZeroRadiusY.getLocation());
    assertEquals(new Color(255, 0, 0), ovalZeroRadiusY.getColor());
    assertEquals("Name: TestOvalZeroRadiusY\nType: oval\nCenter: (0.0,0.0), "
      + "X radius: 10.0, Y radius: 0.0, Color: (255,0,0)", ovalZeroRadiusY.getDescription());
  }

  /**
   * Tests the scaling of an oval.
   */
  @Test
  public void testScaleOval() {
    // Test scaling with positive factor
    Oval ovalPositiveFactor = new Oval("ScalableOval",
      new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    ovalPositiveFactor.scale(2.0);
    assertEquals("Name: ScalableOval\nType: oval\nCenter: (30.0,30.0), "
        + "X radius: 120.0, Y radius: 240.0, Color: (0,255,0)",
      ovalPositiveFactor.getDescription());

    // Test scaling with zero factor
    Oval ovalZeroFactor = new Oval("ScalableOval", new Point(30, 30), 60.0, 120.0, new Color(0, 255, 0));
    ovalZeroFactor.scale(0.0);
    assertEquals("Name: ScalableOval\nType: oval\nCenter: (30.0,30.0), "
      + "X radius: 0.0, Y radius: 0.0, Color: (0,255,0)", ovalZeroFactor.getDescription());

    // Test scaling with negative factor - Assuming your model allows for negative
    // scaling, which might not be realistic
    Oval ovalNegativeFactor = new Oval("ScalableOval", new Point(30, 30),
      60.0, 120.0, new Color(0, 255, 0));
    ovalNegativeFactor.scale(-2.0);
    assertEquals("Name: ScalableOval\nType: oval\nCenter: (30.0,30.0), "
        + "X radius: -120.0, Y radius: -240.0, Color: (0,255,0)",
      ovalNegativeFactor.getDescription());
  }

  /**
   * Tests the description of an oval.
   */
  @Test
  public void testOvalDescription() {
    // Test with positive radiusX and radiusY
    Oval ovalPositiveRadius = new Oval("DescriptiveOval",
      new Point(40, 40), 80.0, 160.0, new Color(0, 255, 0));
    String expectedDescriptionPositive = "Name: DescriptiveOval\nType: "
      + "oval\nCenter: (40.0,40.0), X radius: 80.0, Y radius: 160.0, Color: (0,255,0)";
    assertEquals(expectedDescriptionPositive, ovalPositiveRadius.getDescription());

    // Test with zero radiusX and radiusY
    Oval ovalZeroRadius = new Oval("DescriptiveOval", new Point(40, 40), 0.0, 0.0,
      new Color(0, 255, 0));
    String expectedDescriptionZero = "Name: DescriptiveOval\nType: oval\nCenter: "
      + "(40.0,40.0), X radius: 0.0, Y radius: 0.0, Color: (0,255,0)";
    assertEquals(expectedDescriptionZero, ovalZeroRadius.getDescription());

    // Test with negative radiusX and radiusY
    Oval ovalNegativeRadius = new Oval("DescriptiveOval",
      new Point(40, 40), -80.0, -160.0, new Color(0, 255, 0));
    String expectedDescriptionNegative = "Name: DescriptiveOval\nType: "
      + "oval\nCenter: (40.0,40.0), X radius: -80.0, Y radius: -160.0, Color: (0,255,0)";
    assertEquals(expectedDescriptionNegative, ovalNegativeRadius.getDescription());
  }
}