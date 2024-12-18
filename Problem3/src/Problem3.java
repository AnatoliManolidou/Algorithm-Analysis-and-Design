import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {//Main method

        Scanner sc = new Scanner(System.in);// Scanner object

        System.out.println("Enter the length of the string");
        int n = sc.nextInt();
        System.out.println("Enter the number of cuts that you want to perform");
        int m = sc.nextInt();
        int[] cuts = new int[m + 2];
        cuts[0] = 0;
        cuts[m + 1] = n;
        System.out.println("Enter the positions of the cuts");
        for(int i = 0; i < m; i++){
            int k = sc.nextInt();
            if(k < n)
                cuts[i] = k;
            else {
                System.out.println("Invalid input, insert new position");
                i--;
            }
        }

        Arrays.sort(cuts); //Sorting so we can compare the different cases

        for (int i = 0; i < cuts.length; i++) { //Validation of the cuts
            int cut = cuts[i];
            System.out.println(cut);
        }

        int mincost =  MinCost(cuts);
        System.out.println("The minimum cost to cut the string in the given positions is:" + mincost);
    }
        public static int MinCost(int[] cuts) {

            int[][] tab = new int[cuts.length][cuts.length]; // tab comes from tabulation

            for (int i = 0; i < cuts.length; i++)
                for (int j = 0; j < cuts.length; j++)
                    tab[i][j] = Integer.MAX_VALUE; // setting the array values with a large int in order to calculate the min in the following nested for loop

            //int max_length = (int) (Math.log10(Integer.MAX_VALUE) + 1);

            tab[0][0] = 0; //Standard values in array
            tab[0][1] = 0; //Standard values in array
            tab[cuts.length - 1][cuts.length - 1] = 0; //Standard values in array

            for (int i = 1; i <= (cuts.length - 2); i++) //Standard values in array
                for (int j = i; j < (i + 2); j++)
                        tab[i][j] = 0;

            for (int i = 0; i < cuts.length; i++) { //Just printing
                for (int j = 0; j < cuts.length; j++) {
                    System.out.print(tab[i][j]);
                    //for(int l = 0; l <= ((max_length - ((int) (Math.log10(tab[i][j]) + 1))) + 1); l++){
                        System.out.print(" ");
                    //}
                }
                System.out.println();
                System.out.println();
            }

            System.out.println("          -           ");

            for (int i = 2; i < (cuts.length); i++)  //Tabulation Algorithm
                for (int j = 0; j < (cuts.length - 2); j++) {

                    if(j == 0)
                        for (int x = j + 1; x < i; x++)
                            tab[j][i] = Math.min(tab[j][i],cuts[i] - cuts[j] + tab[x][i] + tab[j][x]);
                    else {
                        int h = j + i;
                        if(h < cuts.length)
                            for (int x = j + 1; x < h; x++)
                                tab[j][h] = Math.min(tab[j][h], cuts[h] - cuts[j] + tab[x][h] + tab[j][x]);
                    }

                    for (int b = 0; b < cuts.length; b++) { //Just printing
                        for (int g = 0; g < cuts.length; g++) {
                            System.out.print(tab[b][g]);
                            System.out.print(" ");
                        }
                        System.out.println();
                        System.out.println();
                    }
                    System.out.println("          -           ");
                }

            for (int i = 0; i < cuts.length; i++) { //Just printing
                for (int j = 0; j < cuts.length; j++) {
                    System.out.print(tab[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }

         return tab[0][cuts.length - 1]; //The final answer of the problem
    }
}
