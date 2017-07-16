package dao.concrete;

import dao.interfaces.EmployeeDAO;
import java.sql.Date;
import java.util.List;
import model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class EmployeeDAOImplTest {

    private final EmployeeDAO employeeDAO = new DAOFactoryImpl().getEmployeeDAO();
    private Employee testEmployee;

    @Before
    public void setUp() {
        testEmployee = new Employee();
    }

    @Test
    public void findEmployeeByExample() {

        testEmployee.setFirstName("rndName");
        testEmployee.setMiddleName("rndName");
        testEmployee.setLastName("rndName");
        testEmployee.setDateOfEmployment(new Date(1545454));
        testEmployee.setDateOfBirth(new Date(4575454));
        testEmployee.setAddress("rndAddress");
        testEmployee.setPhoneNumber("rndphoneNumber");
        testEmployee.setPositionName("cook");
        testEmployee.setUsername("rndName");
        testEmployee.setHashPassword(125);

        employeeDAO.insertEmployee(testEmployee);

        List<Employee> recievedEmployeees = employeeDAO.findByExample(testEmployee);

        for (Employee recievedEmployee : recievedEmployeees) {
            assertEquals(recievedEmployee.getFirstName(), testEmployee.getFirstName());
            assertEquals(recievedEmployee.getMiddleName(), testEmployee.getMiddleName());
            assertEquals(recievedEmployee.getAddress(), testEmployee.getAddress());
            assertEquals(recievedEmployee.getPhoneNumber(), testEmployee.getPhoneNumber());
            assertEquals(recievedEmployee.getPositionName(), testEmployee.getPositionName());
            assertEquals(recievedEmployee.getUsername(), testEmployee.getUsername());
            assertEquals(recievedEmployee.getHashPassword(), testEmployee.getHashPassword());
        }
    }

    @Test
    public void findEmployeeByUnexistingID() {

        assertEquals(employeeDAO.findByID(-1), null);
    }

    @Test
    public void findAllEmployee() {
        testEmployee.setFirstName("rndName");
        testEmployee.setMiddleName("rndName");
        testEmployee.setLastName("rndName");
        testEmployee.setDateOfEmployment(new Date(1545454));
        testEmployee.setDateOfBirth(new Date(4575454));
        testEmployee.setAddress("rndAddress");
        testEmployee.setPhoneNumber("rndphoneNumber");
        testEmployee.setPositionName("cook");
        testEmployee.setUsername("rndName");
        testEmployee.setHashPassword(125);

        employeeDAO.insertEmployee(testEmployee);

        assertTrue(employeeDAO.findAll().size() > 0);

    }

    @Test(expected = NullPointerException.class)
    public void insertUnvalidEmployee() {
        testEmployee.setFirstName(null);
        testEmployee.setMiddleName(null);
        testEmployee.setLastName(null);
        testEmployee.setDateOfEmployment(null);
        testEmployee.setDateOfBirth(null);
        testEmployee.setAddress(null);
        testEmployee.setPhoneNumber(null);
        testEmployee.setPositionName(null);
        testEmployee.setUsername(null);
        testEmployee.setHashPassword(null);

        employeeDAO.insertEmployee(testEmployee);
    }

    @Test
    public void insertEmployee() {
        testEmployee.setFirstName("rndName");
        testEmployee.setMiddleName("rndName");
        testEmployee.setLastName("rndName");
        testEmployee.setDateOfEmployment(new Date(1545454));
        testEmployee.setDateOfBirth(new Date(4575454));
        testEmployee.setAddress("rndAddress");
        testEmployee.setPhoneNumber("rndphoneNumber");
        testEmployee.setPositionName("cook");
        testEmployee.setUsername("rndName");
        testEmployee.setHashPassword(125);

        int beforeEmployeesCount = employeeDAO.findAll().size();
        employeeDAO.insertEmployee(testEmployee);
        int afterEmployeesCount = employeeDAO.findAll().size();

        assertTrue((afterEmployeesCount - beforeEmployeesCount) == 1);
    }

    @Test
    public void updateEmployee() {
        testEmployee.setFirstName("rndName");
        testEmployee.setMiddleName("rndName");
        testEmployee.setLastName("rndName");
        testEmployee.setDateOfEmployment(new Date(1545454));
        testEmployee.setDateOfBirth(new Date(4575454));
        testEmployee.setAddress("rndAddress");
        testEmployee.setPhoneNumber("rndphoneNumber");
        testEmployee.setPositionName("cook");
        testEmployee.setUsername("rndName");
        testEmployee.setHashPassword(125);

        employeeDAO.insertEmployee(testEmployee);

        Employee firstEmployee = employeeDAO.findByExample(testEmployee).get(0);

        testEmployee.setEmployeeID(firstEmployee.getEmployeeID());

        testEmployee.setFirstName("gfName");
        testEmployee.setMiddleName("fgfName");
        testEmployee.setLastName("rfgfgName");
        testEmployee.setDateOfEmployment(new Date(71454));
        testEmployee.setDateOfBirth(new Date(645754));
        testEmployee.setAddress("fgfgddress");
        testEmployee.setPhoneNumber("rndpfgf");
        testEmployee.setPositionName("waiter");
        testEmployee.setUsername("rndfde");
        testEmployee.setHashPassword(125);

        employeeDAO.updateEmployee(testEmployee);

        Employee secondEmployee = employeeDAO.findByID(firstEmployee.getEmployeeID());

        assertNotEquals(firstEmployee, secondEmployee);
    }

    @Test
    public void deleteEmployee() {
        testEmployee.setFirstName("rndName");
        testEmployee.setMiddleName("rndName");
        testEmployee.setLastName("rndName");
        testEmployee.setDateOfEmployment(new Date(1545454));
        testEmployee.setDateOfBirth(new Date(4575454));
        testEmployee.setAddress("rndAddress");
        testEmployee.setPhoneNumber("rndphoneNumber");
        testEmployee.setPositionName("cook");
        testEmployee.setUsername("rndName");
        testEmployee.setHashPassword(125);

        employeeDAO.insertEmployee(testEmployee);

        testEmployee = employeeDAO.findByExample(testEmployee).get(0);

        employeeDAO.deleteEmployee(testEmployee);

        assertEquals(employeeDAO.findByID(testEmployee.getEmployeeID()), null);

    }

    @After
    public void tearDown() {
        List<Employee> recievedEmployeees = employeeDAO.findByExample(testEmployee);
        if (recievedEmployeees != null && recievedEmployeees.size() == 1) {
            employeeDAO.deleteEmployee(employeeDAO.findByExample(testEmployee).get(0));
        } 
    }
}
