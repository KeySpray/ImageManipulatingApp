package controller;

import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.JFrame;

import model.ImageDatabase;
import model.imagestrategy.BufferedConverter;
import model.transformationstrategy.BrighteningStrategy;
import model.transformationstrategy.ColorStrategy;
import model.transformationstrategy.DarkeningStrategy;
import model.transformationstrategy.FilterStrategy;
import model.transformationstrategy.IntensityStrategy;
import model.transformationstrategy.LumaStrategy;
import model.transformationstrategy.OneComponentStrategy;
import model.transformationstrategy.ValueComponentStrategy;
import view.GraphicalView;

/**
 * A Controller implementation for the GUI.
 */
public class GUIController implements GUIControllerInterface {
  private final ImageDatabase db;
  private final GraphicalView view;

  /**
   * A Controller constructor for the GUI.
   */
  public GUIController(GraphicalView view) {
    this.db = new ImageDatabase(); // creates database
    this.view = Objects.requireNonNull(view); // takes in view
    this.view.addViewListener(this); // adds controller as listener to view
  }

  public void run() {
    this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.view.setSize(1024, 1024);
    this.view.setVisible(true);
  }

  /**
   * A method to handle a load event from the GUI.
   * @param fileName - the name of the file/desired nickname
   * @param filePath - the path of the file in absolute terms
   */
  @Override
  public void handleLoadEvent(String filePath, String fileName) {
    if (filePath != null && fileName != null) { // ensures file path and name not null
      try {
        this.db.addImage(filePath, fileName); // loads/adds/reads image to database
        this.view.setDisplayText("Loaded image: " + fileName); // updates display message
      }

      catch (Exception e) {
        this.view.setDisplayText(e.getMessage()); // prints error message
      }
    }

    else {
      this.view.setDisplayText("File or filename cannot be null!"); // prints null arg message
    }
  }

  /**
   * A method to handle a save event from the GUI.
   * @param imageKey - the name of the file as loaded
   * @param filePath - the desired save path of the file in absolute terms
   */
  @Override
  public void handleSaveEvent(String imageKey, String filePath) {
    Objects.requireNonNull(imageKey);
    Objects.requireNonNull(filePath);
    try {
      this.db.saveImage(imageKey, filePath); // saves image to file path
      this.view.setDisplayText("Saved image: " + imageKey); // updates display message
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage()); // prints error message to display
    }
  }

  /**
   * A method to enable displaying of my ImageTypes in canvas.
   * @param imageKey - the name of the file as loaded/stored in database
   * @return - a BufferedImage to be displayed in GUI canvas
   */
  @Override
  public BufferedImage handleDisplayImageEvent(String imageKey) {
    // Converts ImageType as stored in database to BufferedImage for displaying only
    BufferedConverter converter = new BufferedConverter(this.db.getImage(imageKey));
    return converter.getBufferedImage();
  }

  /**
   * A method to handle brighten transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param factor - a string to be converted to integer to denote amount by which to brighten
   */
  @Override
  public void handleBrightenEvent(String imageKey, String factor) {
    try {
      // parses factor in controller to enable error catch and display
      int factorInt = Integer.parseInt(factor);
      // applies transformation
      this.db.applyTransformation(imageKey, new BrighteningStrategy(factorInt));
      this.view.setDisplayText("Applied brighten to: " + imageKey); // prints display message
    }
    catch (NumberFormatException n) { // catch integer parse exception
      this.view.setDisplayText("Please enter a number between 0-255.");
    }
    catch (Exception e) { // general exception catch
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle darken transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param factor - a string to be converted to integer to denote amount by which to darken
   */
  @Override
  public void handleDarkenEvent(String imageKey, String factor) {
    try {
      // parses factor in controller to enable error catch and display
      int factorInt = Integer.parseInt(factor);
      // applies transformation
      this.db.applyTransformation(imageKey, new DarkeningStrategy(factorInt));
      this.view.setDisplayText("Applied darken to: " + imageKey); // success message
    }
    catch (NumberFormatException n) { // catches number format exception from parsing
      this.view.setDisplayText("Please enter a number between 0-255.");
    }
    catch (Exception e) { // catches and displays other exceptions
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle color transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param filter - the type of kernel/transformation to be applied
   */
  @Override
  public void handleColorEvent(String imageKey, String filter) {
    try {
      this.db.applyTransformation(imageKey, new ColorStrategy(filter));
      this.view.setDisplayText("Applied " + filter + " to: " + imageKey);
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle filter transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param filter - the type of kernel/transformation to be applied
   */
  @Override
  public void handleFilterEvent(String imageKey, String filter) {
    try {
      this.db.applyTransformation(imageKey, new FilterStrategy(filter));
      this.view.setDisplayText("Applied " + filter + " to: " + imageKey);
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle intensity transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   */
  @Override
  public void handleIntensityEvent(String imageKey) {
    try {
      this.db.applyTransformation(imageKey, new IntensityStrategy());
      this.view.setDisplayText("Applied intensity to: " + imageKey);
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle luma transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   */
  @Override
  public void handleLumaEvent(String imageKey) {
    try {
      this.db.applyTransformation(imageKey, new LumaStrategy());
      this.view.setDisplayText("Applied luma to: " + imageKey);
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle value component transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   */
  @Override
  public void handleValueCompEvent(String imageKey) {
    try {
      this.db.applyTransformation(imageKey, new ValueComponentStrategy());
      this.view.setDisplayText("Applied value component to: " + imageKey);
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage());
    }
  }

  /**
   * A method to handle a one component transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param color - the pixel channel of which to base the transformation
   */
  @Override
  public void handleOneCompEvent(String imageKey, String color) {
    try {
      this.db.applyTransformation(imageKey, new OneComponentStrategy(color));
      this.view.setDisplayText("Applied one component " + color + " to: " + imageKey);
    }
    catch (Exception e) {
      this.view.setDisplayText(e.getMessage());
    }
  }
}

