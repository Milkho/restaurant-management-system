package dao.concrete;

import dao.interfaces.DishDAO;
import java.util.List;
import model.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class DishDAOImplTest {

    private final DishDAO dishDAO = new DAOFactoryImpl().getDishDAO();
    private Dish testDish;

    @Before
    public void setUp() {
        testDish = new Dish();
    }

    @Test
    public void findDishByExample() {

        testDish.setDishName("rndName");
        testDish.setNetWeight(100.99);
        testDish.setPrice(99.100);

        dishDAO.insertDish(testDish);

        List<Dish> recievedDishes = dishDAO.findByExample(testDish);

        for (Dish recievedDish : recievedDishes) {
            assertEquals(recievedDish.getDishName(), testDish.getDishName());
            assertEquals(recievedDish.getNetWeight(), testDish.getNetWeight());
            assertEquals(recievedDish.getPrice(), testDish.getPrice());
        }
    }

    @Test
    public void findDishByUnexistingID() {

        assertEquals(dishDAO.findByID(-1), null);
    }

    @Test
    public void findAllDish() {
        testDish.setDishName("rndName");
        testDish.setNetWeight(100.99);
        testDish.setPrice(99.100);

        dishDAO.insertDish(testDish);

        assertTrue(dishDAO.findAll().size() > 0);

    }

    @Test(expected = NullPointerException.class)
    public void insertUnvalidDish() {
        testDish.setDishName(null);
        testDish.setNetWeight(null);
        testDish.setPrice(null);

        dishDAO.insertDish(testDish);
    }

    @Test
    public void insertDish() {
        testDish.setDishName("rndName");
        testDish.setNetWeight(100.120);
        testDish.setPrice(420.25);
        int beforeDishesCount = dishDAO.findAll().size();
        dishDAO.insertDish(testDish);
        int afterDishesCount = dishDAO.findAll().size();

        assertTrue((afterDishesCount - beforeDishesCount) == 1);
    }

    @Test
    public void updateDish(){
        testDish.setDishName("rndName");
        testDish.setNetWeight(100.120);
        testDish.setPrice(420.25);
        dishDAO.insertDish(testDish);
        
        Dish firstDish = dishDAO.findByExample(testDish).get(0);
        
        testDish.setDishID(firstDish.getDishID());
        testDish.setDishName("RndName");
        testDish.setNetWeight(120.100);
        testDish.setPrice(25.420);
        
        dishDAO.updateDish(testDish);
        
        Dish secondDish = dishDAO.findByID(firstDish.getDishID());
        
        assertNotEquals(firstDish, secondDish);        
    }
    
    @Test
    public void deleteDish(){
        testDish.setDishName("rndName");
        testDish.setNetWeight(100.120);
        testDish.setPrice(420.25);
        
        dishDAO.insertDish(testDish);
        
        testDish = dishDAO.findByExample(testDish).get(0);
        
        dishDAO.deleteDish(testDish);
        
        assertEquals(dishDAO.findByID(testDish.getDishID()), null);

    }
    
    @After
    public void tearDown() {
        List<Dish> recievedDishes = dishDAO.findByExample(testDish);
        if (recievedDishes != null && recievedDishes.size() == 1) {
            dishDAO.deleteDish(dishDAO.findByExample(testDish).get(0));
        }
    }

}
