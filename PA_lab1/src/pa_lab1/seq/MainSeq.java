/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_lab1.seq;

import java.util.Random;
import pa_lab1.utils.Buffer;
import pa_lab1.utils.Reader;
import pa_lab1.utils.Writer;

/**
 *
 * @author Admin
 */
public class MainSeq {
    
    public static void main(String[] args) {
        int N = 10000000;
        int bufSize = 10000000;
        Double[] arrayA = getRandomArray(N);
        Double[] arrayB = getRandomArray(N);
        Buffer buffer = new Buffer(new Double[bufSize]);
        Reader r = new Reader(buffer, arrayB);
        Writer w = new Writer(buffer, arrayA);
        long timeStart = System.currentTimeMillis();
        while(w.canWrite()){
            for (int i = 0; i < bufSize - 1 && w.canWrite(); i++) {
                w.write();
            }
            for (int i = 0; i < bufSize - 1 && r.canRead(); i++){
                r.read();
            }
        }  
        long timeEnd = System.currentTimeMillis();
        System.out.println("time " + (timeEnd - timeStart));
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
