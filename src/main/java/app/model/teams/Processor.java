package app.model.teams;

import app.model.crypto.EncryptionMod;
import app.model.keyboard.InlineButton;
import app.model.keyboard.InlineButtonConfig;
import app.model.message.Messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor{
    private Update update;
    private Team team;
    private InlineButton inlineButtonDate;
    private static Processor processor;
    private static EncryptionMod encryptionMod;

    private Processor(){
        initiation();
        this.teamsList = team.getTeamList();
        this.dateButtonList = inlineButtonDate.getInlineDateButtonList();
    }
    public static Processor getProcessor(){
        if(processor == null){
            processor = new Processor();
        }
        return processor;
    }

    private String idChat;
    private String dataButton;
    private SendMessage sendMessage;
    private String userMessage;
    private String userSendTeam;

    public SendMessage processorStart(Update update) throws IOException {
        this.update = update;
        if(update.hasMessage()){
            this.userMessage = update.getMessage().getText();
            this.idChat = String.valueOf(update.getMessage().getChatId());
            processorTeam();
        } else if (update.hasCallbackQuery()) {
            this.idChat = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            this.dataButton = update.getCallbackQuery().getData();
            this.dateButtonList = new ArrayList<>(InlineButton.getInlineButton().getInlineDateButtonList());
            processorButton();
        }
        return sendMessage;
    }

    private List<String> teamsList;
    public SendMessage processorTeam(){
        System.out.println("Повідомлення отримав, починаю опрацьовувати  СТАТУС [ОК]");

        if(checkCommands(userMessage)) {
             if(userSendTeam.equals(teamsList.get(teamsList.indexOf(TeamsConfig.START.getTitle())))){
                 getMenuStart();
                 return sendMessage;
             }

            if(userSendTeam.equals(teamsList.get(teamsList.indexOf(TeamsConfig.HELP.getTitle())))) {
                Help help = new Help(idChat, teamsList);
                this.sendMessage = help.teamHelpStart();
                return sendMessage;
            }
        } else if (encryptionMod != null && encryptionMod.getIsActiveEncryptionMod()) {
            this.sendMessage = encryptionMod.configurationEncryptionMod(userMessage,idChat);
            if(encryptionMod.getIsResetEncryptionMod()){
                encryptionMod.resetEncryptionMod();
            }
            return sendMessage;
        }
        this.sendMessage = new Messages(
                "Невідома команда.\n Для отримання списока команд напишіть /help",
                idChat).creatMessages();
        return sendMessage;
    }
    private List<String> dateButtonList;
    public SendMessage processorButton() throws IOException {

        final String EXCHANGE_RATE = InlineButtonConfig.EXCHANGE_RATE.getDateTitle();
        final String ENCRYPTION_MOD = InlineButtonConfig.ENCRYPTION_MOD.getDateTitle();
        final String ENCRYPTION = InlineButtonConfig.ENCRYPTION.getDateTitle();
        final String DECRYPTION = InlineButtonConfig.DECRYPTION.getDateTitle();
        final String BRUT_FORCE = InlineButtonConfig.BRUT_FORCE.getDateTitle();
        final String LANG_UK = InlineButtonConfig.LANG_UK.getDateTitle();
        final String LANG_ENG = InlineButtonConfig.LANG_ENG.getDateTitle();
        final String BACK = InlineButtonConfig.BACK.getDateTitle();

        if(dataButton.equals(dateButtonList.get(dateButtonList.indexOf(EXCHANGE_RATE)))){
            ExchangeRate exchangeRate = new ExchangeRate();
            this.sendMessage = new Messages(
                    exchangeRate.start(),idChat,exchangeRate.getInlineKeyboardMarkup()
            ).creatMessages();
        }

        //EncryptionMod
        else if (dataButton.equals(dateButtonList.get(dateButtonList.indexOf(ENCRYPTION_MOD)))) {
            encryptionMod = EncryptionMod.getEncryptionMod();
            encryptionMod.setActiveEncryptionMod(true);
            this.sendMessage = encryptionMod.sendInfoEncryptionModMenu(idChat);
        }
        else if(dataButton.equals(dateButtonList.get(dateButtonList.indexOf(ENCRYPTION)))){
            this.sendMessage = encryptionMod.sendInfoEncryptionSelectLanguage(idChat);
            encryptionMod.setEncryptionModOption(ENCRYPTION);
        }
        else if(dataButton.equals(dateButtonList.get(dateButtonList.indexOf(DECRYPTION)))){
            encryptionMod.setEncryptionModOption(DECRYPTION);
        }
        else if(dataButton.equals(dateButtonList.get(dateButtonList.indexOf(BRUT_FORCE)))){
            encryptionMod.setEncryptionModOption(BRUT_FORCE);
        }
        else if(dataButton.equals(dateButtonList.get(dateButtonList.indexOf(LANG_UK)))){
            encryptionMod.setLanguage(LANG_UK);
            encryptionMod.setStageSetting("setTextUser");
            this.sendMessage = encryptionMod.sendInfoEncryptionSelectTextOrFile(idChat);
        }
        else if(dataButton.equals(dateButtonList.get(dateButtonList.indexOf(LANG_ENG)))){
            encryptionMod.setLanguage(LANG_ENG);
            encryptionMod.setStageSetting("setTextUser");
            this.sendMessage = encryptionMod.sendInfoEncryptionSelectTextOrFile(idChat);
        } else if (dataButton.equals(dateButtonList.get(dateButtonList.indexOf(BACK)))) {
            getMenuStart();
            return sendMessage;
        }

        return sendMessage;
    }
    private boolean checkCommands(String message) {
        char key = message.charAt(0);
        if (String.valueOf(key).equals("/")) {
            this.userSendTeam = message;
            return true;
        }
        return false;
    }

    private void initiation(){
        this.team = Team.getTeam();
        this.inlineButtonDate = InlineButton.getInlineButton();
    }

    private void getMenuStart(){
        Start start = new Start(idChat);
        this.sendMessage = start.teamStart();
    }

}
