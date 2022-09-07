import java.util.Date;

public class CD extends Video{
    public CD(String title, int videoType, int priceCode, Date registeredDate){
        super(title, videoType, priceCode, registeredDate);
    }
    public int getLateReturnPointPenalty() {
        return 2;
    }

}
