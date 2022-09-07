import java.util.Date;

public class Rental {
	public static final int MILISECONDS_IN_A_DAY = 1000 * 60 * 60 * 24;
	private Video video ;
	private boolean isRented; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		isRented = false ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public boolean getIsRented() {
		return isRented;
	}

	public void returnVideo() {
		if ( isRented == true ) {
			this.isRented = true;
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int daysRented ;
		long diff;
		if (getIsRented() == true) {
			diff = returnDate.getTime() - rentDate.getTime();
		} else {
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = (int) (diff / MILISECONDS_IN_A_DAY) + 1;

		if ( daysRented <= 2) return 0 ;

		return video.getLimit() ;
	}
}
