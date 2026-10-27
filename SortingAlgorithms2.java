
package sortingalgorithms2;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms2 {

    static int SIZE = 2000;
    static Random random = new Random();

    public static void main(String[] args) {

        String[] caseNames = {
            "Random", "Reverse", "Partial", "Sorted"
        };

        String[] algorithmNames = {
            "Quick Sort", "Bubble Sort"
        };

        int[][] arrays = createArrays();
        long[][] results = new long[2][4];

        for (int i = 0; i < arrays.length; i++) {

            System.out.println("\n" + caseNames[i] + " Array:");

            for (int j = 0; j < algorithmNames.length; j++) {

                int[] testArray = Arrays.copyOf(arrays[i], SIZE);

                long start = System.nanoTime();

                if (j == 0) {
                    quickSort(testArray, 0, testArray.length - 1);
                } else {
                    bubbleSort(testArray);
                }

                long end = System.nanoTime();

                results[j][i] = (end - start) / 1000;

                if (isSorted(testArray)) {
                    System.out.printf("%-15s %12d microseconds%n",
                            algorithmNames[j], results[j][i]);
                } else {
                    System.out.println(algorithmNames[j] + " failed!");
                }
            }
        }

        printResults(algorithmNames, caseNames, results);
    }

    static int[][] createArrays() {

        int[][] arrays = new int[4][SIZE];

        // Random array
        for (int i = 0; i < SIZE; i++) {
            arrays[0][i] = random.nextInt(SIZE);
        }

        // Reverse array
        for (int i = 0; i < SIZE; i++) {
            arrays[1][i] = SIZE - i;
        }

        // Sorted array
        for (int i = 0; i < SIZE; i++) {
            arrays[3][i] = i;
        }

        // Partially sorted array
        arrays[2] = Arrays.copyOf(arrays[3], SIZE);

        for (int i = 0; i < SIZE / 10; i++) {
            int a = random.nextInt(SIZE);
            int b = random.nextInt(SIZE);

            int temp = arrays[2][a];
            arrays[2][a] = arrays[2][b];
            arrays[2][b] = temp;
        }

        return arrays;
    }

    static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    

static void quickSort(int[] arr, int left, int right) {

    if (left >= right) {
        return;
    }

    int middle = partition(arr, left, right);

    quickSort(arr, left, middle - 1);
    quickSort(arr, middle + 1, right);
}

static int partition(int[] arr, int left, int right) {

    int pivot = arr[left];
    int i = left + 1;
    int j = right;

    while (i <= j) {

        while (i <= right && arr[i] <= pivot) {
            i++;
        }

        while (j > left && arr[j] > pivot) {
            j--;
        }

        if (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    int temp = arr[left];
    arr[left] = arr[j];
    arr[j] = temp;

    return j;
}

    static boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }

        return true;
    }

    static void printResults(String[] algorithms,
                             String[] cases, long[][] results) {

        System.out.println("\n========== SUMMARY TABLE ==========");

        System.out.printf("%-15s", "Algorithm");

        for (String name : cases) {
            System.out.printf("%12s", name);
        }

        System.out.println();

        for (int i = 0; i < algorithms.length; i++) {
            System.out.printf("%-15s", algorithms[i]);

            for (int j = 0; j < cases.length; j++) {
                System.out.printf("%12d", results[i][j]);
            }

            System.out.println();
        }

        System.out.println("Times are in microseconds.");
    }
}
