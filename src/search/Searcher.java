package search;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstraction over the idea of a search.
 *
 * @author liberato
 *
 * @param <T>
 */
public abstract class Searcher<T> {
	protected final SearchProblem<T> searchProblem;
	protected final List<T> visitedStates;
	protected List<T> solution;

	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
		this.visitedStates = new ArrayList<T>();
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public abstract List<T> solve();

	/**
	 * Checks that a solution is valid.
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValid(List<T> solution) throws NullPointerException{
        // TODO
		if (solution == null) {throw new NullPointerException();}
		if (solution.isEmpty()) {return false;}
		T curNode = searchProblem.getInitialState();
		int count = 0;
		if (solution.get(0).equals(curNode) && searchProblem.isGoalState(solution.get(solution.size()-1))) {
			while (count < solution.size()-1) {
				if (searchProblem.getSuccessors(curNode).contains(solution.get(count + 1))) {
					curNode = solution.get(count + 1);
					count+=1;
				}
				else {
					break;
				}
			}
		}
		if (count == solution.size() - 1) {
			return true;
		}
        return false;
	}
}
