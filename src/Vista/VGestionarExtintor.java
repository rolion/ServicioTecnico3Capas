/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DatosSql.AgenteQuimicoDTO;
import DatosSql.ExtintorDTO;
import Negocio.NGestionarAgenteQuimico;
import Negocio.NGestionarExtintor;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import util.jTableModel;

/**
 *
 * @author oscar
 */
public class VGestionarExtintor extends javax.swing.JFrame {

    /**
     * Creates new form VGestionarExtintor
     */
    private NGestionarAgenteQuimico ngaq;
    private NGestionarExtintor nge;
    Object [] titulo={"Id","Agente Quimico","Peso","Clasificacion"};
    
    public VGestionarExtintor() {
        initComponents();
        this.ngaq=new NGestionarAgenteQuimico();
        try {
            this.nge=new NGestionarExtintor();
        } catch (SQLException ex) {
            Logger.getLogger(VGestionarExtintor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VGestionarExtintor.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComboBox();
    }
    private void initComboBox(){
        List listaAgenteQuimico;
        try {
            listaAgenteQuimico = this.ngaq.listarTodos();
            this.jCBagentequimico.setModel(new DefaultComboBoxModel(listaAgenteQuimico.toArray()));
        } catch (Exception ex) {
            Logger.getLogger(VGestionarExtintor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCBagentequimico = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTFpeso = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFclasificacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jBListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestionar Extintor");

        jPanel1.setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Id");
        jPanel1.add(jLabel1);

        jTFid.setEnabled(false);
        jPanel1.add(jTFid);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Agente Quimico");
        jPanel1.add(jLabel2);

        jCBagentequimico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jCBagentequimico);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Peso");
        jPanel1.add(jLabel3);

        jTFpeso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPanel1.add(jTFpeso);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Clasificacion");
        jPanel1.add(jLabel4);
        jPanel1.add(jTFclasificacion);

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Modificar");
        jButton2.setActionCommand("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setText("Dar de baja");
        jButton3.setActionCommand("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jBListar.setText("Listar");
        jBListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBListarActionPerformed(evt);
            }
        });
        jPanel2.add(jBListar);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Agente Quimico", "Peso", "Clasificacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            this.jTFid.setText(this.nge.nuevoExtintor(
                        (AgenteQuimicoDTO)this.jCBagentequimico.getSelectedItem(),
                    this.jTFclasificacion.getText(),
                    Integer.valueOf(this.jTFpeso.getText())).getId().toString());
            showMessage("Datos Guardados", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            this.nge.modificarExtintor(
                    Integer.valueOf(this.jTFid.getText()),
                    (AgenteQuimicoDTO)this.jCBagentequimico.getSelectedItem(),
                    this.jTFclasificacion.getText(),
                    Integer.valueOf(this.jTFpeso.getText()));
            showMessage("Datos modificados", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            this.nge.darDeBajaExtintor(Integer.valueOf(this.jTFid.getText()),
                    (AgenteQuimicoDTO)this.jCBagentequimico.getSelectedItem(),
                    this.jTFclasificacion.getText(),
                    Integer.valueOf(this.jTFpeso.getText()));
            showMessage("Datos eliminados", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
           
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListarActionPerformed
        
        try {
            List listaExtintor=this.nge.listar();
            Object matriz [][]=new Object[listaExtintor.size()][4];
            int i=0;
            for (Object aq : listaExtintor) {
               i=listaExtintor.indexOf(aq);
                ExtintorDTO a=(ExtintorDTO) aq;
                matriz[i][0]=a.getId();
                matriz[i][1]=a.getAgenteQuimico();
                matriz[i][2]=a.getPeso();
                matriz[i][3]=a.getClasificacion();
                settableListener();
            }
            this.jTable1.setModel(new jTableModel(titulo, matriz));
        } catch (Exception ex) {
            Logger.getLogger(VGestionarAgenteQuimico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBListarActionPerformed
    private void settableListener(){
        
        this.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRowIndex=jTable1.getSelectedRow();
                if(selectedRowIndex>-1 && selectedRowIndex<jTable1.getModel().getRowCount()){
                    TableModel model= jTable1.getModel();
                    jTFid.setText( model.getValueAt(selectedRowIndex, 0).toString());
                    jCBagentequimico.setSelectedItem( model.getValueAt(selectedRowIndex, 1));
                    jTFpeso.setText(model.getValueAt(selectedRowIndex, 2).toString());
                   jTFclasificacion.setText(model.getValueAt(selectedRowIndex, 3).toString());
                    
                }
            }
        });

    
    }
    private void showMessage(String mensaje, int type){
        JOptionPane.showMessageDialog(this, mensaje, "mensaje", type);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VGestionarExtintor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VGestionarExtintor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VGestionarExtintor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VGestionarExtintor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VGestionarExtintor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBListar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jCBagentequimico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFclasificacion;
    private javax.swing.JTextField jTFid;
    private javax.swing.JFormattedTextField jTFpeso;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
