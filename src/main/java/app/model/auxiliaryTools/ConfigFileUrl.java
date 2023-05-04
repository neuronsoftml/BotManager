package app.model.auxiliaryTools;

public enum ConfigFileUrl {
    DOWNLOAD_FILE("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\resources\\downloadFile\\"),
    SEND_FILE("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\resources\\sendFile\\"),
    A("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\A.txt"),
    V("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\B.txt"),
    B("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Б.txt"),
    G("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Г.txt"),
    D("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Д.txt"),
    E("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Е.txt"),
    J("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Ж.txt"),
    Z("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\З.txt"),
    W("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\И.txt"),
    IA("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Є.txt"),
    I("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\І.txt"),
    II("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Ї.txt"),
    GG("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Ґ.txt"),
    IW("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Й.txt"),
    K("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\К.txt"),
    L("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Л.txt"),
    M("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\М.txt"),
    H("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Н.txt"),
    O("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\О.txt"),
    P("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\П.txt"),
    R("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Р.txt"),
    C("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\C.txt"),
    T("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Т.txt"),
    U("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\У.txt"),
    F("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\Ф.txt"),
    X("C:\\Users\\Ira\\Desktop\\com.bot.telegram\\BotManager\\src\\main\\java\\app\\db\\ukraine\\X.txt");
    private String urlLink;

    ConfigFileUrl(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getUrlLink(){
        return  urlLink;
    }
}
