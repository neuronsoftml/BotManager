package app.model.crypto;

import java.util.ArrayList;
import java.util.List;

public class Caesar {
    private String dateAlphabet;
    private String message;
    private String language;
    private int retreat;
    private char[] dataArrayAlphabetToUp;
    private char[] dataArrayAlphabetToLower;
    private char[] dataMessage;

    public Caesar(String language, String message, int retreat) {
        this.message = message;
        checkSelectLanguage(language);
        checkSelectKey(retreat);
        this.dataArrayAlphabetToUp = dateAlphabet.toUpperCase().toCharArray();
        this.dataArrayAlphabetToLower = dateAlphabet.toLowerCase().toCharArray();

        this.dataMessage = message.toCharArray();
    }
    public Caesar(String language, String message) {
        this.message = message;
        checkSelectLanguage(language);
        this.dataArrayAlphabetToUp = this.dateAlphabet.toUpperCase().toCharArray();
        this.dataArrayAlphabetToLower = this.dateAlphabet.toLowerCase().toCharArray();

        this.dataMessage = this.message.toCharArray();
    }
    private void checkSelectLanguage(String language){
        checkTextLanguage(language);
        if (this.language.equals("LanguageEnglish")) {
            this.dateAlphabet = DateAlphabet.ENGLISH.getDate();
        } else if (this.language.equals("LanguageUkraine")) {
            this.dateAlphabet = DateAlphabet.UKRAINIAN.getDate();
        }
    }
    private void checkTextLanguage(String language){
        if(language.equals("LanguageEnglish")&& checkTextLanguageUkraine() > checkTextLanguageEnglish()){
            this.language = "LanguageUkraine";
        }
        else if(language.equals("LanguageUkraine")&& checkTextLanguageEnglish() > checkTextLanguageUkraine()){
            this.language = "LanguageEnglish";
        }else {
            this.language = language;
        }
    }
    private int checkTextLanguageUkraine(){
        char[] dataArrayAlphabet = DateAlphabet.UKRAINIAN.getDate().toCharArray();
        char[] dataArrayMessage = message.toCharArray();

        int result = 0;
        for(int i = 0; i < dataArrayMessage.length; i++){
            for(int j = 0; j < dataArrayAlphabet.length; j++){
                if(String.valueOf(dataArrayMessage[i]).equals(String.valueOf(dataArrayAlphabet[j]))){
                    result++;
                    break;
                }
            }
        }
        return  result;
    }
    private int checkTextLanguageEnglish(){
        char[] dataArrayAlphabet = DateAlphabet.ENGLISH.getDate().toCharArray();
        char[] dataArrayMessage = message.toCharArray();

        int result = 0;
        for(int i = 0; i < dataArrayMessage.length; i++){
            for(int j = 0; j < dataArrayAlphabet.length; j++){
                if(String.valueOf(dataArrayMessage[i]).equals(String.valueOf(dataArrayAlphabet[j]))){
                    result++;
                    break;
                }
            }
        }
        return  result;
    }
    private void checkSelectKey(int key){
        if(this.language.equals("LanguageUkraine")){
            int maxValue = DateAlphabet.UKRAINIAN.getDate().length()+1;
            if(key > 0 && key < maxValue){
                this.retreat = key;
            }else {
                this.retreat = generationRandomKey(maxValue);
            }
        } else if (this.language.equals("LanguageEnglish")) {
            int maxValue = DateAlphabet.ENGLISH.getDate().length()+1;
            if(key > 0 && key < maxValue){
                this.retreat = key;
            }else {
                this.retreat = generationRandomKey(maxValue);
            }
        }
    }
    private String encryptionText;
    public void encryption() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dataMessage.length; i++) {
            for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (dataArrayAlphabetToLower[y] == dataMessage[i]) {
                    if (y >= dataArrayAlphabetToUp.length - retreat) {
                        indexNewChar = retreat - ((dataArrayAlphabetToUp.length - 1) - y) - 1;
                    } else {
                        indexNewChar = y + retreat;
                    }
                   result.append(dataArrayAlphabetToLower[indexNewChar]);
                    break;
                } else if (dataArrayAlphabetToUp[y] == dataMessage[i]) {
                    if (y >= dataArrayAlphabetToUp.length - retreat) {
                        indexNewChar = retreat - ((dataArrayAlphabetToUp.length - 1) - y) - 1;
                    } else {
                        indexNewChar = y + retreat;
                    }
                    result.append(dataArrayAlphabetToUp[indexNewChar]);
                    break;
                }
            }
        }
        this.encryptionText = result.toString();
    }
    private String decryptionText;
    public void decryption() {
        String result = "";

        for (int i = 0; i < dataMessage.length; i++) {
            for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (dataArrayAlphabetToLower[y] == dataMessage[i]) {
                    if (y <= retreat - 1) {
                        indexNewChar = dataArrayAlphabetToUp.length - (retreat - y);
                    } else {
                        indexNewChar = y - retreat;
                    }
                    result = result + dataArrayAlphabetToLower[indexNewChar];
                    break;
                } else if (dataArrayAlphabetToUp[y] == dataMessage[i]) {
                    if (y <= retreat - 1) {
                        indexNewChar = dataArrayAlphabetToUp.length - (retreat - y);
                    } else {
                        indexNewChar = y - retreat;
                    }
                    result = result + dataArrayAlphabetToUp[indexNewChar];
                    break;
                }
            }
        }
        this.decryptionText = result;
    }

    public void bruteForce(int cipherDepth) {
        List<String> dataBaseResult = new ArrayList<>();
        for (int key = 1; key <= cipherDepth; key++) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < dataMessage.length; i++) {
                for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                    int indexNewChar;

                    if (dataArrayAlphabetToLower[y] == dataMessage[i]) {
                        if (y <= key - 1) {
                            indexNewChar = dataArrayAlphabetToUp.length - (key - y);
                        } else {
                            indexNewChar = y - key;
                        }
                        result.append(dataArrayAlphabetToLower[indexNewChar]);
                        break;
                    } else if (dataArrayAlphabetToUp[y] == dataMessage[i]) {
                        if (y <= key - 1) {
                            indexNewChar = dataArrayAlphabetToUp.length - (key - y);
                        } else {
                            indexNewChar = y - key;
                        }
                        result.append(dataArrayAlphabetToUp[indexNewChar]);
                        break;
                    }

                }
            }
            dataBaseResult.add(result.toString());
        }
        System.out.println(analyzerBruteForce(dataBaseResult));
    }

    public String analyzerBruteForce(List<String> dataBaseResult) {
        char[] dataElement = {'.', '!', '?', ':'};

        List<String> newDataBaseResult = new ArrayList<>();

        for (String element : dataBaseResult) {
            int collLengthWord; //  Довжина слова
            int collLengthComa; //  Кількість ком
            boolean isWordOneUpCase; //Перша буква з великої літери
            boolean isEndOfSentence; //Закінчення речення спец символом .!?:

            int compliancePercentage; //Відсоток правельної відповіді
            System.out.println(element);
        }


        return "";
    }
    private int generationRandomKey(int maxValue){
        System.out.println("спрацював генератор");
        int key;
        int maxKeyValue = maxValue;
        int minKeyValue = 1;
        maxKeyValue -= minKeyValue;
        key = (int) (Math.random() * ++maxKeyValue) + minKeyValue;
        return  key;
    }
    public String getLanguage() {
        return language;
    }
    public int getKey(){
        return retreat;
    }
    public String getEncryptionText(){
        return  encryptionText;
    }
    public String getDecryptionText(){
        return  decryptionText;
    }
}
