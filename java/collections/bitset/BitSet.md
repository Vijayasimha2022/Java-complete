
# The BitSet Class in Java

## 1. Introduction

The `BitSet` class, located in `java.util`, is a specialized data structure that functions like a dynamic array of **bits** (flags).

* **Not a Collection:** Interestingly, `BitSet` does not implement the standard `Collection` interface (like List or Set). It is a standalone class designed for high-performance bit manipulation.
* **Concept:** Imagine an array where every index holds a boolean value, but instead of using a large `boolean` wrapper object, it uses a single **bit** (0 or 1).
* **1 (Set):** Represents `true`.
* **0 (Clear):** Represents `false`.


* **Growth:** Just like an `ArrayList`, a `BitSet` automatically grows as needed to accommodate the indices you set.

---

## 2. Core Operations

We can perform standard logical operations on these bits.

* **`set(int index)`**: Turns a bit **ON** (sets it to true/1).
* **`clear(int index)`**: Turns a bit **OFF** (sets it to false/0).
* **`flip(int index)`**: Toggles the value (0 becomes 1, 1 becomes 0).
* **`get(int index)`**: Returns true if the bit is set, false otherwise.

### Bulk Operations (Between two BitSets)

You can perform logical operations between two different `BitSet` objects:

1. **`AND`**: The result keeps bits ON only if they are ON in **both** sets. (Intersection)
2. **`OR`**: The result keeps bits ON if they are ON in **either** set. (Union)
3. **`XOR`**: The result keeps bits ON if they are ON in one set but **not** both.

---

## 3. Code Demonstration

Let's look at how to implement these concepts in Java code.

```java
import java.util.BitSet;

public class BitSetDemo {
    public static void main(String[] args) {
        
        // 1. Create two BitSet objects
        BitSet bs1 = new BitSet();
        BitSet bs2 = new BitSet();

        // 2. Setup BitSet 1 (Evens: 0, 2, 4, 6, 8)
        bs1.set(0);
        bs1.set(2);
        bs1.set(4);
        bs1.set(6);
        bs1.set(8);
        
        // 3. Setup BitSet 2 (Odds: 1, 3, 5, 7)
        bs2.set(1);
        bs2.set(3);
        bs2.set(5);
        bs2.set(7);

        System.out.println("BitSet 1 (Evens): " + bs1); // Output: {0, 2, 4, 6, 8}
        System.out.println("BitSet 2 (Odds): " + bs2);  // Output: {1, 3, 5, 7}

        // 4. Perform OR Operation (Union)
        // This modifies bs1 to include bits from bs2
        bs1.or(bs2);
        
        System.out.println("After OR (Union): " + bs1); 
        // Output: {0, 1, 2, 3, 4, 5, 6, 7, 8}

        // 5. Flip Operation
        // Let's flip everything in the range 0 to 8 (exclusive of 9)
        // Since they are all currently TRUE (from the step above), they will become FALSE.
        bs1.flip(0, 9);
        
        System.out.println("After Flip (0-8): " + bs1); 
        // Output: {} (Empty, because all bits were turned off)
    }
}

```

---

## 4. Real-World Use Cases

Why would you use `BitSet` instead of a `boolean[]` array or a `HashSet`?

### A. Compact Storage (Space Efficiency)

A `boolean` in Java often takes up a byte (8 bits) or more depending on the JVM. A `BitSet` uses exactly 1 bit per flag. If you have 1 million flags, `BitSet` is roughly **8x to 64x more memory efficient** than other structures.

### B. Finding Missing Numbers

Imagine you have a sequence of numbers from 1 to 10, but some are missing.

1. Create a `BitSet` of size 10.
2. Iterate through your incoming numbers and call `bs.set(number)`.
3. Any index that remains `false` (or 0) after the loop is a missing number.

### C. Permissions/Access Control

You can map permissions to bits.

* Bit 0: Read Access
* Bit 1: Write Access
* Bit 2: Delete Access
  If User A has `BitSet {0, 2}` and User B has `BitSet {0, 1}`, you can quickly check overlapping permissions using `AND`.

---

## 5. Summary Table

| Method | Description |
| --- | --- |
| `set(int index)` | Sets the bit at the specified index to `true`. |
| `clear(int index)` | Sets the bit at the specified index to `false`. |
| `flip(int index)` | Toggles the bit (true -> false, false -> true). |
| `get(int index)` | Returns the boolean value of the bit. |
| `and(BitSet set)` | Performs a logical **AND** with another BitSet. (Intersection) |
| `or(BitSet set)` | Performs a logical **OR** with another BitSet. (Union) |
| `xor(BitSet set)` | Performs a logical **XOR** with another BitSet. |
| `cardinality()` | Returns the number of bits set to `true`. |

---
