# The Arrays Class & Comparator Interface

## 1. Introduction to the Arrays Class

The `Arrays` class, located in the **`java.util`** package, is a powerful utility class designed to manipulate arrays. Since arrays in Java are simple objects with limited built-in methods (unlike `ArrayList`), this class provides static methods to perform common tasks like sorting, searching, and comparing.

---

## 2. Core Methods of the Arrays Class

Let's explore the most important methods provided by this class.

### A. Comparing Arrays (`compare`)

The `compare` method checks two arrays element by element (lexicographically).

* **Returns `0**`: If both arrays are identical.
* **Returns `> 0**`: If the first array is "greater" (lexicographically) or longer.
* **Returns `< 0**`: If the first array is "smaller".

```java
import java.util.Arrays;

public class ArrayCompareDemo {
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 6, 8, 1};
        int[] arr2 = {2, 4, 6, 8, 1};
        int[] arr3 = {2, 4, 6, 8, 0}; // Last element is smaller

        // 1. Identical Arrays
        System.out.println(Arrays.compare(arr1, arr2)); // Output: 0

        // 2. First array is greater (1 > 0)
        System.out.println(Arrays.compare(arr1, arr3)); // Output: 1
    }
}

```

### B. Copying Arrays (`copyOf`)

This method creates a **new array** which is a copy of the original, with a specified length. It is very useful when you need to resize an array or take a snapshot of data.

```java
int[] original = {10, 20, 30, 40, 50};

// Copy first 3 elements
int[] shortCopy = Arrays.copyOf(original, 3); 
// Result: {10, 20, 30}

// Copy full length
int[] fullCopy = Arrays.copyOf(original, original.length);

```

### C. Filling Arrays (`fill`)

If you need to initialize an array with a specific default value (e.g., setting all scores to -1 or all statuses to 0), use `fill`.

```java
int[] scores = new int[5];
Arrays.fill(scores, -1);
// Result: {-1, -1, -1, -1, -1}

```

### D. Sorting (`sort` and `parallelSort`)

Sorting is critical for many algorithms.

* **`sort()`**: Uses Dual-Pivot Quicksort. Good for smaller datasets.
* **`parallelSort()`**: Uses specific threading mechanisms (Fork/Join framework) to sort large datasets faster by using multiple CPU cores.

```java
int[] data = {5, 1, 9, 3};
Arrays.sort(data);
// Result: {1, 3, 5, 9}

```

### E. Binary Search (`binarySearch`)

This method searches for a specific element index.
**Critical Rule:** The array **MUST be sorted** before calling `binarySearch`. If the array is not sorted, the result is undefined.

```java
int[] sortedData = {10, 20, 30, 40, 50};

// Search for value 30
int index = Arrays.binarySearch(sortedData, 30);
System.out.println("Index of 30: " + index); // Output: 2

```

---

## 3. The Comparator Interface

We previously discussed `Comparable` (Natural Ordering). Now, let's look at `Comparator` in the context of Arrays.

If we have an array of Objects (like `Integer`, `String`, or custom objects), we can define a **Custom Sorting Logic** using the `Comparator` interface.

**Scenario:** We want to sort an array of Integers in **Descending (Reverse) Order**.

### Step 1: Create the Comparator

We implement the `Comparator` interface and override the `compare` method. To reverse the order, we simply flip the return logic:

* Normally: If `i1 < i2`, return `-1`.
* Reverse: If `i1 < i2`, return `1`.

### Step 2: Apply to Array

Note: To use a Comparator, the array must be of **Objects** (Wrapper Class `Integer`), not primitives (`int`).

```java
import java.util.Arrays;
import java.util.Comparator;

// Custom Comparator Class
class ReverseSorter implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
        // Logic to reverse the sort
        if (i1 < i2) return 1;  // Normally -1
        if (i1 > i2) return -1; // Normally 1
        return 0;
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        // Must use Wrapper class Integer, not primitive int
        Integer[] numbers = {2, 4, 6, 8, 1, 3, 5, 7};

        // Pass the array AND the custom comparator
        Arrays.sort(numbers, new ReverseSorter());

        System.out.println(Arrays.toString(numbers));
        // Output: [8, 7, 6, 5, 4, 3, 2, 1]
    }
}

```

### Quick Tip: The Lambda Way (Java 8+)

You don't actually need to write the `ReverseSorter` class separately in modern Java. You can pass the logic directly:

```java
Arrays.sort(numbers, (i1, i2) -> i2 - i1); // Simple reverse logic

```

---

## 4. Summary of Key Methods

| Method | Description |
| --- | --- |
| `Arrays.toString(arr)` | Returns a printable String representation of the array contents. |
| `Arrays.sort(arr)` | Sorts the array in ascending order. |
| `Arrays.binarySearch(arr, key)` | Finds the index of a key (Array must be sorted!). |
| `Arrays.compare(arr1, arr2)` | Compares two arrays lexicographically. |
| `Arrays.copyOf(arr, len)` | Creates a new array copy with the specified length. |
| `Arrays.fill(arr, val)` | Fills every index of the array with the specified value. |

---