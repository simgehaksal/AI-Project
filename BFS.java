/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.problem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Simge Haksal
 */
public class BFS{
	public State exec(State initialState) {
		if (initialState.isGoal()) {
			return initialState;
		}
		Queue<State> frontier = new LinkedList<State>();	// FIFO queue
		Set<State> explored = new HashSet<State>();
		frontier.add(initialState);
		while (true) {
			if (frontier.isEmpty()) {
				return null;	// failure
			}
			State state = frontier.poll();
			explored.add(state);
			for (int i = 0 ; i < state.child.size();i++) {
				if (!explored.contains(state.child.get(i)) || !frontier.contains(state.child.get(i))) {
					if (state.child.get(i).isGoal()) {
						return state.child.get(i);
					}
					frontier.add(state.child.get(i));
				}
			}
		}
	}
}

