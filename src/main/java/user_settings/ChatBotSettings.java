package user_settings;

import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
public class ChatBotSettings {

    private final Long chatId;
    private String group = "KS232";
    private String dayOfWeek = String.valueOf(LocalDate.now().getDayOfWeek());
    private List<Integer> reminderHours = new LinkedList<>();
    private boolean reminderStarted = false;

    public ChatBotSettings(Long chatId) {
        this.chatId = chatId;
    }

    public boolean isReminderStarted() {
        return reminderStarted;
    }
}
