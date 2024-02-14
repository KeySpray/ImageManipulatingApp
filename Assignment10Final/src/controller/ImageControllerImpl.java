package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.ImageDatabase;
import model.transformationstrategy.BrighteningStrategy;
import model.transformationstrategy.ColorStrategy;
import model.transformationstrategy.DarkeningStrategy;
import model.transformationstrategy.DeepFriedStrategy;
import model.transformationstrategy.FilterStrategy;
import model.transformationstrategy.IntensityStrategy;
import model.transformationstrategy.LumaStrategy;
import model.transformationstrategy.OneComponentStrategy;
import model.transformationstrategy.ValueComponentStrategy;
import view.EditorView;

/**
 * Controller class for the photo editing program.
 */
public class ImageControllerImpl implements ImageController {

  public final ImageDatabase db;
  private final Map<String, Command> commands;
  boolean keepRunning = true;
  private final EditorView view;
  private final Scanner scanner;

  /**
   * Constructor class for the controller. Stores commands in HashMap for easy lookup.
   */
  public ImageControllerImpl(EditorView view, Readable rd) {
    if (view == null || rd == null) {
      throw new IllegalArgumentException("Readable or view not found.");
    }
    db = new ImageDatabase(); // Creates database
    this.view = view;
    this.scanner = new Scanner(rd);

    commands = new HashMap<>(); // Creates HashMap to store command key and command.
    commands.put("load", (args -> db.addImage(args[1], args[2]))); // command for loading
    commands.put("brighten", (args -> db.applyTransformation(args[1], // command for brightening
            new BrighteningStrategy(Integer.parseInt(args[2])))));
    commands.put("darken", (args -> db.applyTransformation(args[1],
            new DarkeningStrategy(Integer.parseInt(args[2]))))); // command to darken
    commands.put("value-component", (args -> db.applyTransformation(args[1],
            new ValueComponentStrategy()))); // command for value-component greyscale
    // command for intensity greyscale method
    commands.put("intensity", (args -> db.applyTransformation(args[1], new IntensityStrategy())));
    // command for luma greyscale method
    commands.put("luma", (args -> db.applyTransformation(args[1], new LumaStrategy())));
    commands.put("one-component", (args -> db.applyTransformation(args[2],
            new OneComponentStrategy(args[1])))); // command for single component greyscale method
    // command for deep-fry/inverse method
    commands.put("deep-fry", (args -> db.applyTransformation(args[1], new DeepFriedStrategy())));
    commands.put("filter", (args -> db.applyTransformation(args[2], new FilterStrategy(args[1]))));
    commands.put("color", (args -> db.applyTransformation(args[2], new ColorStrategy(args[1]))));
    commands.put("save", (args -> db.saveImage(args[1], args[2]))); // command for saving
    commands.put("quit", (args -> {
      keepRunning = false;
    } )); // command to quit program
  }

  /**
   * Helper method to retrieve input from readable (currently only system.in).
   */
  public String[] getInput() {
    String inputLine = scanner.nextLine(); // gets next line of readable
    return inputLine.trim().split("\\s+"); // splits line into array of arguments
  }

  /**
   * Main method of the controller that enables running of the program.
   */
  public void run() {
    while (keepRunning) { // boolean for program running
      String[] input = getInput(); // calls and stores return from getInput
      Command command = commands.get(input[0].toLowerCase()); // ignores cases
      if (command != null) { // ensures command present
        try { // attempts to execute command
          command.execute(input);
          view.renderMessage("Operation: " + input[0] + " finished.");
        }
        catch (ArrayIndexOutOfBoundsException e) { // case if insufficient arguments
          view.renderMessage("Insufficient number of arguments for command: " + input[0] + ".");
        }
        catch (Exception e) { // general error handling
          view.renderMessage(e.getMessage());
        }
      }
      else {
        view.renderMessage("Invalid command: " + input[0]); // case when command is invalid
      }
    }
    scanner.close();
    view.renderMessage("Program quit or exited."); // case when program quit
  }
}
