import java.util.ArrayList;
import java.util.List;

public class Customer {
	private final String name;

	private List<Rental> rentals = new ArrayList<>();

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
		List<Rental> rentals = getRentals();
		double totalCharge = 0;
		int totalPoint = 0;
		String result = "Customer Report for " + getName() + "\n";
		for (Rental rental : rentals) {
			Rental.Report report = rental.getReport();
			totalCharge += report.totalCharge() ;
			totalPoint += report.totalPoint() ;
			result += report.result();
		}
		printEarnedCoupon(totalPoint);
		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";
		return result ;
	}

	private static void printEarnedCoupon(int totalPoint) {
		if ( totalPoint >= 10 ) {
			System.out.println("Congrats! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrats! You earned two free coupon");
		}
	}
}
