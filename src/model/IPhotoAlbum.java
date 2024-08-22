package model;

import java.util.List;

/**
 * Interface for a photo album.
 */
public interface IPhotoAlbum {
    void addShape(IShape shape);

    void applyTransformation(ITransformation transformation);

    void takeSnapshot(String description);

    List<Snapshot> getSnapshots();

    void reset();

    String printAlbumDetails();

    List<IShape> getShapes();

    void removeShape(IShape shape);
}
