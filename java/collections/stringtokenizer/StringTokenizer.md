Here are the detailed notes on the `StringTokenizer` class for your GitHub repository.

---

# The StringTokenizer Class

## 1. Introduction

The `StringTokenizer` class is a utility class located in the **`java.util`** package. Its primary purpose is to break a single String into smaller pieces, known as **Tokens**.

* **Token:** The individual piece of data (e.g., a word in a sentence).
* **Delimiter:** The separator character(s) used to identify boundaries between tokens (e.g., spaces, commas, semicolons).

> **Note:** `StringTokenizer` is a legacy class that is retained for compatibility reasons, but it is still useful for simple, high-performance string splitting operations where you don't need complex Regular Expressions (Regex).

---

## 2. Basic Usage

To use `StringTokenizer`, you create an instance by passing the **Source String** and the **Delimiters** to the constructor.

### Key Methods

* `hasMoreTokens()`: Checks if there are more tokens available (returns `true`/`false`).
* `nextToken()`: Returns the next token from the string.
* `countTokens()`: Returns the number of tokens remaining.

### Example: Splitting Key-Value Pairs

Imagine a single string containing multiple user details separated by distinct symbols.

```java
import java.util.StringTokenizer;

public class TokenizerDemo {
    public static void main(String[] args) {
        // A raw data string: "Key=Value;Key=Value"
        String rawData = "name=Alice;address=New York;country=USA;id=101";

        // Create Tokenizer
        // Input: rawData
        // Delimiters: "=" (to separate key/value) and ";" (to separate pairs)
        StringTokenizer tokenizer = new StringTokenizer(rawData, "=;");

        System.out.println("--- Extracted Tokens ---");
        
        // Loop while there are more tokens
        while (tokenizer.hasMoreTokens()) {
            // Retrieve the next token
            String token = tokenizer.nextToken();
            System.out.println(token);
        }
    }
}

```

**Output:**

```text
--- Extracted Tokens ---
name
Alice
address
New York
country
USA
id
101

```

### Understanding Delimiters

In the constructor `new StringTokenizer(str, "=;")`:

1. The second argument is a string containing **all** characters that should act as delimiters.
2. It treats `=` and `;` individually as separators.
3. **Whitespace:** If you do not explicitly include a space `" "` in your delimiter string, spaces will be treated as part of the token data.

---

## 3. Advanced Scenario: Reading & Parsing File Data

A common real-world scenario is reading a file where data is separated by commas (CSV style) or other symbols, and then storing that data into a Collection like an `ArrayList`.

### Scenario

We have a file named `Numbers.txt` containing:
`10,20,30,40,50,60`

We want to read this, strip the commas, convert the Strings to Integers, and store them in a list.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileTokenizerDemo {
    public static void main(String[] args) throws IOException {
        
        // 1. Read data from file into a String
        // (Note: In a real app, use try-with-resources for safety)
        String filePath = "Numbers.txt"; // Ensure path uses forward slashes '/'
        FileInputStream fis = new FileInputStream(filePath);
        
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        String fileContent = new String(buffer);
        
        fis.close();

        // 2. Tokenize the file content
        // Delimiter is comma ","
        StringTokenizer tokenizer = new StringTokenizer(fileContent, ",");

        // 3. Store in ArrayList
        ArrayList<Integer> numberList = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            
            // Trim whitespace (good practice) and Parse to Integer
            int number = Integer.parseInt(token.trim());
            
            numberList.add(number);
        }

        // 4. Verification
        System.out.println("Parsed Numbers List: " + numberList);
    }
}

```

**Output:**

```text
Parsed Numbers List: [10, 20, 30, 40, 50, 60]

```

---

## 4. Comparisons: `StringTokenizer` vs `split()`

*(Added for technical depth)*

You might wonder why we use `StringTokenizer` when `String.split()` exists.

| Feature | `StringTokenizer` | `String.split()` |
| --- | --- | --- |
| **Complexity** | Simple, character-based. | Uses Regular Expressions (Regex). |
| **Performance** | Faster (checks characters one by one). | Slower (compiles and runs regex engine). |
| **Flexibility** | Limited. Can only split by single characters. | Very flexible. Can split by complex patterns (e.g., "digits followed by dot"). |
| **Return Type** | Enumeration of Objects. | Array of Strings `String[]`. |
| **Empty Tokens** | Skips empty tokens by default. | Can preserve empty tokens (e.g., `,,` results in empty string). |

### Example Comparison

```java
String text = "Java,Python,,C++";

// StringTokenizer (Skips the empty value between commas)
// Output: "Java", "Python", "C++"

// String.split(",") (Preserves the empty value)
// Output: "Java", "Python", "", "C++"

```

## 5. Summary & Tips

1. **Path Separators:** When providing file paths in Windows, use forward slashes (`/`) or double backslashes (`\\`). E.g., `C:/Users/Data.txt`.
2. **Legacy:** While `StringTokenizer` is fast, for complex parsing requirements (like validating email formats while splitting), use `String.split()` or the `java.util.Scanner` class.
3. **Delimiters:** Remember that every character in the delimiter string is a separate separator. Passing `"AB"` means "Split by A OR Split by B", not "Split by AB".

---

> **Next Step:** Would you like me to create a "Cheat Sheet" comparing `ArrayList`, `LinkedList`, `HashSet`, and `HashMap`? It acts as a great quick-reference guide for the Collections framework.