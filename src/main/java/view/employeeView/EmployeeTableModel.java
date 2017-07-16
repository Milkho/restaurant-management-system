package view.employeeView;

import controller.EmployeeController;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Employee;

public final class EmployeeTableModel extends AbstractTableModel {

    private final EmployeeController employeeController;
    private final ArrayList<Employee> employees;

    public EmployeeTableModel(EmployeeController employeeController) {
        super();
        this.employeeController = employeeController;
        employees = employeeController.getAll();
    }

    public EmployeeTableModel(EmployeeController employeeController, int employeeID, String firstName, String middleName, String lastName,
            String positionName, Date dateOfEmployment, Date dateOfBirth, String Address, String phoneNumber, String username) {
        super();
        this.employeeController = employeeController;
        employees = employeeController.getByExample(employeeID, firstName, middleName, lastName, positionName,
                dateOfEmployment, dateOfBirth, Address, phoneNumber, username, null);
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {

            case 0:
                return "ID";
            case 1:
                return "First name";
            case 2:
                return "Middle name";
            case 3:
                return "Last name";
            case 4:
                return "Position";
            case 5:
                return "Employment date";
            case 6:
                return "Birth date";
            case 7:
                return "Address";
            case 8:
                return "Phone number";
            case 9:
                return "Username";
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex < 1) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return employees.get(rowIndex).getEmployeeID();
            case 1:
                return employees.get(rowIndex).getFirstName();
            case 2:
                return employees.get(rowIndex).getMiddleName();
            case 3:
                return employees.get(rowIndex).getLastName();
            case 4:
                return employees.get(rowIndex).getPositionName();
            case 5:
                return employees.get(rowIndex).getDateOfEmployment();
            case 6:
                return employees.get(rowIndex).getDateOfBirth();
            case 7:
                return employees.get(rowIndex).getAddress();
            case 8:
                return employees.get(rowIndex).getPhoneNumber();
            case 9:
                return employees.get(rowIndex).getUsername();
            default:
                return "------";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int employeeID = employees.get(rowIndex).getEmployeeID();
        String firstName = employees.get(rowIndex).getFirstName();
        String middleName = employees.get(rowIndex).getMiddleName();
        String lastName = employees.get(rowIndex).getLastName();
        String positionName = employees.get(rowIndex).getPositionName();
        Date dateOfEmployment = Date.valueOf(getValueAt(rowIndex, 5).toString());
        Date dateOfBirth = Date.valueOf(getValueAt(rowIndex, 6).toString());
        String Address = employees.get(rowIndex).getAddress();
        String phoneNumber = employees.get(rowIndex).getPhoneNumber();
        String username = employees.get(rowIndex).getUsername();
        try {
            switch (columnIndex) {
                case 1:
                    firstName = aValue.toString();
                    break;
                case 2:
                    middleName = aValue.toString();
                    break;
                case 3:
                    lastName = aValue.toString();
                    break;
                case 4:
                    positionName = aValue.toString();
                    break;
                case 5:
                    dateOfEmployment = Date.valueOf(aValue.toString());
                    break;
                case 6:
                    dateOfBirth = Date.valueOf(aValue.toString());
                    break;
                case 7:
                    Address = aValue.toString();
                    break;
                case 8:
                    phoneNumber = aValue.toString();
                    break;
                case 9:
                    username = aValue.toString();
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid format", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (employeeController.updateEmployee(employeeID, firstName, middleName, lastName,
                positionName, dateOfEmployment, dateOfBirth, Address, phoneNumber, username) == false) {
            JOptionPane.showMessageDialog(null, "Error while updating the record", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.fireTableDataChanged();
    }

}
