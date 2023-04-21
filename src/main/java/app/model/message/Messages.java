package app.model.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.nio.charset.StandardCharsets;

public class Messages {
    private String text;
    private String id;
    private SendMessage sendMessage;

    private InlineKeyboardMarkup inlineKeyboardMarkup;

    public Messages(String text, String id){
        this.text = text;
        this.id = id;
    }

    public Messages(String text, String id, InlineKeyboardMarkup inlineKeyboardMarkup){
        this.text = text;
        this.id = id;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

    public SendMessage creatMessages(){
        sendMessage = new SendMessage();
        sendMessage.setChatId(this.id);
        sendMessage.setText(convectorTextFormatUTF_8(this.text));

        if(inlineKeyboardMarkup !=  null){
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }

    private String convectorTextFormatUTF_8(String srt){
        String result = new String(srt.getBytes(),StandardCharsets.UTF_8);
        return result;
    }

}
