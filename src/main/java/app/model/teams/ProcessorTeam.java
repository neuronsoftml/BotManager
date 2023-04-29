package app.model.teams;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ProcessorTeam {
    private String idChat;
    private String sendTeam;
    private SendMessage sendMessage;
    public ProcessorTeam(String idChat, String sendTeam){
        this.idChat = idChat;
        this.sendTeam = sendTeam;
    }
    public SendMessage start(){
        checkCommand();
        return sendMessage;
    }
    private void checkCommand(){
        if(sendTeam.equals(TeamsConfig.START.getTitle())){
            teamStart();
        }

        else if(sendTeam.equals(TeamsConfig.HELP.getTitle())) {
            teamHelp();
        }
    }
    private void teamStart(){
        Start start = new Start(idChat);
        this.sendMessage = start.teamStart();
    }
    private void teamHelp(){
        Help help = new Help(idChat);
        this.sendMessage = help.teamHelpStart();
    }

}

