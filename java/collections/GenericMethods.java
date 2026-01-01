package collections;

public class GenericMethods {

    static <T> void show( T[] list){
        for(T i:list ){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    static <T> void show1( T... list){
        for(T i:list ){
            System.out.print(i+" ");
        }
        System.out.println();
    }

//    bounded type genric methods
//    only classess that extends from Number allowed
    static <T extends Number> void show2( T... list){
        for(T i:list ){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void main(String [] args){
        MyArray<Integer> arr = new MyArray();
        arr.append(5);
        arr.append(6);
        arr.append(7);

        show(new String[]{"hello","bye"});
        show(new Integer[]{1,2,3,4});
//        show(arr);

//        varaible arguments

        show1(1,2,3,4,5);

//        bounded type
        show2(1,2,3,4);
        show2(5.2f,3.4f);
//        show2("hello","bye");

//        Generic parameters -    wild card ?, lower bound (extends) , upperbound(super)



    }
}
