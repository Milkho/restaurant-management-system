package dao.concrete;

import dao.interfaces.OrdersAccountDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Ordersaccount;

public final class OrdersAccountDAOImpl implements OrdersAccountDAO {

    @Override
    public boolean insertOrdersAccount(Ordersaccount OrdersAccount) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "insert into ordersaccount  values(?, ?, ?)");
            preparedStatement.setInt(1, OrdersAccount.getOrderID());
            preparedStatement.setInt(2, OrdersAccount.getDishID());
            preparedStatement.setInt(3, OrdersAccount.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOrdersAccount(Ordersaccount OrdersAccount) {
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            statement.executeUpdate("delete from ordersaccount where orderID = " + OrdersAccount.getOrderID() + " and dishID = " + OrdersAccount.getDishID());
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateOrdersAccount(Ordersaccount OrdersAccount) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "update ordersaccount set amount = ?  where orderID = ? and dishID = ?");
            preparedStatement.setInt(1, OrdersAccount.getAmount());
            preparedStatement.setInt(2, OrdersAccount.getOrderID());
            preparedStatement.setInt(3, OrdersAccount.getDishID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Ordersaccount findByID(int orderID, int mealID) {
        Ordersaccount res = null;
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ordersaccount where orderID = " + orderID);

            if (resultSet.next()) {
                res = new Ordersaccount();
                res.setOrderID(resultSet.getInt("orderID"));
                res.setDishID(resultSet.getInt("dishID"));
                res.setAmount(resultSet.getInt("amount"));
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
    public List<Ordersaccount> findAll() {
        ArrayList<Ordersaccount> result = new ArrayList<>();
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ordersaccount order by orderID desc");

            while (resultSet.next()) {
                Ordersaccount OrdersAccount = new Ordersaccount();
                OrdersAccount.setOrderID(resultSet.getInt("orderID"));
                OrdersAccount.setDishID(resultSet.getInt("dishID"));
                OrdersAccount.setAmount(resultSet.getInt("amount"));
                result.add(OrdersAccount);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    @Override
    public List<Ordersaccount> findByExample(Ordersaccount filterOrdersAccount) {
        ArrayList<Ordersaccount> result = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "select * from ordersaccount "
                    + "where (orderID = ? or ? = -2147483648)"
                    + "and (dishID = ? or ?  = -2147483648) "
                    + "and (amount = ? or ? = -2147483648)"
                    + "order by orderID");
            if (filterOrdersAccount.getOrderID() == null) {
                preparedStatement.setInt(1, -2147483648);
                preparedStatement.setInt(2, -2147483648);
            } else {
                preparedStatement.setInt(1, filterOrdersAccount.getOrderID());
                preparedStatement.setInt(2, filterOrdersAccount.getOrderID());
            }
            if (filterOrdersAccount.getDishID() == null) {
                preparedStatement.setInt(3, -2147483648);
                preparedStatement.setInt(4, -2147483648);
            } else {
                preparedStatement.setInt(3, filterOrdersAccount.getDishID());
                preparedStatement.setInt(4, filterOrdersAccount.getDishID());
            }
            if (filterOrdersAccount.getAmount() == null) {
                preparedStatement.setInt(5, -2147483648);
                preparedStatement.setInt(6, -2147483648);
            } else {
                preparedStatement.setInt(5, filterOrdersAccount.getAmount());
                preparedStatement.setInt(6, filterOrdersAccount.getAmount());
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ordersaccount OrdersAccount = new Ordersaccount();
                OrdersAccount.setOrderID(resultSet.getInt("orderID"));
                OrdersAccount.setDishID(resultSet.getInt("dishID"));
                OrdersAccount.setAmount(resultSet.getInt("amount"));
                result.add(OrdersAccount);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }
}
