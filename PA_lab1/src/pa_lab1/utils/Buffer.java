/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_lab1.utils;

/**
 *
 * @author Admin
 */
public class Buffer<E> {
    private E[] array;
    private int start;
    private int end;       

    public Buffer(E[] array) {
        this.array = array;
        start = 0;
        end = 0;
    }
    
    public synchronized int count(){
        if (end == start) {
            return 0;
        }
        if(end > start){
            return end - start;
        }
        else{
            return array.length - start + end;
        }
    }   
    
    public int length(){
        return array.length;
    }
    
    public synchronized void put(E elem){
        if (end >= length()) {
            end = 0;
        }
        array[end] = elem;
        end++;
    }
    
    public synchronized E get(){
        E current = array[start];
        start++;
        if (start >= length()) {
            start = 0;
        }
        return current;
    }

    public synchronized void clear() {
        start = end;
    }
}
