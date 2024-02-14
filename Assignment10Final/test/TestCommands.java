import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import controller.ImageControllerImpl;
import model.imagestrategy.PPMImage;
import model.transformationstrategy.BrighteningStrategy;
import model.transformationstrategy.ColorStrategy;
import model.transformationstrategy.DarkeningStrategy;
import model.transformationstrategy.FilterStrategy;
import model.transformationstrategy.OneComponentStrategy;
import view.EditorView;
import view.TextView;

/**
 * A JUnit test suite to test various aspects of controller and commands.
 */
public class TestCommands {

  /**
   * Test method to ensure commands are parsed correctly by controller.
   */
  @Test
  public void testCommands() {
    // Setup objects
    StringBuilder output = new StringBuilder(); // creates StringBuilder object as appendable
    EditorView view = new TextView(output); // creates view with StringBuilder as ap

    Readable rd = new StringReader("load ./res/test5by5.ppm test1\n"
            + "brighten test1 50\n"
            + "darken test1 50\n"
            + "luma test1\n"
            + "intensity test1\n"
            + "value-component test1\n"
            + "one-component red test1\n"
            + "one-component green test1\n"
            + "one-component blue test1\n"
            + "filter blur test1\n"
            + "filter sharpen test1\n"
            + "color sepia test1\n"
            + "color greyscale test1\n"
            + "save test1 save1.ppm\n"
            + "quit");

    ImageControllerImpl controller = new ImageControllerImpl(view, rd); // creates controller

    controller.run(); // runs controller with commands in StringReader

    // verify that image is now in database
    Assert.assertTrue(controller.db.getImage("test1") instanceof PPMImage);
    // verify commands were processed correctly via prints
    String expectedOutput = "Operation: load finished.\n"
            + "Operation: brighten finished.\n"
            + "Operation: darken finished.\n"
            + "Operation: luma finished.\n"
            + "Operation: intensity finished.\n"
            + "Operation: value-component finished.\n"
            + "Operation: one-component finished.\n"
            + "Operation: one-component finished.\n"
            + "Operation: one-component finished.\n"
            + "Operation: filter finished.\n"
            + "Operation: filter finished.\n"
            + "Operation: color finished.\n"
            + "Operation: color finished.\n"
            + "Operation: save finished.\n"
            + "Operation: quit finished.\n"
            + "Program quit or exited.\n";
    Assert.assertEquals(expectedOutput, output.toString());
  }

  /**
   * Test method to ensure invalid commands will return error message.
   */
  @Test
  public void testCommandFailures() {
    // Setup objects
    StringBuilder output2 = new StringBuilder(); // creates StringBuilder object as appendable
    EditorView view = new TextView(output2); // creates view with StringBuilder as ap

    Readable rd = new StringReader("load ./res/test5by5.ppm test1\n"
            + "brighton test1 50\n"
            + "filtern test1\n"
            + "quit\n");

    ImageControllerImpl controller = new ImageControllerImpl(view, rd); // creates controller

    controller.run(); // runs controller with commands in StringReader

    Assert.assertEquals("Operation: load finished.\n" +
            "Invalid command: brighton\n" +
            "Invalid command: filtern\n" +
            "Operation: quit finished.\n" +
            "Program quit or exited.\n", output2.toString());
  }

  /**
   * Test method to ensure insufficient number of arguments will result in error message.
   */
  @Test
  public void testInsufficientArguments() {
    // Setup objects
    StringBuilder output2 = new StringBuilder(); // creates StringBuilder object as appendable
    EditorView view = new TextView(output2); // creates view with StringBuilder as ap

    Readable rd = new StringReader("load ./res/test5by5.ppm test1\n"
            + "brighten test1\n"
            + "filter test1\n"
            + "color test1\n"
            + "quit");

    ImageControllerImpl controller = new ImageControllerImpl(view, rd); // creates controller

    controller.run(); // runs controller with commands in StringReader

    Assert.assertEquals("Operation: load finished.\n" +
            "Insufficient number of arguments for command: brighten.\n" +
            "Insufficient number of arguments for command: filter.\n" +
            "Insufficient number of arguments for command: color.\n" +
            "Operation: quit finished.\n" +
            "Program quit or exited.\n", output2.toString());
  }

  /**
   * Tests exceptions across various methods.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testExceptions() {
    new PPMImage(-5, -5, -5);
    new ImageControllerImpl(null, null);
    new BrighteningStrategy(-50);
    new BrighteningStrategy(256);
    new DarkeningStrategy(256);
    new DarkeningStrategy(-50);
    new OneComponentStrategy("burple");
    new FilterStrategy("blurAndSharpen");
    new ColorStrategy("sepier");
  }
}
