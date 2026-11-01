
package sortingalgorithms;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    static int SIZE = 100000;
    static Random random = new Random();

    public static void main(String[] args) {
    String[] caseNames = {
            "Random", "Reverse", "Partial", "Sorted"
        };

        String[] algorithmNames = {
            "Selection Sort", "Insertion Sort", "Bubble Sort",
            "Merge Sort", "Quick Sort", "Radix Sort"
        };

        int[][] arrays = createArrays();
        long[][] results = new long[6][4];

        for (int i = 0; i < arrays.length; i++) {

            System.out.println("\n" + caseNames[i] + " Array:");

            for (int j = 0; j < algorithmNames.length; j++) {

                int[] testArray = Arrays.copyOf(arrays[i], SIZE);

                long start = System.nanoTime();

                if (j == 0) {
                    selectionSort(testArray);
                } else if (j == 1) {
                    insertionSort(testArray);
                } else if (j == 2) {
                    bubbleSort(testArray);
                } else if (j == 3) {
                    mergeSort(testArray, 0, testArray.length - 1);
                } else if (j == 4) {
                    quickSort(testArray, 0, testArray.length - 1);
                } else {
                    radixSort(testArray);
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

        // Partially sorted array (approximately 80% sorted)
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

    // Selection Sort: find the smallest element
    static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // Insertion Sort: insert elements into correct positions
    static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Bubble Sort: compare and swap neighboring elements
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

    // Merge Sort: divide the array and merge the sorted halves
    static void mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    static void merge(int[] arr, int left, int middle, int right) {

        int[] temp = new int[right - left + 1];
        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // Quick Sort: choose a random pivot and sort both sides
    static void quickSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int middle = partition(arr, left, right);

        quickSort(arr, left, middle - 1);
        quickSort(arr, middle + 1, right);
    }

    static int partition(int[] arr, int left, int right) {

        int randomIndex = left + (int) (Math.random() * (right - left + 1));

        int temp = arr[left];
        arr[left] = arr[randomIndex];
        arr[randomIndex] = temp;

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
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            } else {
                break;
            }
        }

        temp = arr[left];
        arr[left] = arr[j];
        arr[j] = temp;

        return j;
    }

    // Radix Sort: sort numbers digit by digit
    static void radixSort(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        for (long place = 1; max / place > 0; place *= 10) {

            int[] output = new int[arr.length];
            int[] count = new int[10];

            for (int i = 0; i < arr.length; i++) {
                int digit = (int) ((arr[i] / place) % 10);
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                int digit = (int) ((arr[i] / place) % 10);
                output[count[digit] - 1] = arr[i];
                count[digit]--;
            }

            for (int i = 0; i < arr.length; i++) {
                arr[i] = output[i];
            }
        }
    }

    // Check if the array is sorted
    static boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }

        return true;
    }

    // Display the final results
    static void printResults(String[] algorithms,
                             String[] cases, long[][] results) {

        System.out.println("\n==================== SUMMARY TABLE ====================");

        System.out.printf("%-17s", "Algorithm");

        for (String name : cases) {
            System.out.printf("%14s", name);
        }

        System.out.println();

        for (int i = 0; i < algorithms.length; i++) {
            System.out.printf("%-17s", algorithms[i]);

            for (int j = 0; j < cases.length; j++) {
                System.out.printf("%14d", results[i][j]);
            }

            System.out.println();
        }

        System.out.println("Times are in microseconds.");
    }
}
