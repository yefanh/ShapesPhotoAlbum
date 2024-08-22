package view;

import model.Color;

/**
 * This class adapts custom Color objects to AWT Color objects.
 */
public class ColorAdapter {
    public static java.awt.Color toAwtColor(Color customColor) {
        return new java.awt.Color(customColor.getRed(), customColor.getGreen(), customColor.getBlue());
    }
}
