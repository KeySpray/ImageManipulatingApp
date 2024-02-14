import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import controller.GUIController;
import controller.ImageControllerImpl;
import view.EditorView;
import view.GraphicalView;
import view.TextView;

/**
 * The main method to run the image editing program.
 */
public class Main {

  /**
   * Constructor for the main method to run the program.
   */
  public static void main(String[] args) throws FileNotFoundException {
    InputStreamReader reader;

    if (args.length == 2 && args[0].equals("-file")) { // checks if script file given
      FileInputStream file = new FileInputStream(args[1]); // makes FileStream for script
      reader = new InputStreamReader(file); // sets reader to script
      EditorView view = new TextView();
      ImageControllerImpl controller = new ImageControllerImpl(view, reader);
      controller.run(); // calls main method of controller to enable program.
    }

    else if (args.length == 1 && args[0].equals("-text")) {
      reader = new InputStreamReader(System.in); // sets reader to system.in as default
      EditorView view = new TextView();
      ImageControllerImpl controller = new ImageControllerImpl(view, reader);
      controller.run(); // calls main method of controller to enable program.
    }

    else if (args.length == 0) {
      GraphicalView view = new GraphicalView();
      GUIController controller = new GUIController(view);
      controller.run();
    }

    else {
      System.out.println("Invalid commands provided!");
      System.out.println("Please see USEME.txt for instructions on running the program.");
      System.exit(1);
    }
  }
}
