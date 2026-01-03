# 1.Introduction to Lambda Expressions

Lambda expressions are essentially used to define **anonymous methods** (nameless methods or functions). To understand how they work, we first need to understand their relationship with Interfaces.

I will walk you through the evolution of how we implement interfaces in Java, starting from the traditional approach, moving to anonymous classes, and finally arriving at Lambda expressions. This comparative approach will help you comfortably grasp the concept.

## 1. The Functional Interface

First, let's define an interface. For this example, we will create an interface called `TaskRunner`. It has a single method called `execute()`.

```java
@FunctionalInterface
interface TaskRunner {
    public void execute();
}

```

**Key Concepts:**

* By default, methods inside an interface are `public` and `abstract`.
* **Functional Interface:** If an interface contains **exactly one abstract method**, it is called a Functional Interface.
* **@FunctionalInterface Annotation:** While optional, adding this annotation is good practice. It tells the compiler to check that the interface has only one abstract method.
* If you try to add a second abstract method while this annotation is present, the compiler will throw an error.
* This single-method rule is compulsory for Lambda expressions to work.



## 2. Approach 1: The Traditional Implementation

Normally, when we have an interface, we create a concrete class to implement it and override its method.

```java
// 1. Define the implementation class
class TaskImpl implements TaskRunner {
    @Override
    public void execute() {
        System.out.println("Executing task via Concrete Class...");
    }
}

public class Main {
    public static void main(String[] args) {
        // 2. Create an object of the class
        TaskRunner runner = new TaskImpl();
        
        // 3. Call the method
        runner.execute();
    }
}

```

This works perfectly. The reference `TaskRunner` holds the object of `TaskImpl`. However, this requires writing a whole separate class just to define one method.

## 3. Approach 2: Anonymous Inner Class

We can make this slightly more concise. Instead of creating a separate file or class called `TaskImpl`, we can use an **Anonymous Inner Class**. We define the implementation right where we instantiate it.

```java
public class Main {
    public static void main(String[] args) {
        
        // Defining an anonymous inner class
        TaskRunner runner = new TaskRunner() {
            @Override
            public void execute() {
                System.out.println("Executing task via Anonymous Class...");
            }
        };

        runner.execute();
    }
}

```

Here, we are technically creating an object of an interface by providing the implementation immediately. This saves us from naming a new class, but the code is still quite verbose.

## 4. Approach 3: Lambda Expressions

Now, we are very near to the Lambda expression. Let's convert the Anonymous Inner Class into a Lambda.

The compiler already knows:

1. The interface is `TaskRunner`.
2. The method must be `execute()`.
3. The method takes no arguments.

Because there is only **one** method in a Functional Interface, there is no confusion. We can remove the boilerplate code.

### The Transformation Steps:

1. Remove `new TaskRunner()`.
2. Remove the method name `public void execute`.
3. Remove the outer braces.
4. Add the arrow operator `->`.

### The Resulting Code:

```java
public class Main {
    public static void main(String[] args) {
        
        // Lambda Expression
        // () represents the empty arguments of execute()
        // -> represents the link to the method body
        TaskRunner runner = () -> {
            System.out.println("Executing task via Lambda!");
        };

        runner.execute();
    }
}

```

We can simplify this even further. Since there is only one line of code in the method body, we can remove the curly braces `{}`.

```java
TaskRunner runner = () -> System.out.println("Executing task via Lambda!");

```

### Analysis

* **Reference:** `TaskRunner runner`
* **Object:** The lambda expression `() -> System.out.println(...)` acts as the object implementation.
* **Call:** When we write `runner.execute()`, the program runs the code defined in the lambda expression.

## Summary

To make Functional Interfaces easy to implement, Java introduced Lambda expressions. They are powerful, handy, and drastically reduce the amount of boilerplate code programmers need to write.

Instead of writing a lengthy class or an anonymous inner class just to print a single line or perform a small calculation, we can treat code as data.

**Key Syntax:**

```java
(parameter_list) -> { body }

```
---
# 2.Lambda Expressions: Parameters and Return Types

Lets expand our understanding of Lambda expressions by learning how to handle **parameters** and **return values**.

## 1. Handling a Single Parameter

