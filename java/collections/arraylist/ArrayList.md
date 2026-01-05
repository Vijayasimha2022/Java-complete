# ArrayList
### **1. Introduction to ArrayList**

`ArrayList` is part of the **Java Collection Framework** (located in `java.util` package). It provides a **resizable-array** implementation of the `List` interface. Unlike standard arrays in Java which have a fixed size, an `ArrayList` grows and shrinks automatically as elements are added or removed.

### **2. Initialization and Creation**

Since `ArrayList` is a **generic** class, it is type-safe. You must specify the type of objects it will store (e.g., `<Integer>`, `<String>`). Note that collections cannot store primitives (`int`, `char`); they require **Wrapper Classes** (`Integer`, `Character`).

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. Default Constructor (Default initial capacity is 10)
        ArrayList<Integer> list1 = new ArrayList<>();

        // 2. Constructor with Initial Capacity (Optimization)
        // Helps avoid resizing overhead if you know the approximate size
        ArrayList<Integer> list2 = new ArrayList<>(20);

        // 3. Initialization from another Collection
        // List.of creates an immutable list; we pass it to ArrayList to make it mutable
        ArrayList<Integer> list3 = new ArrayList<>(List.of(50, 60, 70, 80, 90));
    }
}

```

---

### **3. Core Operations (CRUD)**

#### **A. Adding Elements**

* **`add(E element)`**: Appends element to the end of the list. Time complexity:  (amortized).
* **`add(int index, E element)`**: Inserts element at the specified position. Elements to the right are shifted. Time complexity: .
* **`addAll(Collection<? extends E> c)`**: Appends all elements from another list.
* **`addAll(int index, Collection c)`**: Inserts another list at a specific index.

```java
list1.add(10);          // [10]
list1.add(0, 5);        // [5, 10] (5 is inserted at index 0)
list1.addAll(list3);    // Appends list3 elements to the end
list1.addAll(1, list3); // Inserts list3 elements starting at index 1

```

#### **B. Accessing Elements**

* **`get(int index)`**: Returns the element at the specified position.
* **Performance:** extremely fast () because `ArrayList` uses an array internally and can calculate the memory address directly.

```java
int val = list1.get(5); // Retrieves element at index 5

```

#### **C. Modifying Elements**

* **`set(int index, E element)`**: Replaces the element at the specified index.
* **Difference:** `add(index, val)` shifts elements; `set(index, val)` overwrites the existing element.

```java
list1.set(6, 100); // Replaces the element at index 6 with 100

```

#### **D. Removing Elements**

* **`remove(int index)`**: Removes element at specific index.
* **`remove(Object o)`**: Removes the first occurrence of the specified object.
* **`clear()`**: Removes all elements from the list.
* **`retainAll(Collection c)`**: Keeps only elements present in collection `c` (Intersection).

---

### **4. Search and Check Methods**

* **`contains(Object o)`**: Returns `true` if the list contains the element. Uses `.equals()` internally.
* **`indexOf(Object o)`**: Returns index of the **first** occurrence. Returns `-1` if not found.
* **`lastIndexOf(Object o)`**: Returns index of the **last** occurrence.

```java
boolean has50 = list1.contains(50); // true/false
int first70 = list1.indexOf(70);    // Returns index (e.g., 3)
int last70 = list1.lastIndexOf(70); // Returns index (e.g., 5)

```

---

### **5. Iteration Techniques (Traversing the List)**

Java offers multiple ways to iterate through an `ArrayList`.

#### **Method 1: Standard For-Loop**

Best when you need the **index** of the element.

```java
for (int i = 0; i < list1.size(); i++) {
    System.out.println(list1.get(i));
}

```

#### **Method 2: Enhanced For-Loop (For-Each)**

Cleanest syntax for reading elements.

```java
for (Integer x : list1) {
    System.out.println(x);
}
// Using 'var' (Java 10+)
for (var x : list1) {
    System.out.println(x);
}

```

#### **Method 3: Iterator (Forward Only)**

The standard way to iterate legacy collections. It is safe for removing elements during iteration (avoids `ConcurrentModificationException`).

```java
Iterator<Integer> it = list1.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
    // it.remove(); // Can safely remove elements here
}

```

#### **Method 4: ListIterator (Bidirectional)**

Exclusive to List implementations. Allows traversal in **both directions** and element modification.

* `hasNext()` / `next()`
* `hasPrevious()` / `previous()`
* `set()` (modify current), `add()` (insert new)

```java
ListIterator<Integer> lit = list1.listIterator();
while (lit.hasNext()) {
    // ... forward traversal
}

```

#### **Method 5: Java 8 Stream / Lambda / Method Reference**

Modern, functional approach.

```java
// Lambda Expression
list1.forEach(n -> System.out.println(n));

// Method Reference
list1.forEach(System::out::println);

// Custom Logic in Lambda
list1.forEach(n -> {
    if (n > 60) show(n);
});

```

---

### **6. Advanced Concepts (Added Context)**

#### **Internal Architecture: How Resizing Works**

When you create an `ArrayList`, it creates an internal array of size 10 (default). When that array gets full:

1. **New Array Creation:** Java creates a new, larger array (usually **50% larger** than the current size).
* *Formula:* 


2. **Copying:** It copies all old elements to the new array.
3. **Reference Update:** The reference is updated to point to the new array.

* *Note:* This resizing operation is costly (), which is why setting an initial capacity in the constructor is good practice if the size is known.

#### **Fail-Fast Iterators**

The iterators returned by `ArrayList` are **fail-fast**. If you modify the list structurally (add/remove) using the list's methods while a thread is iterating over it using an iterator, the iterator will throw a `ConcurrentModificationException`.

#### **ArrayList vs Array**

| Feature | Array | ArrayList |
| --- | --- | --- |
| **Size** | Fixed length. | Dynamic (Resizes automatically). |
| **Performance** | Faster (no overhead). | Slightly slower due to resizing/generics. |
| **Types** | Primitives & Objects. | Objects only (uses Wrappers). |
| **Length** | `arr.length` property. | `list.size()` method. |

#### **Synchronization**

`ArrayList` is **not synchronized** (not thread-safe). If multiple threads access it concurrently, you must synchronize it manually or use:

* `Collections.synchronizedList(new ArrayList(...))`
* `CopyOnWriteArrayList` (for concurrent scenarios).
