package dao.concrete;

import dao.interfaces.DishDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Dish;

public final class DishDAOImpl implements DishDAO {

    @Override
    public boolean insertDish(Dish dish) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "insert into dish (dishName, netWeight, price) values( ?, ?, ?)");
            preparedStatement.setString(1, dish.getDishName());
            preparedStatement.setDouble(2, dish.getNetWeight());
            preparedStatement.setDouble(3, dish.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDish(Dish dish) {
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            statement.executeUpdate("delete from dish where dishID = " + dish.getDishID());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDish(Dish dish) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "update dish set dishName =  ? ,  netWeight =  ?, price = ? where dishID = ?");
            preparedStatement.setString(1, dish.getDishName());
            preparedStatement.setDouble(2, dish.getNetWeight());
            preparedStatement.setDouble(3, dish.getPrice());
            preparedStatement.setInt(4, dish.getDishID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public Dish findByID(int ID) {
        Dish res = null;
        try {

            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dish where dishID = " + ID);

            if (resultSet.next()) {
                res = new Dish();
                res.setDishID(resultSet.getInt("dishID"));
                res.setDishName(resultSet.getString("dishName"));
                res.setNetWeight(resultSet.getDouble("netWeight"));
                res.setPrice(resultSet.getDouble("price"));
            }
            if (resultSet.next()) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return res;
    }

    @Override
    public List<Dish> findAll() {
        ArrayList<Dish> result = new ArrayList<>();
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dish order by dishID desc");

            while (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishID(resultSet.getInt("dishID"));
                dish.setDishName(resultSet.getString("dishName"));
                dish.setNetWeight(resultSet.getDouble("netWeight"));
                dish.setPrice(resultSet.getDouble("price"));

                result.add(dish);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    @Override
    public List<Dish> findByExample(Dish filterDish) {
        if (filterDish == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Dish> result = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "select * from dish "
                    + "where (dishID = ? or ? = -2147483648)"
                    + "and (dishName = ? or ? is null)"
                    + "and (netWeight = ? or ? = -2147483648)"
                    + "and (price = ? or ? = -2147483648)"
                    + "order by dishID");
            if (filterDish.getDishID() == null) {
                preparedStatement.setInt(1, Integer.MIN_VALUE);
                preparedStatement.setInt(2, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(1, filterDish.getDishID());
                preparedStatement.setInt(2, filterDish.getDishID());
            }
            preparedStatement.setString(3, filterDish.getDishName());
            preparedStatement.setString(4, filterDish.getDishName());

            if (filterDish.getNetWeight() == null) {
                preparedStatement.setDouble(5, Integer.MIN_VALUE);
                preparedStatement.setDouble(6, Integer.MIN_VALUE);
            } else {
                preparedStatement.setDouble(5, filterDish.getNetWeight());
                preparedStatement.setDouble(6, filterDish.getNetWeight());
            }
            if (filterDish.getPrice() == null) {
                preparedStatement.setDouble(7, Integer.MIN_VALUE);
                preparedStatement.setDouble(8, Integer.MIN_VALUE);
            } else {
                preparedStatement.setDouble(7, filterDish.getPrice());
                preparedStatement.setDouble(8, filterDish.getPrice());
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishID(resultSet.getInt("dishID"));
                dish.setDishName(resultSet.getString("dishName"));
                dish.setNetWeight(resultSet.getDouble("netWeight"));
                dish.setPrice(resultSet.getDouble("price"));

                result.add(dish);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

}
