import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array1 = new int[3];
        int[] array2 = new int[3];
        int b1Sum = 0, b2Sum = 0;

        for (int i = 0; i < 3; i++) {
            int r = scanner.nextInt();
            array1[i] = r;
            b1Sum += r;
        }
        for (int i = 0; i < 3; i++) {
            int r = scanner.nextInt();
            array2[i] = r;
            b2Sum += r;
        }

        Arrays.sort(array1);
        Arrays.sort(array2);

        if (b1Sum < b2Sum && array1[0] <= array2[0] && array1[1] <= array2[1] && array1[2] <= array2[2]) {
            System.out.println("Box 1 < Box 2");
        } else if (b2Sum < b1Sum && array2[0] <= array1[0] && array2[1] <= array1[1] && array2[2] <= array1[2]) {
            System.out.println("Box 1 > Box 2");
        } else if (array2[0] == array1[0] && array2[1] == array1[1] && array2[2] == array1[2]) {
            System.out.println("Box 1 = Box 2");
        } else {
            System.out.println("Incomparable");
        }

    }
}