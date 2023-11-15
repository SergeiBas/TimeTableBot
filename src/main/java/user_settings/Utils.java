package user_settings;

import java.io.*;

public class Utils {
    private Utils() {
    }

    public static String getCurrentData(ChatBotSettings userSettings) {
        return String.valueOf(new StringBuilder(readFileWithFormatting("TimeTables/"
                + userSettings.getDayOfWeek()
                + "/" + userSettings.getGroup() + ".txt")));
    }

    private static String readFileWithFormatting(String filePath){
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }
}