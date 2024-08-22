package view;

import model.Color;
import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Point;
import model.Rectangle;
import model.Snapshot;

/**
 * This class represents the web view of the photo album.
 */
public class WebView implements IWebView{
    private PhotoAlbum model;

    /**
     * Constructs a web view with the given model.
     *
     * @param model the model
     */
    public WebView(PhotoAlbum model) {
        this.model = model;
    }

    /**
     * Generates the HTML for the photo album.
     *
     * @return the HTML
     */
    public String generateHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>Photo Album Snapshots</title>\n")
                .append("</head>\n")
                .append("<body>\n");

        for (Snapshot snapshot : model.getSnapshots()) {
            sb.append("<div>\n")
                    .append("<h2>").append(snapshot.getSnapshotID()).append("</h2>\n");

            if (!snapshot.getDescription().isEmpty()) {
                sb.append("<h3>").append("Description: ").append(snapshot.getDescription()).append("</h3>\n");
            }

            sb.append("<svg width =\"1000\" height=\"800\" xmlns=\"http://www.w3.org/2000/svg\">\n");

            // Debug: Print details about each snapshot
            System.out.println("Processing snapshot: " + snapshot.getDescription()); // Debug added

            for (IShape shape : snapshot.getShapes()) {
                System.out.println(
                        "Processing shape: " + shape.getName() + ", " + shape.getColor() + ", " + shape.getLocation()); // Debug
                                                                                                                        // added
                Color color = shape.getColor();
                Point location = shape.getLocation();

                // Debug: Print details about each shape
                System.out.printf("Adding %s at (%f, %f) with color %s\n", // Debug added
                        shape.getClass().getSimpleName(), location.getX(), location.getY(),
                        color.toString());

                if (shape instanceof Rectangle) {
                    Rectangle rect = (Rectangle) shape;
                    sb.append(String.format(
                            "<rect x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" fill=\"rgb(%d,%d,%d)\" />\n",
                            location.getX(), location.getY(),
                            rect.getWidth(), rect.getHeight(),
                            color.getRed(), color.getGreen(), color.getBlue()));
                } else if (shape instanceof Oval) {
                    Oval oval = (Oval) shape;

                    sb.append(String.format(
                            "<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"rgb(%d,%d,%d)\" />\n",
                            location.getX(), location.getY(),
                            oval.getRadiusX(), oval.getRadiusY(),
                            color.getRed(), color.getGreen(), color.getBlue()));
                }
            }

            sb.append("</svg>\n")
                    .append("</div>\n");
        }

        sb.append("</body>\n")
                .append("</html>\n");

        return sb.toString();
    }
}
