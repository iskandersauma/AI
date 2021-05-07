package hmmc;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("/home/iskander/eclipse-workspace/hmmc/src/hmmc/input_10000.in");
		BufferedReader stdin = new BufferedReader(new FileReader(file));
		String rad1 = stdin.readLine();
		String rad2 = stdin.readLine();
		String rad3 = stdin.readLine();
		String rad4 = stdin.readLine();
		String rad5 = stdin.readLine();
		String rad6 = stdin.readLine();
		String rad7 = stdin.readLine();
		stdin.close();
	        
		 matrix A = new matrix(rad1);
		 matrix B = new matrix(rad2);
		 matrix pi = new matrix(rad3);
		 matrix Observations = new matrix(rad4,"random_string");
		 matrix A_comp = new matrix(rad5);
		 matrix B_comp = new matrix(rad6);
		 matrix pi_comp = new matrix(rad7);
		 
		 int N = pi.row;
		 int time_steps = Observations.column;
		 int K = B.row;
		 double[][] beta = new double[time_steps][N];
		 double[][] alpha = new double[time_steps][N];
		 double[][][] gamma_ij = new double[time_steps][N][N];
		 double[][] gamma_i = new double[time_steps][N];
		 int maxIters = 15000;
		 int iters = 0;
		 double oldLogProb = Double.NEGATIVE_INFINITY;
		 boolean convergance = true;
		 
		 while(convergance) {
		     //calculate alpha
			 double[] c = new double[time_steps];
			 c[0] = 0;
		     for(int i = 0; i < N; i++) {
		    	 alpha[0][i] = pi.getvalue(0, i)*B.getvalue(i, Observations.getvalue(0, 0));
		    	 c[0] = c[0] + alpha[0][i];
		     }
		     
		     c[0] = 1/c[0];
		     for(int i = 0; i < N;i++) {
		    	 alpha[0][i] = c[0]*alpha[0][i];
		     }
		     
		     for(int t = 1; t < time_steps;t++) {
		    	 c[t] = 0;
		    	 for(int i = 0; i < N; i++) {
		    		 alpha[t][i] = 0;
		    		 for(int j = 0; j < N;j++) {
		    			 alpha[t][i] = alpha[t][i] + alpha[t-1][j]*A.getvalue(j,i);
		    		 }
		    		 alpha[t][i] = alpha[t][i]*B.getvalue(i, Observations.getvalue(t, 0));
		    		 c[t] = c[t] + alpha[t][i];
		    	 }
		    	 c[t] = 1/c[t];
		    	 for(int i = 0;i < N;i++) {
		    		 alpha[t][i] = c[t]*alpha[t][i];
		    	 }
		     }
			 //done with alpha calculations
		     
		     //calculating beta
		     for(int i = 0; i<N; i++) {
		    	 beta[time_steps-1][i] = c[time_steps-1];
		     }
		     
		     for(int t = time_steps-2; t > 0; t--) {
		    	 for(int i = 0; i < N; i++) {
		    		 beta[t][i] = 0;
		    		 for(int j = 0; j < N; j++) {
		    			 beta[t][i] = beta[t][i] + A.getvalue(i,j)*B.getvalue(j, Observations.getvalue(t+1, 0))*beta[t+1][j];
		    		 }
		    		 beta[t][i] = c[t]*beta[t][i];
		    	 }
		     }
		     //done with beta calculations
		     
		     //calculating gamma_i and gamma_ij
		     
		     for(int t = 0; t < time_steps-1;t++) {
		    	 double denom = 0;
		    	 for(int i = 0; i < N; i++) {
		    		 for(int j = 0; j < N; j++) {
		    			 denom = denom + alpha[t][i]*A.getvalue(i,j)*B.getvalue(j, Observations.getvalue(t+1, 0))*beta[t+1][j];
		    		 }
		    	 }
		    	 for(int i = 0; i < N; i++) {
		    		 gamma_i[t][i] = 0;
		    		 for(int j = 0; j < N; j++) {
		    			 gamma_ij[t][i][j] = alpha[t][i]*A.getvalue(i,j)*B.getvalue(j, Observations.getvalue(t+1, 0))*beta[t+1][j]/denom;
		    			 gamma_i[t][i] = gamma_i[t][i] + gamma_ij[t][i][j];
		    		 }
		    	 }	    	 
		     }
		     
		     double denom = 0;
		     for(int i = 0; i < N; i++) {
		    	 denom = denom + alpha[time_steps-1][i];
		     }
		     for(int i = 0; i < N; i++) {
		    	 gamma_i[time_steps-1][i] = alpha[time_steps-1][i]/denom;
		     }
		     // done calculating gamma_i and gamma_ij
		     
		     //re-calculate A,B,PI
		     for(int i = 0; i < N; i++) {
		    	 pi.setvalue(0, i, gamma_i[0][i]);
		     }
		     
		     for(int i = 0; i < N; i++) {
		    	 for(int j = 0; j < N; j++) {
		    		 double numer = 0;
		    		 denom = 0;
		    		 for(int t = 0; t < time_steps-1; t++) {
		    			 numer = numer + gamma_ij[t][i][j];
		    			 denom = denom + gamma_i[t][i];
		    		 }
		    		 A.setvalue(i,j,(numer/denom));
		    	 }
		     }
		     
		     for(int i = 0; i < N; i++) {
		    	 for(int j = 0; j < K; j++) {
		    		 double numer = 0;
		    		 denom = 0;
		    		 for(int t = 0;t < time_steps; t++) {
		    			 if(Observations.getvalue(t, 0) == j) {
		    				 numer = numer + gamma_i[t][i];
		    			 }
		    			 denom = denom + gamma_i[t][i];
		    		 }
		    		 B.setvalue(i,j,(numer/denom));
		    	 }
		     }
		     //Done calculating A,B and PI.
		     
		     //Calculate probability
		     double logProb = 0;
		     for(int i = 0; i < time_steps; i++) {
		    	 logProb = logProb + Math.log(c[i]);
		     }
		     logProb = -logProb;
		     
		     iters++;
		     
		     //check if converges
		     if(iters < maxIters && logProb > oldLogProb) {
		    	 oldLogProb = logProb;
		     }
		     else if(iters >= maxIters && logProb > oldLogProb) {
		    	 convergance = false;
		    	 System.out.println("Did not converge");
		     }
		     else {
		    	 convergance = false;
		    	 System.out.println(A.toString());
				 System.out.println(B.toString());
				 System.out.println(pi.toString());
				 System.out.println("Number of iterations; " + iters);
		     }
		     
		 }
		 
		 double comp_pi = 0;
		 for(int i = 0; i < N; i++) {
			 comp_pi += (pi.getvalue(0, i)-pi_comp.getvalue(0,i))*(pi.getvalue(0, i)-pi_comp.getvalue(0,i));
		 }
		 comp_pi = Math.sqrt(comp_pi);
		 System.out.println("distance for pi matrices: " + comp_pi);
		 
		 double comp_a = 0;
		 for(int i = 0; i < N; i++) {
			 for(int j = 0; j < N; j++) {
				 comp_a += (A.getvalue(i, j)-A_comp.getvalue(i,j))*(A.getvalue(i, j)-A_comp.getvalue(i,j));
			 }
		 }
		 
		 comp_a = Math.sqrt(comp_a);
		 System.out.println("distance for A matrices: " + comp_a);
		 
		 double comp_b = 0;
		 for(int i = 0; i < N; i++) {
			 for(int j = 0; j < K; j++) {
				 comp_b += (B.getvalue(i, j)-B_comp.getvalue(i,j))*(B.getvalue(i, j)-B_comp.getvalue(i,j));
			 }
		 }
		 
		 comp_b = Math.sqrt(comp_b);
		 System.out.println("distance for A matrices: " + comp_b);
		 
	}

}
