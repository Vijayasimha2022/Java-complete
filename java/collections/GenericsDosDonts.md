# Java Generics: Do's and Don'ts

A summary of rules, constraints, and best practices when working with Generics in Java.

## 1. The `extends` Keyword in Definitions

When defining a generic class or interface, you must always use the `extends` keyword to define bounds, even if the bound is an **Interface**.

* **Rule:** The `implements` keyword is **not allowed** in generic parameter definitions.
* **Usage:** `<T extends Class>` or `<T extends Interface>`.

```java
// ✅ CORRECT
class MyClass<T extends Runnable> { } 

// ❌ INCORRECT (Compiler Error)
class MyClass<T implements Runnable> { } 

```

## 2. Multiple Bounds & Order

You can define multiple bounds for a single type parameter (e.g., specific class and interfaces), but strict ordering rules apply.

* **Rule 1:** You can extend only **one class** (Java does not support multiple inheritance of state).
* **Rule 2:** You can extend **multiple interfaces**.
* **Rule 3:** The **Class must come first**, followed by interfaces.

```java
class A {}
interface B {}
interface C {}

// ✅ CORRECT: Class first, then interfaces
class MyArray<T extends A & B & C> { }

// ❌ INCORRECT: Interface first
class MyArray<T extends B & A> { } 

// ❌ INCORRECT: Multiple Classes
class MyArray<T extends A & AnotherClass> { } 

```

## 3. Usage of `super` vs `extends`

There are specific scopes where the `super` keyword is allowed.

* **In Class Definitions:** Only `extends` is allowed. You **cannot** use `super` here.
* **In Method Definitions (Wildcards):** Both `extends` and `super` are allowed.

```java
// ❌ Class Definition: NOT ALLOWED
class MyClass<T super Number> { }

// ✅ Method Definition: ALLOWED
public void method(List<? super Integer> list) { }

```

## 4. Wildcard `<?>` Limitations (Data Access)

When using the wildcards (e.g., `MyClass<?>` or `? extends Number`), the compiler cannot guarantee the specific type of the object.

* **Consequence:** You **cannot modify/add** data to the object (except `null`).
* **Behavior:** It acts strictly as a "Holder" or a reference handle.
* **Analogy:** It is like holding someone's car keys. You hold the keys (reference), but you cannot drive the car (perform operations based on specific types).

```java
public void fun(MyArray<?> obj) {
    // ❌ ERROR: Type is unknown, cannot add generic data
    obj.append("Hello"); 
    
    // ✅ ALLOWED: Null is type-neutral
    obj.append(null); 
}

```

## 5. Strict Type Compatibility

Generic types in Java are invariant. The reference type and the object type must match exactly unless using wildcards.

* **Rule:** `List<Object>` is **not** a superclass of `List<String>`.

```java
// ✅ CORRECT: Types match exactly
MyArray<String> m = new MyArray<String>();

// ❌ INCORRECT: Polymorphism does not work this way with Generics
MyArray<Object> m = new MyArray<String>(); 
MyArray<Number> m = new MyArray<Integer>();

// ✅ CORRECT: Using Wildcard as a holder
// (Note: You face the access limitations mentioned in Point 4)
MyArray<?> m = new MyArray<String>(); 

```