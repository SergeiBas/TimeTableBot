package usersettings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telegrambot.MyTelegramBot;
import telegrambot.Reminder;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Utils {
    public static Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {
    }

    public static void writeUserSettings(ChatBotSettings userSetting) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileName = "src/main/resources/User" + userSetting.getChatId() + ".json";

        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(fileName, false))) {
            String json = gson.toJson(userSetting);

            jsonWriter.jsonValue(json);
            jsonWriter.flush();
        } catch (Exception e) {
            logger.error("Error writing user settings", e);
        }
    }

    public static ChatBotSettings readUserSetting(Long chatId) {
        ChatBotSettings newUserSetting = new ChatBotSettings(chatId);
        String fileName = "src/main/resources/User" + chatId + ".json";
        Gson gson = new GsonBuilder().create();
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader in = new FileReader(fileName)) {
            Scanner scanner = new Scanner(in);

            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
                if (scanner.hasNext()) {
                    stringBuilder.append("\n");
                }
            }

            newUserSetting = gson.fromJson(stringBuilder.toString(), ChatBotSettings.class);
        } catch (Exception e) {
            logger.error("Error reading user settings", e);
        }

        return newUserSetting;
    }

    public static void updateSettings(Long chatId) {
        if (!MyTelegramBot.getSettings().containsKey(chatId)) {
            URL url = MyTelegramBot.class.getResource("/User" + chatId + ".json");

            if (url != null) {
                logger.info("Found user settings file for chatId: {}", chatId);
                ChatBotSettings settingFromResource = readUserSetting(chatId);

                if (settingFromResource.isReminderStarted()) {
                    Reminder.startAllTimers(settingFromResource.getReminderHours(), chatId);
                    logger.info("Reminder timers started for chatId: {}", chatId);
                }
                MyTelegramBot.getSettings().put(chatId, settingFromResource);
                logger.info("User settings added to MyTelBot.getSettings() for chatId: {}", chatId);

            } else {
                MyTelegramBot.getSettings().put(chatId, new ChatBotSettings(chatId));
                logger.info("No user settings file found for chatId {}. Default settings used.", chatId);
            }
        }
    }
}