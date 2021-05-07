package hmm0;

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
        
        matrix A = new matrix(rad1);
        matrix B = new matrix(rad2);
        matrix pi = new matrix(rad3);
        matrix because_I_can = pi.matrix_multi(A);
        System.out.println(because_I_can.matrix_multi(B));
        }
}
