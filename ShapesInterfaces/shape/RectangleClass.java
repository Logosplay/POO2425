package shape;

public class RectangleClass extends Shape {
    private int height, width;

    public RectangleClass(String id, int x, int y, int height, int width) {
        super(id, x, y);
        this.height = height;
        this.width = width;
        this.type = "RECTANGLE";  // Set the type for Rectangle
    }

    public double getArea() {
        return height * width;
    }
}