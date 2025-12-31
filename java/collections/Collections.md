# Java Collections Framework: Deep Dive

## 1. Overview & Hierarchy
The Collections Framework provides a set of standard data structures and algorithms. The root interface is `Collection` (which extends `Iterable`), but `Map` is a separate hierarchy.

### The Core Hierarchy
* **`Iterable`** (Interface)
    * **`Collection`** (Interface)
        * **`List`**: Ordered, allows duplicates. (`ArrayList`, `LinkedList`, `Vector`)
        * **`Set`**: Unordered, unique elements. (`HashSet`, `LinkedHashSet`, `TreeSet`)
        * **`Queue`**: FIFO (usually). (`PriorityQueue`, `LinkedList`)
        * **`Deque`**: Double-ended queue. (`ArrayDeque`)
* **`Map`** (Interface): Key-Value pairs. (`HashMap`, `TreeMap`, `LinkedHashMap`)

---

## 2. List Interface (Ordered Collection)

### ArrayList (The Default Choice)
* **Internal Structure:** Dynamic Array.
* **Resizing Logic:** When full, it creates a new array with size **~1.5x (50% growth)** and copies elements using `System.arraycopy`.
* **Performance:**
    * Access (`get`): **O(1)** (Direct index access).
    * Insertion/Deletion (`add`/`remove`): **O(N)** (Requires shifting elements).
* **Best For:** Read-heavy operations.

### LinkedList
* **Internal Structure:** Doubly Linked List (Nodes with `prev` and `next` pointers).
* **Performance:**
    * Access: **O(N)** (Must traverse from head/tail).
    * Insertion/Deletion: **O(1)** (Only reference changes, *if position is known*).
* **Memory:** Higher overhead due to storing node pointers.
* **Best For:** Frequent insertions/deletions in the middle.

---

## 3. Map Interface (Key-Value)
*Note: This is the most critical topic for backend interviews.*

### HashMap (Internal Working)
How `put(K key, V value)` works internally:
1.  **Hashing:** It calls `key.hashCode()`.
2.  **Index Calculation:** Uses `(n - 1) & hash` to find the bucket index.
3.  **Collision Handling:**
    * If the bucket is empty, a Node is added.
    * If the bucket is not empty (Collision), it checks `equals()` on keys.
    * If keys are equal, it updates the value.
    * If keys are different, it appends to the Linked List (Chaining).
4.  **Java 8 Improvement (Treeify):** If a single bucket has more than **8 nodes** (TREEIFY_THRESHOLD), the Linked List converts into a **Red-Black Tree** to improve worst-case performance from O(N) to **O(log N)**.

### TreeMap
* **Internal Structure:** Red-Black Tree (Self-balancing BST).
* **Ordering:** Sorts keys according to their "Natural Ordering" (Comparable) or a custom `Comparator`.
* **Complexity:** O(log N) for `get`, `put`, `remove`.
* **Best For:** Sorted data requirements.

### LinkedHashMap
* **Internal Structure:** HashMap + Doubly Linked List maintaining insertion order.
* **Best For:** Caching (can be configured as an LRU Cache).

---

## 4. Set Interface (Uniqueness)

### HashSet
* **Internal Structure:** It actually creates a `HashMap` internally!
* **How it works:** When you call `add(value)`, it calls `map.put(value, PRESENT)`, where `PRESENT` is a dummy object.
* **Uniqueness:** Relies on the Key uniqueness of the underlying HashMap.

### TreeSet
* **Internal Structure:** Backed by a `TreeMap`.
* **Behavior:** Stores unique elements in sorted order.

---

## 5. Fail-Fast vs Fail-Safe

| Feature | Fail-Fast | Fail-Safe |
| :--- | :--- | :--- |
| **Classes** | ArrayList, HashMap, HashSet | ConcurrentHashMap, CopyOnWriteArrayList |
| **Behavior** | Throws `ConcurrentModificationException` if modified during iteration. | Works on a clone/snapshot; no exception thrown. |
| **Mechanism** | Uses an internal `modCount` variable. | Iterates over a copy or uses advanced locking. |
| **Use Case** | Single-threaded debugging. | Multi-threaded environments. |

---

## 6. Time Complexity Reference

| Collection | Get | Add | Contains | Remove |
| :--- | :--- | :--- | :--- | :--- |
| **ArrayList** | O(1) | O(1) avg | O(N) | O(N) |
| **LinkedList** | O(N) | O(1) | O(N) | O(1) |
| **HashSet** | - | O(1) | O(1) | O(1) |
| **TreeSet** | - | O(log N) | O(log N) | O(log N) |
| **HashMap** | O(1) | O(1) | O(1) | O(1) |
| **TreeMap** | O(log N) | O(log N) | O(log N) | O(log N) |

---

## 7. Key Interview Questions (Self-Check)
1. Why should you override `equals()` and `hashCode()` together?
2. What happens if two different keys have the same hash code? (Answer: Collision -> Chaining)
3. What is the difference between `Comparable` and `Comparator`?
4. When does a HashMap resize? (Answer: When `size > capacity * loadFactor` (default 0.75)).