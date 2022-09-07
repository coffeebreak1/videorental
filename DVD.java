public class DVD implements VideoType{

    public int getLateReturnPointPenalty() {
        return 3;
    }

    public int getLimit()
    {
        return 2;
    }
}
