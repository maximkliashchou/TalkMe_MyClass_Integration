package by.mmkle.telegram.bot;

public enum BotCommand {
    START("/start"),
    HELP("/help"),
    SETTING("/settings"),
    MOYCLASS("/moyclass"),
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