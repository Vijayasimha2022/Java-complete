package collections;

class MyArray<T>{

    @SuppressWarnings("unchecked")
    private T[] arr = (T[]) new Object[10];

    private int length =0 ;

    public void append(T value){
        arr[length++] = value;
    }
    public void display(){
        for(int i =0; i<length;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }
}

class MyArray2 extends MyArray{
    // sub types - As no type was specified for MyArray, Java implicitly treats it as type Object.
}

class MyArray3 extends MyArray<String>{
    //sub types - Since String is passed as Generic parameter only MyArray3 can be string arrays
}

class MyArray4<T> extends MyArray<T>{
    //sub types - same type will be initialised as MyArray4 to MyArray
}

public class GenericArray {

    public static void main(String[] args){

        MyArray<Integer> a = new MyArray();
        a.append(5);
        a.append(10);
        a.append(15);
        a.display();


//        No parameter - when no parameter is mentioned for a generic class, Java takes by default as an Object

        MyArray b = new MyArray();

        b.append("Hello");
        b.append("Bro");
        b.append(1);
        b.display();

//        Multiple parameters - java allows multiple paramters Eg: <V,K>, used mainly for key value pairs, Eg : Map

//        sub types

//        Bounded types




    }

}
