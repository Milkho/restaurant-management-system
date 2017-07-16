package controller;

import dao.concrete.DAOFactoryImpl;
import dao.interfaces.DishDAO;
import java.util.ArrayList;
import java.util.List;
import model.Dish;

public final class DishController {

    private final DishDAO dishDao;

    public DishController() {
        dishDao = new DAOFactoryImpl().getDishDAO();
    }

    public Dish getByID(Integer dishID) {
        return dishDao.findByID(dishID);
    }

    public ArrayList<Dish> getByExample(int dishID, String dishName, double netWeight, double price) {

        Dish dish = new Dish();

        dish.setDishID(dishID);
        dish.setDishName(dishName);
        dish.setNetWeight(netWeight);
        dish.setPrice(price);

        List<Dish> result = dishDao.findByExample(dish);
        
        return new ArrayList(result);
    }

    public ArrayList<Dish> getAll() {
        return new ArrayList(dishDao.findAll());
    }

    public boolean updateDish(int dishID, String dishName, double netWeight, double price) {

        Dish dish = new Dish();

        dish.setDishID(dishID);
        dish.setDishName(dishName);
        dish.setNetWeight(netWeight);
        dish.setPrice(price);

        return dishDao.updateDish(dish);
    }

    public boolean addDish(String dishName, double netWeight, double price) {

        Dish dish = new Dish();

        dish.setDishName(dishName);
        dish.setNetWeight(netWeight);
        dish.setPrice(price);

        return dishDao.insertDish(dish);
    }

    public boolean deleteDish(int DishID) {
        return dishDao.deleteDish(dishDao.findByID(DishID));
    }

    public int getDishIDByName(String dishName) {

        Dish dish = new Dish();
        dish.setDishName(dishName);
        List<Dish> resultSet = dishDao.findByExample(dish);
        if (resultSet.size() != 1) {
            return -1;
        }

        return resultSet.get(0).getDishID();
    }

}
