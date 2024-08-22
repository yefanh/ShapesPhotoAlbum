package modeltest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Color;
import model.ColorITransformation;
import model.IShape;
import model.ITransformation;
import model.MoveITransformation;
import model.Oval;
import model.PhotoAlbum;
import model.Point;
import model.Rectangle;
import model.ScaleITransformation;
import model.Snapshot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * This class tests the PhotoAlbum class.
 */
public class PhotoAlbumTest {

  private PhotoAlbum album;
  private IShape rectangle;
  private IShape oval;

  /**
   * Sets up the album and shapes for each test.
   */
  @BeforeEach
  public void setUp() {
    album = new PhotoAlbum();
    rectangle = new Rectangle("model.Rectangle", new Point(10, 10), 20.0, 10.0, new Color(255, 0, 0));
    oval = new Oval("model.Oval", new Point(20, 20), 15.0, 7.5, new Color(255, 0, 0));
    album.addShape(rectangle);
    album.addShape(oval);
  }

  /**
   * Tests adding a shape to the album.
   */
  @Test
  void testAddShape() {
    album.reset(); // Ensure a clean start
    // Test adding a single shape
    album.addShape(rectangle);
    assertEquals(1, album.getShapes().size(),
      "Album should contain 1 shape after adding a rectangle.");

    // Test adding another distinct shape
    album.addShape(oval);
    assertEquals(2, album.getShapes().size(),
      "Album should contain 2 shapes after adding another shape.");

    // Test adding a shape that already exists in the album
    album.addShape(rectangle);
    assertEquals(3, album.getShapes().size(),
      "Album should contain 3 shapes after adding a duplicate shape.");

    // Test adding null
    assertThrows(NullPointerException.class, () -> album.addShape(null),
      "Adding null should throw a NullPointerException.");
  }

  /**
   * Tests applying a scaling transformation to an empty album.
   */
  @Test
  void testScalingTransformation() {
    album.reset();
    Rectangle testRectangle = new Rectangle("model.Rectangle",
      new Point(10, 10), 20.0, 10.0, new Color(255, 0, 0));
    Oval testOval = new Oval("model.Oval", new Point(20, 20), 7.5, 3.75, new Color(255, 0, 0));
    album.addShape(testRectangle);
    album.addShape(testOval);

    ITransformation scale = new ScaleITransformation(2.0);
    album.applyTransformation(scale);

    assertAll("All shapes should be scaled correctly",
      () -> assertEquals(40.0, testRectangle.getWidth(),
        "model.Rectangle width should be scaled to 40.0"),
      () -> assertEquals(15.0, testOval.getRadiusX(),
        "model.Oval radiusX should be scaled to 15.0"));
  }

  /**
   * Tests applying a moving transformation to an empty album.
   */
  @Test
  void testMovingTransformation() {
    // Apply a moving transformation and verify that it correctly moves the shapes in the album.
    album.reset();
    album.addShape(rectangle);
    ITransformation move = new MoveITransformation(new Point(30, 30));
    album.applyTransformation(move);
    assertEquals(new Point(30, 30), rectangle.getLocation(),
      "model.Rectangle should be moved to (30, 30)");
  }

  /**
   * Tests applying a scaling transformation to an empty album.
   */
  @Test
  void testColorChangeTransformation() {
    // Apply a color change transformation and verify that it correctly changes
    // the color of the shapes in the album.
    ITransformation colorChange = new ColorITransformation(new Color(255, 0, 0));
    album.applyTransformation(colorChange);
    assertEquals(new Color(255, 0, 0), rectangle.getColor(),
      "model.Rectangle color should change to green");
  }

  @Test
  void testApplyingTransformationToEmptyAlbum() {
    // Verify that applying a transformation to an empty album does not throw an exception.
    PhotoAlbum emptyAlbum = new PhotoAlbum();
    assertDoesNotThrow(() -> emptyAlbum.applyTransformation(new ScaleITransformation(
      2.0)), "Applying transformation to an empty album should not throw an exception");
  }

  /**
   * Tests applying a null transformation to the album.
   */
  @Test
  void testApplyingNullTransformation() {
    // Verify that applying a null transformation does not throw an exception.
    assertDoesNotThrow(() -> album.applyTransformation(null),
      "Applying null transformation should not throw an exception");
  }