Let's modify our functional interface to accept an argument. We will create an interface called `StringHandler` that has a method to process a generic string.

### The Interface

```java
@FunctionalInterface
interface StringHandler {
    // This method takes one String argument
    public void process(String str);
}

```

### The Lambda Implementation

When defining the lambda, we need to pass a parameter variable. Let's call it `s`.

```java
public class Main {
    public static void main(String[] args) {
        
        // Define the Lambda
        // 's' is automatically inferred as a String because 
        // the interface method 'process(String str)' expects a String.
        StringHandler handler = (s) -> {
            System.out.println("Processing: " + s);
        };

        // Call the method
        handler.process("Hello World");
        handler.process("Java Programming");
    }
}

```

**Key Observation:**

* We did not write `(String s)`. The compiler looks at the interface `StringHandler`, sees that `process` takes a `String`, and automatically assumes `s` is of type `String`.
* If you have only one parameter, you can even remove the parentheses around `s`.
* `s -> System.out.println(s);` is valid.



## 2. Multiple Parameters and Return Values

Now, let's look at a scenario where a method takes multiple parameters and returns a result. We will define an interface called `MathOperation`.

### The Interface

```java
@FunctionalInterface
interface MathOperation {
    // Takes two integers and returns an integer
    public int calculate(int x, int y);
}

```

### The Lambda Implementation (Explicit Return)

First, let's write this the "standard" way using a return statement.

```java
public class Main {
    public static void main(String[] args) {
        
        // Lambda taking two arguments (a, b)
        // We use curly braces to define a block of code
        MathOperation adder = (a, b) -> {
            return a + b;
        };

        int result = adder.calculate(20, 30);
        System.out.println("Result: " + result); // Output: 50
    }
}

```

### The Lambda Implementation (Implicit Return)

The true power of Lambda expressions comes from their ability to be concise. Since `a + b` is a single expression, we can simplify the code further.

If the body of your lambda is a single expression, you can:

1. Remove the curly braces `{}`.
2. **Remove the `return` keyword.**

The compiler understands that the result of `a + b` should be returned automatically.

```java
public class Main {
    public static void main(String[] args) {
        
        // Concise version
        // No 'return' keyword needed. No curly braces needed.
        MathOperation adder = (a, b) -> a + b;

        int result = adder.calculate(20, 30);
        System.out.println("Result: " + result); 
    }
}

```

**Why is it called a Lambda "Expression"?**
In the example `(a, b) -> a + b`, the code on the right side (`a + b`) is just a mathematical **expression**. This is why the name fits so well—you are writing expressions that act like methods.

### Summary of Syntax Rules

| Lambda Type | Syntax Example | Notes |
| --- | --- | --- |
| **No Args** | `() -> System.out.println("Hi");` | Empty parentheses required. |
| **One Arg** | `s -> System.out.println(s);` | Parentheses around `s` are optional. |
| **Multiple Args** | `(x, y) -> x + y;` | Parentheses required. |
| **Block Body** | `(x, y) -> { return x + y; };` | Braces `{}` and `return` keyword required together. |
| **Expression Body** | `(x, y) -> x + y;` | No braces, no `return` keyword allowed. |

# 3.Lambda Expressions: Scope and Passing as Parameters

We will answer two important questions:

1. **Variable Scope:** Can Lambdas access variables from their surroundings (local variables, instance variables)? Can they modify them?
2. **Passing Lambdas:** Can we pass a Lambda expression as an argument to a method?

For this explanation, we will move away from the `static main` method and use a standard class structure to better illustrate how instance variables work.

## 1. Variable Capture and Scope

Let's define our setup. We have a functional interface called `Printer` and a class called `ScopeDemo` to test our code.

```java
@FunctionalInterface
interface Printer {
    void print();
}

public class ScopeDemo {
    // 1. Instance Variable
    int instanceVar = 10; 

    public void demonstrateScope() {
        
        // 2. Method-Local Variable
        int localMethodVar = 0;

        // Defining the Lambda Expression
        Printer p = () -> {
            
            // 3. Lambda-Local Variable
            int x = 100; 
            System.out.println("Lambda Local: " + x);
            
            // Accessing Method-Local Variable?
            System.out.println("Method Local: " + localMethodVar);
            
            // Accessing Instance Variable?
            System.out.println("Instance Var: " + instanceVar);
        };
        
        p.print();
    }
    
    public static void main(String[] args) {
        ScopeDemo demo = new ScopeDemo();
        demo.demonstrateScope();
    }
}

```

