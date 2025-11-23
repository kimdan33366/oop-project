import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);

        System.out.println(" welcome to our Hotel Reservation System ");
        System.out.println(" Menu.");
        System.out.println(" 1 . Book a Room");
        System.out.println(" 2 . View room vailability"); 
        System.out.println(" 3 . Check room rates ");
        System.out.println(" 4 . Exit");
        
        System.out.print(" Please select an option (1-4): ");
        int choice = scan.nextInt();
        
        switch(choice){
            case 1:
                // Start of case 1
        System.out.print("Enter Last Name: ");
        String lname = scan.nextLine();
        System.out.print("Enter First Name: ");
        String fname = scan.nextLine();
        System.out.print("Enter Age: ");
        int age = scan.nextInt();
        scan.nextLine(); 
        System.out.print("Enter Sex: ");
        String Sex = scan.nextLine();
       

        guest guest1 = new guest(lname, fname, age, Sex);

        System.out.println("Guest Information: ");
        System.out.println("Last Name: " + guest1.getLname());  
        System.out.println("First Name: " + guest1.getFname());
        System.out.println("Age: " + guest1.getAge());
        System.out.println("sex " + guest1.getSex());

        System.out.println("Available Room Types: Single Room, Double Room, Suite Room");
        System.out.println(" 1. Single Room");
        System.out.println(" 2. Double Room");
        System.out.println(" 3. Suite Room"); 
        System.out.print("Select a room type (1-3): ");

        int roomchoice = scan.nextInt();
        //room selection switch case
        switch(roomchoice) {
            case 1:
                Singleroom single = new Singleroom(guest1);
                single.guestinfo();
                single.pay();
            break;
            case 2:
                Doubleroom doubleRoom = new Doubleroom(guest1);
                doubleRoom.guestinfo();
                doubleRoom.pay();
             break;
            case 3:
            Suiteroom suite = new Suiteroom(guest1);
                suite.guestinfo();
                suite.pay();
             break;
            default:
                System.out.println("Invalid choice.");
            break;
    }
    // End of case 1
            break;
            case 2:
                System.out.println(" Viewing room availability...");
            break;
            case 3:
                System.out.println(" Single Room: 1000 per night");
                System.out.println(" Double Room: 1500 per night");
                System.out.println(" Suite Room: 2500 per night");
            break;
            case 4:
                System.out.println(" Exiting the system. Thank you!");
            break;
            default:
                System.out.println(" Invalid choice. Please select a valid option (1-4).");
            break;
        }
    scan.close();
    }
}