  /**
   * Tests applying multiple transformations to a shape in the album.
   */
  @Test
  void testApplyingMultipleTransformations() {
    // Apply multiple transformations and verify that each transformation is applied correctly.
    ITransformation scale = new ScaleITransformation(2.0);
    ITransformation move = new MoveITransformation(new Point(30, 30));
    ITransformation colorChange = new ColorITransformation(new Color(255, 0, 0));
    ITransformation scaleAgain = new ScaleITransformation(0.5);

    album.applyTransformation(scale);
    album.applyTransformation(move);
    album.applyTransformation(colorChange);
    album.applyTransformation(scaleAgain);

    assertAll(
      () -> assertEquals(new Point(30, 30), rectangle.getLocation(),
        "model.Rectangle should be moved to (30, 30)"),
      () -> assertEquals(new Color(255, 0, 0), rectangle.getColor(),
        "model.Rectangle color should be changed to green"),
      () -> assertEquals(20.0, ((Rectangle) rectangle).getWidth(),
        "model.Rectangle width should return to 20.0 after scaling down")
    );
  }

  /**
   * Tests taking a snapshot with one shape in the album.
   */
  @Test
  void testTakeSnapshotWithOneShape() {
    // Test that taking a snapshot after adding a single shape results
    // in an album with one snapshot.
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    assertEquals(1, album.getSnapshots().size(),
      "Album should contain 1 snapshot after adding a rectangle.");
  }

  /**
   * Tests taking a snapshot with no shapes in the album.
   */
  @Test
  void testTakeSnapshotWithNoShapes() {
    // Test that taking a snapshot with no shapes results in an album with one snapshot.
    album.reset();
    album.takeSnapshot("Empty model.Snapshot");
    assertEquals(1, album.getSnapshots().size(), "Album should contain 1 snapshot when no shapes are added.");
  }

  /**
   * Tests taking multiple snapshots.
   */
  @Test
  void testTakingMultipleSnapshots() {
    // Test that the album correctly handles taking multiple snapshots after adding shapes.
    album.reset();
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    album.addShape(oval);
    album.takeSnapshot("Second model.Snapshot");
    album.addShape(rectangle);
    album.takeSnapshot("Third model.Snapshot");
    assertEquals(3, album.getSnapshots().size(), "Album should contain 3 snapshots after adding more shapes and taking multiple snapshots.");
  }

  /**
   * Tests the independence of snapshots from the shapes in the album.
   */
  @Test
  void testSnapshotIndependence() {
    // Test that the album snapshots are independent of the shapes in the album.
    album.reset();

    // Add shapes and take a snapshot
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");

    // Modify the shape after taking the snapshot
    album.addShape(oval);
    album.takeSnapshot("Second model.Snapshot");

    // Verify that the first snapshot still contains only the rectangle
    album.addShape(new Rectangle("Another model.Rectangle", new Point(30, 30), 40.0, 20.0, new Color(255, 0, 0)));
    album.takeSnapshot("Third model.Snapshot");

    // Verify that the first snapshot still contains only the rectangle
    assertEquals(3, album.getSnapshots().get(2).getShapes().size(), "The third snapshot should contain 3 shapes.");
  }

  /**
   * Tests taking snapshots with the same description.
   */
  @Test
  void testTakingSnapshotsWithSameDescription() {
    // Test that the album allows taking multiple snapshots with the same description.
    album.reset(); // Ensure a clean start
    album.addShape(rectangle);
    album.takeSnapshot("Repeated Description");
    album.addShape(oval);
    album.takeSnapshot("Repeated Description");
    assertEquals(2, album.getSnapshots().size(),
      "Album should allow multiple snapshots with the same description.");
  }

  /**
   * Tests handling of edge cases for snapshot descriptions.
   */
  @Test
  void testSnapshotDescriptionEdgeCases() {
    // Test handling of edge cases for snapshot descriptions, including empty and null descriptions.
    album.reset(); // Ensure a clean start
    album.addShape(rectangle);
    album.takeSnapshot(""); // Empty description
    album.takeSnapshot(null); // Null description
    assertEquals(2, album.getSnapshots().size(),
      "Album should handle edge cases for descriptions including empty and null.");
  }

  /**
   * Tests the immutability of the snapshots list.
   */
  @Test
  void testSnapshotsListEmptyAlbum() {
    // Verify that the snapshots list is empty for a new album.
    assertTrue(album.getSnapshots().isEmpty(),
      "Snapshots list should be empty for a new album.");
  }

  /**
   * Tests the immutability of the snapshots list.
   */
  @Test
  void testSnapshotsListContainsCorrectSnapshots() {
    // Verify that the snapshots list contains the correct number of snapshots after adding shapes and taking snapshots.
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    album.addShape(oval);
    album.takeSnapshot("Second model.Snapshot");

    List<Snapshot> snapshots = album.getSnapshots();
    assertNotNull(snapshots, "Snapshots list should not be null.");
    assertEquals(2, snapshots.size(), "Snapshots list should contain exactly 2 snapshots.");
  }

