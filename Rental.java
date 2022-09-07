import java.util.Date;

public class Rental {
	public static final int MILLISECOND_IN_A_DAY = 1000 * 60 * 60 * 24;
	private final Video video ;
	private boolean isRented; // 0 for Rented, 1 for Returned
	private final Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		isRented = false ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public boolean getIsRented() {
		return isRented;
	}

	public void returnVideo() {
		if (isRented) {
			returnDate = new Date() ;
		}
	}

	public int getDaysRented() {
		long timeRented = (getIsRented() ? returnDate.getTime() : new Date().getTime()) - rentDate.getTime();
		return (int) (timeRented / MILLISECOND_IN_A_DAY) + 1;
	}

	public int getDaysRentedLimit() {
		return (getDaysRented() <= 2) ? 0 : video.getLimit();
	}

	record Report(double totalCharge, int totalPoint, String result) {}
	public Report getReport() {
		int daysRented = getDaysRented();
		int eachPoint = getPoint(daysRented);
		double eachCharge = Video.getCharge(this, daysRented);
		return new Report(eachCharge, eachPoint,
						"\t" + getVideo().getTitle() + "\tDays rented: "
						+ daysRented + "\tCharge: " + eachCharge + "\tPoint: " + eachPoint + "\n");
	}

	private int getPoint(int daysRented) {
		int eachPoint = 1;

		if ((getVideo().getPriceCode() == Video.NEW_RELEASE) )
			eachPoint++;
		if ( daysRented > getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty()) ;
		return eachPoint;
	}
}
