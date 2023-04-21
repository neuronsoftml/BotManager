package app.model.teams;

import app.model.keyboard.InlineButton;
import app.model.message.Messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Start{
    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    private InlineKeyboardMarkup  inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private String idChat;
    public Start(String idChat){
        this.idChat = idChat;
    }
    public SendMessage teamStart() {
        creatMarkupInline();

        Messages messages = new Messages(getTextMessage(), idChat,inlineKeyboardMarkup);

        return messages.creatMessages();
    }

    private List<InlineKeyboardButton> keyboardButtonsRowOne;
    private List<InlineKeyboardButton> keyboardButtonsRowTwo;
    private void creatMarkupInline(){
        keyboardButtonsRowOne = new ArrayList<>();
        keyboardButtonsRowTwo = new ArrayList<>();

        creatKeyboardMoney();
        creatKeyboardBitcoin();
        creatKeyboardConvectorMoney();
        creatEncryptionMod();

        rowList.add(keyboardButtonsRowOne);
        rowList.add(keyboardButtonsRowTwo);

        inlineKeyboardMarkup.setKeyboard(rowList);
    }

    private void creatKeyboardMoney(){
        InlineButton keyboardMoney = new InlineButton("Курс Валют \n (НБУ)","ExchangeRate");
        keyboardButtonsRowOne.add(keyboardMoney.getKeyboardButton());
    }

    private void creatKeyboardBitcoin(){
        InlineButton keyboardBitcoin = new InlineButton("Курс Bitcoin","Ти нажав на кнопку \" Кус Bitcoin\" ");
        keyboardButtonsRowOne.add(keyboardBitcoin.getKeyboardButton());
    }
    private void creatKeyboardConvectorMoney(){
        InlineButton keyboardConvectorMoney = new InlineButton("Convector money","Convector money");
        keyboardButtonsRowTwo.add(keyboardConvectorMoney.getKeyboardButton());
    }

    private void creatEncryptionMod(){
        InlineButton keyboardEncryptionMod = new InlineButton("Шифр \"Цезарь\" ", "encryptionMod");
        keyboardButtonsRowTwo.add(keyboardEncryptionMod.getKeyboardButton());
    }

    private String  getTextMessage(){
        String str = "Привіт мене звани \"Jarvis\" мене створили щоб допомогати людям\n"+
                "1) Дізнаватися про курс валют\n"+
                "2) Дізнаватися про курс Bitcoin\n"+
                "3) Працювати з шифром Цезаря\n"+
                "4) Конвертувати гроші\n";

        return str;
    }

}
