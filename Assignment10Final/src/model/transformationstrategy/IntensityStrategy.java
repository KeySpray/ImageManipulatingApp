package model.transformationstrategy;

import model.imagestrategy.ImageType;

import static java.lang.Math.round;

/**
 * A strategy method to apply the intensity greyscale strategy to an ImageType.
 */
public class IntensityStrategy implements TransformationStrategy {

  /**
   * Empty constructor for the intensity strategy.
   */
  public IntensityStrategy() {
    // empty constructor as it needs no parameters.
  }

  /**
   * A method to apply the image transformation.
   * @param image - the image which to apply the transformation.
   */
  @Override
  public ImageType transform(ImageType image) {
    int height = image.getHeight();
    int width = image.getWidth();
    ImageType newImage = image.clone();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = (RGBPixel) image.getPixel(i, j);
        int avgComponent = round((float) (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);

        RGBPixel newPixel = new RGBPixel(avgComponent, avgComponent, avgComponent);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
