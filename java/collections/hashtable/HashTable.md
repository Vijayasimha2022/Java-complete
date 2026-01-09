# Java Hashtable: In-Depth Notes

These notes explore the `Hashtable` class. While it is similar to `HashMap` and uses the same underlying hashing principles, it has distinct historical and functional differences, particularly regarding thread safety.

## 1. What is a Hashtable?

`Hashtable` is one of the oldest collection classes in Java (introduced in JDK 1.0), making it a **Legacy Class**.

* **Implements:** `Map` Interface.
* **Underlying Data Structure:** Hash Table (Arrays + Linked Lists for chaining).
* **Key Behavior:** It stores data as Key-Value pairs.
* **Thread Safety:** It is **Synchronized**. This means it is thread-safe and can be shared between multiple threads without corrupting data.

## 2. Key Characteristics (The "Rules")

While it shares the "Hashing" logic (buckets, indices, collision handling) with `HashMap`, it enforces stricter rules:

### A. No Nulls Allowed

Unlike `HashMap` (which allows one null key and multiple null values), `Hashtable` is strict.

* **Key:** Cannot be `null`.
* **Value:** Cannot be `null`.
* *Result:* If you try to insert `null`, it throws a `NullPointerException` immediately.

### B. Synchronization (Thread Safety)

Every public method in `Hashtable` (like `put`, `get`, `remove`) is modified with the `synchronized` keyword.

* **Pros:** You don't need to manually lock code when accessing the map from multiple threads.
* **Cons:** It is **slower** than `HashMap`. Acquiring and releasing locks takes time. Even if only one thread is using it, the performance cost exists.

## 3. Internal Working

The internal logic is nearly identical to the generic Hashing technique:

1. **Hashing:** It calculates `key.hashCode() % capacity` to find the bucket index.
2. **Collision:** If a bucket is full, it uses a **Linked List** (Chaining) to store multiple entries at that index.
3. **Resizing:** When the table exceeds its Load Factor (default 0.75), it rehashes everything into a larger array.

## 4. Hashtable vs. HashMap (Crucial Interview Topic)

| Feature | Hashtable | HashMap |
| --- | --- | --- |
| **Status** | Legacy (Old). | Standard (Newer, JDK 1.2). |
| **Thread Safety** | **Synchronized** (Thread-safe). | **Not Synchronized** (Not thread-safe). |
| **Null Keys/Values** | **Not Allowed** (throws Exception). | **Allowed** (1 null key, any null values). |
| **Performance** | Slower (due to overhead). | Faster. |
| **Iterators** | Uses `Enumeration` (old) & `Iterator`. | Uses `Iterator` (fail-fast). |

## 5. Modern Alternatives

Since `Hashtable` is legacy, you should rarely use it in modern development.

* **For Single Thread:** Use `HashMap` (it's faster).
* **For Multi-Thread:** Use `ConcurrentHashMap` (it handles locking much more efficiently than `Hashtable`).

## 6. Code Demonstration

Here is a practical example showing strict null handling and basic operations.

```java
import java.util.Hashtable;
import java.util.Map;

public class HashtableDemo {
    public static void main(String[] args) {
        // 1. Creation
        // We can use the Map interface reference, but typically for legacy code
        // we might see the specific class used.
        Hashtable<Integer, String> serverConfig = new Hashtable<>();

        // 2. Insertion
        serverConfig.put(101, "Localhost");
        serverConfig.put(102, "RemoteDB");
        serverConfig.put(103, "CacheServer");

        // 3. Thread Safety in Action (Implicit)
        // If another thread tried to write to 'serverConfig' right now,
        // it would have to wait until this thread finishes its operation.

        // 4. Retrieving Data
        String dbServer = serverConfig.get(102);
        System.out.println("Database is at: " + dbServer);

        // 5. The Null Restriction
        try {
            // This line will CRASH the program with NullPointerException
            serverConfig.put(104, null); 
        } catch (NullPointerException e) {
            System.out.println("Error: Hashtable does not allow null values!");
        }

        try {
            // This also crashes
            serverConfig.put(null, "BackupServer");
        } catch (NullPointerException e) {
            System.out.println("Error: Hashtable does not allow null keys!");
        }

        // 6. Iterating (Legacy style used Enumeration, but we can use for-each)
        System.out.println("Active Servers:");
        for (Map.Entry<Integer, String> entry : serverConfig.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

```