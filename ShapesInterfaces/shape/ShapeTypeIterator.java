package shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeTypeIterator implements Iterator {

    private int index;
    private List<Shape> filteredShapes;

    public ShapeTypeIterator(List<Shape> shapes, String type) {
        filteredShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getType().equalsIgnoreCase(type)) {
                filteredShapes.add(shape);
            }
        }
        this.index = 0;
    }
    

    public boolean hasNext() {
        return index < filteredShapes.size();
    }

    public Shape next() {
        if (this.hasNext()) {
            return filteredShapes.get(index++);
        }
        return null;
    }
}