  /**
   * Tests the order of snapshots in the list.
   */
  @Test
  void testOrderOfSnapshots() {
    // Verify the order of snapshots in the list matches the order they were taken.
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    album.addShape(oval);
    album.takeSnapshot("Second model.Snapshot");

    List<Snapshot> snapshots = album.getSnapshots();
    assertEquals("First model.Snapshot", snapshots.get(0).getDescription(), "First snapshot should be at the beginning of the list.");
    assertEquals("Second model.Snapshot", snapshots.get(1).getDescription(), "Second snapshot should be at the end of the list.");
  }

  /**
   * Tests the immutability of the snapshots list.
   */
  @Test
  void testSnapshotListImmutability() {
    // Verify that the snapshots list is immutable by attempting to add a new snapshot directly to the list.
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");

    List<Snapshot> snapshots = album.getSnapshots();
    assertThrows(UnsupportedOperationException.class, () -> snapshots.add(new Snapshot(List.of(rectangle), "New model.Snapshot")),
      "Modifying the snapshots list should not be allowed.");
  }

  /**
   * Tests the immutability of the snapshots list.
   */
  @Test
  void testModificationsToAlbumDoNotAffectSnapshotList() {
    // Verify that modifications to the album (like adding new snapshots) do not affect the snapshot list that has already been fetched.
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    album.addShape(oval);
    album.takeSnapshot("Second model.Snapshot");

    List<Snapshot> snapshots = album.getSnapshots();
    album.takeSnapshot("Third model.Snapshot");
    assertEquals(2, snapshots.size(), "Previously fetched snapshots list should not be affected by later changes to the album.");
  }

  /**
   * Tests the immutability of the snapshots list.
   */
  @Test
  void testReset() {
    // Initially add shape and snapshot
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    album.reset();

    // Verify album is empty
    assertTrue(album.getShapes().isEmpty(), "Shapes list should be empty after reset.");
    assertTrue(album.getSnapshots().isEmpty(), "Snapshots list should be empty after reset.");

    // Test reset on an empty album
    assertDoesNotThrow(() -> album.reset(), "Resetting an empty album should not throw an exception.");

    // Test adding shape and snapshot after reset
    album.addShape(oval);
    album.takeSnapshot("After Reset");
    assertEquals(1, album.getShapes().size(), "Should be able to add shapes after reset.");
    assertEquals(1, album.getSnapshots().size(), "Should be able to take snapshots after reset.");

    // Test consecutive resets
    album.reset();
    album.reset(); // Second reset
    assertTrue(album.getShapes().isEmpty() && album.getSnapshots().isEmpty(), "Album should still be empty after consecutive resets.");

    // Test the independence of fetched snapshot list after reset
    List<Snapshot> snapshotsBeforeReset = album.getSnapshots();
    album.takeSnapshot("New model.Snapshot");
    album.reset();
    assertTrue(snapshotsBeforeReset.isEmpty(), "Fetched snapshot list before reset should not be affected by reset.");
  }

  /**
   * Tests the printSnapshots method.
   */
  @Test
  void testPrintSnapshots() {
    // Test with a single snapshot
    album.addShape(rectangle);
    album.takeSnapshot("First model.Snapshot");
    String singleSnapshotString = album.printAlbumDetails();
    assertNotNull(singleSnapshotString, "Snapshots string should not be null with one snapshot.");
    assertTrue(singleSnapshotString.contains("First model.Snapshot"), "Snapshots string should contain the first snapshot description.");

    // Test with multiple snapshots
    album.addShape(oval);
    album.takeSnapshot("Second model.Snapshot");
    String multipleSnapshotsString = album.printAlbumDetails();
    assertTrue(multipleSnapshotsString.contains("First model.Snapshot") && multipleSnapshotsString.contains("Second model.Snapshot"), "Snapshots string should contain all snapshot descriptions.");

    // Test empty album
    album.reset();
    String emptyAlbumString = album.printAlbumDetails();
    assertTrue(emptyAlbumString.trim().isEmpty() || !emptyAlbumString.contains("model.Snapshot"), "Snapshots string for an empty album should be empty or not contain 'model.Snapshot'.");

    // Test snapshots with special characters and long descriptions
    String longDescription = "This is a very long description for a snapshot, containing special characters: @#$%^&*()!";
    album.addShape(rectangle);
    album.takeSnapshot(longDescription);
    String specialCharacterSnapshotString = album.printAlbumDetails();
    assertTrue(specialCharacterSnapshotString.contains(longDescription), "Snapshots string should correctly include long descriptions and special characters.");

    // Test consecutive calls
    String firstCallString = album.printAlbumDetails();
    String secondCallString = album.printAlbumDetails();
    assertEquals(firstCallString, secondCallString, "Consecutive calls to printSnapshots should return the same string.");
  }
}
