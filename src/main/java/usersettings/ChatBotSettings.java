package usersettings;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ChatBotSettings {

    private final Long chatId;
    private List<Integer> reminderHours = new LinkedList<>();
    private boolean reminderStarted = false;

    public ChatBotSettings(Long chatId) {
        this.chatId = chatId;
    }

    public boolean isReminderStarted() {
        return reminderStarted;
    }
}
