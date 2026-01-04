# The ArrayDeque Class
Lets explore the `ArrayDeque` class. The word **Deque** stands for **Double-Ended Queue**.

As the name suggests, this is a data structure where elements can be inserted and deleted from **both ends** (the front/head and the rear/tail).

## 1. Internal Structure & Performance

* **Data Structure:** It uses a resizable **Array** internally to store elements.
* **Performance:** It claims to perform operations (add, remove, peek) in **amortized constant time** (O(1)).
* *Instructor's Note:* To achieve constant time for adding at the "First" position, the internal implementation likely doesn't start filling the array from index 0. It might start in the middle, allowing room to grow in both directions without needing to shift elements immediately.


* **Comparison:**
* It is generally **faster than the legacy `Stack` class**.
* It is **faster than `LinkedList**` when used as a Queue because it uses arrays, which are more cache-friendly.



## 2. Versatility: Stack vs. Queue

The beauty of `ArrayDeque` is that it allows you to choose how it behaves based on which methods you use.

### Option A: Acting as a Queue (FIFO)

If you insert elements at the **Last** and remove them from the **First**, it behaves exactly like a Queue (First In, First Out).

### Option B: Acting as a Stack (LIFO)

If you insert elements at the **Last** and remove them from the **Last** (or insert First/remove First), it behaves like a Stack (Last In, First Out).

## 3. Important Methods

The `Deque` interface provides two sets of methods for every operation. One set throws exceptions on failure, and the other returns a special value (`null` or `false`) which is generally safer.

| Operation | Throws Exception | Returns Special Value (Safer) |
| --- | --- | --- |
| **Insert at Head** | `addFirst(e)` | `offerFirst(e)` |
| **Insert at Tail** | `addLast(e)` | `offerLast(e)` |
| **Remove from Head** | `removeFirst()` | `pollFirst()` |
| **Remove from Tail** | `removeLast()` | `pollLast()` |
| **Examine Head** | `getFirst()` | `peekFirst()` |
| **Examine Tail** | `getLast()` | `peekLast()` |

* **Tip:** It is usually better to use the `offer`, `poll`, and `peek` methods so you can handle empty states gracefully without `try-catch` blocks.

## 4. Code Examples

Let's write some code to demonstrate the flexibility of this class. We will use a generic `ArrayDeque` of Integers.

### Example 1: Mixing First and Last Insertions

```java
import java.util.*;

public class DequeUsage {
    public static void main(String[] args) {
        // Create the ArrayDeque
        ArrayDeque<Integer> container = new ArrayDeque<>();

        // 1. Add elements to the END (Last)
        container.offerLast(10);
        container.offerLast(20);
        container.offerLast(30);
        
        // Current State: [10, 20, 30]

        // 2. Add elements to the FRONT (First)
        container.offerFirst(1);
        container.offerFirst(2);
        
        // Logic: 
        // 1 is added before 10.
        // 2 is added before 1.
        // Final State: [2, 1, 10, 20, 30]

        // 3. Print elements using Lambda
        container.forEach((element) -> System.out.print(element + " "));
        // Output: 2 1 10 20 30
    }
}

```

### Example 2: Implementing a Stack (LIFO)

To implement a Stack, we perform all operations on the **same end** (let's use the "Last" end).

```java
public class Stack implementation {
    public static void main(String[] args) {
        ArrayDeque<String> stack = new ArrayDeque<>();

        // Push (Add to Last)
        stack.offerLast("Page 1");
        stack.offerLast("Page 2");
        stack.offerLast("Page 3");

        // Pop (Remove from Last)
        String top = stack.pollLast(); 
        
        System.out.println("Popped: " + top); // Output: Page 3
    }
}

```

### Example 3: Implementing a Queue (FIFO)

To implement a Queue, we add to one end and remove from the other.

```java
public class QueueImplementation {
    public static void main(String[] args) {
        ArrayDeque<String> queue = new ArrayDeque<>();

        // Enqueue (Add to Last)
        queue.offerLast("Customer A");
        queue.offerLast("Customer B");
        queue.offerLast("Customer C");

        // Dequeue (Remove from First)
        String served = queue.pollFirst();
        
        System.out.println("Served: " + served); // Output: Customer A
    }
}

```

## Summary

* **ArrayDeque** is a powerful, generic class that is efficient for adding/removing items from both ends.
* It is the recommended replacement for the old `Stack` class.
* It is often a better choice than `LinkedList` for Queues due to memory efficiency and cache locality.
* Always check if your application needs a Stack or a Queue behavior, and choose your methods (`offerFirst` vs `offerLast`) accordingly.

