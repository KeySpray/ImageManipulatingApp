package model.transformationstrategy;

import model.imagestrategy.ImageType;

import static java.lang.Math.round;

/**
 * A strategy method to apply the luma greyscale strategy to an ImageType.
 */
public class LumaStrategy implements TransformationStrategy {

  /**
   * Empty constructor for the luma greyscale strategy.
   */
  public LumaStrategy() {
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
        int sumComponent = (int) round((pixel.getRed() * 0.2126) + (pixel.getGreen() * 0.7152)
                + (pixel.getBlue() * .0722));

        RGBPixel newPixel = new RGBPixel(sumComponent, sumComponent, sumComponent);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
