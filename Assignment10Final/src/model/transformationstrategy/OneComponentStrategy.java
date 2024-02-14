package model.transformationstrategy;

import model.imagestrategy.ImageType;

/**
 * A strategy method to apply the one-component greyscale strategy to an ImageType.
 */
public class OneComponentStrategy implements TransformationStrategy {

  private final String component;

  /**
   * A constructor for the one-component strategy.
   * @param component - the pixel channel to which the other channels are set.
   */
  public OneComponentStrategy(String component) {
    this.component = component;
  }

  /**
   * A method to apply the image transformation.
   * @param image - the image which to apply the transformation.
   */
  @Override
  public ImageType transform(ImageType image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int newValue;
    ImageType newImage = image.clone();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = (RGBPixel) image.getPixel(i, j);

        switch (this.component.toLowerCase()) {
          case "red":
            newValue = pixel.getRed();
            break;
          case "green":
            newValue = pixel.getGreen();
            break;
          case "blue":
            newValue = pixel.getBlue();
            break;
          default:
            throw new IllegalArgumentException("Must be either red, green, or blue.");
        }

        RGBPixel newPixel = new RGBPixel(newValue, newValue, newValue);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
