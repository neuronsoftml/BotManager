package app.model.keyboard;

import app.model.Processor;
import app.model.crypto.EncryptionMod;
import app.model.message.Messages;
import app.model.teams.ExchangeRate;
import app.model.teams.Start;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

public class ProcessorButton {

    private static ProcessorButton processorButton;
    private static EncryptionMod encryptionMod;
    private String inputCallBackData;
    private SendMessage outputSendMessage;
    private String inputIdChat;

    public ProcessorButton(){

    }
    public static ProcessorButton getProcessorButton(){
        if(processorButton == null){
            processorButton = new ProcessorButton();
        }
        return processorButton;
    }

    public SendMessage startProcessorButton(String callBackData, String idChat) throws IOException {
        this.inputCallBackData = callBackData;
        this.inputIdChat = idChat;
        checkDataButton();
        return outputSendMessage;
    }

    private void checkDataButton() throws IOException {
        if(inputCallBackData.equals(InlineButtonConfig.EXCHANGE_RATE.getDateTitle())){
            exchangeRate();
        }
        else if (inputCallBackData.equals(InlineButtonConfig.ENCRYPTION_MOD.getDateTitle())) {
            encryptionMod();
        }
        else if(inputCallBackData.equals(InlineButtonConfig.ENCRYPTION.getDateTitle())){
            encryption();
        }
        else if(inputCallBackData.equals(InlineButtonConfig.DECRYPTION.getDateTitle())){
            decryption();
        }
        else if(inputCallBackData.equals(InlineButtonConfig.BRUT_FORCE.getDateTitle())){
            brutForce();
        }
        else if(inputCallBackData.equals(InlineButtonConfig.LANG_UK.getDateTitle())){
            setLangUK();
        }
        else if(inputCallBackData.equals(InlineButtonConfig.LANG_ENG.getDateTitle())){
          setLangENG();
        }
        else if (inputCallBackData.equals(InlineButtonConfig.BACK.getDateTitle())) {
            getMenuStart();
        }else {
            errorSendMessage();
        }
    }
    private void exchangeRate() throws IOException {
        ExchangeRate exchangeRate = new ExchangeRate();
        this.outputSendMessage = new Messages(
                exchangeRate.start(), inputIdChat,exchangeRate.getInlineKeyboardMarkup()
        ).creatMessages();
    }
    private void encryptionMod(){
        encryptionMod = EncryptionMod.getEncryptionMod();
        encryptionMod.setActiveEncryptionMod(true);
        Processor processor = Processor.getProcessor();
        processor.setEncryptionMod(encryptionMod);
        this.outputSendMessage = encryptionMod.sendInfoEncryptionModMenu(inputIdChat);
    }
    private void encryption(){
        this.outputSendMessage = encryptionMod.sendInfoEncryptionSelectLanguage(inputIdChat);
        encryptionMod.setEncryptionModOption(InlineButtonConfig.ENCRYPTION.getDateTitle());
    }
    private void decryption(){
        this.outputSendMessage = encryptionMod.sendInfoDecryptionSelectLanguage(inputIdChat);
        encryptionMod.setEncryptionModOption(InlineButtonConfig.DECRYPTION.getDateTitle());
    }
    private void brutForce(){
        encryptionMod.setEncryptionModOption(InlineButtonConfig.BRUT_FORCE.getDateTitle());
    }
    private void setLangUK(){
        encryptionMod.setLanguage(InlineButtonConfig.LANG_UK.getDateTitle());
        encryptionMod.setStageSetting("setTextUser");
        if(encryptionMod.getEncryptionModOption().equals("encryption")){
            this.outputSendMessage = encryptionMod.sendInfoEncryptionSelectTextOrFile(inputIdChat);
        } else if (encryptionMod.getEncryptionModOption().equals("decryption")) {
            this.outputSendMessage = encryptionMod.sendInfoDecryptionSelectTextOrFile(inputIdChat);
        }
    }
    private void setLangENG(){
        encryptionMod.setLanguage(InlineButtonConfig.LANG_ENG.getDateTitle());
        encryptionMod.setStageSetting("setTextUser");
        if(encryptionMod.getEncryptionModOption().equals("encryption")){
            this.outputSendMessage = encryptionMod.sendInfoEncryptionSelectTextOrFile(inputIdChat);
        } else if (encryptionMod.getEncryptionModOption().equals("decryption")) {
            this.outputSendMessage = encryptionMod.sendInfoDecryptionSelectTextOrFile(inputIdChat);
        }
    }
    private void getMenuStart(){
        Start start = new Start(inputIdChat);
        this.outputSendMessage = start.teamStart();
    }
    private void errorSendMessage(){
        this.outputSendMessage = new Messages("Невідома кнопка або помилка", inputIdChat).creatMessages();
    }

}
