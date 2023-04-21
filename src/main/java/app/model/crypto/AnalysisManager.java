package app.model.crypto;

public class AnalysisManager {
    private char[] elementAlphabet;

    public void start(String language){
        if (language.equals("ENGLISH")) {
            this.elementAlphabet = DateAlphabet.ENGLISH.getDate().toCharArray();
        } else if (language.equals("UKRAINIAN")) {
            this.elementAlphabet = DateAlphabet.UKRAINIAN.getDate().toCharArray();
        }
    }
}
