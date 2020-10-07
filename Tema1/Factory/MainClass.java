//un alt exemplu,nu e cel de la code examples
public class MainClass {
    public static void main(String[] args) {
        FabricaDeNotiifcari f=new FabricaDeNotiifcari();
        Notificare notificare=f.DoNotificare("Porumbel");
        notificare.Notify();
    }
}
