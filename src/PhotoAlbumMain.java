import controller.CommandFileReader;
import controller.PhotoAlbumController;
import model.PhotoAlbum;
import view.GraphicalView;
import view.WebView;

import javax.swing.SwingUtilities;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.Desktop;
import java.io.File;

public class PhotoAlbumMain {
    public static void main(String[] args) {
        String inputFileName = null;
        String outputFileName = null;
        String viewType = null;
        int xmax = 1000;
        int ymax = 1000;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-in":
                    inputFileName = args[++i];
                    break;
                case "-out":
                    outputFileName = args[++i];
                    break;
                case "-view":
                case "-v":
                    viewType = args[++i];
                    if (i + 1 < args.length && args[i + 1].matches("\\d+")) {
                        xmax = Integer.parseInt(args[++i]);
                    }
                    if (i + 1 < args.length && args[i + 1].matches("\\d+")) {
                        ymax = Integer.parseInt(args[++i]);
                    }
                    break;
                default:
                    System.out.println("Error: Unknown argument " + args[i]);
                    return;
            }
        }

        if (inputFileName == null || viewType == null) {
            System.out.println("Error: Mandatory arguments -in and -view are required.");
            return;
        }

        final String finalInputFileName = inputFileName;

        PhotoAlbum model = new PhotoAlbum();

        CommandFileReader fileReader = new CommandFileReader(model);
        System.out.println("Starting to read the input file.");
        fileReader.readFile(finalInputFileName);
        System.out.println("File read completed.");

        if ("graphical".equalsIgnoreCase(viewType)) {
            GraphicalView view = new GraphicalView(null, model);
            PhotoAlbumController controller = new PhotoAlbumController(model, view);
            view.setController(controller);

            final int finalXmax = xmax;
            final int finalYmax = ymax;

            // Display the view on the Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                view.display(finalXmax, finalYmax);
                controller.updateView();
            });
        } else if ("web".equalsIgnoreCase(viewType)) {
            WebView webView = new WebView(model);
            String htmlContent = webView.generateHtml();
            String outputPath = outputFileName != null ? outputFileName : "album.html";

            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(htmlContent);
                System.out.println("Web photo album generated successfully at " + outputPath);

                // Open the file in the default browser
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    File htmlFile = new File(outputPath);
                    desktop.browse(htmlFile.toURI());
                } else {
                    System.out.println("Desktop is not supported, please open the file manually: " + outputPath);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while writing the web photo album file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Unsupported view type: " + viewType);
        }
    }
}
