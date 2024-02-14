import org.junit.Assert;
import org.junit.Test;

import controller.imageio.MyImageIO;
import controller.imageio.PPMImageIO;
import model.ImageDatabase;
import model.imagestrategy.ImageType;
import model.transformationstrategy.FilterStrategy;
import model.transformationstrategy.RGBPixel;
import model.transformationstrategy.TransformationStrategy;

/**
 * A JUnit test suite for image transformations.
 */
public class TestNewImageTransformations {

  private final ImageDatabase db = new ImageDatabase();

  /**
   * A JUnit test method to ensure blurring jpgs works.
   */
  @Test
  public void testJpgBlur() {
    TransformationStrategy transform = new FilterStrategy("blur");
    db.addImage("./res/test3by3.jpg", "jpgTest");
    ImageType testImage = db.getImage("jpgTest").applyTransformation(transform);
    RGBPixel testPixel1 = (RGBPixel) testImage.getPixel(0, 0);
    RGBPixel testPixel2 = (RGBPixel) testImage.getPixel(0, 1);
    RGBPixel testPixel6 = (RGBPixel) testImage.getPixel(0, 2);
    RGBPixel testPixel3 = (RGBPixel) testImage.getPixel(1, 0);
    RGBPixel testPixel4 = (RGBPixel) testImage.getPixel(1, 1);
    RGBPixel testPixel5 = (RGBPixel) testImage.getPixel(1, 2);
    RGBPixel testPixel7 = (RGBPixel) testImage.getPixel(2, 0);
    RGBPixel testPixel8 = (RGBPixel) testImage.getPixel(2, 1);
    RGBPixel testPixel9 = (RGBPixel) testImage.getPixel(2, 2);

    Assert.assertEquals(82, testPixel1.getRed());
    Assert.assertEquals(23, testPixel1.getGreen());
    Assert.assertEquals(37, testPixel1.getBlue());
    Assert.assertEquals(91, testPixel2.getRed());
    Assert.assertEquals(50, testPixel2.getGreen());
    Assert.assertEquals(73, testPixel2.getBlue());
    Assert.assertEquals(121, testPixel3.getRed());
    Assert.assertEquals(68, testPixel3.getGreen());
    Assert.assertEquals(66, testPixel3.getBlue());
    Assert.assertEquals(132, testPixel4.getRed());
    Assert.assertEquals(67, testPixel4.getGreen());
    Assert.assertEquals(112, testPixel4.getBlue());
    Assert.assertEquals(75, testPixel5.getRed());
    Assert.assertEquals(58, testPixel5.getGreen());
    Assert.assertEquals(74, testPixel5.getBlue());
    Assert.assertEquals(71, testPixel6.getRed());
    Assert.assertEquals(73, testPixel6.getGreen());
    Assert.assertEquals(38, testPixel6.getBlue());
    Assert.assertEquals(73, testPixel7.getRed());
    Assert.assertEquals(70, testPixel7.getGreen());
    Assert.assertEquals(62, testPixel7.getBlue());
    Assert.assertEquals(88, testPixel8.getRed());
    Assert.assertEquals(51, testPixel8.getGreen());
    Assert.assertEquals(87, testPixel8.getBlue());
    Assert.assertEquals(50, testPixel9.getRed());
    Assert.assertEquals(19, testPixel9.getGreen());
    Assert.assertEquals(75, testPixel9.getBlue());
  }

