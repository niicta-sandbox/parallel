package pa_lab2.utils;

import java.util.Queue;

public class StringComparator
{
    private Buffer buffer1;
    private Buffer buffer2;
    private Queue<Integer> deltas;

    public StringComparator(Buffer buffer1, Buffer buffer2, Queue<Integer> deltas){
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
        this.deltas = deltas;
    }

    public void compare(){
        do{
            int delta = 0;
            if (buffer1.isFilled() && buffer2.isFilled()){
                String string1 = buffer1.extractString();
                String string2 = buffer2.extractString();
                int minLength = calculateMinLength(string1, string2);
                int maxLength = calculateMaxLength(string1, string2);
                delta = maxLength - minLength;
                for (int i = 0; i < minLength; i++){
                    if ((string1.charAt(i) != string2.charAt(i))){
                        delta++;
                    }
                }
                deltas.add(delta);
            }
            else{
                Thread.yield();
            }
        }while(!oneFileIsEnded());
        Buffer remainingBuffer = getRemainingBuffer();
        deltas.add(-1);
        int delta = 0;
        while (remainingBuffer != null && !remainingBuffer.isFileEnded()){
            if (remainingBuffer.isFilled()){
                remainingBuffer.extractString();
                delta++;
            }
        }
        deltas.add(delta);
    }

    private Buffer getRemainingBuffer(){
        if (buffer1.isFileEnded())
            return buffer2;
        else if (buffer2.isFileEnded())
            return buffer1;
        else return null;
    }

    private int calculateMaxLength(String string1, String string2){
        return string1.length() - string2.length() > 0?  string1.length() : string2.length();
    }

    private int calculateMinLength(String string1, String string2){
        return string1.length() - string2.length() < 0?  string1.length() : string2.length();
    }

    private boolean oneFileIsEnded(){
        return buffer1.isFileEnded() || buffer2.isFileEnded();
    }
}
