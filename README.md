# Sorting Algorithms Benchmark

A Java project that compares the performance of Quick Sort and Bubble Sort using four different input-array cases.

## Student Information

- **Student Name:** [احمد صلاح الدين القلالي]
- **University ID:** [3167062]
- **Section Number:** [B]

## Project Description

This project benchmarks two sorting algorithms using Java:

1. Quick Sort
2. Bubble Sort

The program measures execution time in microseconds and checks whether each algorithm successfully sorts the array.

## Test Cases

The program tests each algorithm using four different arrays:

- **Random:** An array containing randomly generated integers.
- **Reverse:** An array arranged in descending order.
- **Partial:** An initially sorted array modified by random swaps.
- **Sorted:** An array arranged in ascending order.

## Configuration

| Parameter | Value |
|---|---|
| Programming Language | Java |
| Array Size | 2,000 elements |
| Number of Algorithms | 2 |
| Number of Test Cases | 4 |
| Total Sorting Runs | 8 |
| Timing Method | `System.nanoTime()` |
| Time Unit | Microseconds (µs) |

## Algorithm Overview

### Quick Sort

Quick Sort uses the Divide and Conquer approach. The first element of each array segment is selected as the pivot. The partition method rearranges elements around the pivot, and recursive calls sort the remaining left and right segments.

- Average Time Complexity: `O(n log n)`
- Worst-case Time Complexity: `O(n²)`

Choosing the first element as the pivot may cause poor performance on already sorted or reverse-sorted arrays.

### Bubble Sort

Bubble Sort repeatedly compares adjacent elements and swaps them when they are in the wrong order. The implementation uses a `swapped` flag to stop early if no swaps occur during a complete pass.

- Best-case Time Complexity: `O(n)` for an already sorted array
- Average Time Complexity: `O(n²)`
- Worst-case Time Complexity: `O(n²)`

## Execution Time Results

Replace the placeholders with the actual measurements printed by the program.

| Algorithm | Random (µs) | Reverse (µs) | Partial (µs) | Sorted (µs) |
|---|---:|---:|---:|---:|
| Quick Sort | 526 | 1940 | 635 | 730 |
| Bubble Sort | 5821 | 4077 | 4333 | 5 |

## Performance Chart

A comparison chart should be added after collecting the actual execution times. It should compare Quick Sort and Bubble Sort across all four test cases.

The chart must use measured results rather than estimated values.

## Implementation Details

- `main()` runs the sorting experiments and records execution times.
- `createArrays()` generates the four test arrays.
- `quickSort()` performs recursive Quick Sort.
- `partition()` partitions the array using the first element as the pivot.
- `bubbleSort()` implements Bubble Sort with early termination.
- `isSorted()` checks whether the resulting array is sorted in ascending order.
- `printResults()` displays the summary table.

Each algorithm receives an independent copy of the original array. Array copying is performed before timing begins, ensuring that the measured duration represents the sorting operation rather than the copying process.

## Conclusion

This project demonstrates how the initial order of data can affect sorting performance. Bubble Sort can perform efficiently on an already sorted array because it can terminate after one pass. Quick Sort is generally efficient on average, but its performance may deteriorate when the first-element pivot produces highly unbalanced partitions.

The final performance analysis should be based on the actual measurements collected during execution.
