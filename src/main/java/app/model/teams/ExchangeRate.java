package app.model.teams;

import app.model.keyboard.InlineButton;
import org.apache.commons.io.IOUtils;
import app.model.parsing.MoneyDate;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class ExchangeRate {

    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private List<InlineKeyboardButton> keyboardButtonsRowOne;

    public String start() throws IOException {
        List<MoneyDate> moneyDateList = new ArrayList<>(parserJsonElement());
        StringBuilder message = new StringBuilder();
        for (MoneyDate moneyDate : moneyDateList){
            message
                    .append("\n")
                    .append("[")
                    .append(moneyDate.getCodeMoney())
                    .append(" ]")
                    .append(moneyDate.getNameMoney())
                    .append(": ")
                    .append(moneyDate.getValue())
                    .append(" Час :")
                    .append(moneyDate.getDate())
                    .append("\n");
        }
        return message.toString();
    }

    private String downloadDataMoneyBank() throws IOException {
        String json = IOUtils.toString(URI.create(
                "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json"));
        return json;
    }
    private List<MoneyDate> parserJsonElement() throws IOException {

        List<String> jsonElement = new ArrayList<>(
                parserJsonElement(downloadDataMoneyBank())
        );

        List<MoneyDate> moneyDateList = new ArrayList<>();

        for(String j : jsonElement){
            String[] array;
            array = j.split("  ");
            for(int i = 0; i < array.length; i++){
                String str = array[i].trim();
                if(str.equals("r030")){
                    moneyDateList.add(new MoneyDate(
                            array[i+1],
                            array[i+3],
                            array[i+5],
                            array[i+7],
                            array[i+9]
                    ));
                }
            }
        }
        return moneyDateList;
    }

    private List<String> parserJsonElement(String json){
        List<String> strings = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(json,"{");

        while (stringTokenizer.hasMoreTokens()){
            String str = stringTokenizer.nextToken();
            str = str.replace('}',' ');
            str = str.replace('[',' ');
            str = str.replace(']',' ');
            str = str.replace(',',' ');
            str = str.replace('\n',' ');
            str = str.replace('\"',' ');
            str = str.replace(':',' ');

            //Виляємо пробіли
            str = str.trim();
            strings.add(str);
        }
        return  strings;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup(){
        creatMarkupInlineBack();
        return inlineKeyboardMarkup;
    }
    private void creatMarkupInlineBack(){
        rowList.clear();
        keyboardButtonsRowOne = new ArrayList<>();

        creatKeyboardBack();

        rowList.add(keyboardButtonsRowOne);

        inlineKeyboardMarkup.setKeyboard(rowList);
    }
    private void creatKeyboardBack(){
        InlineButton keyboard = new InlineButton("Повернутися назад","back");
        keyboardButtonsRowOne.add(keyboard.getKeyboardButton());
    }
}
