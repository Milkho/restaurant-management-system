package dao.concrete;

import dao.interfaces.ProductDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public final class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean insertProduct(Product product) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "insert into product (productName, amount, unitOfMeasurement) values( ?, ?, ?)");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getAmount());
            preparedStatement.setString(3, product.getUnitOfMeasurement());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            statement.executeUpdate("delete from product where productID = " + product.getProductID());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "update product set productName =  ? , amount =  ? , unitOfMeasurement =  ? where productID = ?");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getAmount());
            preparedStatement.setString(3, product.getUnitOfMeasurement());
            preparedStatement.setInt(4, product.getProductID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public Product findByID(int ID) {
        Product res = null;
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product where productID = " + ID);

            if (resultSet.next()) {
                res = new Product();
                res.setProductID(resultSet.getInt("productID"));
                res.setProductName(resultSet.getString("productName"));
                res.setAmount(resultSet.getInt("amount"));
                res.setUnitOfMeasurement(resultSet.getString("unitOfMeasurement"));
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
    public List<Product> findAll() {
        ArrayList<Product> result = new ArrayList<>();
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product order by productID");

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("productID"));
                product.setProductName(resultSet.getString("productName"));
                product.setAmount(resultSet.getInt("amount"));
                product.setUnitOfMeasurement(resultSet.getString("unitOfMeasurement"));

                result.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return result;
    }

    @Override
    public List<Product> findByExample(Product filterProduct) {
        ArrayList<Product> result = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAOFactoryImpl.getConnection().prepareStatement(
                    "select * from product "
                    + "where (productID = ? or ? = -2147483648)"
                    + "and (productName = ? or ? is null) "
                    + "and (amount = ? or ? = -2147483648)"
                    + "and (unitOfMeasurement = ? or ? is null)"
                    + "order by productID");
            if (filterProduct.getProductID() == null) {
                preparedStatement.setInt(1, Integer.MIN_VALUE);
                preparedStatement.setInt(2, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(1, filterProduct.getProductID());
                preparedStatement.setInt(2, filterProduct.getProductID());
            }
            preparedStatement.setString(3, filterProduct.getProductName());
            preparedStatement.setString(4, filterProduct.getProductName());
            if (filterProduct.getAmount() == null) {
                preparedStatement.setInt(5, Integer.MIN_VALUE);
                preparedStatement.setInt(6, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(5, filterProduct.getAmount());
                preparedStatement.setInt(6, filterProduct.getAmount());
            }
            preparedStatement.setString(7, filterProduct.getUnitOfMeasurement());
            preparedStatement.setString(8, filterProduct.getUnitOfMeasurement());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("productID"));
                product.setProductName(resultSet.getString("productName"));
                product.setAmount(resultSet.getInt("amount"));
                product.setUnitOfMeasurement(resultSet.getString("unitOfMeasurement"));

                result.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return result;
    }

}
