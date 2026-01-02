# Collection Interface

### 1. Overview

The Collection Framework is located in the **`java.util`** package. The `Collection` interface acts as the **root parent** for the most common collection types: **List**, **Set**, and **Queue**.

* **Generic Nature:** The interface is **generic** (represented as `<E>`), meaning it can store objects of any class type (e.g., Strings, Integers, or custom objects), similar to how you can create an array of any type.

### 2. Key Methods of the Collection Interface

Since `List`, `Set`, and `Queue` all extend this interface, they all inherit these fundamental methods.

#### A. Modification Methods (Adding & Removing)

These methods allow you to change the contents of the collection. Most return a `boolean` (true/false) indicating if the collection was modified.

* **`add(Object element)`**: Adds a single object to the collection.
* **`addAll(Collection c)`**: Adds all elements from another collection into the current one (mathematical union).
* **`remove(Object element)`**: Deletes a specific object from the collection if it exists.
* **`removeAll(Collection c)`**: Removes all objects from the current collection that are also present in the specified collection `c` (mathematical difference).
* **`retainAll(Collection c)`**: The opposite of `removeAll`. It **keeps** only the elements that are present in *both* collections and deletes everything else (mathematical intersection).
* **`clear()`**: Removes all elements, leaving the collection empty.

#### B. Query Methods (Checking Status)

These methods allow you to check the status or contents of the collection without modifying it.

* **`contains(Object element)`**: specific element is present. Returns `true` or `false`.
* **`containsAll(Collection c)`**: Checks if **every** element in collection `c` is present in the current collection. Returns `true` only if all are found.
* **`isEmpty()`**: Returns `true` if the collection has no elements (size is 0).
* **`size()`**: Returns the integer count of elements currently in the collection.
* **`equals(Collection c)`**: Compares two collections to see if they are identical in content and size.

#### C. Traversal & Conversion

* **`iterator()`**: Returns an **Iterator** object. This is used to traverse (scan) through the collection one by one, similar to using a counter `i` in a standard `for` loop for arrays.
* **`toArray()`**: Converts the collection into a standard array.

### Summary Table

| Category | Methods | Description |
| --- | --- | --- |
| **Adding** | `add`, `addAll` | Insert one or multiple elements. |
| **Removing** | `remove`, `removeAll`, `retainAll`, `clear` | Delete specific items, subtract sets, intersect sets, or wipe data. |
| **Checking** | `contains`, `containsAll`, `isEmpty`, `size` | Check for existence or count elements. |
| **Other** | `iterator`, `toArray` | Loop through elements or convert to array. |

### 1. List Interface

The `List` interface extends the `Collection` interface. It represents an **ordered collection** where elements are stored based on an index (0, 1, 2, etc.). Because it is ordered, it allows for duplicate elements.

Since `List` maintains an index for every element, it includes all methods from the `Collection` interface plus several extra methods specifically for handling indices:

* **Adding Elements:**
* `add(int index, Object element)`: Inserts an element at a specific index. Unlike the standard `add` which usually appends to the end, this shifts existing elements to make space.
* `addAll(int index, Collection c)`: Inserts an entire collection starting at a specified index, shifting existing elements to accommodate the new ones.


* **Accessing & Modifying:**
* `get(int index)`: Returns the object stored at the specified index.
* `set(int index, Object element)`: Replaces the element at the specified index with a new element. (Note: This differs from `add`; `set` replaces, while `add` inserts).


* **Removing:**
* `remove(int index)`: Deletes the element at the specified index. Subsequent elements are shifted to fill the gap.


* **Searching:**
* `subList(int fromIndex, int toIndex)`: Returns a view of the list between the specified range (start index is inclusive, end index is exclusive).
* `indexOf(Object o)`: Returns the index of the first occurrence of the specified object.
* `lastIndexOf(Object o)`: Returns the index of the last occurrence of the specified object (searches backwards from the end).


* **Iterating:**
* `listIterator()`: Returns a `ListIterator` object. Unlike the standard `Iterator` which is unidirectional (forward only), the `ListIterator` is **bidirectional** (can move forward and backward) and can be initialized to start at a specific index.



---

### 2. Set Interface

The `Set` interface also extends the `Collection` interface but models a mathematical set. It is an **unordered collection** that contains **unique elements** only (no duplicates).

* **Methods:** It has **no extra methods** compared to the `Collection` interface. It relies entirely on the standard methods inherited from `Collection`, but its implementation ensures that duplicates are prevented and order is generally not guaranteed.

---

### 3. Queue Interface

The `Queue` interface extends `Collection` and is designed to hold elements prior to processing. It typically follows the **FIFO (First-In-First-Out)** principle, where elements are added to the tail and removed from the head.

It introduces specific methods to support this mechanism, often providing two versions of each operation: one that throws an exception on failure and one that returns a special value (null or false).

| Operation | Throws Exception | Returns Special Value | Description |
| --- | --- | --- | --- |
| **Insert** | `add(e)` |  | Adds an element to the end of the queue. |
| **Remove** | `remove()` | `poll()` | Removes and returns the element at the head of the queue. `poll()` returns `null` if the queue is empty. |
| **Examine** | `element()` | `peek()` | Retrieves, but does not remove, the head of the queue. `peek()` returns `null` if the queue is empty. |
