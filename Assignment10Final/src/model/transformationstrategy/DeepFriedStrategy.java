package model.transformationstrategy;

import model.imagestrategy.ImageType;

import static java.lang.Math.max;

/**
 * A strategy method to apply the deepfry/inverse transformation strategy to an ImageType.
 */
public class DeepFriedStrategy implements TransformationStrategy {

  /**
   * Empty constructor for the deep-fry strategy.
   */
  public DeepFriedStrategy() {
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
        int newRed = max(0, 255 - pixel.getRed());
        int newGreen = max(0, 255 - pixel.getGreen());
        int newBlue = max(0, 255 - pixel.getBlue());

        RGBPixel newPixel = new RGBPixel(newRed, newGreen, newBlue);

        newImage.setPixel(i, j, newPixel);
      }
    }

    return newImage;
  }
}
