package telegrambot.button_menus;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import telegrambot.MyTelegramBot;
import telegrambot.KeyboardBuilder;
import user_settings.ChatBotSettings;

import java.util.List;

public class TabloMenu {

    public static InlineKeyboardMarkup getChoiceTabloKeyBoard(ChatBotSettings userSettings) {
        List<Integer> groups = userSettings.getReminderHours();

        ButtonValue[] buttons = new ButtonValue[]{
                new ButtonValue((groups.contains(9)) ? "✅ КС-231" : "КС-231", "KS231")
                , new ButtonValue((groups.contains(10)) ? "✅ КС-232" : "КС-232", "KS232")
                , new ButtonValue((groups.contains(11)) ? "✅ КТ-23" : "КТ-23", "KN23")
                , new ButtonValue((groups.contains(12)) ? "✅ КІ-23" : "КІ-23", "KI23")
                , new ButtonValue((groups.contains(13)) ? "✅ КТ-23" : "КТ-23", "KT23")
                , new ButtonValue((groups.contains(14)) ? "✅ КС-22" : "КС-22", "KS22")
                , new ButtonValue((groups.contains(15)) ? "✅ КН-22" : "КН-22", "KN22")
                , new ButtonValue((groups.contains(16)) ? "✅ КІ-22" : "КІ-22", "KI22")
                , new ButtonValue((groups.contains(17)) ? "✅ КТ-22" : "КТ-22", "KT22")};

        return KeyboardBuilder.getGroupsKeyboard(buttons);
    }

    public static void sendChoiceGroupMessage(SendMessage sendMessage, ChatBotSettings userSettings) {
        InlineKeyboardMarkup inlineKeyboardMarkup = getChoiceTabloKeyBoard(userSettings);

        sendMessage.setText("Оберіть группу:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        new MyTelegramBot().sendNextMessage(sendMessage);
    }
}