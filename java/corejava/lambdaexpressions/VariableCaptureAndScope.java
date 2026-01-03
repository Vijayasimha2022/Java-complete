package corejava.lambdaexpressions;

public class VariableCaptureAndScope {


    int instanceVar =0;

    public void demo(){

        int methodLocalVar = 1;

        TaskRunner tr = ()->{
            int localVar =2;

            System.out.println("local variable inside lamda : "+ (++localVar));

            System.out.println("Method local variable : "+ (methodLocalVar));

            System.out.println("Instance local Variable : "+ (++instanceVar));
        };

        //methodLocalVar++;
        // ERROR: Local variable defined in an enclosing scope
        //must be final or effectively final
        // Even modifying it outside the lambda afterwards causes an error

        tr.execute();
    }

    public static void main(String[] args){
        VariableCaptureAndScope obj = new VariableCaptureAndScope();
        obj.demo();


    }
}
