package app.setting;

public enum BotConfig {
    BOT_NAME("Manager_BOT"),
    BOT_TOKEN("5930682693:AAHaawGeIlNdUWeDN6A8Hl4wt-VmC8j6hNY");
    private String value;
    BotConfig(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
