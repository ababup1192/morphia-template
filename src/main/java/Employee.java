import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity("employees")
@Indexes(
        @Index(value = "salary", fields = @Field("salary"))
)
public class Employee {
    @Id
    private ObjectId id;
    private String name;
    @Reference
    private Employee manager;
    @Reference
    private List<Employee> directReports;
    @Property("wage")
    private Double salary;

    public Employee(){}

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
        directReports = new ArrayList<Employee>();
    }

    public List<Employee> getDirectReports(){
        return directReports;
    }


    public Double getSalary(){
        return salary;
    }

    @Override
    public String toString(){
        return "Employee(" + id  + ", " + name + ", " + salary + ")";
    }

}