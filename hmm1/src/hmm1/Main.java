package hmm1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main
{
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
        float[][] alpha = new float[time_steps][N]; 
        
        for(int i = 0; i < N; i++) {
        	alpha[0][i] = B.getvalue(i, Observations.getvalue(0, 0))*pi.getvalue(0,i);
        }
        
        for(int t = 1; t < time_steps; t++){
        	for(int i = 0; i < N; i++) {
        		float sum = 0;
        		for(int j = 0; j < N; j++) {
        			sum = sum + A.getvalue(j, i)*alpha[t-1][j];
        		}
        		alpha[t][i] = B.getvalue(i, Observations.getvalue(t, 0))*sum;
        	}
        	
        }
        float prob = 0;
        for(int i = 0; i < N;i++) {
        	prob = prob + alpha[time_steps-1][i];
        }
        System.out.println(prob);
        }
}

