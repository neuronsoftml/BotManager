package app.model.keyboard;

public enum InlineButtonConfig {
    EXCHANGE_RATE("ExchangeRate"),
    ENCRYPTION_MOD("encryptionMod"),
    ENCRYPTION("encryption"),
    DECRYPTION("decryption"),
    BRUT_FORCE("brut_force"),
    LANG_UK("LanguageUkraine"),
    LANG_ENG("LanguageEnglish"),
    BACK("back");
    private String dateTitle;

    InlineButtonConfig(String exchangeRate) {
        this.dateTitle = exchangeRate;
    }
    public String  getDateTitle(){
        return dateTitle;
    }
}
