/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_lab1.par;

import java.util.Random;
import static pa_lab1.seq.MainSeq.getRandomArray;
import pa_lab1.utils.Buffer;
import pa_lab1.utils.Reader;
import pa_lab1.utils.Writer;

/**
 *
 * @author Admin
 */
public class MainPar {
    public static void main(String[] args) throws InterruptedException{
        final int N = 10000000;
        int bufSize = 10000000;

        long time = 0;
        for (int i = 0; i < 1; i++)
        {
            Double[] arrayA = getRandomArray(N);
            Double[] arrayB = getRandomArray(N);
            //System.out.println("iteration " + i);
            final Buffer buffer = new Buffer(new Double[bufSize]);
            final Reader r = new Reader(buffer, arrayB);
            final Writer w = new Writer(buffer, arrayA);
            buffer.clear();
            Thread writerThread = new Thread(new Runnable()
            {
                @Override
                public void run(){
                    while (w.canWrite())
                    {
                        int l = buffer.length();
                        int c = buffer.count();
                        if (c < l)
                        {
                            w.write();
                        }
                    }
                }
            });
            Thread readerThread = new Thread(new Runnable()
            {
                @Override
                public void run(){
                    while (r.canRead())
                    {
                        if (buffer.count() != 0)
                        {
                            r.read();
                        }
                    }
                }
            });

            long timeStart = System.currentTimeMillis();

            writerThread.start();
            readerThread.start();
            writerThread.join();
            readerThread.join();
            long timeEnd = System.currentTimeMillis();
            time += timeEnd - timeStart;
        }
        System.out.println("\ntime " + time);
        System.out.println("bufsize " + (bufSize));
        System.out.println("N " + (N));
//        System.out.println("ArrayA " + printArray(arrayA));
//        System.out.println("ArrayB " + printArray(arrayB));
    }
    
    
    public static Double[] getRandomArray(int N){
        Double[] array = new Double[N];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Double(r.nextDouble());
        }
        return array;
    }

    private static String printArray(Double[] arrayB) {
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < arrayB.length; i++) {
            buffer.append(arrayB[i]).append(" ");
        }
        return buffer.toString();
    }
    
}
