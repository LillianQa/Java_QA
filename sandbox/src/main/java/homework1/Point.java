package homework1;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p1, Point p2) {

    double dx = (p2.x - p1.x);
    double dy = (p2.y - p1.y);
    return Math.sqrt((dx*dx) * 2 + ((dy*dy)) * 2);

  }

  public static void main (String[] args) {

    Point p1 = new Point(5.6, 7.3);
    Point p2 = new Point(8.6, 2.5);

//    System.out.println("Odlegosc od " + p1.x + " i " +  p1.y + " oraz " +  p2.x  + " i " + p2.y  + " wynosi " + distance(p1, p2));
  }

}

//class FooBuilder {
//  private String a = "";
//  private Integer b = 0;
//
//  FooBuilder setA(String a) {
//    this.a = a;
//    return this;
//  }
//
//  FooBuilder setB(Integer b) {
//    this.b = b;
//    return this;
//  }
//
//  public class Rectangle {
//
//    public double a;
//    public double b;
//
//    public Rectangle(double a, double b) {
//
//      this.a = a;
//      this.b = b;
//    }
//
//    public double area() {
//      return this.a * this.b;
//    }
//
//  }}
