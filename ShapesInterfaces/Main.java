import java.util.Scanner;
import shape.*;

public class Main {
    public final static String ADD = "ADD";
    public final static String LIST = "LIST";
    public final static String MOVE = "MOVE";
    public final static String MINAREA = "MINAREA";
    public final static String EXIT = "EXIT";

    public static final String QUIT_MSG = "Exiting...";
    public static final String COMMAND_ERROR = "There was an error.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShapesCollectionClass sCollection = new ShapesCollectionClass();
    
        while (true) {
            String[] input = in.nextLine().trim().split(" ");
            String command = input[0].toUpperCase();
    
            switch (command) {
                case ADD: {
                    String type = input.length > 1 ? input[1].toUpperCase() : null;
                    System.out.println(type);
                    addShapeType(in, sCollection, type);
                    break;
                }
                case LIST:
                    listShape(in, sCollection);
                    break;
                case MOVE:
                    moveShape(in, sCollection);
                    break;
                case MINAREA:
                    minareaShape(sCollection);
                    break;
                case EXIT:
                    System.out.println(QUIT_MSG);
                    return;
                default:
                    System.out.println(COMMAND_ERROR);
                    break;
            }
            in.close();
        }
    }


private static void addShapeType(Scanner in, ShapesCollection sCollection, String type){
    
    if (type == null || !ShapesApp.isValidType(type)) {
        System.out.println("Type does not exist.");
        return;
    }
    
    String[] parts = in.nextLine().trim().split(" ");
    String id = parts[0];
    int x = Integer.parseInt(parts[1]);
    int y = Integer.parseInt(parts[2]);
    
    if (sCollection.hasElem(id)) {
        System.out.println("Identifier already exists.");
        return;
    }
    
    if (type.equals(ShapesApp.CIRCLE)) {
        int radius = Integer.parseInt(parts[3]);
        sCollection.addElem(new CircleClass(id, x, y, radius));
    } else {
        int height = Integer.parseInt(parts[3]);
        int width = Integer.parseInt(parts[4]);
        sCollection.addElem(new RectangleClass(id, x, y, height, width));
    }
    System.out.println("A new " + type + " was added.");
}

    private static void listShape(Scanner in, ShapesCollection  sCollection) {
        String line = in.hasNextLine() ? in.nextLine().trim().toUpperCase() : "";
        System.out.println("All shapes:");

        Iterator iterator;
        if (line.isEmpty()) {
            iterator =  sCollection.allShapesIterator();
        } else if (ShapesApp.isValidType(line)) {
            iterator =  sCollection.allShapesIterator(line);
        } else {
            System.out.println("Type does not exist.");
            return;
        }

        if (!iterator.hasNext()) {
            System.out.println("Without geometric shapes.");
            return;
        }

        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            System.out.println(shape.getId() + " " + shape.getPosition() + " " + shape.getType());
        }
    }

    private static void moveShape(Scanner in, ShapesCollection  sCollection) {
        String[] parts = in.nextLine().trim().split(" ");
        String id = parts[0];
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);

        if (! sCollection.hasElem(id)) {
            System.out.println("Identifier does not exist.");
            return;
        }

        Shape shape =  sCollection.getElement(id);
        shape.move(x, y);
        System.out.println("Shape was moved.");
    }

    private static void minareaShape(ShapesCollection  sCollection) {
        if ( sCollection.isEmpty()) {
            System.out.println("Without geometric shapes.");
            return;
        }

        Iterator iterator =  sCollection.allShapesIterator();
        Shape min = null;
        while (iterator.hasNext()) {
            Shape current = iterator.next();
            if (min == null || current.getArea() < min.getArea() ||
               (current.getArea() == min.getArea() && isLater(current, min,  sCollection))) {
                min = current;
            }
        }
        System.out.println(min.getId() + " " + min.getPosition() + " " + min.getType());
    }

    private static boolean isLater(Shape s1, Shape s2, ShapesCollection  sCollection) {
        Iterator it =  sCollection.allShapesIterator();
        while (it.hasNext()) {
            Shape current = it.next();
            if (current == s1) return true;
            if (current == s2) return false;
        }
        return false;
    }
}