/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.problem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simge Haksal
 */
public class AIProblem {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        State root = new State();
        BFS search = new BFS();
	State solution = search.exec(root);
        printSolution(solution);
        }
    

private static void printSolution(State solution) {
		if (null == solution) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.print(state.toString());
				} else {
					System.out.print(state.toString() + " -> ");
				}
			}
			System.out.println("\nDepth: " + depth);
		}
	}
}
