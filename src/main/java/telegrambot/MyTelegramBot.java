package telegrambot;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.button_menus.TabloMenu;
import user_settings.ChatBotSettings;
import user_settings.Utils;

import java.util.*;

public class MyTelegramBot extends TelegramLongPollingBot {
    public static Logger logger = LoggerFactory.getLogger(MyTelegramBot.class);

    @Getter
    private static final Map<Long, ChatBotSettings> settings = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = update.getMessage().getChatId();
            Utils.updateSettings(chatId);

            if (message.hasText()) {
                String text = message.getText();
                SendMessage sendMessage = new SendMessage();

                sendMessage.setChatId(String.valueOf(chatId));

                switch (text) {
                    case "/start" -> sendNextMessage(new TelegramBotUtils().sendHelloMessage(chatId));
                    case "Отримати розклад" -> {
                        sendMessage.setText(Utils.getCurrentData(settings.get(chatId)));
                        sendNextMessage(sendMessage);
                    }
                    case "Налаштування", "/settings" -> new SettingsKeyboardsUtils().sendChoiceOptionsMessage(sendMessage);
                    case "/end" -> sendNextMessage(new TelegramBotUtils().sendEndMessage(chatId));
                    default -> {
                        sendMessage.setText("Немає обробки тексту " + text);
                        sendNextMessage(sendMessage);
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

            Utils.updateSettings(chatId);

            String inputQueryMessage = String.valueOf(update.getCallbackQuery().getData());

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            EditMessageReplyMarkup editMessage = new EditMessageReplyMarkup();
            editMessage.setChatId(chatId);
            editMessage.setMessageId(messageId);

            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(update.getCallbackQuery().getId());

            switch (inputQueryMessage) {
                case("group") -> TabloMenu.sendChoiceGroupMessage(sendMessage, settings.get(chatId));
                case("KS231"), ("KS232"), ("KN23"), ("KI23"), ("KT23"), ("KS22"), ("KN22"), ("KI22"), ("KT22") ->{

                    boolean isNewSetting = SettingsKeyboardsUtils.isThisNewSetting(inputQueryMessage, settings.get(chatId));
                    new SettingsKeyboardsUtils().sendAnswerCallbackQuery(answerCallbackQuery, isNewSetting);

                    if (isNewSetting) {
                        settings.get(chatId).setGroup(inputQueryMessage);
                        editMessage.setReplyMarkup(TabloMenu.getChoiceTabloKeyBoard(settings.get(chatId)));
                        sendNextEditMessage(editMessage);
                        Utils.writeUserSettings(settings.get(chatId));
                    }
                }
                default -> {
                    sendMessage.setText("Немає обробки цієї кнопки: " + update.getCallbackQuery().getData());
                    sendNextMessage(sendMessage);
                }
            }
        }
    }

    public void sendNextMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error sending message", e);
        }
    }

    public void sendNextQuery(AnswerCallbackQuery answerCallbackQuery) {
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            logger.error("Error sending query", e);
        }
    }

    public void sendNextEditMessage(EditMessageReplyMarkup editMessage) {
        try {
            execute(editMessage);
        } catch (TelegramApiException e) {
            logger.error("Error editing message", e);
        }
    }

    @Override
    public String getBotUsername() {
        return MyTelegramBotConst.MY_TEL_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return MyTelegramBotConst.MY_TEL_BOT_TOKEN;
    }
}