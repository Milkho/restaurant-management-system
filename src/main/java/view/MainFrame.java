package view;

import controller.EmployeeController;
import view.productView.ProductFrame;
import view.employeeView.EmployeeFrame;
import view.orderView.OrderFrame;
import view.dishView.DishFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public final class MainFrame extends javax.swing.JFrame {

    private final int currentUserID;
    private final EmployeeController employeeController;

    private OrderFrame orderFrame;
    private EmployeeFrame employeeFrame;
    private ProductFrame productFrame;
    private DishFrame dishFrame;
    private PersonalCabinetFrame cabinetFrame;
    
    public MainFrame(int currentUserID) {
        initComponents();
        
        employeeController = new EmployeeController();
        String role = employeeController.getByID(currentUserID).getPositionName();
        this.currentUserID = currentUserID;

        switch (role) {
            case "admin":
            case "director":
                employees.setVisible(true);
                orders.setVisible(true);
                storehouse.setVisible(true);
                menu.setVisible(true);
                break;
            case "cook":
                employees.setVisible(false);
                orders.setVisible(true);
                storehouse.setVisible(true);
                menu.setVisible(true);
                break;
            case "merchandiser":
                employees.setVisible(false);
                orders.setVisible(false);
                storehouse.setVisible(true);
                menu.setVisible(false);
                break;
            case "waiter":
                employees.setVisible(false);
                orders.setVisible(true);
                storehouse.setVisible(false);
                menu.setVisible(true);
                break;
            default:
                break;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        orders = new javax.swing.JMenu();
        menu = new javax.swing.JMenu();
        storehouse = new javax.swing.JMenu();
        employees = new javax.swing.JMenu();
        cabinet = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("null");
        setFocusable(false);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jDesktopPane1.setForeground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1);

        orders.setText("Orders");
        orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersMouseClicked(evt);
            }
        });
        jMenuBar1.add(orders);

        menu.setText("Dishes");
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        jMenuBar1.add(menu);

        storehouse.setText("Products");
        storehouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                storehouseMouseClicked(evt);
            }
        });
        jMenuBar1.add(storehouse);

        employees.setText("Employees");
        employees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeesMouseClicked(evt);
            }
        });
        jMenuBar1.add(employees);

        cabinet.setText("Personal Cabinet");
        cabinet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cabinetMouseClicked(evt);
            }
        });
        jMenuBar1.add(cabinet);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeesMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            try {
                employeeFrame = EmployeeFrame.getEmployeeFrame();
                jDesktopPane1.add(employeeFrame);
                employeeFrame.setLocation(jDesktopPane1.getLocation().x + 40, jDesktopPane1.getLocation().y + 10);
                employeeFrame.toFront();
                employeeFrame.setSelected(true);
                employeeFrame.setClosable(true);
                employeeFrame.setVisible(true);
                employeeFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                
            }
        });
    }//GEN-LAST:event_employeesMouseClicked

    private void storehouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storehouseMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            try {
                productFrame = ProductFrame.getProductFrame();
                jDesktopPane1.add(productFrame);
                productFrame.setLocation(jDesktopPane1.getLocation().x + 40, jDesktopPane1.getLocation().y + 10);
                productFrame.toFront();
                productFrame.setSelected(true);
                productFrame.setClosable(true);
                productFrame.setVisible(true);
                productFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                
            }
        });
    }//GEN-LAST:event_storehouseMouseClicked

    private void ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            try {
                orderFrame = OrderFrame.getOrderFrame(currentUserID);
                jDesktopPane1.add(orderFrame);
                orderFrame.setLocation(jDesktopPane1.getLocation().x + 10, jDesktopPane1.getLocation().y + 10);
                orderFrame.toFront();
                orderFrame.setSelected(true);
                orderFrame.setClosable(true);
                orderFrame.setVisible(true);
                orderFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                
            }
        });
    }//GEN-LAST:event_ordersMouseClicked

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            try {
                dishFrame = DishFrame.getDishFrame();
                jDesktopPane1.add(dishFrame);
                dishFrame.setLocation(jDesktopPane1.getLocation().x + 10, jDesktopPane1.getLocation().y + 10);
                dishFrame.toFront();
                dishFrame.setSelected(true);
                dishFrame.setClosable(true);
                dishFrame.setVisible(true);
                dishFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                
            }
        });
    }//GEN-LAST:event_menuMouseClicked

    private void cabinetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cabinetMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            try {
                cabinetFrame = PersonalCabinetFrame.getPersonalCabinetFrame(currentUserID);
                jDesktopPane1.add(cabinetFrame);
                cabinetFrame.setLocation(jDesktopPane1.getLocation().x + 10, jDesktopPane1.getLocation().y + 10);
                cabinetFrame.toFront();
                cabinetFrame.setSelected(true);
                cabinetFrame.setClosable(true);
                cabinetFrame.setVisible(true);
                cabinetFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                
            }
        });
    }//GEN-LAST:event_cabinetMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu cabinet;
    private javax.swing.JMenu employees;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menu;
    private javax.swing.JMenu orders;
    private javax.swing.JMenu storehouse;
    // End of variables declaration//GEN-END:variables
}
