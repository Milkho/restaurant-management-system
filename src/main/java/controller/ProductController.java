package controller;

import dao.concrete.DAOFactoryImpl;
import dao.interfaces.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public final class ProductController {

    private final ProductDAO productDao;

    public ProductController() {
        productDao = new DAOFactoryImpl().getProductDAO();
    }

    public ArrayList<Product> getAll() {
        return new ArrayList(productDao.findAll());
    }

    public ArrayList<Product> getByExample(Product filterProduct) {
        List<Product> result = productDao.findByExample(filterProduct);
        return new ArrayList(result);
    }

    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public boolean updateProduct(int productID, String productName, int amount, String unitOfMeasurement) {

        Product product = new Product();

        product.setProductID(productID);
        product.setProductName(productName);
        product.setAmount(amount);
        product.setUnitOfMeasurement(unitOfMeasurement);

        return productDao.updateProduct(product);
    }


    public boolean addProduct(String productName, int amount, String unitOfMeasurement) {
        Product product = new Product();

        product.setProductName(productName);
        product.setAmount(amount);
        product.setUnitOfMeasurement(unitOfMeasurement);

        return productDao.insertProduct(product);
    }

    public boolean deleteProduct(int ProductID) {
        return productDao.deleteProduct(productDao.findByID(ProductID));
    }
}
