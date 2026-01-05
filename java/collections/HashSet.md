# HashSet: 

These notes explore the `HashSet` class, which is one of the most commonly used implementations of the `Set` interface. It is designed for storing unique elements and uses the **Hashing** mechanism we previously discussed to ensure efficient performance.

## 1. What is a HashSet?

`HashSet` is a collection class found in the `java.util` package.

* **Implements:** `Set` Interface.
* **Underlying Data Structure:** Hash Table.
* **Key Behavior:** It does **not** allow duplicate values. If you try to insert a duplicate, the set simply ignores it (it won't throw an error, it just returns `false`).
* **Ordering:** It is **unordered**. You cannot guarantee that the elements will be stored or retrieved in the same order you inserted them.

## 2. Internal Working (The "Secret")

You might be surprised to know that `HashSet` does not actually implement the hashing logic from scratch. Internally, **it uses a `HashMap**`.

* **The Backing Map:** When you create a `HashSet`, Java internally creates a `HashMap`.
* **Storage Logic:**
* The elements you add to the `Set` are actually stored as **Keys** in the `HashMap`.
* Since Map keys must be unique, this automatically ensures the Set has unique values.
* **What about the Value?** Since a Map requires Key-Value pairs, `HashSet` inserts a dummy object (often called `PRESENT`) as the value for every entry.



## 3. How Duplicate Detection Works

For `HashSet` to correctly identify duplicates, the objects you store must correctly override two methods from the `Object` class:

1. **`hashCode()`:** Determines the bucket location.
2. **`equals()`:** Checks if the object in that bucket is essentially the same as the new one.

> **Rule:** If two objects are equal according to `.equals()`, they **must** have the same `hashCode()`. If this contract is broken, `HashSet` will fail to filter duplicates.

## 4. Constructors & Tuning

You can tune the performance of a HashSet using its constructors, exactly like a HashMap.

1. **`new HashSet<>()`**: Creates a default set with **Capacity 16** and **Load Factor 0.75**.
2. **`new HashSet<>(int capacity)`**: Useful if you know you will store many elements (prevents frequent resizing).
3. **`new HashSet<>(int capacity, float loadFactor)`**: Advanced tuning for space vs. time trade-offs.

## 5. Code Demonstration

Here is a practical example showing basic operations and how duplicates are handled.

```java
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        // 1. Creation
        // We use the Set interface reference for good practice
        Set<String> fruitBasket = new HashSet<>();

        // 2. Adding Elements
        // add() returns true if the element was added successfully
        fruitBasket.add("Apple");
        fruitBasket.add("Banana");
        fruitBasket.add("Mango");

        // 3. Handling Duplicates
        // "Apple" is already there. hashCode() matches, equals() returns true.
        // This will return false and NOT add the element.
        boolean isAdded = fruitBasket.add("Apple"); 
        System.out.println("Was duplicate Apple added? " + isAdded); // Output: false

        // 4. Null Elements
        // HashSet allows exactly one null value
        fruitBasket.add(null);

        // 5. Checking for Existence (Fast Operation: O(1))
        if (fruitBasket.contains("Banana")) {
            System.out.println("Banana is in the basket.");
        }

        // 6. Removing Elements
        fruitBasket.remove(null); // Remove the null we added

        // 7. Iterating (Order is NOT guaranteed)
        System.out.println("Contents of the basket:");
        for (String fruit : fruitBasket) {
            System.out.println(fruit);
        }
    }
}

```

## 6. Storing Custom Objects

If you store your own classes (like a `Student` or `Employee`), you **must** override `hashCode` and `equals`.

```java
import java.util.HashSet;
import java.util.Objects;

class Book {
    int id;
    String title;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Critical for HashSet to work correctly!
    @Override
    public int hashCode() {
        return Objects.hash(id); // Using ID for uniqueness
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return id == other.id; // Books are equal if IDs are equal
    }
}

public class CustomObjectSet {
    public static void main(String[] args) {
        HashSet<Book> library = new HashSet<>();
        
        Book b1 = new Book(101, "Java Programming");
        Book b2 = new Book(101, "Java Programming"); // Logically same book

        library.add(b1);
        library.add(b2); 

        // Because we overrode hashCode and equals, size is 1.
        // Without overrides, size would be 2 (treated as different objects).
        System.out.println("Library Size: " + library.size()); 
    }
}

```

## 7. Summary of Features

| Feature | Description |
| --- | --- |
| **Ordering** | No guarantee. Elements may appear in random order. |
| **Duplicates** | Not allowed. Determined by `equals()` and `hashCode()`. |
| **Null Values** | Allows **one** `null` element. |
| **Performance** | Basic operations (`add`, `remove`, `contains`) take **constant time**  on average. |
| **Thread Safety** | **Not** thread-safe. Use `Collections.synchronizedSet` if needed. |
