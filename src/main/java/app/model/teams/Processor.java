package app.model.teams;

import app.model.crypto.EncryptionMod;
import app.model.keyboard.InlineButton;
import app.model.keyboard.InlineButtonConfig;
import app.model.message.Messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.List;

public class Processor{
    private Update update;
    private Team team;
    private static Processor processor;
    private static InlineButton inlineButton;
    private static EncryptionMod encryptionMod;

    private Processor(){
        initiation();
        this.teamsList = team.getTeamList();
    }
    public static Processor getProcessor(){
        if(processor == null){
            processor = new Processor();
            inlineButton = InlineButton.getInlineButton();
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
            processorButton();
        }
        return sendMessage;
    }

    private List<String> teamsList;
    public SendMessage processorTeam(){
        System.out.println("Повідомлення отримав, починаю опрацьовувати  СТАТУС [ОК]");

        if(checkCommands(userMessage)) {
             if(userSendTeam.equals(TeamsConfig.START.getTitle())){
                 getMenuStart();
                 return sendMessage;
             }

            if(userSendTeam.equals(TeamsConfig.HELP.getTitle())) {
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

    public SendMessage processorButton() throws IOException {

        if(dataButton.equals(InlineButtonConfig.EXCHANGE_RATE.getDateTitle())){
            ExchangeRate exchangeRate = new ExchangeRate();
            this.sendMessage = new Messages(
                    exchangeRate.start(),idChat,exchangeRate.getInlineKeyboardMarkup()
            ).creatMessages();
        }

        //EncryptionMod
        else if (dataButton.equals(InlineButtonConfig.ENCRYPTION_MOD.getDateTitle())) {
            encryptionMod = EncryptionMod.getEncryptionMod();
            encryptionMod.setActiveEncryptionMod(true);
            this.sendMessage = encryptionMod.sendInfoEncryptionModMenu(idChat);
        }
        else if(dataButton.equals(InlineButtonConfig.ENCRYPTION.getDateTitle())){
            this.sendMessage = encryptionMod.sendInfoEncryptionSelectLanguage(idChat);
            encryptionMod.setEncryptionModOption(InlineButtonConfig.ENCRYPTION.getDateTitle());
        }
        else if(dataButton.equals(InlineButtonConfig.DECRYPTION.getDateTitle())){
            this.sendMessage = encryptionMod.sendInfoDecryptionSelectLanguage(idChat);
            encryptionMod.setEncryptionModOption(InlineButtonConfig.DECRYPTION.getDateTitle());
        }
        else if(dataButton.equals(InlineButtonConfig.BRUT_FORCE.getDateTitle())){
            encryptionMod.setEncryptionModOption(InlineButtonConfig.BRUT_FORCE.getDateTitle());
        }
        else if(dataButton.equals(InlineButtonConfig.LANG_UK.getDateTitle())){
            encryptionMod.setLanguage(InlineButtonConfig.LANG_UK.getDateTitle());
            encryptionMod.setStageSetting("setTextUser");
            if(encryptionMod.getEncryptionModOption().equals("encryption")){
                this.sendMessage = encryptionMod.sendInfoEncryptionSelectTextOrFile(idChat);
            } else if (encryptionMod.getEncryptionModOption().equals("decryption")) {
                this.sendMessage = encryptionMod.sendInfoDecryptionSelectTextOrFile(idChat);
            }
        }
        else if(dataButton.equals(InlineButtonConfig.LANG_ENG.getDateTitle())){
            encryptionMod.setLanguage(InlineButtonConfig.LANG_ENG.getDateTitle());
            encryptionMod.setStageSetting("setTextUser");
            if(encryptionMod.getEncryptionModOption().equals("encryption")){
                this.sendMessage = encryptionMod.sendInfoEncryptionSelectTextOrFile(idChat);
            } else if (encryptionMod.getEncryptionModOption().equals("decryption")) {
                this.sendMessage = encryptionMod.sendInfoDecryptionSelectTextOrFile(idChat);
            }
        } else if (dataButton.equals(InlineButtonConfig.BACK.getDateTitle())) {
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
    }

    private void getMenuStart(){
        Start start = new Start(idChat);
        this.sendMessage = start.teamStart();
    }

}
