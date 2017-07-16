package dao.concrete;

import dao.interfaces.DishDAO;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.DAOFactory;
import dao.interfaces.OrderingDAO;
import dao.interfaces.OrderingDAO;
import dao.interfaces.OrdersAccountDAO;
import dao.interfaces.PositionDAO;
import dao.interfaces.ProductDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DAOFactoryImpl implements DAOFactory{
    public static Connection getConnection() {
        return DataBaseConnection.getConnection();
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOImpl();
    }

    @Override
    public DishDAO getDishDAO() {
        return new DishDAOImpl();
    }

    @Override
    public OrdersAccountDAO getOrdersAccountDAO() {
        return new OrdersAccountDAOImpl();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOImpl();
    }
    
    @Override
    public PositionDAO getPositionDAO() {
        return new PositionDAOImpl();
    }

    @Override
    public OrderingDAO getOrderingDAO() {
        return new OrderingDAOImpl();
    }
    
    private static class DataBaseConnection {

        private static volatile DataBaseConnection DataBaseConnection;
        private static Connection connection;

        private DataBaseConnection() throws Exception {
            String url = "jdbc:mysql://localhost/restaurant?useUnicode=true&characterEncoding=utf-8";
            String name = "root";
            String password = "horoshun";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, name, password);
                
            } catch (SQLException ex) {
                throw ex;
            }
        }

        public static Connection getConnection() {
            if (DataBaseConnection == null) {
                synchronized (DataBaseConnection.class) {
                    if (DataBaseConnection == null) {
                        try {
                            DataBaseConnection = new DataBaseConnection();
                        } catch (Exception e) {
                            e.printStackTrace(System.out);
                            return null;
                        }
                    }
                }
            }
            return DataBaseConnection.connection;
        }

        public static void closeConnection() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    
}
