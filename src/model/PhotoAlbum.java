package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a photo album.
 */
public class PhotoAlbum implements IPhotoAlbum {
    private List<IShape> IShapes;
    private List<Snapshot> snapshots;

    /**
     * Creates a new photo album.
     */
    public PhotoAlbum() {
        this.IShapes = new ArrayList<>();
        this.snapshots = new ArrayList<>();
    }

    /**
     * Adds a shape to the album.
     * 
     * @param IShape the shape to add
     */
    public void addShape(IShape IShape) {
        if (IShape == null) {
            throw new NullPointerException("Cannot add null shape to the album.");
        }
        IShapes.add(IShape);
    }

    /**
     * Applies a transformation to all shapes in the album.
     * 
     * @param ITransformation the transformation to apply
     */
    public void applyTransformation(ITransformation ITransformation) {
        // If the transformation is null, do nothing
        if (ITransformation == null) {
            // Return early to avoid unnecessary processing
            return;
        }

        for (IShape IShape : IShapes) {
            ITransformation.apply(IShape);
        }
    }

    /**
     * Takes a snapshot of the current state of the album.
     * 
     * @param description the description of the snapshot
     */
    public void takeSnapshot(String description) {
        // Create a deep copy of the shapes list
        List<IShape> shapesCopy = new ArrayList<>();
        for (IShape IShape : this.IShapes) {
            shapesCopy.add(IShape.clone());
        }
        snapshots.add(new Snapshot(shapesCopy, description));
    }

    /**
     * Returns the snapshots in the album.
     * 
     * @return the snapshots
     */
    public List<Snapshot> getSnapshots() {
        // Return an unmodifiable list to prevent external modification
        return Collections.unmodifiableList(new ArrayList<>(snapshots));
    }

    /**
     * Resets the album to its initial state.
     */
    public void reset() {
        IShapes.clear();
        snapshots.clear();
    }

    /**
     * Prints the details of the album.
     * 
     * @return the details of the album
     */
    public String printAlbumDetails() {
        StringBuilder sb = new StringBuilder();
        for (Snapshot snapshot : snapshots) {
            sb.append(snapshot.toString()).append("\n\n");
        }
        return sb.toString().trim(); // To remove the last two newline characters
    }

    /**
     * Returns the shapes in the album.
     * 
     * @return the shapes
     */
    public List<IShape> getShapes() {
        // Return an unmodifiable list to prevent external modification
        return Collections.unmodifiableList(IShapes);
    }

    /**
     * Removes a shape from the album.
     * 
     * @param IShape the shape to remove
     */
    public void removeShape(IShape IShape) {
        IShapes.remove(IShape);
    }

    public IShape findShapeByName(String name) {
        for (IShape shape : IShapes) {
            if (shape.getName().equals(name)) {
                return shape;
            }
        }
        return null; // Shape not found
    }
}
