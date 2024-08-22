package view;

import java.util.List;

import model.IShape;

/**
 * Represents a graphical view of the photo album.
 */
public interface IGraphicalView {
    /**
     * Displays the photo album with the given width and height.
     *
     * @param width  the width
     * @param height the height
     */
    void display(int width, int height);

    /**
     * Displays the given snapshot.
     *
     * @param snapshotText the snapshot text
     * @param snapshotID   the snapshot ID
     * @param shapes       the shapes
     */
    void displaySnapshot(String snapshotText, String snapshotID, List<IShape> shapes);

    /**
     * Shows the select menu.
     */
    void showSelectMenu();

    /**
     * Displays the given message with the given title.
     *
     * @param message the message
     * @param title   the title
     */
    void displayMessage(String message, String title);
}
