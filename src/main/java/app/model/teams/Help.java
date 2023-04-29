package app.model.teams;

import app.model.message.Messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class Help {
    private String idChat;
    private List<String> teams  = Team.getTeam().getTeamList();

    public Help(String idChat){
        this.idChat = idChat;
    }
    public SendMessage teamHelpStart(){
        Messages messages = new Messages(setText(),idChat);
        return messages.creatMessages();
    }

    public String setText(){
        StringBuilder result = new StringBuilder();

        int coll = 1;
        for(String team : teams){
            result.append(String.format("\n %d) %s", coll, team));
            coll++;
        }
        result.insert(0, "Список доступних команд: ");
        return result.toString();
    }

}
