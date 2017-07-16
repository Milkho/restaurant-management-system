package dao.concrete;

import dao.interfaces.OrderingDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Ordering;
import java.text.SimpleDateFormat;

public final class OrderingDAOImpl implements OrderingDAO {

    @Override
    public boolean insertOrdering(Ordering Ordering) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "insert into ordering (tableID, employeeID, date) values(?, ?, ?)");
            preparedStatement.setInt(1, Ordering.getTableID());
            preparedStatement.setInt(2, Ordering.getEmployeeID());
            preparedStatement.setDate(3, new Date(Ordering.getDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOrdering(Ordering Ordering) {
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            statement.executeUpdate("delete from ordering where orderID = " + Ordering.getOrderID());
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateOrdering(Ordering Ordering) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "update ordering set tableID = ?, employeeID = ?, date = ?  where orderID = ?");
            preparedStatement.setInt(1, Ordering.getTableID());
            preparedStatement.setInt(2, Ordering.getEmployeeID());
            preparedStatement.setDate(3, (Date) Ordering.getDate());
            preparedStatement.setInt(4, Ordering.getOrderID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Ordering findByID(int orderID) {
        Ordering res = null;
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ordering where orderID = " + orderID);

            if (resultSet.next()) {
                res = new Ordering();
                res.setOrderID(resultSet.getInt("orderID"));
                res.setTableID(resultSet.getInt("tableID"));
                res.setEmployeeID(resultSet.getInt("employeeID"));
                res.setDate(resultSet.getDate("date"));
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
    public List<Ordering> findAll() {
        ArrayList<Ordering> result = new ArrayList<>();
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ordering order by orderID desc");

            while (resultSet.next()) {
                Ordering Ordering = new Ordering();
                Ordering.setOrderID(resultSet.getInt("orderID"));
                Ordering.setTableID(resultSet.getInt("tableID"));
                Ordering.setEmployeeID(resultSet.getInt("employeeID"));
                Ordering.setDate(resultSet.getDate("date"));
                result.add(Ordering);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    @Override
    public List<Ordering> findByExample(Ordering filterOrdering) {
        ArrayList<Ordering> result = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "select * from ordering "
                    + "where (orderID = ? or ? = -2147483648)"
                    + "and (tableID = ? or ? = -2147483648) "
                    + "and (employeeID = ? or ? = -2147483648)"
                    + "and (date = ? or ? = '1900-01-01')"
                    + "order by orderID");
            if (filterOrdering.getOrderID() == null) {
                preparedStatement.setInt(1, Integer.MIN_VALUE);
                preparedStatement.setInt(2, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(1, filterOrdering.getOrderID());
                preparedStatement.setInt(2, filterOrdering.getOrderID());
            }
            if (filterOrdering.getTableID() == null) {
                preparedStatement.setInt(3, Integer.MIN_VALUE);
                preparedStatement.setInt(4, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(3, filterOrdering.getTableID());
                preparedStatement.setInt(4, filterOrdering.getTableID());
            }
            if (filterOrdering.getEmployeeID() == null) {
                preparedStatement.setInt(5, Integer.MIN_VALUE);
                preparedStatement.setInt(6, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(5, filterOrdering.getEmployeeID());
                preparedStatement.setInt(6, filterOrdering.getEmployeeID());
            }
            if (filterOrdering.getDate() == null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                java.util.Date parsed = format.parse("19000101");
                preparedStatement.setDate(7, new Date(parsed.getTime()));
                preparedStatement.setDate(8, new Date(parsed.getTime()));
            } else {
                preparedStatement.setDate(7, new Date(filterOrdering.getDate().getTime()));
                preparedStatement.setDate(8, new Date(filterOrdering.getDate().getTime()));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ordering ordering = new Ordering();
                ordering.setOrderID(resultSet.getInt("orderID"));
                ordering.setTableID(resultSet.getInt("tableID"));
                ordering.setEmployeeID(resultSet.getInt("employeeID"));
                ordering.setDate(resultSet.getDate("date"));
                result.add(ordering);
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return result;
    }

}
