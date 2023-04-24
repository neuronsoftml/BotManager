package app.model.crypto;

public class TestCaesar {
    public static void main(String[] args) {
        String message =  "Ключ — це набір даних, за допомогою якого виконується шифрування та розшифрування інформації. Успішність дешифрування залежить від ключа, що використовується. Якщо з будь-якої причини втрачено доступ до нього, розшифрувати дані буде неможливо.";
        Caesar caesar = new Caesar("UKRAINIAN",message,15);
       System.out.println("Текст повідомлення: "+message);
       caesar.encryption();
       System.out.println("Зашифроване повідомлення: "+caesar.getEncryptionText());
       Caesar caesar1 = new Caesar("UKRAINIAN",caesar.getEncryptionText(),15);
       caesar1.decryption();
       System.out.println("Розшифроване: "+caesar1.getDecryptionText());

       System.out.println("----------------------------------------");
       System.out.println("-____________BruteForce_________________");
       System.out.println("----------------------------------------");
       Caesar bruteForceCaesar =  new Caesar("UKRAINIAN",caesar.getEncryptionText());
       bruteForceCaesar.bruteForce(41);

    }
}
