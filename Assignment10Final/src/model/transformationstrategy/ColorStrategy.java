package model.transformationstrategy;

import model.imagestrategy.ImageType;

/**
 * A strategy method to apply the one-component greyscale strategy to an ImageType.
 */
public class ColorStrategy implements TransformationStrategy {

  private final double[][] kernel;

  /**
   * A constructor for the one-component strategy.
   *
   * @param filter - the desired color transformation to apply to image.
   */
  public ColorStrategy(String filter) {
    switch (filter.toLowerCase()) {
      case "greyscale": // accepts British or American spelling of grey
      case "grayscale":
        // creates greyscale kernel
        this.kernel = new double[][] {{.2126, .7152, .0722},
          {.2126, .7152, .0722},
          {.2126, .7152, .0722}};
        break;
      case "sepia":
        // creates sepia kernel
        this.kernel = new double[][] {{.393, .769, .189},
          {.349, .686, .168},
          {.272, .534, .131}};
        break;
      default:
        throw new IllegalArgumentException("Color type must be either greyscale or sepia.");
    }
  }

  /**
   * A method to apply the image transformation.
   *
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

        RGBPixel pixel = (RGBPixel) image.getPixel(i, j); // gets current pixel

        // stores old values in 1d array
        int[] oldColors = {pixel.getRed(), pixel.getGreen(), pixel.getGreen()};
        // creates empty 1d array for new values
        int[] newColors = new int[3];

        for (int k = 0; k < 3; k++) { // iterates through rows of matrix
          // applies linear transformation for columns in row
          newColors[k] = (int) Math.round(kernel[k][0] * oldColors[0] +
                  kernel[k][1] * oldColors[1] + kernel[k][2] * oldColors[2]);
          newColors[k] = Math.max(0, Math.min(255, newColors[k]));
        }

        // creates pixel with new channel values
        RGBPixel newPixel = new RGBPixel(newColors[0], newColors[1], newColors[2]);


        newImage.setPixel(i, j, newPixel); // sets the new pixel on the copy of the image
      }
    }

    return newImage;
  }
}
