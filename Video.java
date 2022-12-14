import java.util.Date;

public class Video {
	private String title ;

	private int priceCode ;
	public static final int REGULAR = 1 ;
	public static final int NEW_RELEASE =2 ;

	private VideoType videoType ;

	public static VideoType VHS = new VHS();
	public static VideoType CD = new CD();
	public static VideoType DVD = new DVD();

	private Date registeredDate ;
	private boolean rented ;

	public Video(String title, VideoType videoType, int priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	public Video(String title, int videoType, int priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	static double getCharge(Rental rental, int daysRented) {
		double eachCharge = 0;
		switch (rental.getVideo().getPriceCode()) {
		case REGULAR:
			eachCharge += 2;
			if (daysRented > 2)
				eachCharge += (daysRented - 2) * 1.5;
			break;
		case NEW_RELEASE:
			eachCharge = daysRented * 3;
			break;
		}
		return eachCharge;
	}

	public int getLateReturnPointPenalty() {
		return videoType.getLateReturnPointPenalty();
	}
	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}

	public void setVideoType(int videoType) {
		switch (videoType){
			case 1: this.videoType = VHS; break;
			case 2: this.videoType = CD; break;
			case 3: this.videoType = DVD; break;
		}
	}

	public int getLimit(){return videoType.getLimit();}
}
