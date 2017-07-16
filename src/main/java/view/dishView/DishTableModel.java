package view.dishView;

import controller.DishController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Dish;

public final class DishTableModel extends AbstractTableModel {

    private final DishController dishController;
    private final ArrayList<Dish> dishes;

    public DishTableModel(DishController dishController) {
        super();
        this.dishController = dishController;
        dishes = dishController.getAll();
    }

    public DishTableModel(DishController dishController, int DishID, String dishName,
            Integer categoryID, Double netWeight, Double price) {
        super();
        this.dishController = dishController;
        dishes = dishController.getByExample(DishID, dishName, netWeight, price);
    }

    @Override
    public int getRowCount() {
        return dishes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Dish name";
            case 2:
                return "Weight";
            case 3:
                return "Price";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return dishes.get(rowIndex).getDishID();
            case 1:
                return dishes.get(rowIndex).getDishName();
            case 2:
                return dishes.get(rowIndex).getNetWeight();
            case 3:
                return dishes.get(rowIndex).getPrice();
            default:
                return "------";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int dishID = dishes.get(rowIndex).getDishID();
        String dishName = dishes.get(rowIndex).getDishName();
        double netWeight = dishes.get(rowIndex).getNetWeight();
        double price = dishes.get(rowIndex).getPrice();

        try {
            switch (columnIndex) {
                case 1:
                    dishName = aValue.toString();
                    break;
                case 2:
                    netWeight = Double.valueOf(aValue.toString());
                    break;
                case 3:
                    price = Double.valueOf(aValue.toString());
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid format", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (dishController.updateDish(dishID, dishName, netWeight, price) == false) {
            JOptionPane.showMessageDialog(null, "Error while adding a record", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.fireTableDataChanged();
    }
}
