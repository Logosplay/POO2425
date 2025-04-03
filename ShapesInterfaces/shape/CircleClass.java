package shape;

public class CircleClass extends AbstractClass {
  private int radius;
  
  public Circle(int x, int y, int radius) {
    this.x = in.nextInt();
    this.y = in.nextInt();
    this.radius = in.nextInt();
  }

  public CircleClass(int x, int y, String id, int radius) {
    super (x, y, id);
    this.radius = radius;
  }
}
