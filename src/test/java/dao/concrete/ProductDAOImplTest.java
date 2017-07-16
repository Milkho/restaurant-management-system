package dao.concrete;

import dao.interfaces.ProductDAO;
import java.util.List;
import model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class ProductDAOImplTest {

    private final ProductDAO productDAO = new DAOFactoryImpl().getProductDAO();
    private Product testProduct;

    @Before
    public void setUp() {
        testProduct = new Product();
    }

    @Test
    public void findProductByExample() {

        testProduct.setProductName("rndName");
        testProduct.setAmount(100);
        testProduct.setUnitOfMeasurement("kg");

        productDAO.insertProduct(testProduct);

        List<Product> recievedProductes = productDAO.findByExample(testProduct);

        for (Product recievedProduct : recievedProductes) {
            assertEquals(recievedProduct.getProductName(), testProduct.getProductName());
            assertEquals(recievedProduct.getAmount(), testProduct.getAmount());
            assertEquals(recievedProduct.getUnitOfMeasurement(), testProduct.getUnitOfMeasurement());
        }
    }

    @Test
    public void findProductByUnexistingID() {

        assertEquals(productDAO.findByID(-1), null);
    }

    @Test
    public void findAllProduct() {
        testProduct.setProductName("rndName");
        testProduct.setAmount(100);
        testProduct.setUnitOfMeasurement("kg");

        productDAO.insertProduct(testProduct);

        assertTrue(productDAO.findAll().size() > 0);

    }

    @Test(expected = NullPointerException.class)
    public void insertUnvalidProduct() {
        testProduct.setProductName(null);
        testProduct.setAmount(null);
        testProduct.setUnitOfMeasurement(null);

        productDAO.insertProduct(testProduct);
    }

    @Test
    public void insertProduct() {
        testProduct.setProductName("rndName");
        testProduct.setAmount(100);
        testProduct.setUnitOfMeasurement("kg");
        
        int beforeProductesCount = productDAO.findAll().size();
        productDAO.insertProduct(testProduct);
        int afterProductesCount = productDAO.findAll().size();

        assertTrue((afterProductesCount - beforeProductesCount) == 1);
    }

    @Test
    public void updateProduct(){
        testProduct.setProductName("rndName");
        testProduct.setAmount(100);
        testProduct.setUnitOfMeasurement("kg");
        productDAO.insertProduct(testProduct);
        
        Product firstProduct = productDAO.findByExample(testProduct).get(0);
        
        testProduct.setProductID(firstProduct.getProductID());
       
        testProduct.setProductName("dfgdf");
        testProduct.setAmount(99);
        testProduct.setUnitOfMeasurement("ml");
        
        productDAO.updateProduct(testProduct);
        
        Product secondProduct = productDAO.findByID(firstProduct.getProductID());
        
        assertNotEquals(firstProduct, secondProduct);        
    }
    
    @Test
    public void deleteProduct(){
        testProduct.setProductName("rndName");
        testProduct.setAmount(100);
        testProduct.setUnitOfMeasurement("kg");
        
        productDAO.insertProduct(testProduct);
        
        testProduct = productDAO.findByExample(testProduct).get(0);
        
        productDAO.deleteProduct(testProduct);
        
        assertEquals(productDAO.findByID(testProduct.getProductID()), null);

    }
    
    @After
    public void tearDown() {
        List<Product> recievedProductes = productDAO.findByExample(testProduct);
        if (recievedProductes != null && recievedProductes.size() == 1) {
            productDAO.deleteProduct(productDAO.findByExample(testProduct).get(0));
        }
    }

}
