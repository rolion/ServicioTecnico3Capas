/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DatosSql.NotaPeritoDTO;
import Negocio.NGestionarNotaEntrega;
import Negocio.NNotaPerito;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.jTableModel;

/**
 *
 * @author oscar
 */
public class VGestionarNotaEntrega extends javax.swing.JFrame {

    /**
     * Creates new form VGestionarNotaEntrega
     */
    private NGestionarNotaEntrega nNotaEntrega;
    private NNotaPerito nNotaPerito;
    private Object [] titulo={"Id","Id Nota Perito","Perito"};
    private List listNotaPerito;
    public VGestionarNotaEntrega() throws SQLException, ClassNotFoundException {
        initComponents();
        this.nNotaPerito=new NNotaPerito();
        this.nNotaEntrega=new NGestionarNotaEntrega();
        initComboBox();
    }
    private void initComboBox() {
        try{
            listNotaPerito=this.nNotaPerito.listarTodasSinNotaEntrega();
            if(listNotaPerito!=null ){
                
                this.jCBnotaPerito.setModel(new DefaultComboBoxModel(listNotaPerito.toArray()));
            }
                
            else
                this.jCBnotaPerito.setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
        }catch(Exception e){
            showMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
            
    }
     private java.sql.Date  getDate(){
        try { 
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date myDate;
            myDate = formatter.parse(this.jTFfecha.getText());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            return sqlDate;
        } catch (ParseException ex) {
            Logger.getLogger(VNotaPerito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFid = new javax.swing.JTextField();
        jCBnotaPerito = new javax.swing.JComboBox();
        jTFfecha = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nota Entrega");

        jPanel1.setBorder(null);

        jLabel1.setText("Id");

        jLabel2.setText("Nota Perito");

        jLabel3.setText("Fecha");

        jCBnotaPerito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTFfecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/dd/yyyy"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFfecha)
                    .addComponent(jTFid)
                    .addComponent(jCBnotaPerito, 0, 170, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBnotaPerito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("DarDeBaja");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            this.jTFid.setText(
            this.nNotaEntrega.nuevaNotaEntrega((NotaPeritoDTO) this.jCBnotaPerito.getSelectedItem(),
                    getDate()).toString());
            showMessage("data guardado", JOptionPane.INFORMATION_MESSAGE);
            initComboBox();
        } catch (ParseException ex) {
            Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
        try {
            this.nNotaEntrega.anularNota(
                    Integer.valueOf(this.jTFid.getText()), (NotaPeritoDTO)this.jCBnotaPerito.getSelectedItem(), 
                    getDate());
            showMessage("Datos eliminados", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            showMessage("Error al eliminar dato", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            showMessage("Error al eliminar dato", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        try {
//            busqueda=this.nNotaEntrega.buscarPorNotaPerito((NotaPerito) this.jCBnotaPerito.getSelectedItem());
//            this.jTFid.setText(busqueda.getId().toString());
//            SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
//            this.jTFfecha.setText(sdf.format(busqueda.getFecha()));
//        } catch (Exception ex) {
//            Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton3ActionPerformed
    private void showMessage(String mensaje,int tipo){
        JOptionPane.showMessageDialog(this, mensaje, "Error", tipo);
    }
    
//    private void cargarDetalle(){
//            try {
//                Object matriz [][];
//                jTableModel model;
//                if(listSelectedServicio!=null && !listSelectedServicio.isEmpty()){
//                    matriz =new Object[listSelectedServicio.size()][3];
//                    for(int i=0;i<listSelectedServicio.size();i++){
//                        Servicio tp=(Servicio) listSelectedServicio.get(i);
//                        matriz[i][0]=tp.getId().toString();
//                        matriz[i][1]=tp.getDescripcion();
//                        matriz[i][2]=tp.getPrecio().toString();
//                    } 
//                }else{
//                    matriz=new Object[1][3];
//                    matriz[0][0]="";
//                    matriz[0][1]="";
//                    matriz[0][2]="";
//                }
//                model=new jTableModel(titulo, matriz);
//                this.jTable1.setModel(model);
//                settableListener();   
//
//            }catch (Exception ex) {
//                    showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        private void settableListener(){
//        
//            this.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//
//                @Override
//                public void valueChanged(ListSelectionEvent e) {
//                    selectedRowIndex=jTable1.getSelectedRow();
//                }
//            });
//    }
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
            java.util.logging.Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VGestionarNotaEntrega().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VGestionarNotaEntrega.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jCBnotaPerito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField jTFfecha;
    private javax.swing.JTextField jTFid;
    // End of variables declaration//GEN-END:variables
}
