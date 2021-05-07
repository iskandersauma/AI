import java.util.Vector;

public class Minimax {
	
	private GameState nextState;
	
	public Minimax() {
		nextState = new GameState();
	}
	
	public GameState get() {
		return nextState;
	}
	
	public int minimax(GameState current_state, int depth,int player) {
		GameState new_state = new GameState();
		Vector<GameState> states = new Vector<>();
		current_state.findPossibleMoves(states);
		
		if(depth == 0) {
			return get_state(current_state);
		}
		else {
			if(player == Constants.CELL_X) {
				int best_value = Integer.MIN_VALUE;
				for(int i = 0; i <states.size(); i++) {
					int value = minimax(states.get(i),depth -1,Constants.CELL_O);
					if(value >= best_value) {
						best_value = value;
						new_state = states.get(i);
					}
				}
				nextState = new_state;
				return best_value;
			}
			else {
				int best_value = Integer.MAX_VALUE;
				for(int i = 0; i <states.size(); i++) {
					int v = minimax(states.get(i),depth -1,Constants.CELL_X);
					if(v < best_value) {
						best_value = v;
						new_state = states.get(i);
					}
				}
				nextState = new_state;
				return best_value;
			}
		}
	}
	
	
	private int get_state(GameState state) {
		if(state.isEOG()) {
			if(state.getNextPlayer() == Constants.CELL_X) {
				if(state.isXWin() == true) {
					int value = 1;
					return value;
				}
				else {
					int value = 0;
					return value;
				}
			}
			else {
				if(state.isOWin() == true) {
					int value = -1;
					return value;
				}
				else {
					int value = 0;
					return value;
				}
			}
		}
		else {
			return Heuristic.gamma(state);
		}
	}
}
