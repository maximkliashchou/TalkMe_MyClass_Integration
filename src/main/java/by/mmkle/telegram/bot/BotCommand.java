package by.mmkle.telegram.bot;

public enum BotCommand {
    START("/start"),
    HELP("/help"),
    SETTING("/settings"),
    MYCLASS("/myclass"),
    TALKME("/talkme"),
    NONE("/none");

    String command;
    public String getCommand() {
        return command;
    }

    BotCommand(String command) {
        this.command = command;
    }
}