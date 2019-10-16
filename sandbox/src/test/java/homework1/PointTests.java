package homework1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea() {

    Point p1 = new Point(8.0, 7.0);
    Point p2 = new Point(8.0, 2.5);
    Assert.assertEquals(p1.distance(p2), 4.5);
  }

  @Test
  public void testArea2() {

    Point p1 = new Point(8.0, 7.0);
    Point p2 = new Point(8.0, 2.5);
    Assert.assertEquals(p1.distance(p2), 5.6);
  }

  @Test
  public void testArea3() {

    Point p1 = new Point(5.4, 7.6);
    Point p2 = new Point(5.6, 6.3);
    Assert.assertNotEquals(p1.distance(p2), 5.6);
  }
}
