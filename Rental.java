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
	public Date getRentDate() {
		return rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public int getDaysRentedLimit() {
		int daysRented ;
		long diff;
		if (getIsRented()) {
			diff = returnDate.getTime() - rentDate.getTime();
		} else {
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = (int) (diff / MILLISECOND_IN_A_DAY) + 1;

		if ( daysRented <= 2) return 0 ;

		return video.getLimit() ;
	}
}
