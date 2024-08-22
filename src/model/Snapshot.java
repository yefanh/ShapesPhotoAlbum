package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a snapshot of the state of a photo album.
 */
public class Snapshot implements ISnapshot {
    private LocalDateTime timestamp;
    private List<IShape> IShapes;
    private String description;

    /**
     * Creates a new snapshot with the given shapes and description.
     * 
     * @param IShapes     the shapes
     * @param description the description
     */
    public Snapshot(List<IShape> IShapes, String description) {
        this.timestamp = LocalDateTime.now();
        this.IShapes = new ArrayList<>(IShapes);
        this.description = description;
    }

    /**
     * Gets the timestamp of the snapshot.
     * 
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the shapes in the snapshot.
     * 
     * @return the shapes
     */
    public List<IShape> getShapes() {
        return Collections.unmodifiableList(IShapes);
    }

    /**
     * Gets the description of the snapshot.
     * 
     * @return the description
     */

    // TODO: Description not working properly
    public String getDescription() {
        return description;
    }

    /**
     * Gets the id of the snapshot.
     * 
     * @return the id
     */
    public String getSnapshotID() {
        return getTimestamp().toString();
    }

    /**
     * Sets the timestamp of the snapshot.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter Snapshot = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        DateTimeFormatter Timestamp = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        sb.append("Snapshot ID: ").append(timestamp.format(Snapshot)).append("\n");
        sb.append("Timestamp: ").append(timestamp.format(Timestamp)).append("\n");
        sb.append("Description: ").append(description.isEmpty() ? "N/A" : description).append("\n");
        for (IShape shape : IShapes) {
            sb.append(shape.getDescription()).append("\n");
        }
        return sb.toString().trim(); // To remove the last newline character
    }
}
