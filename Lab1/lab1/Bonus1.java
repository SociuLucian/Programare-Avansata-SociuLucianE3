package lab1;
//Sociu Lucian E3
import java.util.Scanner;
public class Bonus1 {
    public static void main(String args[])
    {
        //Create matrix from input
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        in.close();
        int [][] A= new int[n][n];
        for(int lin=0;lin<n;lin++)
        {
            A[lin][(lin+1)%n]=1;
            A[(lin+1)%n][lin]=1;
        }
        //Display matrix
        System.out.println("Adjacency matrix of a Cycle Graph C" + n + " : ");
        for(int lin=0;lin<n;lin++){
            for(int col=0;col<n;col++)
            {
                System.out.print(A[lin][col] + " ");
            }
            System.out.println();
        }
        int [][] Apow= A;
        //compute powers
        for(int i=2;i<=n;i++){
            Apow=multiplyhelper(Apow,A);
            System.out.println("Matrix A^" + i + " : ");
        //Display powers
            for(int lin=0;lin<n;lin++){
                for(int col=0;col<n;col++)
                {
                    System.out.print(Apow[lin][col] + " ");
                }
                System.out.println();
            }
        }

    }
    public static int[][] multiplyhelper(int[][] x, int[][] y)
    {
        int lenx=x.length;
        int leny=y[0].length;
        int [][] z = new int[lenx][leny];
        for(int i=0;i<lenx;i++){
            for(int j=0;j<leny;j++){
                for(int k=0;k<y.length;k++)
                {
                    z[i][j] = z[i][j] + x[i][k] * y[k][j];
                }
            }
        }
        return z;
    }
}
