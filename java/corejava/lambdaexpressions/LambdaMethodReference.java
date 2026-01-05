package corejava.lambdaexpressions;

@FunctionalInterface
interface MessagePrinter{
   void printer(String msg);
}

@FunctionalInterface
interface Twoparameters{
    int compareTo(String str1,String str2);
}

public class LambdaMethodReference {

    public LambdaMethodReference(String str){
        System.out.println(str +" length "+ str.length());
    }

    public LambdaMethodReference(){}

    public void reverse(String str){
        System.out.println(str.toUpperCase());
    }

    public static void main(String[] args){

        MessagePrinter mp = System.out::println;;
        mp.printer("static method assign");

        LambdaMethodReference obj = new LambdaMethodReference();
        MessagePrinter ns = obj::reverse;
        ns.printer("instance method reference");

        //Constructor reference to lambda expressions
        MessagePrinter cr = LambdaMethodReference::new;
        cr.printer("Constructor reference");

        Twoparameters tp = String::compareTo;
        int comparing= tp.compareTo("hello","hello");
        System.out.println(comparing);
    }
}
