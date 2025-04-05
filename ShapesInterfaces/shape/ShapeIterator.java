package shape;

import java.util.List;

public class ShapeIterator implements Iterator {

    private List<Shape> shapes;
    private int index;

    public ShapeIterator(List<Shape> shapes) {
        this.shapes = shapes;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < shapes.size(); // Checks if there are more shapes to iterate
    }

    public Shape next() {
        if (hasNext()) {
            return shapes.get(index++); // Returns the next shape and moves the index
        }
        return null; // If no more elements
    }
}