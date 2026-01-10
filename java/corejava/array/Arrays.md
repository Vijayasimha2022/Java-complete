# Java Arrays Utility Class

These notes cover the `java.util.Arrays` class. While arrays in Java are a basic data type, they lack built-in methods for common tasks (like sorting or printing). The `Arrays` class provides a suite of **static** helper methods to manipulate arrays efficiently.

## 1. What is the Arrays Class?

* **Package:** `java.util.Arrays`
* **Purpose:** A utility class containing static methods to perform common operations on arrays.
* **Scope:** It works on both arrays of **primitives** (e.g., `int[]`, `double[]`) and arrays of **objects** (e.g., `String[]`, `Student[]`).
* **Instantiability:** You cannot create an object of this class (the constructor is private). You access everything statically (e.g., `Arrays.sort()`).

## 2. Key Functionalities

### A. Sorting

Sorting is one of the most common operations.

* **Primitives:** Uses a "Dual-Pivot Quicksort" algorithm. It is very fast ().
* **Objects:** Uses "TimSort" (a hybrid of Merge Sort and Insertion Sort). This is **stable** (it preserves the relative order of equal elements).

### B. Searching

It provides `binarySearch()` for finding elements.

> **Critical Requirement:** The array **must be sorted** before you use `binarySearch`. If you run it on an unsorted array, the result is undefined.

### C. Comparison

* `equals()`: Checks if two arrays contain the same elements in the same order.
* `compare()`: Lexicographically compares two arrays (like comparing words in a dictionary).

### D. Transformation

* `toString()`: Converts an array into a readable String format `[e1, e2, e3]`.
* `asList()`: Converts an array into a `List` (backed by the original array).

---

## 3. Deep Dive: `Arrays.sort()` and `Comparable`

When sorting Objects (like a list of Employees), Java doesn't know how to order them automatically.

1. **Natural Order:** The class must implement the `Comparable` interface.
2. **Custom Order:** You can pass a `Comparator` directly to the sort method.

---

## 4. Code Demonstration

Here is a comprehensive example showing the most useful methods.

```java
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class ArraysClassDemo {
    public static void main(String[] args) {
        
        // --- 1. Basic Integer Operations ---
        int[] numbers = {10, 5, 20, 15, 8};

        // Printing: Direct printing gives hashcode, use Arrays.toString()
        System.out.println("Original: " + Arrays.toString(numbers)); 
        // Output: [10, 5, 20, 15, 8]

        // Sorting
        Arrays.sort(numbers);
        System.out.println("Sorted:   " + Arrays.toString(numbers));
        // Output: [5, 8, 10, 15, 20]

        // Binary Search (Returns index of the element)
        // Note: Array MUST be sorted first
        int index = Arrays.binarySearch(numbers, 15);
        System.out.println("Index of 15: " + index); // Output: 3 (0-based index)

        int notFoundIndex = Arrays.binarySearch(numbers, 99);
        System.out.println("Index of 99: " + notFoundIndex); 
        // Output: negative number (insertion point)

        // --- 2. Copying Arrays ---
        // copyOf(original, newLength) - truncates or pads with zeros
        int[] copy = Arrays.copyOf(numbers, 10);
        System.out.println("Copy (Padded): " + Arrays.toString(copy));
        // Output: [5, 8, 10, 15, 20, 0, 0, 0, 0, 0]

        // --- 3. Filling an Array ---
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 7);
        System.out.println("Filled: " + Arrays.toString(filledArray));
        // Output: [7, 7, 7, 7, 7]

        // --- 4. Comparing Arrays ---
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 4};

        System.out.println("arr1 == arr2? " + Arrays.equals(arr1, arr2)); // true
        System.out.println("arr1 == arr3? " + Arrays.equals(arr1, arr3)); // false
        // Note: For multi-dimensional arrays, use Arrays.deepEquals() or deepToString()

        // --- 5. Sorting Custom Objects with Comparator ---
        String[] fruits = {"Banana", "Apple", "Pear", "Kiwi"};
        
        // Custom Sort: Sort by string length (Shortest to Longest)
        Arrays.sort(fruits, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        
        // Alternatively with Lambda: Arrays.sort(fruits, (s1, s2) -> s1.length() - s2.length());

        System.out.println("Sorted by Length: " + Arrays.toString(fruits));
        // Output: [Pear, Kiwi, Apple, Banana] (Note: Pear/Kiwi order may vary if length is same)

        // --- 6. Converting to List ---
        // Returns a fixed-size list backed by the array
        List<String> fruitList = Arrays.asList(fruits);
        System.out.println("As List: " + fruitList);
    }
}

```

## 5. Important Caveat: `Arrays.asList()`

The list returned by `Arrays.asList()` is a **fixed-size** list linked to the original array.

* **Modification:** If you change an element in the List (`list.set(0, "X")`), the original Array also changes.
* **Structure:** You **cannot** add or remove elements (`list.add("New")` throws `UnsupportedOperationException`).

## 6. Summary Table

| Method | Description | Complexity |
| --- | --- | --- |
| `sort(arr)` | Sorts the array in ascending order. |  |
| `binarySearch(arr, key)` | Finds index of key (must be sorted). |  |
| `toString(arr)` | Returns string representation. |  |
| `deepToString(arr)` | Returns string for multi-dimensional arrays. |  |
| `copyOf(arr, len)` | Copies array to new length (truncates/pads). |  |
| `fill(arr, val)` | Fills entire array with `val`. |  |
| `equals(a, b)` | Returns true if arrays are identical. |  |

---