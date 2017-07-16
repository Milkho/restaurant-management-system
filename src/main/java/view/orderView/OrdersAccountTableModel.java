package view.orderView;

import controller.DishController;
import controller.OrdersAccountController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Ordersaccount;

public final class OrdersAccountTableModel extends AbstractTableModel {

    private final ArrayList<Ordersaccount> orders;
    private final DishController dishController;
    private final OrdersAccountController ordersAccountController;
    
    public OrdersAccountTableModel(OrdersAccountController ordersAccountController, DishController dishController) {
        super();
        this.ordersAccountController = ordersAccountController;
        this.dishController = dishController;
        orders = ordersAccountController.getAll();
    }

    public OrdersAccountTableModel(OrdersAccountController ordersAccountController, DishController dishController, int orderID, int mealID, int amount) {
        super();
        this.ordersAccountController = ordersAccountController;
        this.dishController = dishController;
        orders = ordersAccountController.getByExample(orderID, mealID, amount);
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {

            case 0:
                return "Order ID";
            case 1:
                return "Dish";
            case 2:
                return "Amount";
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex < 2) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return orders.get(rowIndex).getOrderID();
            case 1:
                return dishController.getByID(orders.get(rowIndex).getDishID()).getDishName();
            case 2:
                return orders.get(rowIndex).getAmount();
            default:
                return "------";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int orderID = orders.get(rowIndex).getOrderID();
        int dishID = orders.get(rowIndex).getDishID();
        int amount = orders.get(rowIndex).getAmount();

        try {
            switch (columnIndex) {
                case 2:
                    amount = Integer.valueOf(aValue.toString());
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Invalid format", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (ordersAccountController.updateOrdersaccount(orderID, dishID, amount) == false) {
            JOptionPane.showMessageDialog(null, "Error while updating the record", "Error", JOptionPane.ERROR_MESSAGE);
        }        
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
}
