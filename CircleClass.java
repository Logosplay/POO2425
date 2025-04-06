package shape;

public class CircleClass extends Shape {
    private int radius;

    public CircleClass(String id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
        this.type = "CIRCLE";  // Set the type for Circle
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}