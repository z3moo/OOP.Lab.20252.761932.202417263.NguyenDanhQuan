import java.util.Scanner;

public class AddTwoMatrices {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Rows: ");
        int r = keyboard.nextInt();

        System.out.print("Cols: ");
        int c = keyboard.nextInt();

        int[][] A = new int[r][c];
        int[][] B = new int[r][c];
        int[][] sum = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.println("A[" + i + "][" + j + "]: ");
                A[i][j] = keyboard.nextInt();
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.println("B[" + i + "][" + j + "]: ");
                B[i][j] = keyboard.nextInt();
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum[i][j] = A[i][j] + B[i][j];
            }
        }

        System.out.println("Sum:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
        System.exit(0);
    }
}