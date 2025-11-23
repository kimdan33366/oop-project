import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
public class buffer {
    public static void main(String[] args) {
    
        Scanner scan = new Scanner( System.in);
        String filepath = "C:\\Users\\kimda\\Desktop\\dunno\\oop-Project\\buffer.txt";

  try(FileWriter writer = new FileWriter (filepath, true)){
            writer.write("\n ");
            System.out.println(" Successfully wrote to the file.");
    

        }catch (Exception e) {
            e.printStackTrace();
        }

        try( BufferedReader reader = new BufferedReader(new FileReader (filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
         System.out.println(" File not found. Please check the file path and try again.");

        }

      
}
}
