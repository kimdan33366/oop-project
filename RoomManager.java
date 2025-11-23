// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.StandardOpenOption;
// import java.util.LinkedHashMap;
// import java.util.Map;

// public class RoomManager {

//     // default room names and initial states
//     private static final Map<String,String> DEFAULT_ROOMS = new LinkedHashMap<>();
//     static {
//         DEFAULT_ROOMS.put("SingleRoom", "available");
//         DEFAULT_ROOMS.put("DoubleRoom", "available");
//         DEFAULT_ROOMS.put("SuiteRoom", "available");
//     }

//     // Ensure file exists and contains at least room status lines.
//     public static void ensureRoomFileInitialized(String filepath) {
//         try {
//             Path path = Path.of(filepath);
//             File file = path.toFile();
//             if (!file.exists()) {
//                 // create file and write initial room statuses
//                 Files.createDirectories(path.getParent());
//                 try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
//                     for (Map.Entry<String,String> e : DEFAULT_ROOMS.entrySet()) {
//                         writer.write(e.getKey() + ":" + e.getValue());
//                         writer.newLine();
//                     }
//                 }
//                 return;
//             }

//             // If file exists, check whether each room has a line; if not, prepend missing lines.
//             Map<String,String> current = readRoomStatusMap(filepath);
//             boolean changed = false;
//             StringBuilder extra = new StringBuilder();
//             for (String room : DEFAULT_ROOMS.keySet()) {
//                 if (!current.containsKey(room)) {
//                     // will prepend missing at top
//                     extra.append(room).append(":").append(DEFAULT_ROOMS.get(room)).append(System.lineSeparator());
//                     changed = true;
//                 }
//             }
//             if (changed) {
//                 // read full content, then write extra + old content
//                 String old = Files.readString(path);
//                 try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
//                     writer.write(extra.toString());
//                     writer.write(old);
//                 }
//             }
//         } catch (IOException ex) {
//             ex.printStackTrace();
//         }
//     }

//     // Read the room status lines into a map (roomName -> status)
//     public static Map<String,String> readRoomStatusMap(String filepath) {
//         Map<String,String> map = new LinkedHashMap<>();
//         try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 String trimmed = line.trim();
//                 if (trimmed.isEmpty()) continue;
//                 // only parse lines that look like "RoomName:status"
//                 int idx = trimmed.indexOf(':');
//                 if (idx > 0) {
//                     String key = trimmed.substring(0, idx).trim();
//                     String val = trimmed.substring(idx + 1).trim();
//                     // only treat recognized rooms as status lines
//                     if (DEFAULT_ROOMS.containsKey(key)) {
//                         map.put(key, val);
//                     }
//                 }
//             }
//         } catch (IOException ex) {
//             ex.printStackTrace();
//         }
//         return map;
//     }

//     // Returns true if room is available
//     public static boolean isRoomAvailable(String filepath, String roomName) {
//         ensureRoomFileInitialized(filepath);
//         Map<String,String> map = readRoomStatusMap(filepath);
//         String status = map.get(roomName);
//         if (status == null) return true; // fallback to available
//         return status.equalsIgnoreCase("available");
//     }

//     // Update the given room's status to "booked"
//     public static boolean updateRoomStatusToBooked(String filepath, String roomName) {
//         ensureRoomFileInitialized(filepath);
//         try {
//             Path path = Path.of(filepath);
//             // Read all lines
//             java.util.List<String> lines = Files.readAllLines(path);
//             boolean changed = false;
//             for (int i = 0; i < lines.size(); i++) {
//                 String trimmed = lines.get(i).trim();
//                 if (trimmed.startsWith(roomName + ":")) {
//                     lines.set(i, roomName + ":booked");
//                     changed = true;
//                     break;
//                 }
//             }
//             if (!changed) {
//                 // If not found, append at top
//                 lines.add(0, roomName + ":booked");
//             }
//             Files.write(path, lines);
//             return true;
//         } catch (IOException ex) {
//             ex.printStackTrace();
//             return false;
//         }
//     }

//     // Append guest entry to the file (will append after statuses)
//     // Example guestLine: "Dela Cruz, Juan, 20, Male, SingleRoom"
//     public static boolean appendGuestEntry(String filepath, String guestLine) {
//         ensureRoomFileInitialized(filepath);
//         try {
//             Path path = Path.of(filepath);
//             // Append an empty line for readability if file doesn't already end with newline
//             byte[] lastByteCheck = Files.readAllBytes(path);
//             // Just append the guestLine with newline
//             Files.writeString(path, System.lineSeparator() + guestLine, StandardOpenOption.APPEND);
//             return true;
//         } catch (IOException ex) {
//             ex.printStackTrace();
//             return false;
//         }
//     }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RoomManager {

    // Check if room is available
    public static boolean isRoomAvailable(String filepath, String roomName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(roomName)) {
                    return line.contains("available");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; 
    }

    // Mark room as booked
    public static void updateRoomStatus(String filepath, String roomName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(roomName)) {
                    content.append(roomName).append(":booked\n");
                } else {
                    content.append(line).append("\n");
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(filepath, false);
            writer.write(content.toString());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public static boolean appendGuestEntry(String filepath, String guestLine) {
        try {
            Path path = Path.of(filepath);
            // Append an empty line for readability if file doesn't already end with newline
            byte[] lastByteCheck = Files.readAllBytes(path);
            // Just append the guestLine with newline
            Files.writeString(path, System.lineSeparator() + guestLine, StandardOpenOption.APPEND);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
}
}
