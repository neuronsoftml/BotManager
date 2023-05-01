package app.model.crypto;

import app.model.auxiliaryTools.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class AnalysisManager {
    private String resultBruteForceText;
    public String start(List<String> variantsOfDecodedTerms){
       analyzerBruteForce(variantsOfDecodedTerms);
       return resultBruteForceText;
    }
    private void analyzerBruteForce(List<String> variantsOfDecodedTerms) {
        FileManager fileManager = new FileManager();
        TreeMap<String,Integer> resultList = new TreeMap<>();
        for (String elementTerms : variantsOfDecodedTerms) {
            List<String> termWordsList = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(elementTerms," ");
            while (stringTokenizer.hasMoreTokens()){
                termWordsList.add(stringTokenizer.nextToken());
            }
            int collScores = scoringWords(termWordsList,fileManager);

            resultList.put(elementTerms,collScores);
        }
        this.resultBruteForceText = checkMaxCollScores(resultList);
    }
    private int scoringWords(List<String> termWordsList, FileManager fileManager){
        int collScores = 0;
        for(String elementWords : termWordsList) {
            char letter = elementWords.toUpperCase().charAt(0);
            List<String> dictionaryOfWords;
            if (fileManager.getData(letter) == null) {
                continue;
            } else {
                dictionaryOfWords = new ArrayList<>(fileManager.getData(letter));
                for (String elementDictionaryWords : dictionaryOfWords) {

                    if (elementWords.toLowerCase().equals(removeSpecialCharacters(elementDictionaryWords).toLowerCase())) {
                        collScores++;
                    }
                }
            }

        }
        return collScores;
    }
    private String checkMaxCollScores(TreeMap<String,Integer> resultList){
        String strResultList = null;
        int  maxValueResultList = 0;
        for(String em : resultList.keySet()){
            Integer value = resultList.get(em);
            //System.out.println(em + " --> " + value);

            if(value > maxValueResultList){
                strResultList = em;
                maxValueResultList = value;
            }
        }
        return  strResultList;
    }
    private String removeSpecialCharacters(String str){
        int length = str.length();
        String result = "";
        char[] arrayStr = str.toCharArray();
        char[] specialCharacters = DateAlphabet.SPECIAL_CHARACTERS.getDate().toCharArray();

        boolean isSpecialCharacters;
        for(int i = 0; i < arrayStr.length; i++){
            isSpecialCharacters = false;
            for(int j = 0; j < specialCharacters.length; j++){
                if(arrayStr[i] == specialCharacters[j]){
                    isSpecialCharacters = true;
                    break;
                }
            }
            if (!isSpecialCharacters){
                result = result + String.valueOf(arrayStr[i]);
            }
        }
        return result;
    }
}
