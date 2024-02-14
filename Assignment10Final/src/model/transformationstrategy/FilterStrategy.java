package model.transformationstrategy;

import model.imagestrategy.ImageType;

/**
 * A strategy method to apply the one-component greyscale strategy to an ImageType.
 */
public class FilterStrategy implements TransformationStrategy {

  private final double[][] kernel;

  /**
   * A constructor for the one-component strategy.
   * @param filter - the desired kernel type to be applied.
   */
  public FilterStrategy(String filter) {
    switch (filter.toLowerCase()) { // ignores case for filter/kernel type
      case "sharpen": // creates sharpen kernel
        this.kernel = new double[][] {{-.125, -.125, -.125, -.125, -.125},
          { -.125, .25, .25, .25, -.125},
          {-.125, .25, 1, .25, -.125},
          { -.125, .25, .25, .25, -.125},
          {-.125, -.125, -.125, -.125, -.125}};
        break;
      case "blur": // creates blur kernel
        this.kernel = new double[][] {{.0625, .125, .0625},
          {.125, .25, .125},
          {.0625, .125, .0625}};
        break;
      default: // throws exception if kernel not blur or sharpen
        throw new IllegalArgumentException("Filter type either blur or sharpen.");
    }
  }

  /**
   * A method to apply the image transformation.
   * @param image - the image which to apply the transformation.
   */
  @Override
  public ImageType transform(ImageType image) {
    int height = image.getHeight();
    int width = image.getWidth();
    ImageType newImage = image.clone(); // clones image
    // initializes fields to hold new channel values
    double newRed = 0;
    double newGreen = 0;
    double newBlue = 0;

    int kernelHeight = kernel.length; // stores height of kernel matrix
    int kernelWidth = kernel[0].length; // stores width of kernel matrix by taking length of row
    int halfHeight = kernelHeight / 2; // creates height bound for loop
    int halfWidth = kernelWidth / 2; // creates width bound for loop

    for (int i = 0; i < height; i++) { // iterates through pixels
      for (int j = 0; j < width; j++) {
        newRed = 0; // initializes new red channel to 0
        newGreen = 0; // initializes new green channel to 0
        newBlue = 0; // initializes new blue channel to 0

        // iterates over kernel dimensions
        for (int ki = -halfHeight; ki <= halfHeight; ki++) {
          for (int kj = -halfWidth; kj <= halfWidth; kj++) {
            // ensures kernel applied to valid pixels only
            if (i + ki >= 0 && i + ki < height && j + kj >= 0 && j + kj < width) {
              // gets neighboring pixels
              RGBPixel pixel = (RGBPixel) image.getPixel(i + ki, j + kj);
              // selects correct value from kernel matrix (adds ki, kj to revert to 0 based index)
              double kernelValue = kernel[ki + halfHeight][kj + halfWidth];
              newRed += pixel.getRed() * kernelValue; // running sum of pixel * kernel values
              newGreen += pixel.getGreen() * kernelValue; // running sum of pixel * kernel values
              newBlue += pixel.getBlue() * kernelValue; // running sum of pixel * kernel values
            }
          }
        }

        // creates new pixel based on rounded values of new channels, cast to int
        newRed = Math.max(0, Math.min(Math.round(newRed), 255));
        newGreen = Math.max(0, Math.min(Math.round(newGreen), 255));
        newBlue = Math.max(0, Math.min(Math.round(newBlue), 255));

        // creates new pixel from filtered channel values
        RGBPixel newPixel = new RGBPixel((int) newRed, (int) newGreen, (int) newBlue);


        newImage.setPixel(i, j, newPixel); // sets the new pixel on the copy of the image
      }
    }

    return newImage;
  }
}
