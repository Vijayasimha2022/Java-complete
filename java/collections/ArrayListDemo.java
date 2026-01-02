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

        System.out.println("size of List 3 : "+ list3.size());

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

        





    }

    static <T>void show(ArrayList<T> list){
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i) +" ");
        }
        System.out.println();
    }
}
