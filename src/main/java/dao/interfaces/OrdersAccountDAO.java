package dao.interfaces;

import java.util.List;
import model.Ordersaccount;

public interface OrdersAccountDAO {

    public boolean insertOrdersAccount(Ordersaccount ordersAccount);

    public boolean deleteOrdersAccount(Ordersaccount ordersAccount);

    public boolean updateOrdersAccount(Ordersaccount ordersAccount);

    public Ordersaccount findByID(int orderID, int mealID);
    
    public List<Ordersaccount> findAll();
    
    public List<Ordersaccount> findByExample(Ordersaccount ordersAccount);
    
}


