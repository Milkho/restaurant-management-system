package dao.concrete;

import dao.interfaces.EmployeeDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public final class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean insertEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "insert into employee (firstName, middleName, lastName, positionName, "
                    + "dateOfEmployment, dateOfBirth, Address, phoneNumber, username, hashPassword) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getMiddleName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPositionName());
            preparedStatement.setDate(5, (Date) employee.getDateOfEmployment());
            preparedStatement.setDate(6, (Date) employee.getDateOfBirth());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPhoneNumber());
            preparedStatement.setString(9, employee.getUsername());
            preparedStatement.setInt(10, employee.getHashPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            //ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            statement.executeUpdate("delete from employee where employeeID = " + employee.getEmployeeID());
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "update employee set firstName =  ? , middleName =  ? , lastName =  ? , positionName =  ? , dateOfEmployment =  ? , dateOfBirth =  ? ,"
                    + " Address =  ? , phoneNumber = ?, username = ? where employeeID = ?");
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getMiddleName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPositionName());
            preparedStatement.setDate(5, (Date) employee.getDateOfEmployment());
            preparedStatement.setDate(6, (Date) employee.getDateOfBirth());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPhoneNumber());
            preparedStatement.setString(9, employee.getUsername());
            preparedStatement.setInt(10, employee.getEmployeeID());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public Employee findByID(int ID) {
        Employee res = null;
        try {

            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee where employeeID = " + ID);

            if (resultSet.next()) {
                res = new Employee();
                res.setEmployeeID(resultSet.getInt("employeeID"));
                res.setFirstName(resultSet.getString("firstName"));
                res.setMiddleName(resultSet.getString("middleName"));
                res.setLastName(resultSet.getString("lastName"));
                res.setPositionName(resultSet.getString("positionName"));
                res.setDateOfEmployment(resultSet.getDate("dateOfEmployment"));
                res.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                res.setAddress(resultSet.getString("Address"));
                res.setPhoneNumber(resultSet.getString("phoneNumber"));
                res.setUsername(resultSet.getString("username"));
                res.setHashPassword(resultSet.getInt("hashPassword"));
            }
            if (resultSet.next()) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            return null;
        }
        return res;
    }

    @Override
    public List<Employee> findAll() {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee order by employeeID");

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeID"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setMiddleName(resultSet.getString("middleName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setPositionName(resultSet.getString("positionName"));
                employee.setDateOfEmployment(resultSet.getDate("dateOfEmployment"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                employee.setUsername(resultSet.getString("username"));
                employee.setHashPassword(resultSet.getInt("hashPassword"));
                result.add(employee);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    @Override
    public List<Employee> findByExample(Employee filterEmployee) {
        ArrayList<Employee> result = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "select * from employee "
                    + "where (employeeID = ? or ? = -2147483648)"
                    + "and (middleName = ? or ? is null) "
                    + "and (positionName = ? or ? is null)"
                    + "and (dateOfEmployment = ? or ? = '1900-01-01')"
                    + "and (dateOfBirth = ? or ? = '1900-01-01')"
                    + "and (phoneNumber = ? or ? is null)"
                    + "and (username = ? or ? is null)"
                    + "and (hashPassword = ? or ? = -2147483648)"
                    + "order by employeeID");

            if (filterEmployee.getEmployeeID() == null) {
                preparedStatement.setInt(1, Integer.MIN_VALUE);
                preparedStatement.setInt(2, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(1, filterEmployee.getEmployeeID());
                preparedStatement.setInt(2, filterEmployee.getEmployeeID());
            }
            preparedStatement.setString(3, filterEmployee.getMiddleName());
            preparedStatement.setString(4, filterEmployee.getMiddleName());
            preparedStatement.setString(5, filterEmployee.getPositionName());
            preparedStatement.setString(6, filterEmployee.getPositionName());
            
            if (filterEmployee.getDateOfEmployment() == null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                java.util.Date parsed = format.parse("19000101");
                preparedStatement.setDate(7, new Date(parsed.getTime()));
                preparedStatement.setDate(8, new Date(parsed.getTime()));
            } else {

                preparedStatement.setDate(7, (Date) filterEmployee.getDateOfEmployment());
                preparedStatement.setDate(8, (Date) filterEmployee.getDateOfEmployment());
            }
            if (filterEmployee.getDateOfBirth() == null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                java.util.Date parsed = format.parse("19000101");
                preparedStatement.setDate(9, new Date(parsed.getTime()));
                preparedStatement.setDate(10, new Date(parsed.getTime()));
            } else {
                preparedStatement.setDate(9, (Date) filterEmployee.getDateOfBirth());
                preparedStatement.setDate(10, (Date) filterEmployee.getDateOfBirth());
            }
            preparedStatement.setString(11, filterEmployee.getPhoneNumber());
            preparedStatement.setString(12, filterEmployee.getPhoneNumber());
            preparedStatement.setString(13, filterEmployee.getUsername());
            preparedStatement.setString(14, filterEmployee.getUsername());
            if (filterEmployee.getHashPassword() == null) {
                preparedStatement.setInt(15, Integer.MIN_VALUE);
                preparedStatement.setInt(16, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(15, filterEmployee.getHashPassword());
                preparedStatement.setInt(16, filterEmployee.getHashPassword());
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeID"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setMiddleName(resultSet.getString("middleName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setPositionName(resultSet.getString("positionName"));
                employee.setDateOfEmployment(resultSet.getDate("dateOfEmployment"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                employee.setUsername(resultSet.getString("username"));
                employee.setHashPassword(resultSet.getInt("hashPassword"));
                result.add(employee);
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return result;
    }
}
