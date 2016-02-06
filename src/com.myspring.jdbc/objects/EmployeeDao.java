package objects;

import java.util.List;

/**
 * Created by Geksa on 18.12.15.
 */
public interface EmployeeDao {

    public void saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
}
