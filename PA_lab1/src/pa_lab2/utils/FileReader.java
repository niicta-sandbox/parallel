package pa_lab2.utils;

import java.io.*;

public class FileReader
{
    private Buffer buffer;
    private BufferedReader bufferedReader;

    public FileReader(Buffer buffer, String fileName) throws FileNotFoundException{
        this.buffer = buffer;
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
    }

    public void readToBuffer() throws IOException{
        String string;
        boolean endOfFile = false;
        while (!endOfFile)
        {
            if ((string = bufferedReader.readLine()) == null)
            {
                endOfFile = true;
                continue;
            }
            while (buffer.isFilled())
            {
                ;
            }
            buffer.fillWithString(string);
        }
        buffer.setFileEnded(true);
    }
}