  /**
   * A JUnit test method to ensure blurring pngs works.
   */
  @Test
  public void testPngBlur() {
    TransformationStrategy transform = new FilterStrategy("blur");
    db.addImage("./res/test3by3.png", "pngTest");
    ImageType testImage = db.getImage("pngTest").applyTransformation(transform);
    RGBPixel testPixel1 = (RGBPixel) testImage.getPixel(0, 0);
    RGBPixel testPixel2 = (RGBPixel) testImage.getPixel(0, 1);
    RGBPixel testPixel6 = (RGBPixel) testImage.getPixel(0, 2);
    RGBPixel testPixel3 = (RGBPixel) testImage.getPixel(1, 0);
    RGBPixel testPixel4 = (RGBPixel) testImage.getPixel(1, 1);
    RGBPixel testPixel5 = (RGBPixel) testImage.getPixel(1, 2);
    RGBPixel testPixel7 = (RGBPixel) testImage.getPixel(2, 0);
    RGBPixel testPixel8 = (RGBPixel) testImage.getPixel(2, 1);
    RGBPixel testPixel9 = (RGBPixel) testImage.getPixel(2, 2);
    Assert.assertEquals(84, testPixel1.getRed());
    Assert.assertEquals(23, testPixel1.getGreen());
    Assert.assertEquals(35, testPixel1.getBlue());
    Assert.assertEquals(90, testPixel2.getRed());
    Assert.assertEquals(51, testPixel2.getGreen());
    Assert.assertEquals(73, testPixel2.getBlue());
    Assert.assertEquals(121, testPixel3.getRed());
    Assert.assertEquals(68, testPixel3.getGreen());
    Assert.assertEquals(64, testPixel3.getBlue());
    Assert.assertEquals(131, testPixel4.getRed());
    Assert.assertEquals(67, testPixel4.getGreen());
    Assert.assertEquals(112, testPixel4.getBlue());
    Assert.assertEquals(75, testPixel5.getRed());
    Assert.assertEquals(58, testPixel5.getGreen());
    Assert.assertEquals(78, testPixel5.getBlue());
    Assert.assertEquals(69, testPixel6.getRed());
    Assert.assertEquals(74, testPixel6.getGreen());
    Assert.assertEquals(40, testPixel6.getBlue());
    Assert.assertEquals(70, testPixel7.getRed());
    Assert.assertEquals(70, testPixel7.getGreen());
    Assert.assertEquals(61, testPixel7.getBlue());
    Assert.assertEquals(85, testPixel8.getRed());
    Assert.assertEquals(51, testPixel8.getGreen());
    Assert.assertEquals(86, testPixel8.getBlue());
    Assert.assertEquals(50, testPixel9.getRed());
    Assert.assertEquals(20, testPixel9.getGreen());
    Assert.assertEquals(76, testPixel9.getBlue());
  }

  /**
   * A JUnit test method to ensure sharpening jpgs works.
   */
  @Test
  public void testJpgSharpen() {
    TransformationStrategy transform = new FilterStrategy("sharpen");
    db.addImage("./res/test3by3.jpg", "jpgSharpTest");
    ImageType testImage = db.getImage("jpgSharpTest").applyTransformation(transform);
    RGBPixel testPixel1 = (RGBPixel) testImage.getPixel(0, 0);
    RGBPixel testPixel2 = (RGBPixel) testImage.getPixel(0, 1);
    RGBPixel testPixel3 = (RGBPixel) testImage.getPixel(0, 2);
    RGBPixel testPixel4 = (RGBPixel) testImage.getPixel(1, 0);

    Assert.assertEquals(206, testPixel1.getRed());
    Assert.assertEquals(0, testPixel1.getGreen());
    Assert.assertEquals(52, testPixel1.getBlue());
    Assert.assertEquals(184, testPixel2.getRed());
    Assert.assertEquals(113, testPixel2.getGreen());
    Assert.assertEquals(179, testPixel2.getBlue());
    Assert.assertEquals(199, testPixel3.getRed());
    Assert.assertEquals(216, testPixel3.getGreen());
    Assert.assertEquals(54, testPixel3.getBlue());
    Assert.assertEquals(255, testPixel4.getRed());
    Assert.assertEquals(189, testPixel4.getGreen());
    Assert.assertEquals(131, testPixel4.getBlue());
  }

  /**
   * A JUnit test method to ensure sharpening pngs works.
   */
  @Test
  public void testPngSharpen() {
    TransformationStrategy transform = new FilterStrategy("sharpen");
    db.addImage("./res/test3by3.png", "pngSharpTest");
    ImageType testImage = db.getImage("pngSharpTest").applyTransformation(transform);
    RGBPixel testPixel1 = (RGBPixel) testImage.getPixel(0, 0);
    RGBPixel testPixel2 = (RGBPixel) testImage.getPixel(0, 1);
    RGBPixel testPixel3 = (RGBPixel) testImage.getPixel(0, 2);
    RGBPixel testPixel4 = (RGBPixel) testImage.getPixel(1, 0);

    Assert.assertEquals(215, testPixel1.getRed());
    Assert.assertEquals(0, testPixel1.getGreen());
    Assert.assertEquals(46, testPixel1.getBlue());
    Assert.assertEquals(183, testPixel2.getRed());
    Assert.assertEquals(112, testPixel2.getGreen());
    Assert.assertEquals(179, testPixel2.getBlue());
    Assert.assertEquals(191, testPixel3.getRed());
    Assert.assertEquals(222, testPixel3.getGreen());
    Assert.assertEquals(60, testPixel3.getBlue());
    Assert.assertEquals(255, testPixel4.getRed());
    Assert.assertEquals(184, testPixel4.getGreen());
    Assert.assertEquals(118, testPixel4.getBlue());
  }
}
