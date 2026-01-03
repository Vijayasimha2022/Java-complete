package corejava.lambdaexpressions;

//If a method takes a Functional Interface as a parameter, you don't need to create a class or an object manually.
// You just pass the Lambda expression that defines the logic.

public class PassingLamdaExpression {

    public void callingExecution(TaskRunner ex){
        ex.execute();
    }

    public static void main(String[] args){
        PassingLamdaExpression obj = new PassingLamdaExpression();
        obj.callingExecution(()->System.out.println("Executing.........."));

    }

}
