package user_settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telegrambot.MyTelegramBot;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Utils {
    private Utils() {
    }

    public static String getCurrentData(ChatBotSettings userSettings) {
        return String.valueOf(new StringBuilder(readFileWithFormatting("TimeTables11/"
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