package app;import app.model.Bot;import org.telegram.telegrambots.meta.TelegramBotsApi;import org.telegram.telegrambots.meta.exceptions.TelegramApiException;import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;public class Main {    public static void main(String[] args) {        try {            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);            telegramBotsApi.registerBot(new Bot());            System.out.print(                            "___________________________\n"+                            "Бот запустився. СТАТУС [ОК]\n"+                            "___________________________\n");        } catch (TelegramApiException e) {            e.printStackTrace();        }    }}