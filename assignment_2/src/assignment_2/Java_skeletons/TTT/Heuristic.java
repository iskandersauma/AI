public class Heuristic {
	
	public static int gamma(GameState state) {
		int score = 0;
		int x = 0;
		int o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			if(state.at(i,i) == Constants.CELL_X) {
				x++;
			}
			else if(state.at(i, i) == Constants.CELL_O) {
				o++;
			}
		}
		
		score += score(o,x);
		x = 0;
		o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			if(state.at(GameState.BOARD_SIZE-i-1, i) == Constants.CELL_X) {
				x++;
			}
			else if(state.at(GameState.BOARD_SIZE-i-1, i) == Constants.CELL_O) {
				o++;
			}
		}
		score += score(o,x);
		x = 0;
		o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(i, j) == Constants.CELL_X) {
					x++;
				}
				else if(state.at(i, j) == Constants.CELL_O) {
					o++;
				}
			}
			score = score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(j, i) == Constants.CELL_X) {
					x++;
				}
				else if(state.at(j, i) == Constants.CELL_O) {
					o++;
				}
			}
			score = score(o,x);
			x = 0;
			o = 0;
		}
		return score;
	}
	
	private static int score(int o, int x) {
		int score  = 0;
		if(o+x == GameState.BOARD_SIZE){
			return score;
		}
		if(o == 0) {
			if(x == 1) {
				score += 10;
			}
			else if(x == 2) {
				score += 100;
			}
			else if(x ==3) {
				score += 1000;
			}
			else if(x == 4) {
				score += 10000;
			}
		}
		
		if(x == 0) {
			if(o == 1) {
				score -= 10;
				}
			else if(o == 2) {
				score -= 100;
			}
			else if(o ==3) {
				score -= 1000;
			}
			else if(o == 4) {
				score -= 10000;
			}
		}
		return score;
	}

}
