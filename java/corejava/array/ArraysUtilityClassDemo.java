package corejava.array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArraysUtilityClassDemo {
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
