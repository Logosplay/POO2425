package shape;

public class ShapeTypeIteratorClass impelments Iterator {
  private int size;
  private int index;
  private Shape[] shapes;
  private String type;
  
  public ShapeTypeIteratorClass(ShapeType[] shapeTypes) {
    this.shapes = shapeTypes;
  }
  
  public boolean hasNext() {
    if (index < shapes.length) {
      return true;
    }
    return false;
  }
  
  public ShapeType next() {
    if (this.hasNext()) {
      return shapes[index++];
    }
    return null;
  }
}
