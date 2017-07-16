package view.orderView;

import controller.OrderingController;
import javax.swing.JOptionPane;

public final class AddOrdering extends javax.swing.JInternalFrame {

    private static AddOrdering addOrdering;
    private final OrderingController mealsOrderController;
    private final int currentUserID;

    private AddOrdering(OrderingController mealsOrderController, int currentUserID) {
        initComponents();
        this.mealsOrderController = mealsOrderController;
        this.currentUserID = currentUserID;

    }

    public static AddOrdering getAddOrdering(OrderingController mealsOrderController, int currentUserID) {
        if (addOrdering != null) {
            addOrdering.dispose();
        } else {
            addOrdering = new AddOrdering(mealsOrderController, currentUserID);
        }
        return addOrdering;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableIDField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        addOrderButton = new javax.swing.JButton();

        setTitle("Adding the order");

        tableIDField.setToolTipText("");

        jLabel2.setText("Table ID");

        addOrderButton.setText("Add the order");
        addOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(tableIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(addOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderButtonActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            int tableID = Integer.valueOf(tableIDField.getText());

            if (tableID < 1) {
                JOptionPane.showMessageDialog(null, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (mealsOrderController.addOrdering(tableID, currentUserID, new java.util.Date()) == false) {
                JOptionPane.showMessageDialog(null, "Error while adding the record", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            addOrdering.dispose();
        });
    }//GEN-LAST:event_addOrderButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOrderButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tableIDField;
    // End of variables declaration//GEN-END:variables
}
