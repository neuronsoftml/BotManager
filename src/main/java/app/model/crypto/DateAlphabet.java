package app.model.crypto;

public enum DateAlphabet {
    ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ.,\":-!? "),
    UKRAINIAN("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩьЮЯ,.\":-!? ");

    private String date;
    DateAlphabet(String s) {
        this.date = s;
    }

    public String getDate(){
        return date;
    }
}
