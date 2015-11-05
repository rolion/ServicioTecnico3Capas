/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import interfaces.SpecificParticipant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class DetalleNotaServicioDAO implements SpecificParticipant {

    private long currentTransaction;
    private MySqlConector conn;
    private DetalleNotaServicioDTO detalle;
    private final String tableName = "detalle_nota";
    private final String column_id = "id";
    private final String colum_id_extintor = "id_extintor";
    private final String colum_id_nota = "id_nota";
    private final String colum_cantidad = "cantidad";
    private final String all_column = colum_id_extintor + "," + colum_id_nota
            + "," + colum_cantidad;
    private final ExtintorDAO extintorDAO;


    public DetalleNotaServicioDAO() throws SQLException, ClassNotFoundException {
        this.currentTransaction = 0;
        this.conn = MySqlConector.getInstance();
        this.extintorDAO=new ExtintorDAO();
    }

    public long getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(long currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    
    public DetalleNotaServicioDTO getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleNotaServicioDTO detalle) {
        this.detalle = detalle;
    }
    
    public List getDetalleByIdNota(NotaServicioDTO nota) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String where=colum_id_nota+"="+nota.getId() ;
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            List lista=new ArrayList();
            while(rslt.next()){
                DetalleNotaServicioDTO d=new DetalleNotaServicioDTO();
                d.setCantidad(rslt.getInt(colum_cantidad));
                d.setId(rslt.getInt(column_id));
                d.setNotaServicio(nota);
                ExtintorDTO e=new ExtintorDTO();
                e.setId(rslt.getInt(colum_id_extintor));
                e=this.extintorDAO.getExtintorById(e);
                d.setExtintor(e);
                lista.add(d);
            }
            return lista;
        }
        return null;
    }

    @Override
    public boolean insertar(long transactionID) {
        if (this.conn != null && this.detalle != null) {
            String values = colum_id_extintor + "=" + this.detalle.getExtintor().getId()
                    + "," + colum_id_nota + "=" + this.detalle.getNotaServicio().getId()
                    + "," + colum_cantidad + "=" + this.detalle.getCantidad();
            try {
                int id=this.conn.insert(tableName, all_column, values);
                this.detalle.setId(id);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(DetalleNotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
                
            }
        }
        return false;
    }

    @Override
    public boolean buscar(long transactionID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean anular(long transactionID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actializar(long transactionID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean join(long transactionID) {
        if (this.currentTransaction != 0) {
            return false;
        }
        this.currentTransaction = transactionID;
        return true;

    }

    @Override
    public boolean commit(long transactionID) throws Exception {
        if(this.currentTransaction==transactionID){
            return this.insertar(transactionID);
        }
        return false;
    }

    @Override
    public boolean cancel(long transactionID) {
        if(this.currentTransaction==transactionID){
            try {
                return this.eliminarDetalle();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(DetalleNotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    private boolean eliminarDetalle() throws SQLException{
        if(this.conn!=null && detalle!=null && detalle.getId()!=null){
            String where=column_id+"="+this.detalle.getId();
            this.conn.delete(tableName, where, "");
        }
        return false;
    }

}
