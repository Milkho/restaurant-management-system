package dao.concrete;

import dao.interfaces.OrdersAccountDAO;
import java.sql.Date;
import java.util.List;
import model.Ordersaccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class OrdersAccountDAOImplTest {

    private final OrdersAccountDAO OrdersAccountDAO = new DAOFactoryImpl().getOrdersAccountDAO();
    private Ordersaccount testOrdersAccount;

    @Before
    public void setUp() {
        testOrdersAccount = new Ordersaccount();
    }

    @Test
    public void findOrdersAccountByExample() {

        testOrdersAccount.setOrderID(3);
        testOrdersAccount.setDishID(1);
        testOrdersAccount.setAmount(3);

        OrdersAccountDAO.insertOrdersAccount(testOrdersAccount);

        List<Ordersaccount> recievedOrdersAccountes = OrdersAccountDAO.findByExample(testOrdersAccount);

        for (Ordersaccount recievedOrdersAccount : recievedOrdersAccountes) {
            assertEquals(recievedOrdersAccount.getOrderID(), testOrdersAccount.getOrderID());
            assertEquals(recievedOrdersAccount.getDishID(), testOrdersAccount.getDishID());
            assertEquals(recievedOrdersAccount.getAmount(), testOrdersAccount.getAmount());

        }
    }

    @Test
    public void findOrdersAccountByUnexistingID() {

        assertEquals(OrdersAccountDAO.findByID(-1, -1), null);
    }

    @Test
    public void findAllOrdersAccount() {
        testOrdersAccount.setOrderID(3);
        testOrdersAccount.setDishID(1);
        testOrdersAccount.setAmount(3);

        OrdersAccountDAO.insertOrdersAccount(testOrdersAccount);

        assertTrue(OrdersAccountDAO.findAll().size() > 0);

    }

    @Test(expected = NullPointerException.class)
    public void insertUnvalidOrdersAccount() {
        testOrdersAccount.setOrderID(null);
        testOrdersAccount.setDishID(null);
        testOrdersAccount.setAmount(null);

        OrdersAccountDAO.insertOrdersAccount(testOrdersAccount);
    }

    @Test
    public void insertOrdersAccount() {
        testOrdersAccount.setOrderID(3);
        testOrdersAccount.setDishID(1);
        testOrdersAccount.setAmount(3);

        int beforeOrdersAccountesCount = OrdersAccountDAO.findAll().size();
        OrdersAccountDAO.insertOrdersAccount(testOrdersAccount);
        int afterOrdersAccountesCount = OrdersAccountDAO.findAll().size();

        assertTrue((afterOrdersAccountesCount - beforeOrdersAccountesCount) == 1);
    }

    @Test
    public void updateOrdersAccount() {

        testOrdersAccount.setOrderID(3);
        testOrdersAccount.setDishID(1);
        testOrdersAccount.setAmount(3);

        OrdersAccountDAO.insertOrdersAccount(testOrdersAccount);

        Ordersaccount firstOrdersAccount = OrdersAccountDAO.findByExample(testOrdersAccount).get(0);

        testOrdersAccount.setOrderID(4);
        testOrdersAccount.setDishID(2);
        testOrdersAccount.setAmount(5);

        OrdersAccountDAO.updateOrdersAccount(testOrdersAccount);

        Ordersaccount secondOrdersAccount = OrdersAccountDAO.findByID(firstOrdersAccount.getOrderID(), firstOrdersAccount.getDishID());

        assertNotEquals(firstOrdersAccount, secondOrdersAccount);
    }

    @Test
    public void deleteOrdersAccount() {

        testOrdersAccount.setOrderID(3);
        testOrdersAccount.setDishID(1);
        testOrdersAccount.setAmount(3);

        OrdersAccountDAO.insertOrdersAccount(testOrdersAccount);

        testOrdersAccount = OrdersAccountDAO.findByExample(testOrdersAccount).get(0);

        OrdersAccountDAO.deleteOrdersAccount(testOrdersAccount);

        assertEquals(OrdersAccountDAO.findByID(testOrdersAccount.getOrderID(), testOrdersAccount.getDishID()), null);

    }

    @After
    public void tearDown() {
        List<Ordersaccount> recievedOrdersAccountes = OrdersAccountDAO.findByExample(testOrdersAccount);
        if (recievedOrdersAccountes != null && recievedOrdersAccountes.size() == 1) {
            OrdersAccountDAO.deleteOrdersAccount(OrdersAccountDAO.findByExample(testOrdersAccount).get(0));
        }
    }
}
