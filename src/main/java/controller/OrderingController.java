package controller;

import dao.concrete.DAOFactoryImpl;
import dao.interfaces.OrderingDAO;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import model.Ordering;

public final class OrderingController {

    private final OrderingDAO mealsOrderDao;

    public OrderingController() {
        mealsOrderDao = new DAOFactoryImpl().getOrderingDAO();
    }

    public Ordering getByID(int orderID) {
        return mealsOrderDao.findByID(orderID);
    }

    public ArrayList<Ordering> getAll() {
        return new ArrayList(mealsOrderDao.findAll());
    }

    public ArrayList<Ordering> getByExample(int orderID, int tableID,
            int employeeID, Date date) {
        Ordering mealsorder = new Ordering();

        mealsorder.setOrderID(orderID);
        mealsorder.setTableID(tableID);
        mealsorder.setEmployeeID(employeeID);
        mealsorder.setDate(date);
        List<Ordering> result = mealsOrderDao.findByExample(mealsorder);

        return new ArrayList(result);
    }

    public boolean addOrdering(int tableID, int employeeID, Date date) {

        Ordering Ordering = new Ordering();

        Ordering.setTableID(tableID);
        Ordering.setEmployeeID(employeeID);
        Ordering.setDate(date);
        
        return mealsOrderDao.insertOrdering(Ordering);
    }

    public boolean updateOrdering(int orderID, int tableID, int employeeID, Date date) {

        Ordering Ordering = new Ordering();

        Ordering.setOrderID(orderID);
        Ordering.setTableID(tableID);
        Ordering.setEmployeeID(employeeID);
        Ordering.setDate(date);
        
        return mealsOrderDao.updateOrdering(Ordering);
    }

    public boolean deleteOrdering(int OrderingID) {
        return mealsOrderDao.deleteOrdering(mealsOrderDao.findByID(OrderingID));
    }

}
