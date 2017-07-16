package dao.interfaces;

import java.util.List;
import model.Product;

public interface ProductDAO {

    public boolean insertProduct(Product product);

    public boolean deleteProduct(Product product);

    public boolean updateProduct(Product product);

    public Product findByID(int ProductID);
    
    public List<Product> findAll();

    public List<Product> findByExample(Product product);
}
