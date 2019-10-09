package pl.stqa.pft.sandbox;

public class JavaFirst {

	public static void main (String[] args) {

		hello("User");
		hello("Karolina");

		double l = 5;
		System.out.println("Kwadrat o boku " + l + " = " + area(l));

		double a = 4;
		double b = 6;

		System.out.println("Powierzchnia prostokąta o bokach " + a + " i " + b + " = " + area(a, b));

	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area (double len) {
		return len * len;
	}

	public static double area(double a, double b) {
		return a * b;
	}
}