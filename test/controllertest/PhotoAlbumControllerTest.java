package controllertest;

import controller.PhotoAlbumController;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class tests the PhotoAlbumController class using a mock model.
 */
class PhotoAlbumControllerTest {
  private MockPhotoAlbum model;  // Use the MockPhotoAlbum instead of the real model
  private MockView view;
  private PhotoAlbumController controller;

  @BeforeEach
  void setUp() {
    model = new MockPhotoAlbum();
    view = new MockView();
    controller = new PhotoAlbumController(model, view);

    // Manually add snapshots to the mock model
    model.addShape(new Oval("TestOval", new Point(0, 0), 5.0, 10.0, new Color(255, 0, 0)));
    model.addShape(new Rectangle("TestRectangle", new Point(10, 10), 20.0, 30.0, new Color(0, 255, 0)));
    model.takeSnapshot("Initial Snapshot");
    model.takeSnapshot("Snapshot 2");
    model.takeSnapshot("Snapshot 3");

    // Initial update to set the view
    controller.updateView();
  }

  /**
   * Tests the initial view update.
   */
  @Test
  void testInitialViewUpdate() {
    assertEquals("Initial Snapshot", view.lastDisplayedSnapshotDescription);
    assertEquals(2, view.lastDisplayedShapes.size());
    assertEquals("TestOval", view.lastDisplayedShapes.get(0).getName());
    assertEquals("TestRectangle", view.lastDisplayedShapes.get(1).getName());
  }

  /**
   * Tests the onNextButtonClicked method.
   */
  @Test
  void testOnNextButtonClicked() {
    controller.onNextButtonClicked();
    int currentSnapshotIndex = controller.getCurrentSnapshotIndex();
    assertEquals(1, currentSnapshotIndex);
    assertEquals("Snapshot 2", view.lastDisplayedSnapshotDescription);
  }

  /**
   * Tests the onPrevButtonClicked method.
   */
  @Test
  void testOnPrevButtonClicked() {
    controller.onPrevButtonClicked();  // This should not change as it starts at 0
    int currentSnapshotIndex = controller.getCurrentSnapshotIndex();
    assertEquals(0, currentSnapshotIndex);
    assertEquals("Initial Snapshot", view.lastDisplayedSnapshotDescription);
  }

  /**
   * Tests the updateViewToSnapshotIndex method.
   */
  @Test
  void testEmptySnapshotList() {
    MockPhotoAlbum emptyModel = new MockPhotoAlbum();
    MockView emptyView = new MockView();
    PhotoAlbumController emptyController = new PhotoAlbumController(emptyModel, emptyView);
    emptyController.updateView();
    assertNull(emptyView.lastDisplayedSnapshotDescription, "No snapshot should be displayed");
  }

  /**
   * Tests the updateView method.
   */
  @Test
  void testBoundaryNavigation() {
    // Assuming there are already snapshots in the setup
    controller.updateViewToSnapshotIndex(0); // Move to the first snapshot
    controller.onPrevButtonClicked(); // Try to move before the first snapshot
    assertEquals(0, controller.getCurrentSnapshotIndex(), "Should still be at the first snapshot");
    assertEquals("Initial Snapshot", view.lastDisplayedSnapshotDescription);

    // Move to the last snapshot
    int lastIndex = model.getSnapshots().size() - 1;
    controller.updateViewToSnapshotIndex(lastIndex);
    controller.onNextButtonClicked(); // Try to move past the last snapshot
    assertEquals(lastIndex, controller.getCurrentSnapshotIndex(), "Should still be at the last snapshot");
    assertEquals("Snapshot 3", view.lastDisplayedSnapshotDescription);
  }

  /**
   * Tests the updateViewToSnapshotIndex method.
   */
  @Test
  void testContinuousNextPrevButtonClicks() {
    // Simulate clicking next until the end, then prev back to the start
    for (int i = 0; i < model.getSnapshots().size(); i++) {
      controller.onNextButtonClicked();
    }
    assertEquals(model.getSnapshots().size() - 1, controller.getCurrentSnapshotIndex(), "Should be at the last index");

    for (int i = model.getSnapshots().size() - 1; i > 0; i--) {
      controller.onPrevButtonClicked();
    }
    assertEquals(0, controller.getCurrentSnapshotIndex(), "Should be back at the first index");
  }

}
