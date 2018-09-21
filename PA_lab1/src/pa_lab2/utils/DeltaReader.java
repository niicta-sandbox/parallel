package pa_lab2.utils;

import java.util.Queue;

public class DeltaReader
{
    private Queue<Integer> deltas;

    public DeltaReader(Queue<Integer> deltas){
        this.deltas = deltas;
    }

    public void outputDeltas(){
        int size = deltas.size();
        for (int i = 0; i < size && deltas.peek() >= 0; i++){
            System.out.println(String.format("difference between {0} strings is " + deltas.poll(), i));
        }
        deltas.poll();
        System.out.println("difference between strings count - " + deltas.poll());
    }
}
