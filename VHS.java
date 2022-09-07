import java.util.Date;

public class VHS extends Video{
    public VHS(String title, int videoType, int priceCode, Date registeredDate){
        super(title, videoType, priceCode, registeredDate);
    }
    public int getLateReturnPointPenalty() {
        return 1;
    }
}
