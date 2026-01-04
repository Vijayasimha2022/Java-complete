# The LinkedList Class

In this section, lets explore another very important class in the Collections framework: **LinkedList**.

We have already learned about `ArrayList`. You might be wondering, if we have `ArrayList`, why do we need `LinkedList`?

While they both implement the `List` interface (meaning they are both ordered collections of elements), they work very differently "under the hood." `ArrayList` uses a dynamic array, but `LinkedList` uses a **Doubly Linked List** data structure.

Let's write a program to understand how to create, manipulate, and iterate over a LinkedList.

## 1. Creating and Adding Elements

First, let's create a LinkedList. I will create a list to store integer values. Notice that we can use the `List` reference, but often we use the `LinkedList` reference directly if we want to access specific methods like `addFirst` or `addLast`.

```java
import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        
        // Creating a LinkedList
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // 1. Adding elements
        // This works exactly like ArrayList
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        
        // We can also add elements at a specific index
        numbers.add(1, 15); // Inserts 15 at index 1
        
        System.out.println("List: " + numbers); 
        // Output: [10, 15, 20, 30]
    }
}

```

## 2. Special Methods (Deque Interface)

This is where `LinkedList` shines. Because it implements the `Deque` (Double Ended Queue) interface, we can treat it like a stack or a queue. We can easily add or remove elements from *both* ends.

You cannot do this efficiently with `ArrayList`.

```java
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> tasks = new LinkedList<>();
        
        tasks.add("Coding");
        tasks.add("Testing");
        
        // 1. Adding to the beginning (Head)
        tasks.addFirst("Design"); 
        
        // 2. Adding to the end (Tail)
        tasks.addLast("Deploy");
        
        System.out.println(tasks);
        // Output: [Design, Coding, Testing, Deploy]
        
        // 3. Removing from ends
        tasks.removeFirst(); // Removes "Design"
        tasks.removeLast();  // Removes "Deploy"
        
        System.out.println("After removal: " + tasks);
    }
}

```

**Key Concept:**

* `addFirst()` / `addLast()`
* `removeFirst()` / `removeLast()`
* `getFirst()` / `getLast()`

These methods make `LinkedList` perfect if you are implementing a Queue or a Stack.

## 3. Iterating Over a LinkedList

Just like other collections, we can iterate using a for-loop or a for-each loop. However, since this is a list, we can also use a **ListIterator**.

A `ListIterator` is special because it allows us to traverse the list in **both directions** (forward and backward).

```java
public class IterationDemo {
    public static void main(String[] args) {
        LinkedList<String> cities = new LinkedList<>();
        cities.add("New York");
        cities.add("London");
        cities.add("Tokyo");
        
        System.out.println("--- Forward Iteration ---");
        ListIterator<String> it = cities.listIterator();
        
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        
        System.out.println("--- Backward Iteration ---");
        // The iterator is currently at the end.
        // We can use hasPrevious() to go backwards.
        
        while(it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }
}

```

## 4. ArrayList vs. LinkedList: When to use which?

This is a very popular interview question. Both look the same from the outside, but their performance is different.

| Feature | ArrayList | LinkedList |
| --- | --- | --- |
| **Internal Structure** | Dynamic Array | Doubly Linked List |
| **Access (get)** | **Fast (O(1))** - It jumps directly to the index. | **Slow (O(n))** - It must traverse nodes from the start to find the index. |
| **Insertion/Deletion** | **Slow** - It has to shift all subsequent elements. | **Fast** - It just changes the links (pointers) between nodes. |
| **Memory** | Uses less memory. | Uses more memory (stores data + address of next node + address of previous node). |

### Conclusion

* If your requirement is to **fetch/search** data frequently, use **ArrayList**.
* If your requirement involves frequent **insertion or deletion** of elements (especially in the middle or beginning), use **LinkedList**.

That's it for LinkedList. It is a flexible class that acts as both a List and a Deque. Please practice the `addFirst` and `addLast` methods, as they are unique to this class compared to ArrayList.