### Rule 1: Variables Inside the Lambda

Can we declare variables *inside* the Lambda body?
**Yes.** Just like any other method, you can declare local variables (like `int x = 100` above). Their scope is limited to the Lambda block.

### Rule 2: Accessing Method-Local Variables (Capture)

Can the Lambda access `localMethodVar` declared outside it but inside the same method?
**Yes, but with a condition.**

The variable must be **Final** or **Effectively Final**.

* **Effectively Final** means: You didn't write the keyword `final`, but you never changed the value of the variable after initializing it.

**What happens if we try to modify it?**

```java
public void demonstrateScope() {
    int count = 0;
    
    Printer p = () -> {
        // ERROR: Local variable defined in an enclosing scope 
        // must be final or effectively final
        // count++; 
        System.out.println("Hi");
    };
    
    // Even modifying it outside the lambda afterwards causes an error
    // count++; 
}

```

* **Why?** A Lambda captures the *value* of the local variable, not the variable itself. If the variable changes, the Lambda would hold outdated data, so Java prevents this to ensure thread safety and consistency.

### Rule 3: Accessing Instance Variables

Can the Lambda access and modify `instanceVar` belonging to the class?
**Yes, absolutely.**

```java
Printer p = () -> {
    // This is valid!
    // We can read AND modify instance variables.
    instanceVar++; 
    System.out.println("Instance Var modified: " + instanceVar);
};

```

* **Why?** The Lambda expression has access to `this` (the instance of `ScopeDemo`). Since `instanceVar` belongs to the object, the Lambda can see and change it freely.

### Summary of Scope

| Variable Type | Access inside Lambda? | Can Modify inside Lambda? |
| --- | --- | --- |
| **Lambda Local** | Yes | Yes |
| **Method Local** | Yes | **No** (Must be effectively final) |
| **Instance Field** | Yes | **Yes** |

## 2. Passing Lambda Expressions as Parameters

Now for the next major concept. Since a Lambda expression is essentially an implementation of a Functional Interface, we can pass it as an object to other methods.

This allows us to treat **code as data**. We can pass logic (a method) into another method.

### The Setup

Let's create a utility class called `LambdaExecutor` that accepts our `Printer` interface as a parameter.

```java
class LambdaExecutor {
    // This method expects an implementation of Printer
    public void executePrinter(Printer p) {
        // It calls the method defined in the interface
        p.print();
    }
}

```

### Passing the Lambda

Now, in our main code, we don't need to create a variable for the Lambda first. We can pass the Lambda expression **directly** into the method call.

```java
public class ScopeDemo {
    public void demonstratePassing() {
        LambdaExecutor executor = new LambdaExecutor();

        // SCENARIO: Passing logic as a parameter
        // We are passing the BEHAVIOR (printing "Hello") to the executor
        executor.executePrinter(() -> {
            System.out.println("Hello from passed Lambda!");
        });

        // We can pass different behavior to the same method easily
        executor.executePrinter(() -> {
            System.out.println("Java is flexible.");
        });
    }
    
    public static void main(String[] args) {
        ScopeDemo demo = new ScopeDemo();
        demo.demonstratePassing();
    }
}

```

### Why is this useful?

This pattern is extremely common in modern Java. If a method takes a Functional Interface as a parameter, you don't need to create a class or an object manually. You just pass the Lambda expression that defines the logic.

**Visualizing the Flow:**

1. `executor.executePrinter(...)` is called.
2. The Lambda `() -> System.out.println(...)` is passed as the object `p`.
3. Inside `executePrinter`, `p.print()` is called.
4. The code inside your Lambda executes.

---

# 4. Method References in Java

In this video, we will explore a concise shorthand syntax known as **Method References**.

Just like we can assign a Lambda expression to a Functional Interface, we can also assign a direct reference to an existing method. This allows us to reuse methods that already exist without rewriting them as Lambdas.

