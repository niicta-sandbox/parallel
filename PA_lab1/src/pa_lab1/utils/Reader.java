/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_lab1.utils;

/**
 *
 * @author Admin
 */
public class Reader<E> {
    private Buffer<E> buffer;
    private E[] arrayB;
    private int i;

    public Reader(Buffer<E> buffer, E[] arrayB) {
        this.buffer = buffer;
        this.arrayB = arrayB;
        i = 0;
    }     
    
    public boolean canRead(){
        if (i < arrayB.length) {
            return true;
        }
        return false;
    }
       
    public boolean read(){
        if (i >= arrayB.length) {
            return false;
        }
        if (buffer.count() > 0) {
            arrayB[i] = buffer.get();
            i++;
        }
        return true;
    }    
}
