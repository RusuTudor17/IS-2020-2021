public final class Singleton {
    private static Singleton instance;
    private int valoare;

    private Singleton(int valoare)
    {
        this.valoare=valoare;
    }

    public static Singleton getInstance(int valoare)
    {
        if(instance==null){
            instance=new Singleton(valoare);
        }
        return instance;
    }

    public int getValoare() {
        return valoare;
    }
}
