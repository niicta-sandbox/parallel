package pa_lab2.utils;

import java.io.File;
import java.io.IOException;

public class MainPar
{
    public static void main(String[] args) throws IOException{
        String file1 = "file1.txt";
        String file2 = "file2.txt";
    }

    private class ReaderThread extends Thread{
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

        }
    }
}


