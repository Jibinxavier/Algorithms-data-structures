
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.util.Hashtable;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 1.0 (07/11/14)
 *  @author Jibin Xavier
 */
@RunWith(JUnit4.class)
public class CountiesMapTest
{
    final static Charset ENCODING = StandardCharsets.UTF_8;
    final static String mapFileName = "ireland.png";
    final static String countiesFileName = "countiesIreland.txt";

    // this hashtable is used to store the calculated sizes
    private Hashtable<String,Integer> countySizes = new Hashtable<String,Integer>(40);


    /**
     * This method is run once before the tests
     */
    @Before
    public void calculateCountySizes()
    {

      // load the image
      MapImage img = null;
      try {
        img = new MapImage(mapFileName);
      } catch (IOException e) {
        fail("The file " + mapFileName + " could not be opened.");
      }

      // do the calculations
      CountiesMap cntMap = new CountiesMap(img);
      
      // open the file with the counties coordinates and names
      // and find the sizes of counties.
      // Store everything in a hashtable.
      try {
        Scanner scanner =  new Scanner(Paths.get(countiesFileName), ENCODING.name());
        while (scanner.hasNext()){
          String countyName = scanner.next();
          int countyX = scanner.nextInt();
          int countyY = scanner.nextInt();
          countySizes.put(countyName, cntMap.getCountySize(countyX, countyY));
         
        }
        // System.out.println("   county"+ cntMap.getCountySize(2500, 1000));
      } catch (IOException e) {
        fail("The file " + countiesFileName + " could not be opened.");
      }
    }


    /**
     * This method should return the correct pixel sizes for each county.
     *
     * TODO: replace the -1's with the correct pixel sizes
     *
     * @param countyName  the name of the county
     * @return the pixel size of the county
     */
    private int expectedCountySize(String countyName)
    {
      switch (countyName) {
      case "Antrim":        return 192200;
      case "Armagh":        return 76600;
      case "Carlow":        return 66900;
      case "Cavan":         return 127700;
      case "Clare":         return 167200;
      case "Cork":          return 481600;
      case "Derry":         return 130400;
      case "Donegal":       return 325500;
      case "Down":          return 167200;
      case "Dublin":        return 65800;
      case "Fermanagh":     return 108500;
      case "Galway":        return 381900;
      case "Kerry":         return 311400;
      case "Kildare":       return 106100;
      case "Kilkenny":      return 131000;
      case "Laois":         return 107700;
      case "Leitrim":       return 100000;
      case "Limerick":      return 176100;
      case "Longford":      return 68900;
      case "Louth":         return 56100;
      case "Mayo":          return 346500;
      case "Meath":         return 147400;
      case "Monaghan":      return 81600;
      case "Offaly":        return 125300;
      case "Roscommon":     return 167700;
      case "Sligo":         return 116400;
      case "Tipperary":     return 276900;
      case "Tyrone":        return 130400;
      case "Waterford":     return 120100;
      case "Westmeath":     return 120500;
      case "Wexford":       return 148900;
      case "Wicklow":       return 130700;
      }
      return -1;
    }


    /**
     * The only test here.
     */
    @Test
    public void testdCountySizes()
    {
      for(String countyName : countySizes.keySet())
      {
        int expectedSize = expectedCountySize(countyName);
        assertEquals("County " + countyName + " size", expectedSize, (int)countySizes.get(countyName));
      }
    }


    /**
     * This method simply prints the calculated sizes to the console.
     */
    public void printCountySizes()
    {
      for(String countyName : countySizes.keySet())
      {
        int expectedSize = expectedCountySize(countyName);
        System.out.println("County " + countyName + " size: " + countySizes.get(countyName));
      }
    }

  /**
   * use this main method for console output of the county sizes.
   */
  public static void main(String[] args) {
    CountiesMapTest test = new CountiesMapTest();
    test.calculateCountySizes();
    test.printCountySizes();
  }

}

