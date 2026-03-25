import java.util.Scanner;
import java.util.Arrays;

public class SumAverageArray {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("n: ");
        int n = keyboard.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + ": ");
            arr[i] = keyboard.nextInt();
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        double avg = (double) sum / n;

        System.out.println("Sorted:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("\nSum = " + sum);
        System.out.println("Average = " + avg);
        System.exit(0);
    }
}