package telegrambot;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.buttons.ButtonValue;

import java.util.ArrayList;
import java.util.List;

public class KeyboardBuilder {

    public static InlineKeyboardMarkup getSimpleKeyboard(ButtonValue[] buttons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (ButtonValue button : buttons) {
            List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();

            inlineKeyboardRow.add(getButton(button));
            rowList.add(inlineKeyboardRow);
        }
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardButton getButton(ButtonValue buttonValue) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(buttonValue.getButtonName());
        inlineKeyboardButton.setCallbackData(buttonValue.getCallback());

        return inlineKeyboardButton;
    }
}