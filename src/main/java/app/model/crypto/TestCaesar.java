package app.model.crypto;

public class TestCaesar {
    public static void main(String[] args) {
        String message =  "Ми будемо працювати із симетричним алгоритмом шифрування, тому не вдаватимемося в зайві подробиці.";
        Caesar caesar = new Caesar("LanguageUkraine",message,15);
       System.out.println("Текст повідомлення: "+message);
       caesar.encryption();
       System.out.println("Зашифроване повідомлення: "+caesar.getEncryptionText());
       Caesar caesar1 = new Caesar("LanguageUkraine",caesar.getEncryptionText(),15);
       caesar1.decryption();
       System.out.println("Розшифроване: "+caesar1.getDecryptionText());

       System.out.println("----------------------------------------");
       System.out.println("-____________BruteForce_________________");
       System.out.println("----------------------------------------");
       Caesar bruteForceCaesar =  new Caesar("LanguageUkraine",caesar.getEncryptionText());
       bruteForceCaesar.bruteForce(41);

    }
}
