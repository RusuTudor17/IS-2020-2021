//alt exemplu, nu cel de pe site.
public class MainClass {
    public static void main(String[] args) {
        Department financial= new FinancialD();
        Department sales= new SalesD();

        Head head= new Head();

        head.adauga(sales);
        head.adauga(financial);

        //head print
        System.out.println("Head print:");
        head.printDepartament();
        System.out.println("Sales print:");
        sales.printDepartament();
        System.out.println("Financial print:");
        financial.printDepartament();

    }
}
