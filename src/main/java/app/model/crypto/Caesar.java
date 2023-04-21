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
        this.retreat = retreat;
        this.language = language;

        if (language.equals("LanguageEnglish")) {
            this.dateAlphabet = DateAlphabet.ENGLISH.getDate();
        } else if (language.equals("LanguageUkraine")) {
            this.dateAlphabet = DateAlphabet.UKRAINIAN.getDate();
        }
        this.dataArrayAlphabetToUp = dateAlphabet.toUpperCase().toCharArray();
        this.dataArrayAlphabetToLower = dateAlphabet.toLowerCase().toCharArray();

        this.dataMessage = message.toCharArray();
    }

    public Caesar(String language, String message) {
        this.message = message;
        if (language.equals("LanguageEnglish")) {
            this.dateAlphabet = DateAlphabet.ENGLISH.getDate();
        } else if (language.equals("LanguageUkraine")) {
            this.dateAlphabet = DateAlphabet.UKRAINIAN.getDate();
        }
        this.dataArrayAlphabetToUp = this.dateAlphabet.toUpperCase().toCharArray();
        this.dataArrayAlphabetToLower = this.dateAlphabet.toLowerCase().toCharArray();

        this.dataMessage = this.message.toCharArray();
    }

    public String encryption() {
        String result = "";

        for (int i = 0; i < dataMessage.length; i++) {
            for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (dataArrayAlphabetToLower[y] == dataMessage[i]) {
                    if (y >= dataArrayAlphabetToUp.length - retreat) {
                        indexNewChar = retreat - ((dataArrayAlphabetToUp.length - 1) - y) - 1;
                    } else {
                        indexNewChar = y + retreat;
                    }
                    result = result + dataArrayAlphabetToLower[indexNewChar];
                    break;
                } else if (dataArrayAlphabetToUp[y] == dataMessage[i]) {
                    if (y >= dataArrayAlphabetToUp.length - retreat) {
                        indexNewChar = retreat - ((dataArrayAlphabetToUp.length - 1) - y) - 1;
                    } else {
                        indexNewChar = y + retreat;
                    }
                    result = result + dataArrayAlphabetToUp[indexNewChar];
                    break;
                }
            }
        }

        return result;
    }

    public String decryption() {
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
        return result;
    }

    public void bruteForce(int cipherDepth) {
        List<String> dataBaseResult = new ArrayList<>();
        for (int key = 1; key <= cipherDepth; key++) {
            String result = "";
            for (int i = 0; i < dataMessage.length; i++) {
                for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                    int indexNewChar;

                    if (dataArrayAlphabetToLower[y] == dataMessage[i]) {
                        if (y <= key - 1) {
                            indexNewChar = dataArrayAlphabetToUp.length - (key - y);
                        } else {
                            indexNewChar = y - key;
                        }
                        result = result + dataArrayAlphabetToLower[indexNewChar];
                        break;
                    } else if (dataArrayAlphabetToUp[y] == dataMessage[i]) {
                        if (y <= key - 1) {
                            indexNewChar = dataArrayAlphabetToUp.length - (key - y);
                        } else {
                            indexNewChar = y - key;
                        }
                        result = result + dataArrayAlphabetToUp[indexNewChar];
                        break;
                    }

                }
            }
            dataBaseResult.add(result);
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
}
