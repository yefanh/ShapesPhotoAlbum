package modeltest;

import model.Color;
import model.IShape;
import model.ISnapshot;
import model.Oval;
import model.Point;
import model.Rectangle;
import model.Snapshot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the Snapshot class.
 */
class SnapshotTest {

  private ISnapshot snapshot;
  private List<IShape> IShapes;
  private String description;
  private LocalDateTime beforeCreation, afterCreation;

  /**
   * Sets up the test fixture.
   */
  @BeforeEach
  void setUp() {
    IShape rectangle = new Rectangle("model.Rectangle", new Point(10, 10), 20.0, 10.0,
      new model.Color(0, 0, 255));
    IShape oval = new Oval("model.Oval", new Point(20, 20), 15.0, 7.5,
      new model.Color(255, 0, 0));
    IShapes = List.of(rectangle, oval);
    description = "Test model.Snapshot";
    beforeCreation = LocalDateTime.now();
    snapshot = new Snapshot(IShapes, description);
    afterCreation = LocalDateTime.now();
  }

  /**
   * Tests the properties of the Snapshot class.
   */
  @Test
  void testSnapshotProperties() {
    // Test timestamp
    assertTrue(snapshot.getTimestamp().isAfter(beforeCreation) && snapshot.getTimestamp().isBefore(afterCreation),
      "model.Snapshot timestamp should be between beforeCreation and afterCreation times.");

    // Test shapes
    List<IShape> retrievedIShapes = snapshot.getShapes();
    assertEquals(IShapes.size(), retrievedIShapes.size(), "Retrieved shapes list should match the size of the original.");
    assertNotSame(IShapes, retrievedIShapes, "Retrieved shapes list should not be the same instance as the original.");

    // Test description
    assertEquals(description, snapshot.getDescription(), "model.Snapshot description should match the provided description.");
  }

  /**
   * Tests the toString method of the Snapshot class.
   */
  @Test
  void testToStringMethod() {
    String snapshotString = snapshot.toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    assertAll("model.Snapshot toString validation",
      // Check if the snapshot description is in the string
      () -> assertTrue(snapshotString.contains("Description: " + (snapshot.getDescription().isEmpty() ? "N/A" : snapshot.getDescription())),
        "toString output should contain the snapshot description."),

      // Check if the timestamp is in the string
      () -> assertTrue(snapshotString.contains("Snapshot ID: " + snapshot.getTimestamp().format(formatter)),
        "toString output should contain the timestamp prefix."),

      // Check if the string contains the correct shape information
      () -> snapshot.getShapes().forEach(shape -> assertTrue(snapshotString.contains(shape.getDescription()),
        "toString output should contain descriptions of all shapes."))
    );
  }

  /**
   * Tests the equals method of the Snapshot class.
   */
  @Test
  void testEmptyShapesList() {
    Snapshot snapshot = new Snapshot(Collections.emptyList(), "Empty Shapes");
    assertTrue(snapshot.getShapes().isEmpty(), "model.Snapshot with an empty shapes list should be empty.");
  }

  /**
   * Tests the equals method of the Snapshot class.
   */
  @Test
  void testNullShapesList() {
    Exception exception = assertThrows(NullPointerException.class, () -> new Snapshot(null, "Null Shapes"),
      "Constructor should throw NullPointerException for null shapes list.");
  }

  /**
   * Tests the equals method of the Snapshot class.
   */
  @Test
  void testEmptyAndNullDescription() {
    Snapshot snapshotEmptyDesc = new Snapshot(Collections.emptyList(), "");
    assertEquals("", snapshotEmptyDesc.getDescription(), "model.Snapshot description should be empty string.");

    Snapshot snapshotNullDesc = new Snapshot(Collections.emptyList(), null);
    assertNull(snapshotNullDesc.getDescription(), "model.Snapshot description should be null.");
  }

  /**
   * Tests the equals method of the Snapshot class.
   */
  @Test
  void testShapesListImmutability() {
    IShape IShape = new Rectangle("model.Rectangle", new Point(10, 10), 20.0, 10.0,
      new Color(0, 0, 255));
    Snapshot snapshot = new Snapshot(List.of(IShape), "Test Immutability");
    List<IShape> IShapes = snapshot.getShapes();
    Exception exception = assertThrows(UnsupportedOperationException.class, () -> IShapes.add(IShape),
      "Modifying the shapes list should throw UnsupportedOperationException.");
  }

  /**
   * Tests the equals method of the Snapshot class.
   */
  @Test
  void testLargeNumberOfShapes() {
    List<IShape> largeIShapeList = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      largeIShapeList.add(new Rectangle("model.Rectangle " + i, new Point(i, i), i * 10.0, i * 5.0,
        new Color(0, 0, 255)));
    }
    Snapshot snapshot = new Snapshot(largeIShapeList, "Large Number of Shapes");
    assertEquals(1000, snapshot.getShapes().size(), "model.Snapshot should contain 1000 shapes.");
  }
}
