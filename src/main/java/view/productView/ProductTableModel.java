package view.productView;

import controller.ProductController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Product;

public final class ProductTableModel extends AbstractTableModel {

    private final ArrayList<Product> products;
    private final ProductController productController;

    public ProductTableModel(ProductController productController) {
        super();
        this.productController = productController;
        products = productController.getAll();
    }

    public ProductTableModel(ProductController productController, Integer productID, String productName,
            Integer amount, String unitOfMeasurement) {
        super();
        this.productController = productController;
        Product productFilter = new Product(productID, productName, amount, unitOfMeasurement);
        products = productController.getByExample(productFilter);
    }

    @Override
    public int getRowCount() {
        return products.size();
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
                return "Product name";
            case 2:
                return "Amount";
            case 3:
                return "Unit of measurement";
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
        if (columnIndex < 1) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return products.get(rowIndex).getProductID();
            case 1:
                return products.get(rowIndex).getProductName();
            case 2:
                return products.get(rowIndex).getAmount();
            case 3:
                return products.get(rowIndex).getUnitOfMeasurement();
            default:
                return "------";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int productID = products.get(rowIndex).getProductID();
        String productName = products.get(rowIndex).getProductName();
        int amount = products.get(rowIndex).getAmount();
        String unitOfMeasurement = products.get(rowIndex).getUnitOfMeasurement();

        try {
            switch (columnIndex) {
                case 1:
                    productName = aValue.toString();
                    break;
                case 2:
                    amount = Integer.parseInt(aValue.toString());
                    break;
                case 3:
                    unitOfMeasurement = aValue.toString();
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid format", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (productController.updateProduct(productID, productName, amount, unitOfMeasurement) == false) {
            JOptionPane.showMessageDialog(null, "Error while updating the record", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
