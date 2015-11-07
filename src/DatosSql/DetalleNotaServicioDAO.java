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
public class DetalleNotaServicioDAO implements SpecificParticipant{

    private long currentTransaction;
    private MySqlConector conn;
    private DetalleNotaServicioDTO currentDetalle;
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

    private boolean insertar() throws SQLException{
        if (this.conn != null) {
            String values =  + this.currentDetalle.getExtintor().getId()
                    + "," +  this.currentDetalle.getNotaServicio().getId()
                    + ","  + this.currentDetalle.getCantidad();

                int id=this.conn.insert(tableName, all_column, values);
                this.currentDetalle.setId(id);
                return true;
            
        }
        return false;
    }

    @Override
    public boolean validar(long transactionID, Object object) {
        
        if(this.currentTransaction==transactionID && object instanceof DetalleNotaServicioDTO){
            DetalleNotaServicioDTO detalle=(DetalleNotaServicioDTO) object;
            //validamos que la nota exista
            if(detalle.getExtintor()!=null){
                this.currentDetalle=detalle;
                return true;
            }else
                return false;
        }
        return false;
    }

    @Override
    public boolean join(long transactionID) {
        if(this.currentTransaction!=0){
            return false;
        }
        this.currentTransaction=transactionID;
        return true;
    }

    @Override
    public void commit(long transactionID) throws Exception {
        if(this.currentTransaction==transactionID){
            this.currentTransaction=0;
            this.insertar();
        }
    }

    @Override
    public void cancel(long transactionID) {
        if(this.currentTransaction==transactionID && this.currentDetalle!=null){
            this.currentDetalle=null;
            this.currentTransaction=0;
        }
    }

}
