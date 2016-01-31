

/************************************************************************
 *
 * MapImage
 *
 * @version 1.0
 * @author Vasileios Koutavas
 *
 * This class provides a very simple API for loading and accessing the
 * pixels of an image.
 *
 ************************************************************************/

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public final class MapImage
{

  private final BufferedImage img;

  /**
    * Constructs a map image from a filename.
    *
    * @param fileName   the name of the file containing the image.
    *
    * @throws IOException  if the file cannot be opened.
    *   
    */
  public MapImage(String fileName) throws IOException
  {
    img = ImageIO.read(new File(fileName));
  }

  /**
    * Returns the integer RGB value for a pixel. 
    *
    * An <code>ArrayOutOfBoundsException</code> may be thrown
    * if the coordinates are not in bounds.
    * However, explicit bounds checking is not guaranteed.
    *
    * @param x the X coordinate of the pixel from which to get
    *          the pixel in the default RGB colour model and sRGB
    *          colour space
    * @param y the Y coordinate of the pixel from which to get
    *          the pixel in the default RGB colour model and sRGB
    *          colour space
    * @return  the RGB colour value of the pixel at position (x, y).
    */
  public int getRGB(int x, int y) { return img.getRGB(x,y); }

  /**
    * Returns the minimum x coordinate of this <code>MapImage</code>.
    * This is always zero.
    * @return the minimum x coordinate of this <code>MapImage</code>.
    */
  public int getMinX() { return img.getMinX(); }


  /**
    * Returns the minimum y coordinate of this <code>MapImage</code>.
    * This is always zero.
    * @return the minimum y coordinate of this <code>MapImage</code>.
    */
  public int getMinY() { return img.getMinY(); }

  /**
    * Returns the height of the MapImage.
    * This is the max y coordinate of the MapImage + 1
    * @return the height of this MapImage
    */
  public int getHeight() { return img.getHeight(); }

  /**
    * Returns the width of the MapImage.
    * This is the max x coordinate of the MapImage + 1
    * @return the width of this MapImage
    */
  public int getWidth() { return img.getWidth(); }

}
    


  
