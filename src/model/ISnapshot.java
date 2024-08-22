package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for a snapshot of the state of a photo album.
 */
public interface ISnapshot {
    LocalDateTime getTimestamp();

    List<IShape> getShapes();

    String getDescription();

    String getSnapshotID();
}
