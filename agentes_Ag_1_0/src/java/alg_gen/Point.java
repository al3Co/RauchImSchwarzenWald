package alg_gen;

import java.util.Random;

/**
 * Created by Mario_pc on 15/02/2017.
 */
import mikera.vectorz.Vector2;

/**
 * Created by Carlos on 01/08/2015.
 *
 * The idea of this class is to encapsulate all the stuff related to point in
 * the map in one single entry point This should be the only "Point" class
 * referenced in the whole project
 */
public class Point implements Cloneable {

	protected Vector2 vector;

	public Point(double x, double y) {
		vector = new Vector2(x, y);
	}

	public Point(Point p) {
		this.vector = new Vector2(p.getX(), p.getY());
	}

	public double distance(Point pt) {
		return vector.distance(pt.vector);
	}

	public double getX() {
		return vector.getX();
	}

	public double getY() {
		return vector.getY();
	}

	/**
	 * Provides the required equals method.
	 * <p>
	 * Two points are equal if their (x,y) values are equal to each other within
	 * the floating point tolerance FloatingPoint.epsilon, as defined in
	 *
	 * @param o
	 *            the object against which to compare.
	 * @see FloatingPoint
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (o instanceof Point) {
			Point ip = (Point) o;

			return ((FloatingPoint.value(this.getX() - ip.getX()) == 0)
					&& (FloatingPoint.value(this.getY() - ip.getY()) == 0));
		}

		// nope
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// use java.awt.2DPoint hashcode function
		long bits = java.lang.Double.doubleToLongBits(getX());
		bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
		return (((int) bits) ^ ((int) (bits >> 32)));
	}

	public Point fromPoint(double dx, double dy) {
		return new Point(this.getX() + dx, this.getY() + dy);
	}

	public Point translateInDirectionToPoint(Point target, double distance) {
		Vector2 direction = target.vector.clone();
		direction.sub(vector);
		direction.normalise();

		Vector2 result = vector.clone();
		result.addMultiple(direction, distance);
		return new Point(result.getX(), result.getY());
	}

	public Point translateInPerpendicularDirectionToPointWithRandomSense(Point target, double distance, Random rng) {
		Vector2 direction = target.vector.clone();
		direction.sub(vector);
		direction.normalise();

		// Say you have a vector v = (x, y). If you now choose another vector w
		// = (-y, x) or w = (y, -x) both vectors will be perpendicular to v.
		// Proof:
		// dot(v, w) = x*(-y) + y*x = 0
		// Note that negating the first element rotates ccw and negating the
		// second element rotates cw.
		double newX = direction.getY();
		double newY = direction.getX();
		if (rng.nextBoolean())
			newX *= -1;
		else
			newY *= -1;

		direction.setValues(newX, newY);

		Vector2 result = vector.clone();
		result.addMultiple(direction, distance);
		return new Point(result.getX(), result.getY());
	}

	@Override
	public Point clone() {
		return new Point(this.getX(), this.getY());
	}

	@Override
	public String toString() {
		return "(" + vector.getX() + ", " + vector.getY() + ")";
	}
}