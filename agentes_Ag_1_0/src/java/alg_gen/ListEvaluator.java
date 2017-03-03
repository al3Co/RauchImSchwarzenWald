package alg_gen;

import java.util.ArrayList;
import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 * Created by Mario_pc on 16/02/2017.
 */
public class ListEvaluator implements FitnessEvaluator<List<Point>> {
	public Point base = new Point(2, 1);
	public List<Point> obj = new ArrayList<Point>(16);

	public ListEvaluator() {
		obj.add(0, base);
		obj.add(1, new Point(2, 2));
		obj.add(2, new Point(3, 2));
		obj.add(3, new Point(4, 2));
		obj.add(4, new Point(4, 1));
		obj.add(5, new Point(4, 0));
		obj.add(6, new Point(3, 0));
		obj.add(7, new Point(3, 1));
		obj.add(8, new Point(2, 0));
		obj.add(9, new Point(1, 0));
		obj.add(10, new Point(0, 0));
		obj.add(11, new Point(0, 1));
		obj.add(12, new Point(0, 2));
		obj.add(13, new Point(1, 2));
		obj.add(14, new Point(1, 1));
		obj.add(15, base);

	}

	public double getFitness(List<Point> candidate, List<? extends List<Point>> population) {

		int matches = 0;
		for (int i = 0; i < candidate.size(); i++) {
			if (candidate.get(i).equals(obj.get(i))) {
				++matches;
			}
			;
		}
		return matches;
	}

	public boolean isNatural() {
		return true;
	}

}
