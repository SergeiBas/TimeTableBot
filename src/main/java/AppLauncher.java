import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.time.LocalDate;

import telegrambot.MyTelegramBot;

public class AppLauncher {
    public static Logger logger = LoggerFactory.getLogger(AppLauncher.class);

    public static void main(String[] args) throws IOException {

//        System.out.println(String.valueOf(new StringBuilder(readFileWithFormatting("TimeTables/"
//                + new ChatBotSettings(123L).getDayOfWeek()
//                + "/" + new ChatBotSettings(123L).getGroup() + ".txt"))));

        System.out.println(String.valueOf(LocalDate.now().getDayOfWeek()));


        //BasicConfigurator.configure();
        TelegramBotsApi bolts;

        try {
            bolts = new TelegramBotsApi(DefaultBotSession.class);
            bolts.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
            logger.error("Error occurred while registering the bot", e);
        }

//        System.out.println("TimeTables/" + new ChatBotSettings(123L).getDayOfWeek() + "/" + new ChatBotSettings(123L).getGroup() + ".txt");
//        StringBuilder result = new StringBuilder("");
//        try(FileReader reader = new FileReader("TimeTables11/" + new ChatBotSettings(123L).getDayOfWeek() + "/" + new ChatBotSettings(123L).getGroup() + ".txt")){
//            StringBuilder result = new StringBuilder(reader.toString());
//            System.out.println(result);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("TimeTables11/"
//                    + new ChatBotSettings(123L).getDayOfWeek()
//                    + "/" + new ChatBotSettings(123L).getGroup() + ".txt"));
//            String line = reader.readLine();
//            while (line != null){
//                System.out.println(line);
//                result.append(line);
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        System.out.println(result);

//        try {
//            String fileContent = readFileWithFormatting("TimeTables11/"
//                    + new ChatBotSettings(123L).getDayOfWeek()
//                    + "/" + new ChatBotSettings(123L).getGroup() + ".txt");
//            System.out.println("Содержимое файла:\n" + fileContent);
//        } catch (IOException e) {
//            System.err.println("Ошибка при чтении файла: " + e.getMessage());
//        }
    }
}