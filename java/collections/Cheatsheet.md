# Java Collections Cheat Sheet: The Big Four

## 1. Quick Comparison Table

| Feature | **ArrayList** | **LinkedList** | **HashSet** | **HashMap** |
| --- | --- | --- | --- | --- |
| **Interface** | `List` | `List`, `Deque` | `Set` | `Map` |
| **Underlying Data Structure** | Resizable Array | Doubly Linked List | Hash Table | Hash Table |
| **Duplicates** | Allowed | Allowed | **Not Allowed** | **Keys: No**, Values: Yes |
| **Ordering** | Maintains Insertion Order | Maintains Insertion Order | No Guarantee (Random) | No Guarantee (Random) |
| **Null Values** | Allowed | Allowed | Allowed (Max 1) | **Key: Max 1**, Values: Any |
| **Thread Safety** | No | No | No | No |

---

## 2. Deep Dive & Best Use Cases

### A. ArrayList

**Concept:** Think of it as a "Smart Array" that grows automatically.

* **Internal Working:** Uses a dynamic array. When full, it creates a new array (usually 50% larger) and copies old elements over.
* **Best Use Case:** When you have a **Write-Once, Read-Many** scenario. Excellent for storing data that you need to access randomly by index (e.g., `list.get(5)`).
* **Worst Use Case:** Frequent insertion or deletion in the *middle* of the list (requires shifting all subsequent elements).

### B. LinkedList

**Concept:** A chain of nodes where each node knows its neighbors.

* **Internal Working:** Doubly Linked List. Each element (Node) holds Data, a Pointer to the Previous node, and a Pointer to the Next node.
* **Best Use Case:** Implementing Stacks or Queues, or scenarios with frequent **Insertions/Deletions** in the middle.
* **Worst Use Case:** Random access. To get the 100th element, it must traverse nodes 0 to 99 one by one.

### C. HashSet

**Concept:** A bag of unique items.

* **Internal Working:** Internally uses a `HashMap`. The values you add are stored as "Keys" in the map, and a dummy object is stored as the value.
* **Best Use Case:** When you need to **remove duplicates** from a collection or check if an item exists (`contains()`) extremely fast.
* **Worst Use Case:** When order matters (use `LinkedHashSet` instead) or when you need to access items by index (impossible in Sets).

### D. HashMap

**Concept:** A dictionary (Key-Value pairs).

* **Internal Working:** Uses an array of "Buckets". Keys are hashed to find the bucket index. Handles collisions using Linked Lists (or Red-Black Trees after Java 8).
* **Best Use Case:** Caching, looking up data by a unique identifier (like UserID -> UserObject), or counting frequencies of items.
* **Worst Use Case:** Iterating over all elements is slower than a List.

---

## 3. Time Complexity Cheat Sheet (Big O)

| Operation | ArrayList | LinkedList | HashSet | HashMap |
| --- | --- | --- | --- | --- |
| **Add (End)** | O(1) Amortized | O(1) | O(1) | O(1) |
| **Add (Middle)** | O(n) | O(1) * | O(1) | O(1) |
| **Get / Search** | **O(1)** (Fastest) | O(n) | **O(1)** | **O(1)** |
| **Remove** | O(n) | O(1) * | O(1) | O(1) |
| **Contains** | O(n) | O(n) | **O(1)** | **O(1)** |

*(Note on LinkedList: Insertion is O(1) only if you already have the reference to the node. Finding that node still takes O(n).)*

---

## 4. Syntax Quick-Start

```java
import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        
        // --- ArrayList ---
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        String first = names.get(0); // Fast Access

        // --- LinkedList ---
        List<String> queue = new LinkedList<>();
        queue.add("Task 1");
        // LinkedList specific methods (requires casting or using Deque interface)
        ((LinkedList<String>) queue).addFirst("Urgent Task"); 

        // --- HashSet ---
        Set<Integer> uniqueIds = new HashSet<>();
        uniqueIds.add(101);
        uniqueIds.add(101); // Ignored
        boolean exists = uniqueIds.contains(101); // Fast Check

        // --- HashMap ---
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Player1", 90);
        scores.put("Player1", 95); // Overwrites 90 with 95
        int score = scores.get("Player1");
    }
}

```

---

## 5. Interview "Gotchas" (Pro Tips)

1. **Fail-Fast Iterators:** All these collections return "Fail-Fast" iterators. If you try to modify the collection (add/remove) inside a `for-each` loop while iterating, it throws a `ConcurrentModificationException`. Use `Iterator.remove()` instead.
2. **Initial Capacity:** If you know you need to store 10,000 items in an `ArrayList`, initialize it with that size: `new ArrayList<>(10000)`. This prevents the array from resizing (copying) multiple times, improving performance.
3. **Key Immutability:** Always use immutable objects (like `String` or `Integer`) as Keys in a `HashMap`. If a Key object changes its state (and thus its hashcode) after being inserted, you will never be able to find it again.

---
