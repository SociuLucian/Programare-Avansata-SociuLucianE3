package lab1;

import java.util.Scanner;

public class Bonus2 {
    public static void main(String args[])
    {
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        if((n*k)%2==0){
            System.out.println("The graph have " + n + " vertices and grade " + k + " on each vertix.");
        }
        else{
            System.out.println("The numbers are not correct because their product is not even. Try again!");
            n = in.nextInt();
            k = in.nextInt();
    }
        int [][] matrix = new int[n][n];
        for(int lin=0;lin<n;lin++){
            for(int col=0;col<n;col++){
                matrix[lin][col]=0;
            }
        }
        //connect each vertex to its neighbours
        for(int lin=0;lin<n;lin++){
            for(int col=lin+1;col<=lin+k-1;col++){
                matrix[lin%n][col%n]=1;
                matrix[col%n][lin%n]=1;
            }
        }
        //display matrix
        for(int lin=0;lin<n;lin++){
            for(int col=0;col<n;col++)
            {
                System.out.print(matrix[lin][col] + " ");
            }
            System.out.println();
        }
    }
}


