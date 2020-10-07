
//seamana cu exemplul de la code examples, am mai schimbat putin cand am testat designul
public class MainSingleton {
    public static void main(String[] args) {
        Singleton s1;
        Singleton s2;
        s1=Singleton.getInstance(1);
        s2=Singleton.getInstance(2);

        System.out.println(s1.getValoare());
        System.out.println(s2.getValoare());

    }
}
