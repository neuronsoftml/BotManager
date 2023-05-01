package app.model.crypto;

public class TestCaesar {
    public static void main(String[] args) {
        String message =  "Але щоб ви зрозуміли, звідки виникає це хибне уявлення людей, цуратись насолоди і вихваляти страждання, я розкрию перед вами всю картину і роз’ясню, що саме говорив цей чоловік, який відкрив істину, якого я б назвав зодчим щасливого життя. Дійсно, ніхто не відкидає, не зневажає, не уникає насолод тільки через те, що це насолоди, але лише через те, що тих, хто не вміє розумно вдаватися насолоді, осягають великі страждання. Так само як немає нікого, хто полюбивши, вважав за краще і зажадав би саме страждання тільки за те, що це страждання, а не тому, що інший раз виникають такі обставини, коли страждання і біль приносять якесь і чималу насолоду. Якщо скористатися найпростішим прикладом, то хто з нас став би займатися якими б то не було тяжкими фізичними вправами, якщо б це не приносило з собою якоїсь користі? І хто міг би по справедливості дорікнути прагнення до насолоди, яке не несло б з собою ніяких неприємностей, або того, хто уникав би такого страждання, яке не приносило б з собою ніякої насолоди?";
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
        bruteForceCaesar.bruteForce();
       System.out.println(bruteForceCaesar.getBruteForceText());

    }
}
