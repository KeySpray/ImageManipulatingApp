package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * A simple class to create an Image Canvas and overwrite JPanel paint/drawing methods.
 */
public class ImageCanvas extends JPanel {
  private BufferedImage image;

  /**
   * The method to set an image, which will then check dimensions and resize canvas, then paint.
   * @param image - the BufferedImage to be drawn/painted in the canvas
   */
  public void setImage(BufferedImage image) {
    this.image = image; // sets image attribute
    revalidate(); // makes sure canvas is being sized properly
    repaint(); // repaints image after new one is set
  }

  /**
   * An overwritten method to return dimensions of my image objects.
   * @return - a dimension object containing information from my image
   */
  @Override
  public Dimension getPreferredSize() {
    if (image != null) {
      return new Dimension(image.getWidth(), image.getHeight()); // gets dimensions of image
    }
    else {
      return super.getPreferredSize(); // uses default dimensions
    }
  }

  /**
   * An overwritten method to paint/draw the image in the center of the canvas.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (image != null) { // finds x,y so image is painted in center of canvas
      int x = (this.getWidth() - image.getWidth()) / 2;
      int y = (this.getHeight() - image.getHeight()) / 2;

      g.drawImage(image, x, y, this); // draws the image in the canvas
    }
  }
}
