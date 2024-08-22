package controller;

import model.Color;
import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Point;
import model.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Reads commands from a file and executes them on a PhotoAlbum.
 */
public class CommandFileReader {
    private PhotoAlbum album;
    private PhotoAlbumController controller;

    /**
     * Creates a new CommandFileReader that reads commands from the given file and executes them on the given album.
     *
     * @param album the album
     */
    public CommandFileReader(PhotoAlbum album) {
        this.album = album;
        this.controller = null;
    }

    /**
     * Reads commands from the given file and executes them on the album.
     *
     * @param filename the name of the file to read
     */
    public void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove extra spaces and join the parts of the command
                String command = String.join(" ", line.trim().split("\\s+"));
                executeCommand(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Don't think we need to have controller at all in this class.
        if (controller != null) {
            controller.updateView();
        }
    }

    /**
     * Executes the given command on the album.
     *
     * @param commandLine the command to execute
     */
    private void executeCommand(String commandLine) {
        if (commandLine.startsWith("#") || commandLine.trim().isEmpty()) {
            return; // Skip comment lines and empty lines
        }

        String[] parts = commandLine.split("\\s+");
        switch (parts[0].toLowerCase()) {
            case "shape":
                createShape(parts);
                break;
            case "move":
                moveShape(parts);
                break;
            case "color":
                changeColor(parts);
                break;
            case "resize":
                resizeShape(parts);
                break;
            case "remove":
                removeShape(parts);
                break;
            case "snapshot":
                takeSnapshot(parts);
                break;
            default:
                System.out.println("Unknown command: " + parts[0]);
        }
    }

    /**
     * Creates a shape with the given parameters and adds it to the album.
     *
     * @param parts the parameters of the shape
     */
    private void createShape(String[] parts) {
        String type = parts[2].toLowerCase();
        String name = parts[1];
        double x = Double.parseDouble(parts[3]);
        double y = Double.parseDouble(parts[4]);
        double param1 = Double.parseDouble(parts[5]);
        double param2 = Double.parseDouble(parts[6]);
        int r = Integer.parseInt(parts[7]);
        int g = Integer.parseInt(parts[8]);
        int b = Integer.parseInt(parts[9]);
        Color color = new Color(r, g, b);
        Point location = new Point(x, y);

        IShape shape = null;
        if (type.equals("rectangle")) {
            shape = new Rectangle(name, location, param1, param2, color);
        } else if (type.equals("oval")) {
            shape = new Oval(name, location, param1, param2, color);
        }
        if (shape != null) {
            album.addShape(shape);
        }
    }

    /**
     * Moves the shape with the given name to the given location.
     *
     * @param parts the parameters of the move command
     */
    private void moveShape(String[] parts) {
        IShape shape = album.findShapeByName(parts[1]);
        if (shape != null) {
            double newX = Double.parseDouble(parts[2]);
            double newY = Double.parseDouble(parts[3]);
            shape.moveTo(new Point(newX, newY));
        }
    }

    /**
     * Changes the color of the shape with the given name to the given color.
     *
     * @param parts the parameters of the color command
     */
    private void changeColor(String[] parts) {
        IShape shape = album.findShapeByName(parts[1]);
        if (shape != null) {
            int r = Integer.parseInt(parts[2]);
            int g = Integer.parseInt(parts[3]);
            int b = Integer.parseInt(parts[4]);
            shape.changeColor(new Color(r, g, b));
        }
    }

    /**
     * Resizes the shape with the given name to the given dimensions.
     *
     * @param parts the parameters of the resize command
     */
    private void resizeShape(String[] parts) {
        IShape shape = album.findShapeByName(parts[1]);
        if (shape instanceof Rectangle) {
            ((Rectangle) shape).setDimensions(Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
        } else if (shape instanceof Oval) {
            ((Oval) shape).setDimensions(Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
        }
    }

    /**
     * Removes the shape with the given name from the album.
     *
     * @param parts the parameters of the remove command
     */
    private void removeShape(String[] parts) {
        album.removeShape(album.findShapeByName(parts[1]));
    }

    /**
     * Takes a snapshot with the given description.
     *
     * @param parts the parameters of the snapshot command
     */
    private void takeSnapshot(String[] parts) {
        StringBuilder description = new StringBuilder();
        List<String> partsList = Arrays.asList(parts);
        partsList.forEach((part) -> {
            if (!part.equals("snapshot")) {
                description.append(part).append(" ");
            }
        });
        album.takeSnapshot(description.toString().trim());
        if (controller != null) {
            controller.updateView();
        }
    }
}
