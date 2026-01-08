# The Properties Class in Java

## 1. Introduction

The `Properties` class is a unique and very interesting utility in Java. While it shares many characteristics with the `HashMap` and `Hashtable` families, it has a specific constraint that makes it ideal for configuration settings.

* **Inheritance:** It inherits directly from **`Hashtable`**.
* **Interface:** It implements the **`Map`** interface.
* **The Key Difference:** Unlike a standard `Hashtable` where you can store any object as a key or value, the `Properties` class is designed strictly for **Strings**. Both the **Key** and the **Value** must be Strings.

This class is historically used to manage configuration files (like `database.properties` or `application.properties`) where settings are stored as text.

---

## 2. Basic Operations: Setting and Getting

We use `setProperty()` to add data and `getProperty()` to retrieve it. This is analogous to `put()` and `get()` in a Map, but type-safe for Strings.

### Example: Creating a Configuration Object

Let's create a `Properties` object to store some hypothetical database configurations.

```java
import java.util.Properties;

public class ConfigDemo {
    public static void main(String[] args) {
        // 1. Create the Properties object
        Properties dbConfig = new Properties();

        // 2. Set properties (Key, Value) - Both must be Strings
        dbConfig.setProperty("database.url", "jdbc:mysql://localhost:3306/mydb");
        dbConfig.setProperty("database.user", "admin_user");
        dbConfig.setProperty("database.password", "securePass123");
        dbConfig.setProperty("connection.timeout", "5000");

        // 3. Retrieve and print a specific property
        String url = dbConfig.getProperty("database.url");
        System.out.println("Connecting to: " + url);

        // 4. Print all properties to console
        System.out.println("Full Config: " + dbConfig);
    }
}

```

---

## 3. Persisting Data: Storing to Files

One of the most powerful features of this class is its ability to save the data to a file effortlessly. You can save it as a standard `.properties` text file or as an `.xml` file.

### A. Storing as a Standard Property File

We use the `store()` method. (Note: Avoid the deprecated `save()` method).

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SaveProps {
    public static void main(String[] args) throws IOException {
        Properties appSettings = new Properties();
        appSettings.setProperty("theme", "Dark");
        appSettings.setProperty("window.size", "1920x1080");

        // Storing to a .properties file
        // We use a FileOutputStream pointing to the desired file name
        FileOutputStream fos = new FileOutputStream("app-settings.properties");
        
        // The second argument is a comment header that appears at the top of the file
        appSettings.store(fos, "User Interface Configurations");
        
        fos.close();
        System.out.println("Settings saved successfully.");
    }
}

```

**Output File Content (`app-settings.properties`):**

```properties
#User Interface Configurations
#Wed Jan 08 12:00:00 IST 2026
theme=Dark
window.size=1920x1080

```

### B. Storing as an XML File

We can also export the same data into a standardized XML format using `storeToXML()`. Java automatically handles the Document Type Definition (DTD) validation.

```java
// Storing to an .xml file
FileOutputStream xmlFos = new FileOutputStream("app-settings.xml");
appSettings.storeToXML(xmlFos, "UI Configs in XML Format");
xmlFos.close();

```

**Output File Content (`app-settings.xml`):**

```xml
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
<comment>UI Configs in XML Format</comment>
<entry key="theme">Dark</entry>
<entry key="window.size">1920x1080</entry>
</properties>

```

---

## 4. Loading Data: Reading from Files

Just as we can write to files, we can load configuration data from them into our Java program. This is how real-world applications read startup settings.

### A. Loading from XML

Let's assume we have an external XML file named `server-config.xml` containing server details.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadXmlDemo {
    public static void main(String[] args) throws IOException {
        Properties serverProps = new Properties();

        // Use FileInputStream to read the file
        FileInputStream fis = new FileInputStream("server-config.xml");

        // Load data from XML structure
        serverProps.loadFromXML(fis);

        // Now we can use the data in our Java application
        System.out.println("Server Name: " + serverProps.getProperty("server.name"));
        System.out.println("Server IP: " + serverProps.getProperty("server.ip"));
        
        fis.close();
    }
}

```

