package controller;

/**
 * Simple interface to store the methods necessary for a controller.
 */
public interface ImageController {

  /**
   * Helper method to fetch input from appendable (currently system.in).
   * @return - String array of the command elements.
   */
  String[] getInput();

  /**
   * Main method to run the program.
   */
  void run();
}
