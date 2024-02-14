package model.transformationstrategy;

import model.imagestrategy.ImageType;

import static java.lang.Math.max;

/**
 * A strategy method to apply the brightening strategy to an ImageType.
 */
public class DarkeningStrategy implements TransformationStrategy {

  private final int darkeningFactor;

  /**
   * The constructor for this transformation strategy.
   * @param darkeningFactor - the integer amount by which to decrease each pixel component.
   * @throws IllegalArgumentException - if darkening factor is invalid.
   */
  public DarkeningStrategy(int darkeningFactor) {
    if (darkeningFactor < 0 || darkeningFactor > 255) {
      throw new IllegalArgumentException("Darkening factor must be between 0 and 255.");
    }
    this.darkeningFactor = darkeningFactor;
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
        int newRed = max(0, pixel.getRed() - darkeningFactor);
        int newGreen = max(0, pixel.getGreen() - darkeningFactor);
        int newBlue = max(0, pixel.getBlue() - darkeningFactor);

        RGBPixel newPixel = new RGBPixel(newRed, newGreen, newBlue);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
