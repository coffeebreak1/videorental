import java.util.Date;

public class CD implements VideoType{

    public int getLateReturnPointPenalty() {
        return 2;
    }
    public int getLimit()
    {
        return 3;
    }

}
