package dao.concrete;

import dao.interfaces.OrderingDAO;
import java.sql.Date;
import java.util.List;
import model.Ordering;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class OrderingDAOImplTest {

    private final OrderingDAO OrderingDAO = new DAOFactoryImpl().getOrderingDAO();
    private Ordering testOrdering;

    @Before
    public void setUp() {
        testOrdering = new Ordering();
    }

    @Test
    public void findOrderingByExample() {

        testOrdering.setOrderID(5);
        testOrdering.setTableID(1);
        testOrdering.setEmployeeID(3);
        testOrdering.setDate(new Date(15000));

        OrderingDAO.insertOrdering(testOrdering);

        List<Ordering> recievedOrderinges = OrderingDAO.findByExample(testOrdering);

        for (Ordering recievedOrdering : recievedOrderinges) {
            assertEquals(recievedOrdering.getOrderID(), testOrdering.getOrderID());
            assertEquals(recievedOrdering.getTableID(), testOrdering.getTableID());
            assertEquals(recievedOrdering.getEmployeeID(), testOrdering.getEmployeeID());
            assertEquals(recievedOrdering.getDate(), testOrdering.getDate());
        }
    }

    @Test
    public void findOrderingByUnexistingID() {

        assertEquals(OrderingDAO.findByID(-1), null);
    }

    @Test
    public void findAllOrdering() {
        testOrdering.setOrderID(5);
        testOrdering.setTableID(1);
        testOrdering.setEmployeeID(2);
        testOrdering.setDate(new Date(15000));

        OrderingDAO.insertOrdering(testOrdering);

        assertTrue(OrderingDAO.findAll().size() > 0);

    }

    @Test(expected = NullPointerException.class)
    public void insertUnvalidOrdering() {
        testOrdering.setOrderID(null);
        testOrdering.setTableID(null);
        testOrdering.setEmployeeID(null);
        testOrdering.setDate(null);

        OrderingDAO.insertOrdering(testOrdering);
    }

    @Test
    public void insertOrdering() {
        testOrdering.setOrderID(5);
        testOrdering.setTableID(1);
        testOrdering.setEmployeeID(2);
        testOrdering.setDate(new Date(15000));

        int beforeOrderingesCount = OrderingDAO.findAll().size();
        OrderingDAO.insertOrdering(testOrdering);
        int afterOrderingesCount = OrderingDAO.findAll().size();

        assertTrue((afterOrderingesCount - beforeOrderingesCount) == 1);
    }

    @Test
    public void updateOrdering() {

        testOrdering.setTableID(1);
        testOrdering.setEmployeeID(2);
        testOrdering.setDate(new Date(15000));

        OrderingDAO.insertOrdering(testOrdering);

        Ordering firstOrdering = OrderingDAO.findByExample(testOrdering).get(0);
        
        testOrdering.setOrderID(firstOrdering.getOrderID());
        testOrdering.setTableID(2);
        testOrdering.setEmployeeID(9);
        testOrdering.setDate(new Date(24000));

        OrderingDAO.updateOrdering(testOrdering);

        Ordering secondOrdering = OrderingDAO.findByID(firstOrdering.getOrderID());

        assertNotEquals(firstOrdering, secondOrdering);
    }

    @Test
    public void deleteOrdering() {

        testOrdering.setTableID(1);
        testOrdering.setEmployeeID(2);
        testOrdering.setDate(new Date(15000));
        
        OrderingDAO.insertOrdering(testOrdering);

        testOrdering = OrderingDAO.findByExample(testOrdering).get(0);

        OrderingDAO.deleteOrdering(testOrdering);

        assertEquals(OrderingDAO.findByID(testOrdering.getOrderID()), null);

    }

    @After
    public void tearDown() {
        List<Ordering> recievedOrderinges = OrderingDAO.findByExample(testOrdering);
        if (recievedOrderinges != null && recievedOrderinges.size() == 1) {
            OrderingDAO.deleteOrdering(OrderingDAO.findByExample(testOrdering).get(0));
        }
    }
}