### B. Loading from Standard Property File

If we have a standard text file (key=value format), we use the `load()` method.

```java
Properties simpleProps = new Properties();
FileInputStream simpleFis = new FileInputStream("app-settings.properties");

// Load standard key=value pairs
simpleProps.load(simpleFis);

// Iterate and print all keys using stringPropertyNames() (Advanced Iteration)
for (String key : simpleProps.stringPropertyNames()) {
    System.out.println(key + " : " + simpleProps.getProperty(key));
}

```

---

## 5. Summary & Key Methods

| Method | Description |
| --- | --- |
| `setProperty(String key, String value)` | Inserts a key-value pair (Strings only). |
| `getProperty(String key)` | Retrieves the value for the specified key. |
| `store(OutputStream out, String comments)` | Writes the properties to a text stream (standard format). |
| `storeToXML(OutputStream os, String comment)` | Writes the properties to an XML file with DTD validation. |
| `load(InputStream inStream)` | Reads a property list from a byte stream. |
| `loadFromXML(InputStream in)` | Reads a property list from an XML document. |

### Advanced Concept: Why use `Properties` instead of `HashMap`?

While you could technically use a `HashMap<String, String>` to store this data, `Properties` provides the built-in capability to **persist** (save/load) the data to disk easily.

* **HashMap:** Good for temporary, in-memory data.
* **Properties:** Good for permanent configuration data that needs to be read from or written to a file.

---

Here is a practical, real-world example note demonstrating how the `Properties` class is often used in application development.

# Real World Scenario: Simple Authentication System

## 1. The Concept

In many applications, we need to validate user credentials or read system-critical secrets (like API keys) without hardcoding them directly into the Java source code. Hardcoding is bad practice because changing a password would require recompiling the entire code.

Instead, we use a **Properties file**. This allows us to change user details or configuration settings externally without touching the code.

## 2. Step 1: The Configuration File

First, imagine we have a file named `users.properties` stored in our project folder. This file acts as our simple database.

**File:** `users.properties`

```properties
# User Credentials Database
# Format: username=password
admin=SuperSecret123
support_team=HelpDesk2026
guest=GuestPass!

```

## 3. Step 2: The Java Implementation

We will write a program that loads this file at startup and checks if the user's input matches the stored credentials.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Properties userDatabase = new Properties();
        
        // 1. Load the credentials from the external file
        try (FileInputStream fis = new FileInputStream("users.properties")) {
            userDatabase.load(fis);
        } catch (IOException e) {
            System.out.println("Error: Could not load user database.");
            return; 
        }

        // 2. Get Input from User
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Username: ");
        String inputUser = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String inputPass = scanner.nextLine();

        // 3. Validation Logic
        // We use getProperty() to find the password associated with the typed username.
        String storedPassword = userDatabase.getProperty(inputUser);

        if (storedPassword != null && storedPassword.equals(inputPass)) {
            System.out.println("✅ Login Successful! Welcome, " + inputUser);
        } else {
            System.out.println("❌ Access Denied. Invalid username or password.");
        }
        
        scanner.close();
    }
}

```

## 4. Why this approach is better

1. **Flexibility:** If the `admin` wants to change their password to `NewPass999`, they only edit the text file. The Java code remains untouched.
2. **Security (Basic):** Credentials are not visible in the compiled `.class` files.
3. **Portability:** You can have different property files for different environments (e.g., `dev_users.properties` vs `prod_users.properties`).

## ⚠️ Important Security Note

While `Properties` files are excellent for configuration (like URLs, timeouts, or UI settings), storing **passwords in plain text** (as shown above) is not secure for production enterprise applications.

In a professional environment:

* Passwords in the database/file should be **Hashed** (encrypted).
* The Java program would hash the user's input and compare the *hashes*, not the raw strings.

---

