public class RealizatorDeForma {
    private Forma cerc;
    private Forma sfera;
    private Forma dreptunghi;

    public RealizatorDeForma()
    {
        cerc=new Cerc();
        sfera=new Sfera();
        dreptunghi=new Dreptunghi();
    }
    public void deseneazaDreptunghi()
    {
        this.dreptunghi.deseneaza();
    }

    public void deseneazaSfera()
    {
        this.sfera.deseneaza();
    }

    public void deseneazaCerc()
    {
        this.cerc.deseneaza();
    }



}
