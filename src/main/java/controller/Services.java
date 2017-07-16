package controller;

import dao.concrete.DAOFactoryImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Services {

    public static double getPriceOfOrder(int orderID) {

        double sum = 0;

        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ordering.orderID, SUM(dish.price * ordersaccount.amount) AS sum "
                    + "FROM dish, ordering, ordersaccount WHERE dish.dishID = ordersaccount.dishID "
                    + "AND ordersaccount.orderID = " + orderID
                    + " GROUP BY ordering.orderID");

            if (resultSet.next()) {
                sum = resultSet.getDouble("sum");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return 0;
        }
        return sum;
    }

}
