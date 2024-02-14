package model.transformationstrategy;

import model.imagestrategy.ImageType;

import static java.lang.Math.min;

/**
 * A strategy method to apply the brightening strategy to an ImageType.
 */
public class BrighteningStrategy implements TransformationStrategy {

  private int brighteningFactor;

  /**
   * The constructor for this transformation strategy.
   * @param brighteningFactor - the integer amount by which to increase each pixel component.
   * @throws IllegalArgumentException - if brightening factor is invalid.
   */
  public BrighteningStrategy(int brighteningFactor) {
    if (brighteningFactor < 0 || brighteningFactor > 255) {
      throw new IllegalArgumentException("Brightening factor must be between 0 and 255.");
    }
    this.brighteningFactor = brighteningFactor;
  }

  /**
   * A method to apply the image transformation.
   */
  @Override
  public ImageType transform(ImageType image) {
    int height = image.getHeight();
    int width = image.getWidth();
    ImageType newImage = image.clone();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = (RGBPixel) image.getPixel(i, j);
        int newRed = min(255, pixel.getRed() + brighteningFactor);
        int newGreen = min(255, pixel.getGreen() + brighteningFactor);
        int newBlue = min(255, pixel.getBlue() + brighteningFactor);

        RGBPixel newPixel = new RGBPixel(newRed, newGreen, newBlue);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
