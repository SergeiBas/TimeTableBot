package telegrambot;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import user_settings.ChatBotSettings;
import telegrambot.button_menus.ButtonValue;


public class SettingsKeyboardsUtils {

   public static boolean isThisNewSetting(String inputQueryMessage, ChatBotSettings userSettings) {

        String group = userSettings.getGroup();
        String week = userSettings.getDayOfWeek();

        return !group.equals(inputQueryMessage) && !week.equals(inputQueryMessage);
    }

    private InlineKeyboardMarkup getChoiceOptionsKeyBoard() {

        ButtonValue[] buttons = new ButtonValue[]{
                new ButtonValue("Група", "group")
                , new ButtonValue("Тиждень", "week")
                , new ButtonValue("День", "day")};

        return KeyboardBuilder.getSimpleKeyboard(buttons);
    }

    public void sendChoiceOptionsMessage(SendMessage sendMessage) {
        InlineKeyboardMarkup inlineKeyboardMarkup = getChoiceOptionsKeyBoard();

        sendMessage.setText("Налаштування");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        new MyTelegramBot().sendNextMessage(sendMessage);
    }

    public void sendAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery, boolean isNewSetting) {
        String callBackAnswer = isNewSetting ? "Налаштування оновлені." : "Ці налаштування вже встановлені.";

        answerCallbackQuery.setText(callBackAnswer);
        answerCallbackQuery.setShowAlert(false);
        answerCallbackQuery.setCacheTime(0);

        new MyTelegramBot().sendNextQuery(answerCallbackQuery);
    }
}