## The Concept

A Functional Interface has a single abstract method. We can "wire" that abstract method to an existing method in your code. When you call the interface method, it internally executes the referenced method.

We use the double colon operator `::` for this purpose.

## 1. Reference to a Static Method

Let's start with a simple interface called `StringPrinter`.

```java
@FunctionalInterface
interface StringPrinter {
    void print(String s);
}

```

We can assign the standard `System.out.println` method to this interface because `println` is a static method (conceptually similar usage here) that matches the signature (takes a String, returns void).

```java
public class MethodRefDemo {
    
    // A custom static method
    public static void reverse(String s) {
        StringBuffer sb = new StringBuffer(s);
        System.out.println(sb.reverse());
    }

    public static void main(String[] args) {
        
        // 1. Assigning System.out.println
        // Syntax: ClassName::MethodName (or Object::MethodName for println)
        StringPrinter printer = System.out::println;
        printer.print("Hello"); // Prints: Hello

        // 2. Assigning our custom static method 'reverse'
        // Syntax: ClassName::staticMethodName
        printer = MethodRefDemo::reverse;
        printer.print("Hello"); // Prints: olleH
    }
}

```

**Key Takeaway:** The single reference `printer` acted as a standard printer first, and then as a reverser. We achieved polymorphism—changing behavior dynamically—with very little code.

## 2. Reference to an Instance Method (Non-Static)

If the method you want to use is **not** static, you cannot reference it via the Class name. You must have an object instance first.

```java
public class MethodRefDemo {
    
    // Non-static method
    public void convertToUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    public static void main(String[] args) {
        
        // Step 1: Create the object
        MethodRefDemo demoObj = new MethodRefDemo();
        
        // Step 2: Reference the method on that specific object
        // Syntax: objectName::methodName
        StringPrinter printer = demoObj::convertToUpperCase;
        
        printer.print("hello"); // Prints: HELLO
    }
}

```

## 3. Reference to a Constructor

We can even assign a **Constructor** to a functional interface. This is useful when you want a factory-like pattern.

Let's say our interface expects to take a String. If we map it to a constructor, calling the interface method will instantiate a new object.

```java
public class MethodRefDemo {
    
    // Constructor taking a String
    public MethodRefDemo(String s) {
        System.out.println("Constructor called with: " + s.toUpperCase());
    }

    public static void main(String[] args) {
        
        // Syntax: ClassName::new
        StringPrinter printer = MethodRefDemo::new;
        
        // Calling print() actually triggers the constructor!
        printer.print("hello"); 
    }
}

```

## 4. Reference to an Instance Method of an Arbitrary Object

This is a slightly more advanced but very common scenario, especially with comparisons.

Imagine we have an interface that takes **two** parameters.

```java
@FunctionalInterface
interface StringComparator {
    int compare(String s1, String s2);
}

```

We want to use the `compareTo` method from the `String` class.

* `compareTo` is technically an instance method: `s1.compareTo(s2)`.
* However, we can reference it using the Class name `String::compareTo`.

**How does this work?**
Java maps the **first parameter** (`s1`) as the object invoking the method, and the **second parameter** (`s2`) as the argument passed to that method.

```java
public class Main {
    public static void main(String[] args) {
        
        // Syntax: ClassName::InstanceMethodName
        // Logic: (s1, s2) -> s1.compareTo(s2)
        StringComparator comp = String::compareTo;
        
        int result = comp.compare("Hello", "World");
        
        System.out.println(result); // Output: Negative number (H comes before W)
    }
}

```

## Summary Table

| Reference Type | Syntax | Lambda Equivalent |
| --- | --- | --- |
| **Static Method** | `Class::staticMethod` | `(x) -> Class.staticMethod(x)` |
| **Instance Method (Specific Object)** | `obj::method` | `(x) -> obj.method(x)` |
| **Constructor** | `Class::new` | `(x) -> new Class(x)` |
| **Instance Method (Arbitrary Object)** | `Class::method` | `(a, b) -> a.method(b)` |

Method references allow for compact, readable code. By dynamically assigning different methods to an interface reference, we achieve a lightweight form of polymorphism.
