package controllertest;

import model.IShape;
import model.ITransformation;
import model.PhotoAlbum;
import model.Snapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock photo album for testing the controller.
 */
public class MockPhotoAlbum extends PhotoAlbum {
  private List<IShape> shapes = new ArrayList<>();
  private List<Snapshot> snapshots = new ArrayList<>();

  @Override
  public void addShape(IShape shape) {
    shapes.add(shape);
  }

  @Override
  public void takeSnapshot(String description) {
    // simply add a snapshot with the current shapes and description
    snapshots.add(new Snapshot(new ArrayList<>(shapes), description));
  }

  @Override
  public List<Snapshot> getSnapshots() {
    return snapshots;
  }

  @Override
  public void reset() {
    // This mock view does not need to implement this for the controller test
  }

  @Override
  public String printAlbumDetails() {
    return null;
  }

  @Override
  public List<IShape> getShapes() {
    return null;
  }

  @Override
  public void removeShape(IShape shape) {
    // This mock view does not need to implement this for the controller test
  }

  @Override
  public void applyTransformation(ITransformation transformation) {
    // This mock view does not need to implement this for the controller test
  }
}
