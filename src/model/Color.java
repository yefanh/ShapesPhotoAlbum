package model;

public class Color {
    private int red;
    private int green;
    private int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Color color = (Color) obj;
        return red == color.red &&
                green == color.green &&
                blue == color.blue;
    }

    @Override
    public String toString() {
        return "rgb(" + red + ", " + green + ", " + blue + ")";
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(red, green, blue);
    }
}
