package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	private final List<T> states;
	private final List<T> predecessors;

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		states = new ArrayList<T>();
		predecessors = new ArrayList<T>();
	}

	@Override
	/*public List<T> solve() {
        // TODO
		if (solution != null) {
			return solution;
		}

		final List<T> path = new ArrayList<T>();
		BFSQueueImple(searchProblem.getInitialState(), path);
		if (path.size() > 0) {
			if (!isValid(path)) {
				throw new RuntimeException(
						"searcher should never find an invalid solution!");
			}
		}
		return path;
	}
			

	public boolean BFSQueueImple(T start, List<T> path) {
		path.add(start);
		if (searchProblem.isGoalState(start)) {
			return true;
		}
		visitedStates.add(start);
		states.add(start);
		while (!states.isEmpty()) {
			T temp = states.remove(0);
			for (T element : searchProblem.getSuccessors(temp)) {
				if (!visitedStates.contains(element)) {
					path.add(element);
					visitedStates.add(element);
					states.add(element);
				}
			}
		}
		return true;
	}

	private List<T> findSolutionWithExplicitPredecessors() {
		if (solution != null) {
			return solution;
		}
		final T initialState = searchProblem.getInitialState();
		states.add(initialState);
		predecessors.add(initialState);
		T current = queueBFSWithExplicitPredecessors(initialState);

		final List<T> path = new ArrayList<T>();

		// if a goal was found
		if (current != null) {
			// build a path by looking up each predecessor, starting from
			// the goal state
			path.add(current);
			while (!current.equals(searchProblem.getInitialState())) {
				final T predecessor = predecessors.get(states.indexOf(current));
				path.add(predecessor);
				current = predecessor;
			}

			// the path is in reverse order (goal-to-initial), so we reverse
			// it into the correct order
			Collections.reverse(path);
		}
		if (path.size() > 0) {
			if (!isValid(path)) {
				throw new RuntimeException(
						"searcher should never find an invalid solution!");
			}
		}
		return path;
	}*/

	public List<T> solve() {
		if (solution != null) {
			return solution;
		}
		final T initialState = searchProblem.getInitialState();
		states.add(initialState);
		predecessors.add(initialState);
		T current = queueBFSWithExplicitPredecessors(initialState);

		final List<T> path = new ArrayList<T>();

		// if a goal was found
		if (current != null) {
			// build a path by looking up each predecessor, starting from
			// the goal state
			path.add(current);
			while (!current.equals(searchProblem.getInitialState())) {
				final T predecessor = predecessors.get(states.indexOf(current));
				path.add(predecessor);
				current = predecessor;
			}

			// the path is in reverse order (goal-to-initial), so we reverse
			// it into the correct order
			Collections.reverse(path);
		}
		if (path.size() > 0) {
			if (!isValid(path)) {
				throw new RuntimeException(
						"searcher should never find an invalid solution!");
			}
		}
		return path;
	}

	private T queueBFSWithExplicitPredecessors(T state) {
		if (searchProblem.isGoalState(state)) {
			return state;
		}

		T curT = state;
		List<T> states1 = new ArrayList<T>();
		states.add(state);
		predecessors.add(state);
		states1.add(state);
		visitedStates.add(state);

		while (!states1.isEmpty() && !searchProblem.isGoalState(curT)) {
			curT = states1.remove(0);
			for (T neighbor : searchProblem.getSuccessors(curT)) {
				if (!visitedStates.contains(neighbor)) {
					if (!states.contains(neighbor)) {
						visitedStates.add(neighbor);
						states.add(neighbor);
						predecessors.add(curT);
						states1.add(neighbor);
					}
				}
			}
		}
		return curT;
	}

}
