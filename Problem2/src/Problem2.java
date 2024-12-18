import java.util.Scanner;
import java.util.*;
import java.lang.Math;

public class Problem2 {

    public static void main(String[] args){ //Main method

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose how many citizens are going to be in the queue"); //Letting the user choose the number of citizens
        int c = sc.nextInt();

        System.out.println("Choose a maximum service time"); //Letting the user choose a maximum value for the service time
        int max = sc.nextInt();

        List<Integer> list=new ArrayList<Integer>(c); //Creating a list to store the service time per citizen

        for(int i = 0; i < c; i++) //Filling the list with random values within the range that the user set previously on line14
            list.add((int)(Math.random() * max));

        Collections.sort(list); //Sorting the list, as our algorithm suggests
        int [] times = new int[c]; //Creating an array to store the waiting time per citizen

        System.out.println(list); //Printing the list (with the service times)

        for(int i = 0; i < c; i++)
            times[i] = 0;

        for(int j = 1; j < c; j++) //Every citizen's waiting time is calculated as the sum of the waiting times from all the citizens ahead of him
            for(int k = j - 1; k >= 0; k --)
                times[j] = list.get(k) + times[j];

        int total = 0;

        for(int j = 0; j < c; j++) //Τ= 0 + t1 + (t1+t2) + (t1+t2+t3)+ ….+ (t1+t2+...+tn-1)
            total = total + times[j];

        System.out.println("The minimum waiting time is:" + total);
    }
}
