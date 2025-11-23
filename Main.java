import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
         
String filepath = "C:\\Users\\kimda\\Desktop\\dunno\\oop-Project\\buffer.txt";
      
while (true) {
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
                
                try(FileWriter writer = new FileWriter (filepath, true)) {

                    
                    System.out.print("Enter First Name: ");
                    String fname = scan.next();
                    System.out.print("Enter Last Name: ");
                    String lname = scan.next();
                    System.out.print("Enter Age: ");
                    int age = scan.nextInt();
                    scan.nextLine(); 
                    System.out.print("Enter Sex: ");
                    String Sex = scan.nextLine();
            writer.write(  lname + ", " + fname + ", " + age + ", "+ Sex+"\n");

            System.out.println(" Successfully wrote to the file.");
           guest guest1 = new guest(lname, fname, age, Sex);


        System.out.println("Guest Information: ");
        System.out.println("Last Name: " + guest1.getLname());  
        System.out.println("First Name: " + guest1.getFname());
        System.out.println("Age: " + guest1.getAge());
        System.out.println("sex: " + guest1.getSex());

        System.out.println(" Available Room Types: Single Room, Double Room, Suite Room");
        System.out.println(" 1. Single Room");
        System.out.println(" 2. Double Room");
        System.out.println(" 3. Suite Room"); 
       
        
       
         System.out.print(" Select a room type (1-3): ");
        int roomchoice = scan.nextInt();

        //room selection switch case
        switch(roomchoice) {
            case 1:
                 if (!RoomManager.isRoomAvailable(filepath, "SingleRoom")) {
            System.out.println("Sorry, Single Room is already booked.");
            
        }else{
                Singleroom single = new Singleroom(guest1);
                single.guestinfo();
                single.pay();
                RoomManager.updateRoomStatus(filepath, "SingleRoom");

       
            break;
        }
            case 2:
                    if (!RoomManager.isRoomAvailable(filepath, "DoubleRoom")) {
            System.out.println("Sorry, Double Room is already booked.");    
        }else{
                Doubleroom doubleRoom = new Doubleroom(guest1);
                doubleRoom.guestinfo();
                doubleRoom.pay();
                RoomManager.updateRoomStatus(filepath, "DoubleRoom");
                

             break;
        }
            case 3:
                    if (!RoomManager.isRoomAvailable(filepath, "SuiteRoom")) {
            System.out.println("Sorry, Suite Room is already booked.");
        }else{
            Suiteroom suite = new Suiteroom(guest1);
                suite.guestinfo();
                suite.pay();
                RoomManager.updateRoomStatus(filepath, "SuiteRoom");
             break;
        }
            default:
                System.out.println("Invalid choice.");
            break;
    }
    
         }catch (Exception e) {
            e.printStackTrace();
        }
       
    // End of case 1
            break;
            case 2:
        String inputText = scan.nextLine();
        try( BufferedReader reader = new BufferedReader(new FileReader (filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
         System.out.println(" File not found. Please check the file path and try again.");

        }
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
        if (choice == 4) {
            break;
        }
    }
    }
}
