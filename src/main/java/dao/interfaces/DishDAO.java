package dao.interfaces;

import java.util.List;
import model.Dish;

public interface DishDAO {

    public boolean insertDish(Dish dish);

    public boolean deleteDish(Dish dish);

    public boolean updateDish(Dish dish);

    public Dish findByID(int DishID);

    public List<Dish> findAll();
    
    public List<Dish> findByExample(Dish dish);
}
