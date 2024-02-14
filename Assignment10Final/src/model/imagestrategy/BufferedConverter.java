package model.imagestrategy;

import java.awt.image.BufferedImage;

import model.transformationstrategy.Pixel;
import model.transformationstrategy.RGBPixel;
import model.transformationstrategy.TransformationStrategy;

/**
 * This class enables support of JPG and PNG image formats. It implements ImageType for abstraction.
 */
public class BufferedConverter implements ImageType {

  private final BufferedImage image;

  /**
   * Constructor for BufferedImage types (return type from java's ImageIO class).
   * @param image - the buffered image.
   */
  public BufferedConverter(BufferedImage image) {
    this.image = image;
  }

  /**
   * Constructor for ImageType image types (type for PPMImageIO write()).
   * @param imageType - the PPM image type / eventual abstract type for all images.
   */
  public BufferedConverter(ImageType imageType) {
    int width = imageType.getWidth();
    int height = imageType.getHeight();
    // creates buffered image with RGB color model.
    this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // iterates through pixels in image
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = imageType.getPixel(y, x);
        if (pixel instanceof RGBPixel) { // checks if pixel is RGBPixel type
          RGBPixel rgbPixel = (RGBPixel) pixel;
          // performs bitwise operations to convert pixels to BufferedImage Pixels
          int rgb = (rgbPixel.getRed() << 16) | (rgbPixel.getGreen() << 8) | rgbPixel.getBlue();
          this.image.setRGB(x, y, rgb); // sets pixel
        }

        else {
          throw new IllegalArgumentException("ImageType contains non-RGB pixels");
        }
      }
    }
  }

  /**
   * A method to return channel values of pixels to enable transformation strategies.
   * @param row - row of the desired pixel.
   * @param col - column of the desired pixel.
   * @return - returns an RGBPixel constructed from BufferedImage pixel type.
   */
  @Override
  public Pixel getPixel(int row, int col) {
    int rgb = image.getRGB(col, row);
    int r = (rgb >> 16) & 0xFF; // shifts bits 16 to right, only takes 8 least significant
    int g = (rgb >> 8) & 0xFF; // shifts bits 8 to right, only takes 8 least significant
    int b = rgb & 0xFF; // only takes 8 least significant (already in correct position)
    return new RGBPixel(r, g, b);
  }

  /**
   * A method to set a pixel for BufferedImage type.
   * @param row - row in the array at which pixel should be stored.
   * @param col - column in the array at which pixel should be stored.
   * @param pixel - the pixel at the given col/row.
   */
  @Override
  public void setPixel(int row, int col, Pixel pixel) {
    int rgb = (((RGBPixel) pixel).getRed() << 16 | ((RGBPixel) pixel).getGreen() << 8
            | ((RGBPixel) pixel).getBlue());
    image.setRGB(col, row, rgb);
  }

  /**
   * A method to get the height of the image.
   * @return - height of the image in pixels.
   */
  @Override
  public int getHeight() {
    return image.getHeight();
  }

  /**
   * A method to get the width of the image.
   * @return - width of the image in pixels.
   */
  @Override
  public int getWidth() {
    return image.getWidth();
  }

  /**
   * A method to get the max color value of pixels of the image.
   * @return - 255, default max color value for these image types.
   */
  @Override
  public int getMaxColorValue() {
    return 255;
  }

  /**
   * A method to apply transformation strategies.
   * @return - the new image after being transformed.
   */
  @Override
  public ImageType applyTransformation(TransformationStrategy transformation) {
    return transformation.transform(this);
  }

  /**
   * A method to clone BufferedImage types.
   * @return - BufferedImage.
   */
  @Override
  public ImageType clone() {
    BufferedImage imageCopy = new BufferedImage(image.getColorModel(),
            image.copyData(null), image.isAlphaPremultiplied(), null);
    return new BufferedConverter(imageCopy);
  }

  /**
   * A method to return the image.
   * @return - the image.
   */
  public BufferedImage getBufferedImage() {
    return image;
  }
}
