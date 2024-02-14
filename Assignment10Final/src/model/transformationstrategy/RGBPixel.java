package model.transformationstrategy;

/**
 * A Pixel interface implementation for RGB pixels.
 */
public class RGBPixel implements Pixel {

  public int red;
  public int green;
  public int blue;

  /**
   * Constructor for an RGB Pixel.
   * @param red - red channel of the pixel.
   * @param green - green channel of the pixel.
   * @param blue - blue channel of the pixel.
   */
  public RGBPixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  protected void setRed(int newRed) {
    this.red = newRed;
  }

  protected void setGreen(int newGreen) {
    this.green = newGreen;
  }

  protected void setBlue(int newBlue) {
    this.blue = newBlue;
  }

  /**
   * Simple getter for the red channel of the pixel.
   * @return - integer denoting red channel color value.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Simple getter for the green channel of the pixel.
   * @return - integer denoting green channel color value.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Simple getter for the blue channel of the pixel.
   * @return - integer denoting blue channel color value.
   */
  public int getBlue() {
    return this.blue;
  }
}
