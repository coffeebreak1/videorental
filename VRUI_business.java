import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class VRUI_business {
    private static final Scanner scanner = new Scanner(System.in) ;

    private final List<Customer> customers = new ArrayList<>() ;

    private final List<Video> videos = new ArrayList<>() ;

    private Customer getCustomer(String customerName) {
        for ( Customer customer: customers ) {
            if ( customer.getName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    public void init() {
        Customer james = new Customer("James") ;
        Customer brown = new Customer("Brown") ;
        customers.add(james) ;
        customers.add(brown) ;

        Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
        Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
        videos.add(v1) ;
        videos.add(v2) ;

        Rental r1 = new Rental(v1) ;
        Rental r2 = new Rental(v2) ;

        james.addRental(r1) ;
        james.addRental(r2) ;
    }
    public void clearRentals() {
        System.out.println("Enter customer name: ") ;
        String customerName = scanner.next() ;
        Customer foundCustomer = getCustomer(customerName);

        if ( foundCustomer == null ) {
            System.out.println("No customer found") ;
        } else {
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + foundCustomer.getRentals().size()) ;
            for ( Rental rental: foundCustomer.getRentals() ) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
            }

            List<Rental> rentals = new ArrayList<>() ;
            foundCustomer.setRentals(rentals);
        }
    }

    public void returnVideo() {
        System.out.println("Enter customer name: ") ;
        String customerName = scanner.next() ;

        Customer foundCustomer = getCustomer(customerName);
        if ( foundCustomer == null ) return ;

        System.out.println("Enter video title to return: ") ;
        String videoTitle = scanner.next() ;

        List<Rental> customerRentals = foundCustomer.getRentals() ;
        for ( Rental rental: customerRentals ) {
            if ( rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented() ) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break ;
            }
        }
    }


    public void listVideos() {
        System.out.println("List of videos");

        for ( Video video: videos ) {
            System.out.println("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
        }
        System.out.println("End of list");
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for ( Customer customer: customers ) {
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + customer.getRentals().size()) ;
            for ( Rental rental: customer.getRentals() ) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
            }
        }
        System.out.println("End of list");
    }

    public void getCustomerReport() {
        System.out.println("Enter customer name: ") ;
        String customerName = scanner.next() ;

        Customer foundCustomer = getCustomer(customerName);
        if (foundCustomer == null) {
            System.out.println("No customer found") ;
        } else {
            System.out.println(foundCustomer.getReport());
        }
    }

    public void rentVideo() {
        System.out.println("Enter customer name: ") ;
        String customerName = scanner.next() ;

        Customer foundCustomer = getCustomer(customerName);
        if ( foundCustomer == null ) return ;

        System.out.println("Enter video title to rent: ") ;
        String videoTitle = scanner.next() ;

        Video foundVideo = null ;
        for ( Video video: videos ) {
            if ( video.getTitle().equals(videoTitle) && !video.isRented()) {
                foundVideo = video ;
                break ;
            }
        }

        if ( foundVideo == null ) return ;

        Rental rental = new Rental(foundVideo) ;
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals() ;
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);
    }

    public void registerCustomer() {
        System.out.println("Enter customer name: ") ;
        String name = scanner.next();
        customers.add(new Customer(name)) ;
    }

    public void registerVideo() {
        System.out.println("Enter video title to register: ") ;
        String title = scanner.next() ;

        System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
        int videoType = scanner.nextInt();

        System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
        int priceCode = scanner.nextInt();

        Date registeredDate = new Date();
        Video video = new Video(title, videoType, priceCode, registeredDate) ;
        videos.add(video) ;
    }


}
