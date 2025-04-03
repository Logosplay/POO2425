package shape;

import java.util.Scanner;
import shape.ShapesApp;

public class Main {

  public final static String ADD = "add";
  public final static String LIST = "list";
  public final static String MOVE = "move";
  public final static String MINAREA = "minarea";
  public final static String EXIT = "exit";
  
  public static final String QUIT_MSG = "Bye.";
  public static final String COMMAND_ERROR = "There was an error.";

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    ShapesApp app = new ShapesApp();
    String command;

    do {
      command = getCommand(in);
      switch (command) {
        case ADD:
          addShape(shapeType, id, x, y, radius);
          break;
        case LIST:
          listShapes();
          break;
        case MOVE:
          moveShape(id, x, y);
          break;
        case MINAREA:
          minAreaShape();
          break;
        case EXIT:
          System.out.println(QUIT_MSG);
          break;
        default:
          System.out.println(COMMAND_ERROR);
          break;
    } while (!command.equals("QUIT"));
      in.close();
  }
  }
  
}
