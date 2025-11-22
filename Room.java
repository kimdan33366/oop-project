import java.util.Scanner;
public class Room {
    Scanner scan = new Scanner(System.in);
   
    protected guest guest;
    protected int price;
    protected int pernight;
    protected int totalcost;

    public Room(guest guest, int price, int pernight,int totalcost) {
        this.guest = guest;
        this.price = price;
        this.pernight = pernight;
        this.totalcost = totalcost;
    }
    public Room(guest guest, int price, int pernight) {
        this(guest, price, 1,0); 
    }
    public Room(guest guest, int price) {
        this(guest, price, 1); 
    }
    void guestinfo() {
       
        System.out.println("Guest Information: " + guest.getLname() + " " + guest.getFname() + " " + guest.getAge() + " " + guest.getSex());
        System.out.println("price: " + price + " per night");
        System.out.print("Enter number of nights to stay: ");
        int pernight = scan.nextInt();

       totalcost = price*pernight;
        System.out.println("Total cost for " + pernight + " night(s): " + totalcost);
 
    }
     void pay(){ 
        System.out.print("Select payment method: 1. Cash 2. Credit Card :");
        int paychoice = scan.nextInt();
        switch(paychoice) {
            case 1:
                System.out.println("You have selected Cash payment.");
                System.out.print("please pay the amount at the reception upon check-in. hand the money here: ");
                int cash = scan.nextInt();
                int change = cash - totalcost;
                if(cash < totalcost){
                    System.out.println("Insufficient amount. Please pay the remaining " + cash);}
                    else{
                   System.out.println(cash + " is received. Thank you!");
                   System.out.println(change + " is your change.");
                   System.out.println(" thank you for choosing our hotel! We wish you a pleasant stay.");
                    }
            break;
            case 2:
                System.out.println("You have selected Credit Card payment.");
                System.out.print("please enter your credit card number: ");
                String cardnumber = scan.next();
                System.out.println("Processing payment...");
                System.out.println("Payment of " + totalcost + " is successful. Thank you!");
                System.out.println(" thank you for choosing our hotel! We wish you a pleasant stay.");

             break;
            default:
                System.out.println("Invalid choice.");
            break;
        }
    }
}
 

class Singleroom extends Room {
   
    public Singleroom(guest guest) { super(guest, 1000); }
}


class Doubleroom extends Room {
    public Doubleroom(guest guest) { super(guest, 1500); }
}

class Suiteroom extends Room {
    public Suiteroom(guest guest) { super(guest, 2500); }
}
