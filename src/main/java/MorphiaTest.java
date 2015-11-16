import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class MorphiaTest {

    public MorphiaTest() {
        // Morphia Initialize
        final Morphia morphia = new Morphia();

        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
        datastore.ensureIndexes();

        // Add data using entity

        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);

        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

        datastore.save(elmer);

        // Find collections
        List<Employee> employees = datastore.find(Employee.class).asList();

        System.out.println("==============");
        employees.stream().forEach(System.out::println);
        System.out.println("==============");

        System.out.println("==============");
        employees.stream().
                filter(e -> e.getSalary() > 30000.0).forEach(System.out::println);
        System.out.println("==============");

        // Delete collections
        datastore.delete(daffy);
        datastore.delete(pepe);
        datastore.delete(elmer);
    }

}