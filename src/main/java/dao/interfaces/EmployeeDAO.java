package dao.interfaces;

import java.util.List;
import model.Employee;

public interface EmployeeDAO {

    public boolean insertEmployee(Employee employee);

    public boolean deleteEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public Employee findByID(int EmployeeID);
    
    public List<Employee> findAll();
    
    public List<Employee> findByExample(Employee employee);
        
}
