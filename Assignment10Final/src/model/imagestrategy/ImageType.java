package model.imagestrategy;

import model.transformationstrategy.Pixel;
import model.transformationstrategy.TransformationStrategy;

/**
 * A simple interface to store the methods required by various ImageTypes.
 */
public interface ImageType {

  /**
   * A method to return the pixel at the given row, col position in the array.
   * @param row - row of the desired pixel.
   * @param col - column of the desired pixel.
   * @return - Pixel at the given location.
   */
  Pixel getPixel(int row, int col);

  /**
   * A method to set a pixel at the desired location.
   * @param row - row in the array at which pixel should be stored.
   * @param col - column in the array at which pixel should be stored.
   */
  void setPixel(int row, int col, Pixel pixel);

  /**
   * A method to return the height parameter of the image.
   * @return - integer denoting the height parameter.
   */
  int getHeight();

  /**
   * A method to return the width parameter of the image.
   * @return - integer denoting the width parameter.
   */
  int getWidth();

  /**
   * A method to return the max color value of any/every pixel in the image.
   * @return - integer denoting the max color value (typically between 0 - 255) parameter.
   */
  int getMaxColorValue();

  /**
   * A method to apply the desired transformation to the image.
   */
  ImageType applyTransformation(TransformationStrategy transformation);

  ImageType clone();
}
