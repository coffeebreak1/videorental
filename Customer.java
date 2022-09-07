import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();
		double totalCharge = 0;
		int totalPoint = 0;
		for (Rental each : rentals) {
			int daysRented = getDaysRent(getDiff(each));
			double eachCharge = getCharge(each, daysRented);
			int eachPoint = getPoint(each, daysRented);
			totalCharge += eachCharge;
			totalPoint += eachPoint ;
			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";
		}
		printEarnedCoupon(totalPoint);
		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";
		return result ;
	}

	private static void printEarnedCoupon(int totalPoint) {
		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
	}

	private static long getDiff(Rental each) {
		if (each.getIsRented()) { // returned Video
			return each.getReturnDate().getTime() - each.getRentDate().getTime();
		} else { // not yet returned
			return new Date().getTime() - each.getRentDate().getTime();
		}
	}

	private static int getPoint(Rental rental, int daysRented) {
		int eachPoint = 1;

		if ((rental.getVideo().getPriceCode() == Video.NEW_RELEASE) )
			eachPoint++;
		if ( daysRented > rental.getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, rental.getVideo().getLateReturnPointPenalty()) ;
		return eachPoint;
	}

	private static double getCharge(Rental rental, int daysRented) {
		double eachCharge = 0;
		switch (rental.getVideo().getPriceCode()) {
		case Video.REGULAR:
			eachCharge += 2;
			if (daysRented > 2)
				eachCharge += (daysRented - 2) * 1.5;
			break;
		case Video.NEW_RELEASE:
			eachCharge = daysRented * 3;
			break;
		}
		return eachCharge;
	}

	private static int getDaysRent(long diff) {
		return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
	}
}
