package model;

/**
 * Main class to test the photo album.
 */
public class TestModel {
    /**
     * Main method to test the photo album.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Initialize the photo album
        PhotoAlbum album = new PhotoAlbum();

        // Create shapes
        Rectangle rectangle = new Rectangle("R", new Point(200, 200), 50, 100,
                new Color(255, 0, 0));
        Oval oval = new Oval("O", new Point(500, 100), 60, 30, new Color(0, 0, 255));

        // Add shapes to the album
        album.addShape(rectangle);
        album.addShape(oval);

        // Take a snapshot after adding shapes
        album.takeSnapshot("Initial State");

        // Apply transformations
        rectangle.moveTo(new Point(100, 300));
        rectangle.scale(0.5);
        rectangle.changeColor(new Color(0, 255, 0)); // Change color to green
        oval.moveTo(new Point(500, 400));

        // Take another snapshot after transformations
        album.takeSnapshot("After transformations");

        // Remove the rectangle and take another snapshot
        album.removeShape(rectangle);
        album.takeSnapshot("Final State");

        // Print all snapshots
        System.out.println(album.printAlbumDetails());
    }
}