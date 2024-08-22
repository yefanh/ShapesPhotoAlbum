package controllertest;

import controller.CommandFileReader;
import model.Color;
import model.IShape;
import model.PhotoAlbum;
import model.Point;
import model.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the CommandFileReader class.
 */
class CommandFileReaderTest {

  private PhotoAlbum album;
  private CommandFileReader commandFileReader;

  @TempDir
  Path tempDir;

  /**
   * Sets up the test fixture.
   */
  @BeforeEach
  void setUp() {
    album = new PhotoAlbum();
    commandFileReader = new CommandFileReader(album);
  }

  /**
   * Tests the create shape command.
   *
   * @throws Exception if an error occurs
   */
  @Test
  void testCreateShapeCommand() throws Exception {
    Path commandFile = writeCommandToFile("shape Rect1 rectangle 100 100 50 50 255 0 0");
    commandFileReader.readFile(commandFile.toString());
    assertEquals(1, album.getShapes().size());
    IShape shape = album.getShapes().get(0);
    assertTrue(shape instanceof Rectangle);
    assertEquals("Rect1", shape.getName());
  }

  /**
   * Tests the create shape command with an oval.
   *
   * @throws Exception if an error occurs
   */
  @Test
  void testMoveShapeCommand() throws Exception {
    album.addShape(new Rectangle("Rect1", new Point(0, 0), 10, 10, new Color(0, 0, 0)));
    Path commandFile = writeCommandToFile("move Rect1 100 100");
    commandFileReader.readFile(commandFile.toString());
    IShape shape = album.getShapes().get(0);
    assertEquals(new Point(100, 100), shape.getLocation());
  }

  /**
   * Tests the change color command.
   *
   * @throws Exception if an error occurs
   */
  @Test
  void testChangeColorCommand() throws Exception {
    album.addShape(new Rectangle("Rect1", new Point(0, 0), 10, 10, new Color(0, 0, 0)));
    Path commandFile = writeCommandToFile("color Rect1 255 0 0");
    commandFileReader.readFile(commandFile.toString());
    IShape shape = album.getShapes().get(0);
    assertEquals(new Color(255, 0, 0), shape.getColor());
  }

  /**
   * Tests the resize shape command.
   *
   * @throws Exception if an error occurs
   */
  @Test
  void testResizeShapeCommand() throws Exception {
    album.addShape(new Rectangle("Rect1", new Point(0, 0), 10, 10, new Color(0, 0, 0)));
    Path commandFile = writeCommandToFile("resize Rect1 100 200");
    commandFileReader.readFile(commandFile.toString());
    IShape shape = album.getShapes().get(0);
    assertTrue(shape instanceof Rectangle);
    Rectangle rectangle = (Rectangle) shape;
    assertEquals(100, rectangle.getWidth());
    assertEquals(200, rectangle.getHeight());
  }

  /**
   * Tests the remove shape command.
   *
   * @throws Exception if an error occurs
   */
  @Test
  void testRemoveShapeCommand() throws Exception {
    album.addShape(new Rectangle("Rect1", new Point(0, 0), 10, 10, new Color(0, 0, 0)));
    assertEquals(1, album.getShapes().size());
    Path commandFile = writeCommandToFile("remove Rect1");
    commandFileReader.readFile(commandFile.toString());
    assertTrue(album.getShapes().isEmpty());
  }

  /**
   * Tests the snapshot command.
   *
   * @throws Exception if an error occurs
   */
  @Test
  void testSnapshotCommand() throws Exception {
    Path commandFile = writeCommandToFile("snapshot Description of the snapshot");
    commandFileReader.readFile(commandFile.toString());
    assertEquals(1, album.getSnapshots().size());
  }

  /**
   * Tests the undo command.
   *
   * @throws Exception if an error occurs
   */
  private Path writeCommandToFile(String command) throws Exception {
    Path commandFile = tempDir.resolve("commands.txt");
    Files.write(commandFile, command.getBytes());
    return commandFile;
  }
}
