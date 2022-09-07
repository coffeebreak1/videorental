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
}
