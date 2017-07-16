package controller;

import dao.concrete.DAOFactoryImpl;
import dao.interfaces.EmployeeDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public final class EmployeeController {

    private final EmployeeDAO employeeDao;

    public EmployeeController() {
        employeeDao = new DAOFactoryImpl().getEmployeeDAO();
    }

    public Employee getByID(int employeeID) {
        return employeeDao.findByID(employeeID);
    }

    public ArrayList<Employee> getByExample(Integer EmployeeID, String firstName, String middleName, String lastName,
            String positionName, Date dateOfEmployment, Date dateOfBirth, String Address, String phoneNumber, String username, Integer hashPassword) {
        Employee employee = new Employee();

        employee.setEmployeeID(EmployeeID);
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setPositionName(positionName);
        employee.setDateOfEmployment(dateOfEmployment);
        employee.setDateOfBirth(dateOfBirth);
        employee.setAddress(Address);
        employee.setPhoneNumber(phoneNumber);
        employee.setUsername(username);
        employee.setHashPassword(hashPassword);

        List<Employee> result = employeeDao.findByExample(employee);
        return new ArrayList(result);
    }

    public ArrayList<Employee> getAll() {
        return new ArrayList(employeeDao.findAll());
    }

    public boolean updateEmployee(int EmployeeID, String firstName, String middleName, String lastName,
            String positionName, Date dateOfEmployment, Date dateOfBirth, String Address, String phoneNumber, String username) {

        Employee employee = new Employee();

        employee.setEmployeeID(EmployeeID);
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setPositionName(positionName);
        employee.setDateOfEmployment(dateOfEmployment);
        employee.setDateOfBirth(dateOfBirth);
        employee.setAddress(Address);
        employee.setPhoneNumber(phoneNumber);
        employee.setUsername(username);

        return employeeDao.updateEmployee(employee);
    }

    public boolean addEmployee(String firstName, String middleName, String lastName,
            String positionName, Date dateOfEmployment, Date dateOfBirth, String Address, String phoneNumber, String username, Integer hashPassword) {

        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setPositionName(positionName);
        employee.setDateOfEmployment(dateOfEmployment);
        employee.setDateOfBirth(dateOfBirth);
        employee.setAddress(Address);
        employee.setPhoneNumber(phoneNumber);
        employee.setUsername(username);
        employee.setHashPassword(hashPassword);

        return employeeDao.insertEmployee(employee);
    }

    public boolean deleteEmployee(int EmployeeID) {
        return employeeDao.deleteEmployee(employeeDao.findByID(EmployeeID));
    }

    public boolean updateEmployeeUsernameAndPassword(int EmployeeID, String username, Integer hashPassword) {
        Employee employee = employeeDao.findByID(EmployeeID);
        if (username != null) {
            employee.setUsername(username);
        }

        if (hashPassword != null) {
            employee.setHashPassword(hashPassword);
        }
        return employeeDao.updateEmployee(employee);
    }

    public String getEmployeeFullNameByID(int EmployeeID) {
        Employee employee = employeeDao.findByID(EmployeeID);
        if (employee == null) {
            return null;
        }
        return employee.getFirstName() + " " + employee.getLastName();
    }
    
}
