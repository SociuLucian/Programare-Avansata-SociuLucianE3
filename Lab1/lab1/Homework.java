package lab1;
//Sociu Lucian E3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Homework {
    public static void main(String args[]) {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = 0;
        // Validation
        do {
            if (n != 0) {
                System.out.println("You entered integer " + n);
                k = 1;
            } else {
                System.out.println("Please enter a number > 0!");
                n = in.nextInt();
            }
        } while (k == 0);
        // Create matrix
        int[][] matrix = new int[n][n];
        for (int lin = 0; lin < n; lin++) {
            matrix[0][lin] = lin + 1;
        }
        for (int lin = 1; lin < n; lin++) {
            for (int col = 0; col < n; col++) {
                matrix[lin][col] = matrix[lin - 1][(col + 1) % n];
            }

        }
        // Display the matrix
        /*for (int lin = 0; lin < n; lin++) {
            for (int col = 0; col < n; col++) {
                System.out.print(matrix[lin][col] + " ");
            }
            System.out.println();
        }*/
        //Concatenation of symbols
        if (n < 30000) {
            String con;
            for (int lin = 0; lin < n; lin++) {
                con = Integer.toString(matrix[lin][0]);
                for (int col = 1; col < n; col++) {
                    con = con.concat(Integer.toString(matrix[lin][col]));
                }
                System.out.println("For line " + lin + " we have this concatenation : " + con);
            }
            for (int col = 0; col < n; col++) {
                con = Integer.toString(matrix[col][0]);
                for (int lin = 1; lin < n; lin++) {
                    con = con.concat(Integer.toString(matrix[lin][col]));
                }
                System.out.println("For column " + col + " we have this concatenation : " + con);
            }
        } else {
            long start = System.nanoTime();
            String con;
            for (int lin = 0; lin < n; lin++) {
                con = Integer.toString(matrix[lin][0]);
                for (int col = 1; col < n; col++) {
                    con = con.concat(Integer.toString(matrix[lin][col]));
                }
            }
            for (int col = 0; col < n; col++) {
                con = Integer.toString(matrix[col][0]);
                for (int lin = 1; lin < n; lin++) {
                    con = con.concat(Integer.toString(matrix[lin][col]));
                }
            }
            long end = System.nanoTime();
            System.out.println("Time for calculating the concatenation on every line and column in nanoseconds: " + (end - start));
        }
    }
}



