package model.transformationstrategy;

import model.imagestrategy.ImageType;

import static java.lang.Math.max;

/**
 * A strategy method to apply the value component greyscale strategy to an ImageType.
 */
public class ValueComponentStrategy implements TransformationStrategy {

  /**
   * Empty constructor for the value-component greyscale strategy.
   */
  public ValueComponentStrategy() {
    // empty constructor as this class needs no parameters.
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
        int maxComponent = max(pixel.getRed(), max(pixel.getGreen(), pixel.getBlue()));

        RGBPixel newPixel = new RGBPixel(maxComponent, maxComponent, maxComponent);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
