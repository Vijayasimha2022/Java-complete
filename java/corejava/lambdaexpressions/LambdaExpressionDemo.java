package corejava.lambdaexpressions;

@FunctionalInterface
interface TaskRunner{
    public void execute();
}

@FunctionalInterface
interface Sum{
     int sum(int a,int b);
}

class TaskImpl implements TaskRunner{

    public void execute(){
        System.out.println("Executing task via Concrete Class...");
    }
}

public class LambdaExpressionDemo {
    public static void main(String [] args){

        TaskImpl ti = new TaskImpl();
        ti.execute();

        TaskRunner tr = new  TaskRunner(){
          public void execute(){
              System.out.println("Executing task via Anonymous Class...");
          }
        };
        tr.execute();

        TaskRunner tl = ()->{
            System.out.println("Executing task via Lambda!");
        };
        tl.execute();

        TaskRunner runner = ()->System.out.println("single line lamda task runner");
        runner.execute();

        Sum c = (a,b)->a+b;

        Sum d = (a,b)->{return a+b;};

        System.out.println(c.sum(1,2));
        System.out.println(c.sum(3,4));

    }
}
