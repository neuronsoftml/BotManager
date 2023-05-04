package app.model.auxiliaryTools;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileManager {
    public List<String> getData(char letter){
        String urlFile = getLetterDbUrl(letter);
        if(urlFile == null){
            return  null;
        }
        List<String> alphabetData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(urlFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                alphabetData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return alphabetData;
    }


    private String getLetterDbUrl(char letter){

        if(letter == 'A'){
            return ConfigFileUrl.A.getUrlLink();
        }
        else if(letter == 'В'){
            return ConfigFileUrl.V.getUrlLink();
        }
        else if(letter == 'Б'){
            return ConfigFileUrl.B.getUrlLink();
        }
        else if(letter == 'Г'){
            return ConfigFileUrl.G.getUrlLink();
        }
        else if(letter == 'Д'){
            return ConfigFileUrl.D.getUrlLink();
        }
        else if(letter == 'Е'){
            return ConfigFileUrl.E.getUrlLink();
        }
        else if(letter == 'Ж'){
            return ConfigFileUrl.J.getUrlLink();
        }
        else if(letter == 'З'){
            return ConfigFileUrl.Z.getUrlLink();
        }
        else if(letter == 'И'){
            return ConfigFileUrl.W.getUrlLink();
        }
        else if(letter == 'Є'){
            return ConfigFileUrl.IA.getUrlLink();
        }
        else if(letter == 'І'){
            return ConfigFileUrl.I.getUrlLink();
        }
        else if(letter == 'Ї'){
            return ConfigFileUrl.II.getUrlLink();
        }
        else if(letter == 'Й'){
            return ConfigFileUrl.IW.getUrlLink();
        }
        else if(letter == 'К'){
            return ConfigFileUrl.K.getUrlLink();
        }
        else if(letter == 'Л'){
            return ConfigFileUrl.L.getUrlLink();
        }
        else if(letter == 'М'){
            return ConfigFileUrl.M.getUrlLink();
        }
        else if(letter == 'Н'){
            return ConfigFileUrl.H.getUrlLink();
        }
        else if(letter == 'О'){
            return ConfigFileUrl.O.getUrlLink();
        }
        else if(letter == 'П'){
            return ConfigFileUrl.P.getUrlLink();
        }
        else if(letter == 'Р'){
            return ConfigFileUrl.R.getUrlLink();
        }
        else if(letter == 'С'){
            return ConfigFileUrl.C.getUrlLink();
        }
        else if(letter == 'Т'){
            return ConfigFileUrl.T.getUrlLink();
        }
        else if(letter == 'У'){
            return ConfigFileUrl.U.getUrlLink();
        }
        else if(letter == 'Ф'){
            return ConfigFileUrl.F.getUrlLink();
        }
        else if(letter == 'Х'){
            return ConfigFileUrl.X.getUrlLink();
        }
        return null;
    }




    public String writerFile(String data, String type){
        String urlResultFile = ConfigFileUrl.SEND_FILE.getUrlLink()+type;
        try(FileWriter writer = new FileWriter(urlResultFile)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return urlResultFile;
    }
    public String readFile(String url){
       String unencryptedTerm = null;
        try(FileInputStream fileInputStream = new FileInputStream(url);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            unencryptedTerm = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return unencryptedTerm;
    }
    public void deleteFile(String url){
        File file = new File(url);
        file.delete();
        System.out.println("Файл був знищений");
    }
}
