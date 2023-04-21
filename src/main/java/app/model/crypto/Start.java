package app.model.crypto;

public class Start {
    public static void main(String[] args) {
        String message =  "Ключ — це набір даних, за допомогою якого виконується шифрування та розшифрування інформації. Успішність дешифрування залежить від ключа, що використовується. Якщо з будь-якої причини втрачено доступ до нього, розшифрувати дані буде неможливо.";
        Caesar caesar = new Caesar("UKRAINIAN",message,15);
       String crypter = caesar.encryption();
       System.out.println("Текст повідомлення: "+message);
       System.out.println("Зашифроване повідомлення: "+crypter);
       Caesar caesar1 = new Caesar("UKRAINIAN",crypter,15);
       System.out.println("Розшифроване: "+caesar1.decryption());

       System.out.println("----------------------------------------");
       System.out.println("-____________BruteForce_________________");
       System.out.println("----------------------------------------");
       Caesar bruteForceCaesar =  new Caesar("UKRAINIAN",crypter);
       bruteForceCaesar.bruteForce(41);

    }
}
