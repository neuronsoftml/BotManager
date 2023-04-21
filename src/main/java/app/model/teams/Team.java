package app.model.teams;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<String> teamsList = new ArrayList<>();
    private TeamsConfig[] teams = TeamsConfig.values();
    private static Team Team;
    private Team(){
        setTeamList();
    }

    public static Team getTeam(){
        if(Team == null){
            Team = new Team();
        }
        return Team;
    }
    private void setTeamList(){

        for(TeamsConfig team : teams){
            teamsList.add(team.getTitle());
        }
        System.out.println("_____________________________________________");
        System.out.println("Список команд добавлено СТАТУС [ОК] SIZE: "+ teamsList.size());
        System.out.println("_____________________________________________");
    }

    public List<String> getTeamList() {
        return teamsList;
    }


}
