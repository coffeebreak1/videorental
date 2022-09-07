import java.util.Date;

public class DVD extends Video{
    public DVD(String title, int videoType, int priceCode, Date registeredDate){
        super(title, videoType, priceCode, registeredDate);
    }

    public int getLateReturnPointPenalty() {
        return 3;
    }

}
