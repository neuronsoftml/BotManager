package app.model.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InlineButton {
    private InlineKeyboardButton keyboardButton;
    private List<String> inlineDateButtonList = new ArrayList<>();
    private static InlineButton inlineButton;
    public InlineButton(String textButton, String callBackData){
        this.keyboardButton = new InlineKeyboardButton();
        keyboardButton.setText(convectorTextFormatUTF_8(textButton));
        keyboardButton.setCallbackData(convectorTextFormatUTF_8(callBackData));
    }

    public InlineButton() {
        setInlineDateButtonList();
    }

    public static InlineButton getInlineButton(){
        if(inlineButton == null){
            inlineButton = new InlineButton();
        }
        return inlineButton;
    }
    public InlineKeyboardButton getKeyboardButton() {
        return keyboardButton;
    }

    public List<String> getInlineDateButtonList() {
        return inlineDateButtonList;
    }

    private InlineButtonConfig[] inlineButtonConfigs =  InlineButtonConfig.values();
    public void  setInlineDateButtonList(){
        for (InlineButtonConfig i : inlineButtonConfigs){
            inlineDateButtonList.add(i.getDateTitle());
        }
        System.out.println("_____________________________________________________");
        System.out.println("Список вбудованих кнопок добавлено СТАТУС [ОК] SIZE: "+ inlineDateButtonList.size());
        System.out.println("_____________________________________________________");
    }

    private String convectorTextFormatUTF_8(String srt){
        String result = new String(srt.getBytes(), StandardCharsets.UTF_8);
        return result;
    }

}
