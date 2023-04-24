package app.model.crypto;

import app.model.keyboard.InlineButton;
import app.model.message.Messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EncryptionMod {
    private static EncryptionMod encryptionMod;
    public static EncryptionMod getEncryptionMod(){
        if(encryptionMod == null){
            encryptionMod = new EncryptionMod();
            System.out.println("\nБув включений режим EncryptionMod СТАТУС [ОК]");
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
        String text = "Я тобі пропуную вибрати декілька функцій зв'язані зі шифром \"Цезаря\"."
                +"\n 1) Шифрування за допомогу числового ключа."
                +"\n 2) Розшифрування за допомогу числового ключа."
                +"\n 3) Брут форс атака на шифр \"Цезарь\". ";

        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    public SendMessage sendInfoEncryptionSelectLanguage(String idChat){
        creatMarkupInlineSelectLanguage();
        String text = "Вибери одне з доступних мов для шифрування тексту. \n" +
        "Увага як що вибрана мова не співпадатиме з текстом \"Jarvis\" автоматично перевичначить.";
        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    public SendMessage sendInfoEncryptionSelectTextOrFile(String idChat){
        String text = "Тепер відправ мені файл з текстом або напиши сам текст який хочеш зашифрувати.";
        return new Messages(text,idChat).creatMessages();
    }
    public SendMessage sendInfoEncryptionSelectKey(String idChat){
        String text = "Тепер напиши цифру ключа.\n"
                +"Для української мови від 1 до 41. \n"
                +"Для англійської мови від 1 до 34. \n"
                +"Увага як що ви ведете цифру за цього діапазону \"Jarvis\" сам рандомно вибере число.";
        return new Messages(text,idChat).creatMessages();
    }
    public SendMessage sendInfoEncryptionResult(String idChat){
        Caesar caesar = new Caesar(language,textUser,key);
        caesar.encryption();
        creatMarkupInlineBack();
        String text = "Шифрування пройшло успішно:"+"\n"
                +"Ключь доступа: "+ caesar.getKey() +"\n"+
                "Мова: "+ caesar.getLanguage() +"\n"+
                "Шифр: "+
                "\n"+caesar.getEncryptionText();
        System.out.println("\nШифрування пройшло успішно "+"СТАТУС [OK]");
        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    public SendMessage sendInfoDecryptionSelectLanguage(String idChat){
        creatMarkupInlineSelectLanguage();
        String text = "Вибери одне з доступних мов для розшифрування тексту. \n" +
                "Уага як що ви виберете не правильну мову бот автоматично перевизначить мову.";

        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    public SendMessage sendInfoDecryptionSelectTextOrFile(String idChat){
        String text = "Тепер відправ мені файл з текстом або напиши сам текст який хочеш розшифрувати.";
        return new Messages(text,idChat).creatMessages();
    }
    public SendMessage sendInfoDecryptionSelectKey(String idChat){
        String text = "Тепер напиши цифру ключа.\n"
                +"Для української мови від 1 до 41. \n"
                +"Для англійської мови від 1 до 34. \n";
        return new Messages(text,idChat).creatMessages();
    }
    public SendMessage sendInfoDecryptionResult(String idChat){
        Caesar caesar = new Caesar(language,textUser,key);
        caesar.decryption();
        creatMarkupInlineBack();
        String text = "Розшифрування пройшло успішно:" +"\n"
                +"Ключь доступа: "+ caesar.getKey() +"\n"+
                "Мова: "+ caesar.getLanguage() +"\n"+
                "Шифр: "+
                "\n"+caesar.getDecryptionText();
        System.out.println("\nРозшифрування пройшло успішно "+"СТАТУС [OK]");
        return new Messages(text,idChat,inlineKeyboardMarkup).creatMessages();
    }
    private boolean isActiveEncryptionMod = false;
    private String language;
    private String encryptionModOption;
    private String textUser;
    private String stageSetting;
    private int key;

    public void setLanguage(String language) {
        System.out.println("Встановлено мову "+language+" СТАТУС [OK]");
        this.language = language;
    }
    public void setEncryptionModOption(String encryptionModOption){
        System.out.println("Встановлено режим "+encryptionModOption+" СТАТУС [OK]");
        this.encryptionModOption = encryptionModOption;
    }
    public void setActiveEncryptionMod(boolean activeEncryptionMod) {
        isActiveEncryptionMod = activeEncryptionMod;
    }
    public void setStageSetting(String stageSetting) {
        this.stageSetting = stageSetting;
    }
    public String getEncryptionModOption(){
        return encryptionModOption;
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
            if(encryptionModOption.equals("encryption")){
                sendMessage = sendInfoEncryptionSelectKey(idChat);
            } else if (encryptionModOption.equals("decryption")) {
                sendMessage = sendInfoDecryptionSelectKey(idChat);
            }
        } else if (this.stageSetting.equals("setKey")) {
            this.key = Integer.parseInt(userMessage);
            System.out.printf("\nБув встановлений ключь %d СТАТУС [ОК]",key);
            this.stageSetting = "full";
            if(encryptionModOption.equals("encryption")){
                sendMessage = sendInfoEncryptionResult(idChat);
            } else if (encryptionModOption.equals("decryption")) {
                sendMessage = sendInfoDecryptionResult(idChat);
            }
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
