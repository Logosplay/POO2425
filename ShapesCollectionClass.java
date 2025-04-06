package shape;

import java.util.ArrayList;
import java.util.List;

public class ShapesCollectionClass implements ShapesCollection {

    private List<Shape> shapes;

    public ShapesCollectionClass() {
        shapes = new ArrayList<>();
    }


    public boolean isEmpty() {
        return shapes.isEmpty();
    }


    public boolean hasElem(String ID) {
        return shapes.stream().anyMatch(s -> s.getId().equals(ID));
    }


    public void addElem(Shape elem) {
        shapes.add(elem);
    }


    public Shape getElement(String ID) {
        return shapes.stream().filter(s -> s.getId().equals(ID)).findFirst().orElse(null);
    }


    public Iterator allShapesIterator() {
        return new ShapeIterator(shapes); // Iterate through the shapes using the ShapeIterator
    }

    public Iterator allShapesIterator(String type) {
        return new ShapeTypeIterator(shapes, type); // Filter shapes by type using ShapeTypeIterator
    }
}