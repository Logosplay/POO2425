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
            if (!in.hasNextLine()) break;
            String input = in.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+");
            String command = tokens[0].toUpperCase();

            switch (command) {
                case ADD: {
                    if (tokens.length < 2) {
                        System.out.println("Error: Shape type is missing.");
                        break;
                    }
                    String type = tokens[1].toUpperCase();
                    addShapeType(in, sCollection, type);
                    break;
                }
                case LIST:
                    listShape(tokens, sCollection);
                    break;
                case MOVE:
                    moveShape(tokens, sCollection);
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
        }
    }

    private static void addShapeType(Scanner in, ShapesCollection sCollection, String type) {
        if (!ShapesApp.isValidType(type)) {
            System.out.println("Type does not exist.");
            return;
        }

        if (!in.hasNextLine()) {
            System.out.println("Error: Missing shape parameters.");
            return;
        }

        String[] parts = in.nextLine().trim().split(" ");
        if (parts.length < 3) {
            System.out.println("Error: Insufficient parameters to create a shape.");
            return;
        }

        String id = parts[0];
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);

        if (sCollection.hasElem(id)) {
            System.out.println("Identifier already exists.");
            return;
        }

        if (type.equals(ShapesApp.CIRCLE)) {
            if (parts.length < 4) {
                System.out.println("Error: Missing radius for the circle.");
                return;
            }
            int radius = Integer.parseInt(parts[3]);
            sCollection.addElem(new CircleClass(id, x, y, radius));
        } else {
            if (parts.length < 5) {
                System.out.println("Error: Missing height and/or width for the rectangle.");
                return;
            }
            int height = Integer.parseInt(parts[3]);
            int width = Integer.parseInt(parts[4]);
            sCollection.addElem(new RectangleClass(id, x, y, height, width));
        }
        System.out.println("A new " + type + " was added.");
    }

    private static void listShape(String[] tokens, ShapesCollection sCollection) {
        Iterator iterator;

        if (tokens.length == 1) {
            iterator = sCollection.allShapesIterator();
        } else if (tokens.length == 2) {
            String type = tokens[1].toUpperCase();
            if (ShapesApp.isValidType(type)) {
                iterator = sCollection.allShapesIterator(type);
            } else {
                System.out.println("Type does not exist.");
                return;
            }
        } else {
            System.out.println("Invalid LIST format.");
            return;
        }

        if (!iterator.hasNext()) {
            System.out.println("Without geometric shapes.");
            return;
        }

        System.out.println("All shapes:");
        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getPosition() != null) {
                System.out.println(shape.getId() + " " + shape.getPosition() + " " + shape.getType());
            }
        }
    }

    private static void moveShape(String[] tokens, ShapesCollection sCollection) {
        if (tokens.length < 4) {
            System.out.println("Error: Missing parameters to move the shape.");
            return;
        }

        String id = tokens[1];
        int x = Integer.parseInt(tokens[2]);
        int y = Integer.parseInt(tokens[3]);

        if (!sCollection.hasElem(id)) {
            System.out.println("Identifier does not exist.");
            return;
        }

        Shape shape = sCollection.getElement(id);
        shape.move(x, y);
        System.out.println("Shape was moved.");
    }

    private static void minareaShape(ShapesCollection sCollection) {
        if (sCollection.isEmpty()) {
            System.out.println("Without geometric shapes.");
            return;
        }

        Iterator iterator = sCollection.allShapesIterator();
        Shape min = null;
        while (iterator.hasNext()) {
            Shape current = iterator.next();
            if (min == null || current.getArea() < min.getArea() ||
               (current.getArea() == min.getArea() && isLater(current, min, sCollection))) {
                min = current;
            }
        }
        System.out.println(min.getId() + " " + min.getPosition() + " " + min.getType());
    }

    private static boolean isLater(Shape s1, Shape s2, ShapesCollection sCollection) {
        Iterator it =  sCollection.allShapesIterator();
        while (it.hasNext()) {
            Shape current = it.next();
            if (current == s1) return true;
            if (current == s2) return false;
        }
        return false;
    }
}