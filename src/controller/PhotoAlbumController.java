package controller;

import model.PhotoAlbum;
import model.Snapshot;
import view.IGraphicalView;

import java.util.List;

/**
 * Controller class for the photo album application.
 */
public class PhotoAlbumController {
    private PhotoAlbum model; // The photo album model
    private IGraphicalView view; // The view interface
    private int currentSnapshotIndex = 0;

    /**
     * Creates a new PhotoAlbumController with the given model and view.
     *
     * @param model the photo album model
     * @param view  the view
     */
    public PhotoAlbumController(PhotoAlbum model, IGraphicalView view) {
        this.model = model;
        this.view = view;
        if (!model.getSnapshots().isEmpty()) {
            updateView(); // Ensure the view is updated with the first snapshot on initialization
        }
    }

    /**
     * Updates the view with the current snapshot.
     */
    public void updateView() {
        List<Snapshot> snapshots = model.getSnapshots();
        if (currentSnapshotIndex >= 0 && currentSnapshotIndex < snapshots.size()) {
            Snapshot currentSnapshot = snapshots.get(currentSnapshotIndex);
            if (view != null) {
                view.displaySnapshot(currentSnapshot.getDescription(), currentSnapshot.getSnapshotID(),
                        currentSnapshot.getShapes());
            }
        }
    }

    /**
     * Sets the view for this controller.
     *
     * @param view the view
     */
    public void setView(IGraphicalView view) {
        this.view = view;
        updateView();
    }

    /**
     * Gets the model for this controller.
     *
     * @return the model
     */
    public PhotoAlbum getModel() {
        return model;
    }

    /**
     * Handles the event when the "Previous" button is clicked.
     */
    public void onPrevButtonClicked() {
        if (view == null) {
            System.out.println("View is not initialized");
            return;
        }
        // Check if there is a previous snapshot
        if (currentSnapshotIndex > 0) {
            // Move to the previous snapshot
            currentSnapshotIndex--;
            updateView();
        } else {
            // No previous snapshots, maybe loop around or alert the user
            view.displayMessage("This is the first snapshot.", "Message");
        }
    }

    /**
     * Handles the event when the "Next" button is clicked.
     */
    public void onNextButtonClicked() {
        List<Snapshot> snapshots = model.getSnapshots();
        if (currentSnapshotIndex < snapshots.size() - 1) {
            currentSnapshotIndex++;
            updateView();
        } else {
            view.displayMessage("This is the last snapshot.", "Message");
        }
    }

    /**
     * Returns the current snapshot index.
     *
     * @return the current snapshot index
     */
    public int getCurrentSnapshotIndex() {
        return currentSnapshotIndex;
    }

    /**
     * Updates the view to display the snapshot at the given index.
     *
     * @param index the index of the snapshot to display
     */
    public void updateViewToSnapshotIndex(int index) {
        List<Snapshot> snapshots = model.getSnapshots();
        if (index >= 0 && index < snapshots.size()) {
            currentSnapshotIndex = index;
            Snapshot selectedSnapshot = snapshots.get(currentSnapshotIndex);
            if (view != null) {
                view.displaySnapshot(selectedSnapshot.getDescription(), selectedSnapshot.getSnapshotID(),
                        selectedSnapshot.getShapes());
            }
        }
    }
}
