package collections.priorityqueue;


import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(5);pq.add(10);pq.add(2);pq.add(63);pq.add(2);

//        System.out.print(pq.element());

        displayPriorityQueue(pq);

        System.out.println("Removing the priority element : "+ pq.poll());

        displayPriorityQueue(pq);

        PriorityQueue<Integer> pq1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2){return -1;}
                if(o1<o2){return 1;}
                return 0;
            }
        });

        pq1.add(5);pq1.add(10);pq1.add(2);pq1.add(63);pq1.add(2);
        displayPriorityQueue(pq1);

    }

    public static <T> void displayPriorityQueue(PriorityQueue<T> pQueue){
        pQueue.forEach(i->System.out.print(i+" "));
        System.out.println();
    }
}

