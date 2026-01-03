package collections;

import java.util.ArrayList;

public class ArrayListDemo {

    public static void main(String[] args){

        /*
        list1-emptylist
         */

        //Resizable-array implementation of the List interface.
        ArrayList<Integer> list1 = new ArrayList<>();// Constructs an empty list with an initial capacity of ten.

        ArrayList<Integer> list2 = new ArrayList<>(15);//Constructs an empty list with the specified initial capacity.

        list2.add(1);//Autoboxing
        list2.add(2);//Appends the specified element to the end of this list.
        list2.add(3);

        ArrayList<Integer> list3 = new ArrayList<>(list2); //Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
        //NullPointerException - if the specified collection is null

        System.out.println("size of List 3 : "+ list3.size()); //Returns the number of elements in this list.

        ArrayList<Integer> list4 = new ArrayList<>(list1);//adding emptylist
        System.out.println("size of List 4 : "+ list4.size());

        //ArrayList Methods
        //Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
        // IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
        list2.add(2,5);
        show(list2);

        //Appends all of the elements in the specified collection to the end of this list,
        //Returns: true if this list changed as a result of the call
        //NullPointerException - if the specified collection is null
        System.out.println(list2.addAll(list3));
        show(list2);

        System.out.println("Adding empty list to list 2 : "+list2.addAll(list1));

        //public boolean addAll(int index,Collection<? extends E> c)
        //Inserts all of the elements in the specified collection into this list, starting at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right
        //IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
        //NullPointerException - if the specified collection is null
        list2.addAll(1,list3);
        show(list2);

        //Removes all of the elements from this list. The list will be empty after this call returns.
        System.out.println("After clearing the list :");
        list2.clear();
        show(list2);

        //Compares the specified object with this list for equality.
        // Returns true if and only if the specified object is also a list, both lists have the same size,
        // and all corresponding pairs of elements in the two lists are equal.
        // (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).)
        // In other words, two lists are defined to be equal if they contain the same elements in the same order.
        System.out.println("comparing list2 with list1 : "+list2.equals(list1));
        System.out.println("comparing list1 with list1 : "+list1.equals(list1));
        list2.add(1);
        System.out.println("comparing list2 with list1 : "+list2.equals(list1));

        //Returns true if this list contains no elements.
        System.out.println("is the list1 empty : "+ list1.isEmpty());
        System.out.println("is the list2 empty : "+ list2.isEmpty());

        //Returns true if this list contains the specified element. More formally, returns true if and only if this list contains at least one element e such that Objects.equals(o, e).
        System.out.println("Does list2 contain 1 : "+list2.contains(1));
        System.out.println("Does list1 contain 1 : "+list1.contains(1));

        //Returns the index of the first occurrence of the specified element in this list, or
        // -1 if this list does not contain the element.
        // More formally, returns the lowest index i such that Objects.equals(o, get(i)), or -1 if there is no such index.
        list2.add(1);
        list2.add(2);
        System.out.println("index of '1' in list1 : "+list1.indexOf(1));
        System.out.println("index of '1' in list2 : "+list2.indexOf(1));

        //Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
        //More formally, returns the highest index i such that Objects.equals(o, get(i)), or -1 if there is no such index.
        list2.add(6);
        list2.add(7);
        list2.add(8);
        System.out.print("list2 : ");show(list2);
        System.out.println("last index of '1' in list1 : "+list1.lastIndexOf(1));
        System.out.println("last index of '1' in list2 : "+list2.lastIndexOf(1));

        //Returns the element at the specified position in this list.
        //IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
        System.out.println("Element at index 2 in list2 : "+list2.get(2));

        //Replaces the element at the specified position in this list with the specified element.
        //IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
        //Returns : the element previously at the specified position
        System.out.println("the element is going to replace is : " + list2.set(2,100));
        System.out.print("list2 : ");show(list2);

        //Removes the element at the specified position in this list.
        //Shifts any subsequent elements to the left (subtracts one from their indices).
        //IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
        //Returns: the element that was removed from the list
        System.out.println("removing the element ob index 2 and element is : "+list2.remove(2));
        System.out.print("list2 : ");show(list2);

        //Removes the first occurrence of the specified element from this list, if it is present.
        //If the list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that Objects.equals(o, get(i)) (if such an element exists).
        // Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
        list2.add(7);
        System.out.print("list2 : ");show(list2);
        System.out.println("removing the value 7 from list 2 "+list2.remove(Integer.valueOf(7)));
        System.out.print("list2 : ");show(list2);

        //Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's Iterator.
        //The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
        //NullPointerException - if the specified collection is null
        //Returns : true if this list changed as a result of the call
        System.out.print("list3 : ");show(list3);
        System.out.println(list2.addAll(list3));
        System.out.print("list2 : ");show(list2);

        //Inserts all of the elements in the specified collection into this list, starting at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices).
        // The new elements will appear in the list in the order that they are returned by the specified collection's iterator.
        //IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
        //NullPointerException - if the specified collection is null
        //Returns : true if this list changed as a result of the call
        System.out.println(list2.addAll(2,list3));
        System.out.print("list2 : ");show(list2);

        //Removes from this list all of its elements that are contained in the specified collection.
        //returns : true if this list changed as a result of the call
        //ClassCastException - if the class of an element of this list is incompatible with the specified collection (optional)
        //NullPointerException - if this list contains a null element and the specified collection does not permit null elements (optional), or if the specified collection is null
        //-------------- list2.removeAll(list3);
        System.out.print("list2 : ");show(list2);

        //Retains only the elements in this list that are contained in the specified collection.
        // In other words, removes from this list all of its elements that are not contained in the specified collection.
        //returns : true if this list changed as a result of the call
        //ClassCastException - if the class of an element of this list is incompatible with the specified collection (optional)
        //NullPointerException - if this list contains a null element and the specified collection does not permit null elements (optional), or if the specified collection is null
        list2.retainAll(list3);
        System.out.print("list2 : ");show(list2);

        //Traversing list
        //for each
        for(Integer i : list2){
            System.out.print(i +" ");
        }
        System.out.println();

        //Traversing list
        //for each using var
        for(var i : list2){
            System.out.print(i +" ");
        }
        System.out.println();





        //public Iterator<E> iterator()

        //public ListIterator<E> listIterator()
        //


        //public ListIterator<E> listIterator(int index)
        //Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list. The specified index indicates the first element that would be returned by an initial call to next.
        // An initial call to previous would return the element with the specified index minus one.
        //a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
        //IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())


    }

    static <T>void show(ArrayList<T> list){

        //Traversing list
        //for loop
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i) +" ");
        }
        System.out.println();


    }
}
