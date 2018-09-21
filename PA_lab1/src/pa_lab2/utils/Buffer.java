package pa_lab2.utils;

public class Buffer
{
    private static final String EMPTY = "EMPTY";
    private boolean fileEnded;
    private boolean filled;
    private String string;

    public Buffer(){
        fileEnded = false;
        filled = false;
        string = EMPTY;
    }

    public boolean isFileEnded(){
        return fileEnded;
    }

    public void setFileEnded(boolean fileEnded){
        this.fileEnded = fileEnded;
    }

    public boolean isFilled(){
        return filled;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public String extractString(){
        if(string == EMPTY){
            throw new RuntimeException();
        }
        String stringToReturn = string;
        string = EMPTY;
        filled = false;
        return stringToReturn;
    }

    public void fillWithString(String stringToPut){
        if(string != EMPTY){
            throw new RuntimeException();
        }
        string = stringToPut;
        filled = true;
    }
}
