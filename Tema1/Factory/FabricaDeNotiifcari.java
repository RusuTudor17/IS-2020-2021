public class FabricaDeNotiifcari {
    public Notificare DoNotificare(String tip)
    {
        if(tip==null)
        {
            return null;
        }
        if(tip.equals("SMS"))
        {
            return new NotificareSMS();
        }
        if(tip.equals("Email"))
        {
            return new NotificareEMAIL();
        }
        if(tip.equals("Porumbel"))
        {
            return new NotificarePorumbel();
        }
        return null;
    }
}
