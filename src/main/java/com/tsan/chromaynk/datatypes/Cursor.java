package main.java.com.tsan.chromaynk.datatypes;

public class Cursor {

    private double x = 0, y = 0;
    private int red = 0, green = 0, blue = 0;
    private double rotation = 0;

    public Cursor(){}

    public void rotate(float value)
    {
        rotation = (rotation + value) % 360; 
    }

    // throws coordinates out of range exception
    public void moveCursorBy(int x, int y)
    {
        // check if in range
        this.x += x;
        this.y += y;
    }

    // throws coordinates out of range exception
    public void moveCursorTo(int x, int y)
    {
        // check if parameters in range
        this.x = x;
        this.y = y;
    }

    public void moveCursorForward(int distance)
    {
        // check
        x = x + Math.cos(rotation) * distance;
        y = y + Math.sin(rotation) * distance;
    }

    public void setColourRGB(int red, int green, int blue)
    {
        // check
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
}
