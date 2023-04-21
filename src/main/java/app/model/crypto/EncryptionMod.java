package app.model.crypto;

import app.model.keyboard.InlineButton;
import app.model.message.Messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class EncryptionMod {
    private static EncryptionMod encryptionMod;
    public static EncryptionMod getEncryptionMod(){
        if(encryptionMod == null){
            encryptionMod = new EncryptionMod();
            System.out.println("Був включений режим EncryptionMod СТАТУС [ОК]");
        }
        return encryptionMod;
    }
    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private List<InlineKeyboardButton> keyboardButtonsRowOne;
    private List<InlineKeyboardButton> keyboardButtonsRowTwo;
    private void creatMarkupInlineMenuEncryptionMod(){
        rowList.clear();
        keyboardButtonsRowOne = new ArrayList<>();
        keyboardButtonsRowTwo = new ArrayList<>();

        creatKeyboardEncryption();
        creatKeyboardDecryption();
        creatKeyboardBrutForce();

        rowList.add(keyboardButtonsRowOne);
        rowList.add(keyboardButtonsRowTwo);

        inlineKeyboardMarkup.setKeyboard(rowList);
    }
    private void creatMarkupInlineSelectLanguage(){
        rowList.clear();
        keyboardButtonsRowOne = new ArrayList<>();

        creatKeyboardLanguageUkraine();
        creatKeyboardLanguageEnglish();

        rowList.add(keyboardButtonsRowOne);


        inlineKeyboardMarkup.setKeyboard(rowList);
    }
    private void creatMarkupInlineBack(){
        rowList.clear();
        keyboardButtonsRowOne = new ArrayList<>();

        creatKeyboardBack();

        rowList.add(keyboardButtonsRowOne);

        inlineKeyboardMarkup.setKeyboard(rowList);
    }
    private void creatKeyboardEncryption(){
        InlineButton keyboard = new InlineButton("Шифрування","encryption");
        keyboardButtonsRowOne.add(keyboard.getKeyboardButton());
    }
    private void creatKeyboardDecryption(){
        InlineButton keyboard = new InlineButton("Розшифрування","decryption");
        keyboardButtonsRowOne.add(keyboard.getKeyboardButton());
    }
    private void creatKeyboardBrutForce(){
        InlineButton keyboard = new InlineButton("Brut-Force атака на шифр \" Цезаря \" ","brut_force");
        keyboardButtonsRowTwo.add(keyboard.getKeyboardButton());
    }
    private void creatKeyboardLanguageUkraine(){
        InlineButton keyboard = new InlineButton("Українська","LanguageUkraine");
        keyboardButtonsRowOne.add(keyboard.getKeyboardButton());
    }
    private void creatKeyboardLanguageEnglish(){
        InlineButton keyboard = new InlineButton("Англійська","LanguageEnglish");
        keyboardButtonsRowOne.add(keyboard.getKeyboardButton());
    }
    private void creatKeyboardBack(){
        InlineButton keyboard = new InlineButton("Повернутися назад","back");
        keyboardButtonsRowOne.add(keyboard.getKeyboardButton());
    }
    public SendMessage sendInfoEncryptionModMenu(String idChat){
        creatMarkupInlineMenuEncryptionMod();
        String text = "Шифр Цезаря, також відомий як шифр зсуву, код Цезаря — один із найпростіших і найвідоміших методів шифрування.\n"
                + "\n"
                + "Я тобі пропуную вибрати декілька функцій зв'язані зі шифром Цезаря"
                +"\n 1) Шифрування за допомогу числового ключа від 1 до 41 числа"
                +"\n 2) Розшифрування за допомогу числового ключа від 1 до 41 числа"
                +"\n 3) Брут форс атака на шифр \"Цезарь\" ";

        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    public SendMessage sendInfoEncryptionSelectLanguage(String idChat){
        creatMarkupInlineSelectLanguage();
        String text = "Вибери одне з доступних мов для шифрування тексту";

        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    public SendMessage sendInfoEncryptionSelectTextOrFile(String idChat){
        String text = "Тепер відправ мені файл з текстом або напиши сам текст який хочеш зашифрувати.";
        return new Messages(text,idChat).creatMessages();
    }
    public SendMessage sendInfoEncryptionSelectKey(String idChat){
        String text = "Тепер напиши цифру ключа від 1 до 41";
        return new Messages(text,idChat).creatMessages();
    }
    public SendMessage sendInfoEncryptionResult(String idChat){
        Caesar caesar = new Caesar(language,textUser,key);
        creatMarkupInlineBack();
        String text = "Шифрування пройшло успішно: "+"\n"
                +"Ключь доступа: "+ key +"\n"+
                "Шифр: "+
                "\n"+caesar.encryption();
        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    private boolean isActiveEncryptionMod = false;
    private String language;
    private String encryptionModOption;
    private String textUser;
    private String stageSetting;
    private int key;

    public void setLanguage(String language) {
        this.language = language;
    }
    public void setEncryptionModOption(String encryptionModOption){
        this.encryptionModOption = encryptionModOption;
    }
    public void setActiveEncryptionMod(boolean activeEncryptionMod) {
        isActiveEncryptionMod = activeEncryptionMod;
    }
    public void setStageSetting(String stageSetting) {
        this.stageSetting = stageSetting;
    }
    public boolean getIsActiveEncryptionMod() {
        return isActiveEncryptionMod;
    }
    private boolean isResetEncryptionMod = false;
    public boolean getIsResetEncryptionMod() {
        return isResetEncryptionMod;
    }
    public SendMessage configurationEncryptionMod(String userMessage, String idChat){
        SendMessage sendMessage = null;
        if(this.stageSetting.equals("setTextUser")){
            System.out.println("Був встановлений текст СТАТУС [ОК]");
            this.textUser = userMessage;
            this.stageSetting = "setKey";
            sendMessage = sendInfoEncryptionSelectKey(idChat);
        } else if (this.stageSetting.equals("setKey")) {
            this.key = Integer.parseInt(userMessage);
            System.out.printf("\nБув встановлений ключь %d СТАТУС [ОК]",key);
            this.stageSetting = "full";
            sendMessage = sendInfoEncryptionResult(idChat);
            this.isResetEncryptionMod = true;
        }
        return sendMessage;
    }
    public void resetEncryptionMod(){
        isActiveEncryptionMod = false;
        language = null;
        textUser = null;
        stageSetting = null;
        key = 0;
        encryptionMod = null;
    }



}
