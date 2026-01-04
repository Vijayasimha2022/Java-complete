Here are the structured notes for the **Priority Queue** class, ready for your GitHub repository.

---

# Java PriorityQueue & Heap Data Structure

These notes explore the `PriorityQueue` class in Java, which is based on the **Heap** data structure. Unlike standard queues that follow FIFO (First-In-First-Out), a Priority Queue handles elements based on their "priority."

## 1. The Core Concept: Heap

The `PriorityQueue` class implements a data structure called a **Heap**. While logically a Heap behaves like a Binary Tree, it is physically implemented using an **Array**.

* **Priority Logic:** Elements are inserted or deleted based on their priority.
* **Default Behavior (Min-Heap):** In Java, the default priority is determined by the natural ordering of numbers.
* **Smallest Number = Highest Priority.**
* **Largest Number = Lowest Priority.**
* *Example:* If you have numbers `10, 25, 15, 8, 26`, the highest priority element is `8`, and the lowest is `26`.


* **Deletion Rule:** You cannot arbitrarily choose which element to delete. The queue always removes the **highest priority** element (the root).

## 2. How it Works (Under the Hood)

Although we use the class methods, it is helpful to understand the underlying **Binary Tree** logic to grasp the performance.

### Logic Visualization

Imagine elements are stored in a binary tree.

1. **Root:** Always holds the "smallest" element (highest priority).
2. **Insertion:** When a new element is added, it may "bubble up" the tree if it is smaller than its parent.
3. **Deletion:** When the root is removed, the last element is moved to the root, and then it "bubbles down" until the order is restored.

### Example Trace

Let's trace the insertion of: `20, 10, 30, 5, 15, 3`.

1. **Insert 20:** Root is 20.
2. **Insert 10:** 10 is smaller than 20. They swap. Root is 10.
3. **Insert 30:** 30 is larger than 10. It sits as a child.
4. **Insert 5:** 5 is smaller than 10. It bubbles up to become the new Root.
5. ...and so on.

**Final Tree Structure (visual approximation):**

```text
      3  (Root/Head)
     / \
   10   5
  / \  /
 20 15 30

```

*Note: The actual array storage might look like `[3, 10, 5, 20, 15, 30]`.*

### Time Complexity

Because the elements shift along the height of the tree:

* **Insertion (Add/Offer):**
* **Deletion (Poll/Remove):**
* **Search (Contains):**  (It must scan the array).
* **Peek (Head):**

---

## 3. Java Implementation: Min-Heap (Default)

Here is a demonstration of the default behavior where smaller numbers come out first.

**Key Methods:**

* `add(E e)` / `offer(E e)`: Inserts an element.
* `peek()`: Retrieves (but does not remove) the head. Returns `null` if empty.
* `poll()`: Retrieves and removes the head. Returns `null` if empty.
* `element()`: Similar to peek but throws exception if empty.

```java
import java.util.PriorityQueue;

public class HeapDemonstration {
    public static void main(String[] args) {
        // Create a Priority Queue for Integers (Default is Min-Heap)
        PriorityQueue<Integer> numberQueue = new PriorityQueue<>();

        // 1. Insertion
        // We insert elements in a random order
        numberQueue.add(20);
        numberQueue.add(10);
        numberQueue.add(30);
        numberQueue.add(5);
        numberQueue.add(15);
        numberQueue.add(3);

        // 2. Inspecting the Head
        // peek() looks at the highest priority element (smallest number)
        System.out.println("Head element (Peek): " + numberQueue.peek()); 
        // Output: 3

        // 3. Iterating (Note: Iterator/forEach does NOT guarantee order)
        System.out.println("Iterating over elements:");
        numberQueue.forEach((num) -> System.out.print(num + " "));
        System.out.println();
        // Likely Output: 3 10 5 20 15 30 (Based on internal array structure)

        // 4. Deletion
        // poll() removes the head (3) and reorganizes the tree
        numberQueue.poll();
        
        System.out.println("After deleting the head:");
        numberQueue.forEach((num) -> System.out.print(num + " "));
        System.out.println();
        // Likely Output: 5 10 30 20 15 (Next smallest, 5, is now likely the head)
    }
}

```

---

## 4. Customizing Priority: Max-Heap

What if you want the **Largest** number to have the highest priority? The `PriorityQueue` cannot guess this, so we must provide a `Comparator`.

### Concept

We define a custom comparison logic:

* If `num1 < num2`, usually we return `-1`. To reverse order, we return `1`.
* If `num1 > num2`, usually we return `1`. To reverse order, we return `-1`.

### Code Example

```java
import java.util.Comparator;
import java.util.PriorityQueue;

// Custom Comparator to reverse the natural ordering
class ReverseOrderComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer num1, Integer num2) {
        if (num1 < num2) {
            return 1; // Treat smaller number as "greater" (lower priority)
        }
        if (num1 > num2) {
            return -1; // Treat larger number as "smaller" (higher priority)
        }
        return 0;
    }
}

public class MaxHeapDemonstration {
    public static void main(String[] args) {
        // Pass the custom comparator to the constructor
        PriorityQueue<Integer> maxHeapQueue = new PriorityQueue<>(new ReverseOrderComparator());

        // Insert the same elements
        maxHeapQueue.offer(20);
        maxHeapQueue.offer(10);
        maxHeapQueue.offer(30);
        maxHeapQueue.offer(5);
        maxHeapQueue.offer(15);
        maxHeapQueue.offer(3);

        // Peek will now show the LARGEST number
        System.out.println("Head element of Max-Heap: " + maxHeapQueue.peek());
        // Output: 30

        // Deleting the head
        maxHeapQueue.poll();
        
        System.out.println("After deleting the largest element (30):");
        maxHeapQueue.forEach((num) -> System.out.print(num + " "));
        // The next largest element (20) will move to the front logically.
    }
}

```

### Important Notes

1. **Nulls:** You cannot insert `null` into a Priority Queue. It will throw an exception.
2. **Comparability:** The objects you store must be comparable. If they are custom objects (like a `Student` class), they must implement `Comparable` or you must provide a `Comparator`, otherwise, you will get a `ClassCastException`.
3. **Usage:** This data structure is heavily used in graph algorithms (like Dijkstra's Shortest Path) and systems scheduling where processing order matters more than arrival time.