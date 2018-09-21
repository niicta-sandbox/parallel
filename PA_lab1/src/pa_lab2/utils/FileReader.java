package pa_lab2.utils;

import java.io.*;

public class FileReader
{
    private Buffer buffer;
    private BufferedReader bufferedReader;
    private String fileName;

    public FileReader(Buffer buffer, String fileName) throws FileNotFoundException{
        this.buffer = buffer;
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        this.fileName = fileName;
    }

    public void readToBuffer() throws IOException{
        String string;
        boolean endOfFile = false;
        while (!endOfFile)
        {
            System.out.println("starting read from " + fileName);
            System.out.println("file " + fileName + " eof " + endOfFile);
            if ((string = bufferedReader.readLine()) == null)
            {
                System.out.println("current string from file" + fileName + " " + string);
                endOfFile = true;
                continue;
            }
            System.out.println("current string from file" + fileName + " " + string);
            while (buffer.isFilled())
            {
                System.out.println("buffer is filled, yield");
                Thread.yield();
            }
            System.out.println("put string to buffer from file " + fileName);
            buffer.fillWithString(string);
        }
        System.out.println("file ended " + fileName);
        buffer.setFileEnded(true);
    }
}
