package sheetsAPI;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SheetExplorer {

//    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//    final String spreadsheetId = "1eQEFYE-ImwKVhW0_QYYyou1J2qBCxO6MzbFcpV0S1R0";
//    final String range = "list1!B4:L13";
//    Sheets service =
//            new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                    .setApplicationName(APPLICATION_NAME)
//                    .build();
//    ValueRange response = service.spreadsheets().values()
//            .get(spreadsheetId, range)
//            .execute();
//
//    writeDataToFile(response.getValues(), "output.txt");

    public String classesFinder(String group, String day){

        return "";
    }

    private static String getRangeForGroup(String groupName, String day) {
        switch (groupName) {
            case "KS231" -> {
                return switch (day) {
                    case "TUESDAY" -> "C21:D34";
                    case "WEDNESDAY" -> "C38:D51";
                    case "THURSDAY" -> "C55:D68";
                    case "FRIDAY" -> "C72:D85";
                    case "SATURDAY" -> "C89:D102";
                    default -> "C4:D17";
                };
            }
            case "KS232" -> {
                return switch (day) {
                    case "TUESDAY" -> "E21:F34";
                    case "WEDNESDAY" -> "E38:F51";
                    case "THURSDAY" -> "E55:F68";
                    case "FRIDAY" -> "E72:F85";
                    case "SATURDAY" -> "E89:F102";
                    default -> "E4:F17";
                };
            }
            case "KN23" -> {
                return "G4:H17";
            }
            case "КI231" -> {
                return "I4:J17";
            }
            default -> {
                return "K4:L17";
            }
        }
    }

    private static void writeDataToFile(List<List<Object>> values, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (values != null && !values.isEmpty()) {
                for (List<Object> row : values) {
                    for (Object cell : row) {
                        writer.write(cell.toString());
                        writer.write("\t");
                    }
                    writer.newLine();
                }
            } else {
                writer.write("No data found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
