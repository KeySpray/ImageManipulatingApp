package controller;

import java.awt.image.BufferedImage;

import view.ViewListener;

/**
 * A simple interface for the GUI controller to extend ViewListener.
 */
public interface GUIControllerInterface extends ViewListener {

  /**
   * A method to handle a load event from the GUI.
   * @param fileName - the name of the file/desired nickname
   * @param filePath - the path of the file in absolute terms
   */
  void handleLoadEvent(String filePath, String fileName);

  /**
   * A method to handle a save event from the GUI.
   * @param imageKey - the name of the file as loaded
   * @param filePath - the desired save path of the file in absolute terms
   */
  void handleSaveEvent(String imageKey, String filePath);

  /**
   * A method to enable displaying of my ImageTypes in canvas.
   * @param imageKey - the name of the file as loaded/stored in database
   * @return - a BufferedImage to be displayed in GUI canvas
   */
  BufferedImage handleDisplayImageEvent(String imageKey);

  /**
   * A method to handle brighten transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param factor - a string to be converted to integer to denote amount by which to brighten
   */
  void handleBrightenEvent(String imageKey, String factor);

  /**
   * A method to handle darken transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param factor - a string to be converted to integer to denote amount by which to darken
   */
  void handleDarkenEvent(String imageKey, String factor);

  /**
   * A method to handle color transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param filter - the type of kernel/transformation to be applied
   */
  void handleColorEvent(String imageKey, String filter);

  /**
   * A method to handle filter transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param filter - the type of kernel/transformation to be applied
   */
  void handleFilterEvent(String imageKey, String filter);

  /**
   * A method to handle intensity transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   */
  void handleIntensityEvent(String imageKey);

  /**
   * A method to handle luma transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   */
  void handleLumaEvent(String imageKey);

  /**
   * A method to handle value component transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   */
  void handleValueCompEvent(String imageKey);

  /**
   * A method to handle a one component transformation events from the GUI.
   * @param imageKey - the name of the image as stored in database
   * @param color - the pixel channel of which to base the transformation
   */
  void handleOneCompEvent(String imageKey, String color);
}
