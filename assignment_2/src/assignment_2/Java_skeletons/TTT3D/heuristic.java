
public class heuristic {
	
	public static int gamma(GameState state) {
		int score = 0;
		int x = 0;
		int o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				for(int k = 0; k < GameState.BOARD_SIZE; k++) {
					if(state.at(i,j,k) == Constants.CELL_X) {
						x++;
					}
					else if(state.at(i,j,k) == Constants.CELL_O) {
						o++;
					}
				}
				score += score(o,x);
				x = 0;
				o = 0;
			}
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				for(int k = 0; k < GameState.BOARD_SIZE; k++) {
					if(state.at(i,k,j) == Constants.CELL_X) {
						x++;
					}
					else if(state.at(i,k,j) == Constants.CELL_O) {
						o++;
					}
				}
				score += score(o,x);
				x = 0;
				o = 0;
			}
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				for(int k = 0; k < GameState.BOARD_SIZE; k++) {
					if(state.at(k,i,j) == Constants.CELL_X) {
						x++;
					}
					else if(state.at(k,i,j) == Constants.CELL_O) {
						o++;
					}
				}
				score += score(o,x);
				x = 0;
				o = 0;
			}
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(j,j,i) == Constants.CELL_X){
					x++;
				}
				else if(state.at(j,j,i) == Constants.CELL_O) {
					o++;
				}
			}
			score += score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(GameState.BOARD_SIZE - 1 - j,j,i) == Constants.CELL_X){
					x++;
				}
				else if(state.at(GameState.BOARD_SIZE - 1 -j,j,i) == Constants.CELL_O) {
					o++;
				}
			}
			score += score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(j,i,j) == Constants.CELL_X){
					x++;
				}
				else if(state.at(j,i,j) == Constants.CELL_O) {
					o++;
				}
			}
			score += score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(GameState.BOARD_SIZE - 1 - j,i,j) == Constants.CELL_X){
					x++;
				}
				else if(state.at(GameState.BOARD_SIZE - 1 - j,i,j) == Constants.CELL_O) {
					o++;
				}
			}
			score += score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(i,j,j) == Constants.CELL_X){
					x++;
				}
				else if(state.at(i,j,j) == Constants.CELL_O) {
					o++;
				}
			}
			score += score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			for(int j = 0; j < GameState.BOARD_SIZE; j++) {
				if(state.at(i,j,GameState.BOARD_SIZE - 1 - j) == Constants.CELL_X){
					x++;
				}
				else if(state.at(i,j,GameState.BOARD_SIZE - 1 - j) == Constants.CELL_O) {
					o++;
				}
			}
			score += score(o,x);
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			if(state.at(i, i, i) == Constants.CELL_X) {
				x++;
			}
			else if(state.at(i, i, i) == Constants.CELL_O) {
				o++;
			}
		}
		score += score(o,x);
		x = 0;
		o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			if(state.at(i, GameState.BOARD_SIZE - 1 - i, i) == Constants.CELL_X) {
				x++;
			}
			else if(state.at(i, GameState.BOARD_SIZE - 1 - i, i) == Constants.CELL_O) {
				o++;
			}
		}
		score += score(o,x);
		x = 0;
		o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			if(state.at(GameState.BOARD_SIZE - 1 - i, i, i) == Constants.CELL_X) {
				x++;
			}
			else if(state.at(GameState.BOARD_SIZE - 1 - i, i, i) == Constants.CELL_O) {
				o++;
			}
		}
		score += score(o,x);
		x = 0;
		o = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++) {
			if(state.at(GameState.BOARD_SIZE - 1 - i, GameState.BOARD_SIZE - 1 - i, i) == Constants.CELL_X) {
				x++;
			}
			else if(state.at(GameState.BOARD_SIZE - 1 - i, GameState.BOARD_SIZE - 1 - i, i) == Constants.CELL_O) {
				o++;
			}
		}
		score += score(o,x);
		x = 0;
		o = 0;
		
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
