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
public class DetallePeritoDAO implements SpecificParticipant {
    private DetallePeritoDTO currentDetalle;
    private MySqlConector conn;
    private long currentTransaction;
    private final String tableName="detalle_perito";
    private final String column_id="id";
    private final String column_id_nota_perito="id_nota_perito";
    private final String column_id_servicio="id_servicio";
    private final String column_cantidad="cantidad";
    private final String column_precio_total="precio_total";
    private final String AllColumn=column_id_nota_perito+","+column_id_servicio+
            ","+column_cantidad+","+column_precio_total;
    public DetallePeritoDAO() throws SQLException, ClassNotFoundException {
        this.currentTransaction=0;
        this.conn=MySqlConector.getInstance();
    }
    private  void insertarDetalle() throws SQLException{
        if (this.conn != null) {
            String values =  + this.currentDetalle.getNotaPerito().getId()
                    + "," +  this.currentDetalle.getServicio().getId()
                    + ","  + this.currentDetalle.getCantidad()
                    +"," + this.currentDetalle.getPrecioTotal();

                int id=this.conn.insert(tableName, AllColumn, values);
                this.currentDetalle.setId(id);
        }
    }
    private ServicioDTO getServicioById(ServicioDTO servicio){
        try {
            ServicioDAO dao=new ServicioDAO();
            return dao.getById(servicio);
        } catch (SQLException ex) {
            Logger.getLogger(DetallePeritoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetallePeritoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    public List getDetalleByIdNota(NotaPeritoDTO nota) throws SQLException{
        List lista=new ArrayList();
        if(this.conn!=null){
            String where=column_id_nota_perito+"="+nota.getId() ;
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            while(rslt.next()){
                DetallePeritoDTO d=new DetallePeritoDTO();
                d.setCantidad(rslt.getInt(column_cantidad));
                d.setId(rslt.getInt(column_id));
                d.setNotaPerito(nota);
                d.setPrecioTotal(rslt.getFloat(column_precio_total));
                ServicioDTO servicio=new ServicioDTO();
                servicio.setId(rslt.getInt(column_id_servicio));
                d.setServicio(getServicioById(servicio));
                ExtintorDTO e=new ExtintorDTO();
                lista.add(d);
            }
        }
        return lista;
    }

    @Override
    public boolean validar(long transactionID, Object data) {
        if(this.currentTransaction==transactionID &&
            data instanceof DetallePeritoDTO){
            this.currentDetalle=(DetallePeritoDTO) data;
            if(this.currentDetalle.getCantidad()!=null &&
                    this.currentDetalle.getNotaPerito()!=null &&
                   // this.currentDetalle.getPrecioTotal()!=null &&
                    this.currentDetalle.getServicio()!=null){
                return true;
            }else
                this.currentDetalle=null;
                return false;
        }
        return false;
    }

    @Override
    public boolean join(long transactionID) {
        if(this.currentTransaction!=0)
            return false;
        this.currentTransaction=transactionID;
        return true;
    }

    @Override
    public void commit(long transactionID) throws Exception {
        if(this.currentTransaction==transactionID){
            this.insertarDetalle();
            this.currentDetalle=null;
            this.currentTransaction=0;
        }
    }

    @Override
    public void cancel(long transactionID) {
        if(this.currentTransaction==transactionID){
            this.currentDetalle=null;
            this.currentTransaction=0;
        }
    }
    
}
