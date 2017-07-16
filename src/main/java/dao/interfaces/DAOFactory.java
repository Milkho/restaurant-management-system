package dao.interfaces;

public interface DAOFactory {

    public EmployeeDAO getEmployeeDAO();

    public DishDAO getDishDAO();

    public OrdersAccountDAO getOrdersAccountDAO();

    public ProductDAO getProductDAO();
    
    public PositionDAO getPositionDAO();
    
    public OrderingDAO getOrderingDAO();
}
