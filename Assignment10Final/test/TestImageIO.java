import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.ImageDatabase;
import controller.imageio.MyImageIO;
import controller.imageio.PPMImageIO;
import model.imagestrategy.PPMImage;

/**
 * A JUnit test suite to test methods from my ImageIO class.
 */
public class TestImageIO {


  private final MyImageIO io = new PPMImageIO();
  private final ImageDatabase db = new ImageDatabase();

  /**
   * A setUp() method to set up objects for testing, as well as testing converting files.
   */
  @Before
  public void setUp() {
    // db.addImage() calls read() in ImageIO
    db.addImage("./res/test5by5.ppm", "test1");
    // saves recently added image under new name
    db.saveImage("test1", "./res/savedTest1.ppm");
    // adds jpg
    db.addImage("./res/test3by3.jpg", "testjpg");
    // saves jpg to ppm
    db.saveImage("testjpg", "./res/jpgToppm.ppm");
    // adds ppm
    db.addImage("./res/test5by5.ppm", "PPMtoJPG");
    // saves ppm to jpg
    db.saveImage("PPMtoJPG", "./res/testPPMtoJPG.jpg");
    // adds ppm
    db.addImage("./res/test5by5.ppm", "PPMtoPNG");
    // saves ppm to png
    db.saveImage("PPMtoPNG", "./res/testPPMtoPNG.png");
    // adds png
    db.addImage("./res/test3by3.png", "PNGtoPPM");
    // saves png to ppm
    db.saveImage("PNGtoPPM", "./res/testPNGtoPPM.ppm");
    // adds png
    db.addImage("./res/test3by3.png", "PNGtoJPG");
    // saves png to jpg
    db.saveImage("PNGtoJPG", "./res/testPNGtoJPG.jpg");
    // adds jpg to be converted to png
    db.addImage("./res/test3by3.jpg", "JPGtoPNG");
    // saves jpg to png
    db.saveImage("JPGtoPNG", "./res/testJPGtoPNG.png");
  }

  /**
   * A test method to test adding image to database.
   */
  @Test
  public void testAddImage() {
    // ensures image added to db, ensures get() works
    Assert.assertTrue(db.getImage("test1") instanceof PPMImage);
    // ensures png added to database
    Assert.assertNotNull(db.getImage("PNGtoPPM"));
    // ensures jpg added to database
    Assert.assertNotNull(db.getImage("JPGtoPNG"));
  }

  /**
   * A test method to test write()/save method works properly.
   */
  @Test
  public void testReloadSaved() {
    // adds the saved image to the database under new name
    db.addImage("./res/savedTest1.ppm", "newUpload");
    // ensures saved image was added back, ensuring write() and get() works
    Assert.assertTrue(db.getImage("newUpload") instanceof PPMImage);
    // adds back jpg that was saved to ppm
    db.addImage("./res/jpgToppm.ppm", "jpgToPPM");
    // ensures jpg converted to ppm
    Assert.assertTrue(db.getImage("jpgToPPM") instanceof PPMImage);
    // adds back png that was saved to ppm
    db.addImage("./res/testPNGtoPPM.ppm", "PNGtoPPMTest");
    // ensures png converted to PPM
    Assert.assertTrue(db.getImage("PNGtoPPMTest") instanceof PPMImage);
  }

  /**
   * A test method to test image getters.
   */
  @Test
  public void testImageGetters() {
    // tests getHeight()
    Assert.assertEquals(5, db.getImage("test1").getHeight());
    // tests getWidth()
    Assert.assertEquals(5, db.getImage("test1").getWidth());
    // tests getMaxColorValue()
    Assert.assertEquals(255, db.getImage("test1").getMaxColorValue());
  }
}
