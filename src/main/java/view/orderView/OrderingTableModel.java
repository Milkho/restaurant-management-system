package view.orderView;

import controller.EmployeeController;
import controller.OrderingController;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Ordering;

public final class OrderingTableModel extends AbstractTableModel {

    private final OrderingController mealsOrderController;
    private final EmployeeController employeeController;
    private final ArrayList<Ordering> mealsOrders;

    public OrderingTableModel(OrderingController mealsOrderController) {
        super();
        employeeController = new EmployeeController();
        this.mealsOrderController = mealsOrderController;
        mealsOrders = mealsOrderController.getAll();
    }

    public OrderingTableModel(OrderingController mealsOrderController, int orderID, int tableID, int employeeID, Date date) {
        super();
        employeeController = new EmployeeController();
        this.mealsOrderController = mealsOrderController;
        mealsOrders = mealsOrderController.getByExample(orderID, tableID, employeeID, date);
    }

    @Override
    public int getRowCount() {
        return mealsOrders.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {

            case 0:
                return "Order ID";
            case 1:
                return "Table ID";
            case 2:
                return "Employee";
            case 3:
                return "Date";
            case 4:
                return "Cost";
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || columnIndex == 4 || columnIndex == 2) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return mealsOrders.get(rowIndex).getOrderID();
            case 1:
                return mealsOrders.get(rowIndex).getTableID();
            case 2:
                return employeeController.getEmployeeFullNameByID(mealsOrders.get(rowIndex).getEmployeeID());
            case 3:
                return mealsOrders.get(rowIndex).getDate();
            case 4:
                return controller.Services.getPriceOfOrder(mealsOrders.get(rowIndex).getOrderID());
            default:
                return "------";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int orderID = mealsOrders.get(rowIndex).getOrderID();
        int tableID = mealsOrders.get(rowIndex).getTableID();
        int employeeID = mealsOrders.get(rowIndex).getEmployeeID();
        Date date = Date.valueOf(getValueAt(rowIndex, 3).toString());
        try {
            switch (columnIndex) {
                case 1:
                    tableID = Integer.parseInt(aValue.toString());
                    break;
                case 3:
                    date = Date.valueOf(aValue.toString());
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid format", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (mealsOrderController.updateOrdering(orderID, tableID, employeeID, date) == false) {
            JOptionPane.showMessageDialog(null, "Incorrect data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.fireTableDataChanged();
    }
}
