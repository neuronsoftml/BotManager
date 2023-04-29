package app.model;

import app.model.crypto.EncryptionMod;
import app.model.keyboard.InlineButton;
import app.model.keyboard.ProcessorButton;
import app.model.message.Messages;
import app.model.teams.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class Processor{
    private Update update;
    private Team team;
    private static Processor processor;
    private static InlineButton inlineButton;
    private static EncryptionMod encryptionMod;
    private static ProcessorButton processorButton;
    private ProcessorTeam processorTeam;

    private Processor(){
        initiation();
    }
    public static Processor getProcessor(){
        if(processor == null){
            processor = new Processor();
            inlineButton = InlineButton.getInlineButton();
        }
        return processor;
    }
    private String inputIdChat;
    private String inputCallBackData;
    private SendMessage outputMessage;
    private String userInputMessage;
    private String userInputTeam;

    public SendMessage processorStart(Update update) throws IOException {
        this.update = update;
        if(update.hasMessage()){
            this.userInputMessage = update.getMessage().getText();
            this.inputIdChat = String.valueOf(update.getMessage().getChatId());
            executeProcessorTeam();
        } else if (update.hasCallbackQuery()) {
            this.inputIdChat = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            this.inputCallBackData = update.getCallbackQuery().getData();
            executeProcessorButton();
        }
        return outputMessage;
    }

    public void executeProcessorTeam(){
        System.out.println("Повідомлення отримав, починаю опрацьовувати  СТАТУС [ОК]");

        if(checkCommands(userInputMessage)) {
            processorTeam = new ProcessorTeam(inputIdChat, userInputTeam);
            this.outputMessage = processorTeam.start();
        } else if (encryptionMod != null && encryptionMod.getIsActiveEncryptionMod()) {
            this.outputMessage = encryptionMod.configurationEncryptionMod(userInputMessage, inputIdChat);
            if(encryptionMod.getIsResetEncryptionMod()){
                encryptionMod.resetEncryptionMod();
            }
        }else {
            this.outputMessage = new Messages(
                    "Невідома команда.\n Для отримання списока команд напишіть /help",
                    inputIdChat).creatMessages();
        }
    }
    public void executeProcessorButton() throws IOException {
        processorButton = ProcessorButton.getProcessorButton();
        this.outputMessage = processorButton.startProcessorButton(inputCallBackData, inputIdChat);
    }
    private boolean checkCommands(String message) {
        char key = message.charAt(0);
        if (String.valueOf(key).equals("/")) {
            this.userInputTeam = message;
            return true;
        }
        return false;
    }

    private void initiation(){
        this.team = Team.getTeam();
    }

    public void setEncryptionMod(EncryptionMod link){
        encryptionMod = link;
    }


}
