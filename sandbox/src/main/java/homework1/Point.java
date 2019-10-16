package homework1;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    return this.getX() - p.getX() + this.getY() - p.getY();
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public static void main (String[] args) {

    Point p1 = new Point(5.6, 7.3);
    Point p2 = new Point(8.6, 2.5);

    System.out.println("Odlegosc od " + p1.x + " i " +  p1.y + " oraz " +  p2.x  + " i " + p2.y  + " wynosi " + p1.distance(p2) );
  }}

