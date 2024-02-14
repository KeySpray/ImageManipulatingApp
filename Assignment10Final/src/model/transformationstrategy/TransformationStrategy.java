package model.transformationstrategy;

import model.imagestrategy.ImageType;

/**
 * Simple interface to enable future addition of other transformation strategies.
 */
public interface TransformationStrategy {

  /**
   * Method to apply the desired strategy to an ImageType.
   * @param image - the image to which apply the transformation.
   */
  ImageType transform(ImageType image);
}
