import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VRUI {

	private static VRUI_business logic = new VRUI_business() ;
	private static Scanner scanner = new Scanner(System.in) ;
	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: logic.listCustomers() ; break ;
				case 2: logic.listVideos() ; break ;
				case 3: logic.registerCustomer() ; break ;
				case 4: logic.registerVideo() ; break ;
				case 5: logic.rentVideo() ; break ;
				case 6: logic.returnVideo() ; break ;
				case 7: logic.getCustomerReport() ; break;
				case 8: logic.clearRentals() ; break ;
				case -1: logic.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

    public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;

	}
}
