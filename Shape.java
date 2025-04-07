package shape;

public abstract class Shape {
    protected String id;
    protected int x, y;
    protected String type;  // Set type in subclasses

    public Shape(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return "(" + x + ", " + y + ")";
    }

    public String getType() {
        return type;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract double getArea();
}