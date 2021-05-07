import java.util.*;


public class Player {
    /**
     * Performs a move
     *
     * @param gameState
     *            the current state of the board
     * @param deadline
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
    public GameState play(final GameState gameState, final Deadline deadline) {
        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);

        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }

        Vector<Integer> states = new Vector<Integer>();
        for(int i = 0; i < nextStates.size(); i++) {
        	AlphaBetaPruning prune = new AlphaBetaPruning();
        	int value = prune.alphaBeta(nextStates.get(i));
        	states.add(value);
        }
        
        int highest_value = states.get(0);
        int best_index = 0;
        for(int i = 1; i < states.size(); i++) {
        	if(states.get(i) > highest_value) {
        		highest_value = states.get(i);
        		best_index = i;
        	}
        }
        GameState best = nextStates.elementAt(best_index);
        return best;
    }    
}
