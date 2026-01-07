# Hashing in Java: Concepts & Implementation

These notes provide a conceptual overview of the Hashing technique, which is the foundational logic behind many Java Collections framework classes.

## 1. Overview

Hashing is a mechanism used to map data of arbitrary size to fixed-size values. Java uses this technique extensively to store and retrieve data quickly (often in constant time, ).

**Common Java Classes using Hashing:**

* `HashSet` (Stores unique values)
* `HashMap` (Stores Key-Value pairs)
* `LinkedHashSet` / `LinkedHashMap` (Preserves insertion order)
* `Hashtable` (Legacy class)
* `Properties`

## 2. Core Terminology

* **Hash Table:** The underlying data structure, essentially an array.
* **Bucket:** Each specific location (index) in the Hash Table. A bucket can hold a value or a Key-Value pair.
* **Size:** The capacity of the table. In Java, the default initial size is often **16**. The table is **self-adjustable**, meaning it resizes as you add more elements.

## 3. The Process: How Insertion Works

To determine where to store a value, we use a **Hash Function**.

### The Formula

* **Input:** The value (or Key) you want to store.
* **Function:** Applies modulo arithmetic (or a more complex algorithm) to the value.
* **Output:** An index number representing the "Bucket" where the data lives.

### Example Trace

**Scenario:** Table Size = 10.
**Values to Insert:** 15, 28, 42.

1. **Insert 15:** . Store 15 at Index 5.
2. **Insert 28:** . Store 28 at Index 8.
3. **Insert 42:** . Store 42 at Index 2.

**Retrieval:** To find 15 again, we calculate  to get index 5 immediately. No need to search the whole array.

---

## 4. Handling Collisions

A **Collision** occurs when two different values result in the same index (e.g., Inserting `35` when `15` is already at Index 5).

There are two primary methods to handle this:

### Method A: Open Addressing (Linear Probing)

If the calculated bucket is occupied, find the **next available** (blank) bucket sequentially.

* **Insertion:**
* Try to insert `35` at Index 5. Occupied?
* Check Index 6. Empty? Insert `35` there.


* **Searching:**
* Calculate Hash for `35` -> Index 5.
* Is Index 5 holding `35`? No.
* Check next (Index 6). Is it `35`? Yes. Found.


* **The "Blank Space" Rule:**
* Search stops when you find the value **OR** when you hit a **Blank Space** (null).
* If the table is 100% full, search becomes infinite or very slow.
* **Load Factor:** To ensure performance, we must keep ~25% of the table empty.
* *Ideal Load:* 50% Full / 50% Empty.
* *Practical Limit:* 75% Full (Load Factor 0.75).



### Method B: Chaining (Linked List)

Instead of storing the value directly in the bucket, each bucket holds a reference (pointer) to a Linked List.

* **Insertion:**
* Insert `15` at Index 5. (Node created).
* Insert `35` at Index 5. It collides. Create a new Node for `35` and link it to `15`.


* **Searching:**
* Go to Index 5.
* Traverse the linked list at that index to find the specific value.


* **Pros:** The table size doesn't strictly need to grow as often; chains just get longer.
* **Cons:** Long chains degrade performance from  to .

---

## 5. Java Implementation Details

### Rehashing & Resizing

In methods like **Open Addressing**, the Table Size and Hash Function are tightly coupled.

* If the table fills up (exceeds Load Factor), Java creates a larger table (usually double the size).
* **Rehashing:** Because `Size` changed,  changes. All existing keys must be recalculated and moved to new locations.

### Comparison Summary

| Feature | Open Addressing | Chaining |
| --- | --- | --- |
| **Collision Handling** | Store in next available slot. | Add to a list at that index. |
| **Search Logic** | Stop at value or **Blank Space**. | Traverse list at index. |
| **Resizing** | Critical (needs blank spaces). | Flexible (chains grow). |
| **Java Usage** | Likely influences `ThreadLocal` maps. | **Used by `HashMap` / `HashSet`.** |

> **Note:** While the concept of "Load Factor" strongly suggests Open Addressing logic, standard Java `HashMap` actually uses **Chaining**. However, it *still* uses a Load Factor (0.75 default) to resize the table and keep those chains short for performance.

## 6. Code Example

Here is how you might use these concepts in Java.

```java
import java.util.HashMap;
import java.util.Map;

public class HashingDemo {
    public static void main(String[] args) {
        // 1. Create a HashMap (Key-Value storage)
        // Default Load Factor is 0.75
        Map<Integer, String> employeeDirectory = new HashMap<>();

        // 2. Insert Data (The Key is hashed to find the bucket)
        employeeDirectory.put(101, "Alice");
        employeeDirectory.put(205, "Bob");
        employeeDirectory.put(309, "Charlie");

        // 3. Collision Scenario
        // If 101 and 401 result in the same bucket index internally,
        // Java handles this automatically (using Chaining/Nodes).
        employeeDirectory.put(401, "Diana"); 

        // 4. Retrieval
        // Uses the hash of 205 to jump directly to the data
        String employee = employeeDirectory.get(205);
        System.out.println("Employee found: " + employee);
        
        // 5. Handling Non-Existent Keys
        // Hashing points to a bucket -> bucket is empty (or key not in chain) -> returns null
        System.out.println("Searching for invalid ID: " + employeeDirectory.get(999));
    }
}

```
