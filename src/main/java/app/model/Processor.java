package app.model;

import app.model.auxiliaryTools.FileManager;
import app.model.crypto.EncryptionMod;
import app.model.keyboard.InlineButton;
import app.model.keyboard.ProcessorButton;
import app.model.message.Messages;
import app.model.teams.*;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private SendDocument outputDocument;
    private String userInputMessage;
    private String userInputTeam;


    public SendMessage executeProcessorButton(Update update) throws IOException {
        this.inputIdChat = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        this.inputCallBackData = update.getCallbackQuery().getData();
        processorButton = ProcessorButton.getProcessorButton();
        this.outputMessage = processorButton.startProcessorButton(inputCallBackData, inputIdChat);
        return outputMessage;
    }
    public SendMessage executeProcessorFile(String urlFile){
        FileManager fileManager = new FileManager();

        if(checkEncryptionMod()){
            String unencryptedTerm = fileManager.readFile(urlFile);
            this.userInputMessage = unencryptedTerm;
            encryptionMod.setIsOutputDataFile(true);
            executeProcessorEncryptionMod();
            return outputMessage;
        }else {
            fileManager.deleteFile(urlFile);
            return new Messages("Режим криптографії Не включений, файл знищенний.",inputIdChat).creatMessages();
        }
    }
    public SendMessage executeProcessorMessage(Update update){
        System.out.println("Повідомлення отримав, починаю опрацьовувати  СТАТУС [ОК]");
        this.userInputMessage = update.getMessage().getText();
        this.inputIdChat = String.valueOf(update.getMessage().getChatId());

        if(checkCommands(userInputMessage)) {
            executeProcessorTeam();
        }  else if (checkEncryptionMod()) {
            encryptionMod.setIsOutputDataText(true);
            executeProcessorEncryptionMod();
        }
        else {
            this.outputMessage = new Messages(
                    "Невідома команда.\n Для отримання списока команд напишіть /help",
                    inputIdChat).creatMessages();
        }
        return outputMessage;
    }
    private SendMessage executeProcessorTeam(){
        processorTeam = new ProcessorTeam(inputIdChat, userInputTeam);
        this.outputMessage = processorTeam.start();
        return outputMessage;
    }
    private void executeProcessorEncryptionMod(){
        this.outputMessage = encryptionMod.configurationEncryptionMod(userInputMessage, inputIdChat);
        if(encryptionMod.getIsResetEncryptionMod()){
            encryptionMod.resetEncryptionMod();
        }
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
    private boolean checkEncryptionMod(){
        return  encryptionMod != null && encryptionMod.getIsActiveEncryptionMod();
    }

    public SendDocument getOutputDocument(){
        return outputDocument;
    }
    public void setOutputDocument(SendDocument sendDocument){
        this.outputDocument = sendDocument;
    }
}
