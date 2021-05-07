package hmm2;


public class matrix {

	int column;
	int row;
	float[][] data;

	matrix(int col, int r){
	column = col;
	row = r;
	data = new float[column][row];
	}
	
	public String toString() {
		String text = column + " " + row;
		for(int i = 0; i < column; i++){
			for(int j = 0; j < row; j++) {
				text = text + " " + data[i][j];
			}
		}
		return text;
	}
	
	matrix(String s){
		String[] split_s = s.split(" ");
		column = Integer.parseInt(split_s[0]);
		row = Integer.parseInt(split_s[1]);
		
		data = new float[column][row];
		for(int i = 0; i < column;i++){
			for(int j = 0; j < row; j++) {
				data[i][j] = Float.parseFloat(split_s[i*row+j+2]);
			}
		}
	}
	

	matrix(String s, String random_string){
		String[] split_s = s.split(" ");
		column = Integer.parseInt(split_s[0]);
		row = 1;
		
		data = new float[column][row];
		for(int i = 0; i < column;i++){
				data[i][0] = Float.parseFloat(split_s[i+1]);
		}
	}

	void setvalue(int i,int j,float value){
		data[i][j] = value;
	}

	float getvalue(int i,int j){
		return data[i][j];
	}
	
	float getvalue(float i, float j) {
		return data[Math.round(i)][Math.round(j)]; 
	}

	public matrix matrix_multi(matrix tensor){
		matrix result = new matrix(column,tensor.row);
		if(row == tensor.column){
			for(int i = 0; i < column; i++){
				for(int j = 0; j < tensor.row; j++){
					float sum = 0;
					for(int k = 0; k < row; k++){
						sum = sum + data[i][k]*tensor.getvalue(k,j);
					}
					result.setvalue(i,j,sum);
				}

			}

		}
		
		else{
			System.out.println("Error in dimensions");
		}

		return result;
	}
}
