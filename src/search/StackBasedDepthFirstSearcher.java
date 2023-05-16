package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	private final List<T> states;
	private final List<T> predecessors;

	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		states = new ArrayList<T>();
		predecessors = new ArrayList<T>();
	}

	@Override
	public List<T> solve() {
		if (solution != null) {
			return solution;
		}
		final T initialState = searchProblem.getInitialState();
		states.add(initialState);
		predecessors.add(initialState);
		T current = stackDFSWithExplicitPredecessors(initialState);

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

	private T stackDFSWithExplicitPredecessors(T state) {
		if (searchProblem.isGoalState(state)) {
			return state;
		}

		T curT = state;	
		List<T> states1 = new ArrayList<T>();
		states.add(state);
		predecessors.add(state);
		states1.add(state);

		while (!states1.isEmpty() && !searchProblem.isGoalState(curT)) {
			curT = states1.remove(states1.size()-1);
			visitedStates.add(curT);
			for (T neighbor : searchProblem.getSuccessors(curT)) {
				if (!visitedStates.contains(neighbor)) {
					if (!states.contains(neighbor)) {
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
