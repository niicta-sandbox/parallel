/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_lab1.utils;

/**
 *
 * @author Admin
 */
public class Writer<E> {
    private Buffer<E> buffer;
    private E[] arrayA;
    private int i;

    public Writer(Buffer<E> buffer, E[] arrayA) {
        this.buffer = buffer;
        this.arrayA = arrayA;
        i = 0;
    }
    
    public boolean canWrite(){
        if (i >= arrayA.length) {
            return false;
        }        
        return true;
    }
       
    public boolean write(){
        if (i >= arrayA.length) {
            return false;
        }
        if (buffer.count() < buffer.length()) {
            buffer.put(arrayA[i]);
            i++;
        }
        
        return true;
    }    
}
