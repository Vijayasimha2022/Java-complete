package collections;

class Data<T>{
    private Object data;

    public void setData(T dt){
        data = dt;
    }

    public T getData()
    {
        return (T)data;
    }
}

public class GenericClass {

    public static void main(String [] args){

        Data<Integer>  d = new Data();
        d.setData(5);
        System.out.println(d.getData());

//        d.setData("code"); //Incompatible types


    }
}
