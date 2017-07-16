package controller;

import dao.concrete.DAOFactoryImpl;
import dao.interfaces.OrdersAccountDAO;
import java.util.ArrayList;
import java.util.List;
import model.Ordersaccount;

public final class OrdersAccountController {

    private final OrdersAccountDAO ordersAccountDao;

    public OrdersAccountController() {
        ordersAccountDao = new DAOFactoryImpl().getOrdersAccountDAO();
    }

    public ArrayList<Ordersaccount> getAll() {
        return new ArrayList(ordersAccountDao.findAll());
    }

    public ArrayList<Ordersaccount> getByExample(int orderID, int dishID, int amount) {
        Ordersaccount ordersAccount = new Ordersaccount();

        ordersAccount.setOrderID(orderID);
        ordersAccount.setDishID(dishID);
        ordersAccount.setAmount(amount);
        List<Ordersaccount> result = ordersAccountDao.findByExample(ordersAccount);
        
        return new ArrayList(result);
    }

    public boolean addOrdersaccount(int orderID, int dishID,int amount) {

        Ordersaccount ordersAccount = new Ordersaccount();

        ordersAccount.setOrderID(orderID);
        ordersAccount.setDishID(dishID);
        ordersAccount.setAmount(amount);
        
        return ordersAccountDao.insertOrdersAccount(ordersAccount);
    }

    public boolean updateOrdersaccount(int orderID, int dishID, int amount) {

        Ordersaccount ordersAccount = new Ordersaccount();

        ordersAccount.setOrderID(orderID);
        ordersAccount.setDishID(dishID);
        ordersAccount.setAmount(amount);

        return ordersAccountDao.updateOrdersAccount(ordersAccount);
    }

    public boolean deleteOrdersaccount(int orderID, int dishID) {
        return ordersAccountDao.deleteOrdersAccount(ordersAccountDao.findByID(orderID, dishID));
    }

}
