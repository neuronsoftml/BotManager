package app.setting;

public enum BotConfig {
    BOT_NAME("Напиши їм'я свого бота."),
    BOT_TOKEN("Напиши свій токен.");
    private String value;
    BotConfig(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
