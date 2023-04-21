package app.model.teams;

public enum TeamsConfig {
    START("/start"),
    HELP("/help"),
    EXIT("/exit");

    private String title;

    TeamsConfig(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
