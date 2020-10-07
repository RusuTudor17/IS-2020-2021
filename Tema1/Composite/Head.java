import java.util.ArrayList;
import java.util.List;

public class Head implements Department{

    private List<Department> departamente;

    @Override
    public void printDepartament() {
        for(Department d:departamente)
        {
            d.printDepartament();
        }
    }

    public Head()
    {
        this.departamente=new ArrayList<>();
    }

    public void adauga(Department department) {
        departamente.add(department);
    }

    public void scoate(Department department) {
        departamente.remove(department);
    }


}
