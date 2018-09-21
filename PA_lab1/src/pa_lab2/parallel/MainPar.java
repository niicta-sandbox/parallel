package pa_lab2.parallel;

import pa_lab2.utils.Buffer;
import pa_lab2.utils.DeltaReader;
import pa_lab2.utils.FileReader;
import pa_lab2.utils.StringComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class MainPar
{
    public static void main(String[] args) throws IOException, InterruptedException{
        String file1 = "file1.txt";
        String file2 = "file2.txt";
        Buffer buffer1 = new Buffer();
        Buffer buffer2 = new Buffer();
        Queue<Integer> deltas = new LinkedList<>();
        ReaderThread thread1 = new ReaderThread(file1, buffer1);
        ReaderThread thread2 = new ReaderThread(file2, buffer2);
        ComparatorThread comparatorThread = new ComparatorThread(buffer1, buffer2, deltas);
        thread1.start();
        thread2.start();
        comparatorThread.start();
        thread1.join();
        thread2.join();
        comparatorThread.join();
        DeltaReader deltaReader = new DeltaReader(deltas);
        deltaReader.outputDeltas();
    }

    private static class ComparatorThread extends Thread{
        private Buffer buffer1;
        private Buffer buffer2;
        private Queue<Integer> deltas;

        private ComparatorThread(){
        }

        public ComparatorThread(Buffer buffer1, Buffer buffer2, Queue<Integer> deltas){
            this.buffer1 = buffer1;
            this.buffer2 = buffer2;
            this.deltas = deltas;
        }

        @Override
        public void run(){
            StringComparator stringComparator = new StringComparator(buffer1, buffer2, deltas);
            stringComparator.compare();
        }
    }

    private static class ReaderThread extends Thread{
        private String fileName;
        private Buffer buffer;

        private ReaderThread(){

        }

        public ReaderThread(String filename, Buffer buffer){
            super();
            this.fileName = filename;
            this.buffer = buffer;
        }

        @Override
        public void run(){
            try
            {
                FileReader fileReader = new FileReader(buffer, fileName);
                fileReader.readToBuffer();
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}


