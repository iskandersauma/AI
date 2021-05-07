package hmm2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	 public static void main(String[] args) throws IOException{ 
		 BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		 String rad1 = stdin.readLine();
		 String rad2 = stdin.readLine();
		 String rad3 = stdin.readLine();
		 String rad4 = stdin.readLine();
	        
		 matrix A = new matrix(rad1);
		 matrix B = new matrix(rad2);
		 matrix pi = new matrix(rad3);
		 matrix Observations = new matrix(rad4,"random_string");
		 
		 int N = pi.row;
		 int time_steps = Observations.column;
		 
		 float[][] delta = new float[time_steps][N];
		 int[][] delta_idx = new int[time_steps][N];
		 
		 for(int i = 0; i < N; i++) {
			 delta[0][i] = B.getvalue(i, Observations.getvalue(0, 0))*pi.getvalue(0, i);
		 }
		 
		 for(int t = 1; t < time_steps; t++){
			 for(int i = 0; i < N; i++) {
				 float max_value = 0;
				 delta_idx[t][i] = 0;
				 for(int j = 0; j < N; j++) {
					 float value = A.getvalue(j, i)*delta[t-1][j]*B.getvalue(i, Observations.getvalue(t, 0));
					 if(value > max_value) {
						 max_value = value;
						 delta_idx[t][i] = j;
						 }
					 }
				 delta[t][i] = max_value;
				 }
			 }
		 
		 int[] x = new int[time_steps];
		 float delta_T = 0;
		 
		 for(int i = 0; i < N; i++) {
			 if(delta[time_steps-1][i] > delta_T) {
				x[time_steps-1] = i; 
			 }
		 }
		 
		 for(int i = 0; i < time_steps-1;i++) {
			 x[time_steps-i-2] = delta_idx[time_steps-i-1][x[time_steps-i-1]];
		 }
		 
		 for(int i = 0; i < time_steps; i++) {
			 System.out.println(x[i] + " ");
		 }
	 }
	 
}
