import java.util.Vector;

public class AlphaBetaPruning {
	
	public int depth = 1;
	
	public int maximin(GameState state, int alpha, int beta, int depth) {
		if(state.isEOG() || depth ==0){
			return get_state(state);
		}
		
		int value = Integer.MIN_VALUE;
		Vector<GameState> new_states = new Vector<>();
		state.findPossibleMoves(new_states);
		
		for(int i = 0; i < new_states.size(); i++) {
			value = Math.max(value, minimax(new_states.get(i),alpha,beta,depth - 1));
			if(value >= beta) {
				return value;
			}
			alpha = Math.max(alpha, value);
		}
		return value;
			
	}
	
	public int minimax(GameState state, int alpha, int beta, int depth) {
		if(state.isEOG() || depth ==0){
			return get_state(state);
		}
		
		int value = Integer.MAX_VALUE;
		Vector<GameState> new_states = new Vector<>();
		state.findPossibleMoves(new_states);
		
		for(int i = 0; i < new_states.size(); i++) {
			value = Math.min(value, maximin(new_states.get(i),alpha,beta,depth - 1));
			if(value >= beta) {
				return value;
			}
			alpha = Math.min(alpha, value);
		}
		return value;
	}
	
	public int alphaBeta(GameState state) {
		int value = maximin(state,Integer.MIN_VALUE,Integer.MAX_VALUE,depth);
		return value;
	}
	
	public int get_state(GameState state) {
		return heuristic.gamma(state);
	}
}
