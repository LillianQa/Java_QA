package homework1;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    return Math.sqrt((Math.pow(this.getX() - p.getX(), 2)) + (Math.pow(this.getY() - p.getY(), 2)));
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public static void main (String[] args) {

    Point p1 = new Point(9.0, 4.0);
    Point p2 = new Point(4.5, 3.0);

    System.out.println("Odlegosc od " + p1.x + " i " +  p1.y + " oraz " +  p2.x  + " i " + p2.y  + " wynosi " + p1.distance(p2) );
  }}

