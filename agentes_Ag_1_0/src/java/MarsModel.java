
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.factories.ListPermutationFactory;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.ListCrossover;
import org.uncommons.watchmaker.framework.operators.ListOrderMutation;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import alg_gen.ListEvaluator;
import alg_gen.Point;
import jason.environment.grid.GridWorldModel;

class MarsModel extends GridWorldModel {

	List<Point> result2 = new ArrayList<Point>(16);
	int i = 0;
	int j = 0;
	int k = 8;

	public MarsModel model;

	public MarsModel() {
		super(3, 5, 2);
		// TODO Auto-generated constructor stub
		try {
			setAgPos(0, 1, 2);
			setAgPos(0, 1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------generacion de ruta
	void generacion_ruta() throws Exception {

		List<Point> L1 = new ArrayList<Point>(16);
		L1.add(0, new Point(0, 0));
		L1.add(1, new Point(0, 1));
		L1.add(2, new Point(0, 2));
		L1.add(3, new Point(1, 0));
		L1.add(4, new Point(1, 1));
		L1.add(5, new Point(1, 2));
		L1.add(6, new Point(2, 0));
		L1.add(7, new Point(2, 1));
		L1.add(8, new Point(2, 2));
		L1.add(9, new Point(3, 0));
		L1.add(10, new Point(3, 1));
		L1.add(11, new Point(3, 2));
		L1.add(12, new Point(4, 0));
		L1.add(13, new Point(4, 1));
		L1.add(14, new Point(4, 2));
		L1.add(15, new Point(0, 0));
		System.out.println(L1);

		CandidateFactory<List<Point>> factory = new ListPermutationFactory<Point>(L1);
		List<EvolutionaryOperator<List<Point>>> operators = new LinkedList<EvolutionaryOperator<List<Point>>>();
		operators.add(new ListOrderMutation<Point>());
		operators.add(new ListCrossover<Point>(8));
		EvolutionaryOperator<List<Point>> pipeline = new EvolutionPipeline<List<Point>>(operators);
		ListEvaluator fitnessEvaluator = new ListEvaluator();
		// ...........
		SelectionStrategy<Object> selection = new RouletteWheelSelection();
		// SelectionStrategy<Object> selection = new SigmaScaling();
		// SelectionStrategy<Object> selection = new
		// StochasticUniversalSampling() ;
		// SelectionStrategy<Object> selection = new
		// TruncationSelection(0.9);
		// ...........
		Random rng = new MersenneTwisterRNG();
		EvolutionEngine<List<Point>> engine = new GenerationalEvolutionEngine<List<Point>>(factory, pipeline,
				fitnessEvaluator, selection, rng);

		engine.addEvolutionObserver(new EvolutionObserver<List<Point>>() {
			public void populationUpdate(PopulationData<? extends List<Point>> data) {
				System.out.printf("Generation %d: %s\n", data.getGenerationNumber(), data.getBestCandidate());
			}
		});
		// ............................
		List<Point> result = engine.evolve(500, 0, new TargetFitness(16.0, true));
		// List<Point> result = engine.evolve(8, 0, new
		// GenerationCount(100));
		// ............................
		System.out.println("---------------Mejor Trayectoria---------------");
		System.out.println(result);
		result2 = result;

	}

	List<Point> getRuta() {

		return result2;

	}

	// -----------------------------------------
	void nextSlot(List<Point> camino) throws Exception {

		if (i < 8) {

			setAgPos(0, (int) camino.get(i).getY(), (int) camino.get(i).getX());
			Thread.sleep(10000);
			i++;
		} else {
			setAgPos(0, 1, 2);
			Thread.sleep(10000);
		}

	}

	// --------------------------------------------------------------
	void nextSlot1(List<Point> camino) throws Exception {

		if (j == 0) {
			setAgPos(1, 1, 2);
			Thread.sleep(10000);

			j++;
		} else {
			setAgPos(1, (int) camino.get(k).getY(), (int) camino.get(k).getX());
			Thread.sleep(10000);
			k++;

		}

	}

}
