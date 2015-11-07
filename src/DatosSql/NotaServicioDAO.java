/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import interfaces.SpecificParticipant;
import interfaces.TransactionParticipant;
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
public class NotaServicioDAO implements SpecificParticipant{
    
    private NotaServicioDTO currentNota;
    private long currentTransaction;
    private MySqlConector conn;
    private final String tableName="nota_servicio";
    private final String column_id="id";
    private final String column_id_cliente="id_cliente";
    private final String column_fecha="fecha";
    private final String column_descripcion="descripcion_cliente";
    private final String column_eliminado="eliminado";
    private final String all_columns=column_id_cliente+","+
                            column_fecha+","+column_descripcion+","
                            +column_eliminado;

    public NotaServicioDAO() throws SQLException, ClassNotFoundException {
  

        this.conn=MySqlConector.getInstance();
    }

    private PersonaDTO getPersonaById(PersonaDTO p) throws SQLException, ClassNotFoundException{
        return new PersonaDAO().getById(p);
    }
    public List getAll() throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            List lista =new ArrayList();
            ResultSet rslt=this.conn.query("*", tableName, "", "");
            while(rslt.next()){
                NotaServicioDTO nServicio=new NotaServicioDTO();
                nServicio.setId(rslt.getInt(column_id));
                nServicio.setFecha(rslt.getDate(column_fecha));
                nServicio.setDescripcionCliente(rslt.getString(column_descripcion));
                nServicio.setEliminado(rslt.getBoolean(column_eliminado));
                PersonaDTO p= new PersonaDTO();
                p.setId(rslt.getInt(column_id_cliente));
                p=getPersonaById(p);
                nServicio.setPersona(p);
                nServicio.setDetalle(new DetalleNotaServicioDAO().getDetalleByIdNota(nServicio));
                lista.add(nServicio);
            }
            return lista;
        }
        
        return null;
    }
    public NotaServicioDTO getNotaById(NotaServicioDTO n) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String Where=column_id+"="+n.getId()+" and "+column_eliminado+"=0";
            ResultSet rslt=this.conn.query("*", tableName, Where, "");
            if(rslt.next()){
                n=new NotaServicioDTO();
                n.setId(rslt.getInt(column_id));
                n.setFecha(rslt.getDate(column_fecha));
                n.setDescripcionCliente(rslt.getString(column_descripcion));
                n.setEliminado(rslt.getBoolean(column_eliminado));
                PersonaDTO p= new PersonaDTO();
                p.setId(rslt.getInt(column_id_cliente));
                p=getPersonaById(p);
                n.setPersona(p);
                n.setDetalle(new DetalleNotaServicioDAO().getDetalleByIdNota(n));
                return n;
            }
        }
        return null;
    }
    
    private boolean eliminar(NotaServicioDTO nota) throws SQLException{
        if(nota!=null && nota.getDetalle()!=null && this.conn!=null){
            List ldetalle=nota.getDetalle();
            String where=column_id+"="+nota.getId();
            this.conn.delete(tableName, where, "");
            return true;
        }
        return false;
    }
    private void delete() throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+this.currentNota.getId();
            this.conn.delete(tableName, tableName, tableName);
        }
    }
    private void insertar() {
        if(this.conn!=null){
            try {
                String values=this.currentNota.getPersona().getId()+",'"+
                            this.currentNota.getFecha()+"','"+
                            this.currentNota.getDescripcionCliente()+"',"+
                            this.currentNota.getEliminado();
                int id=this.conn.insert(tableName, all_columns, values);
                this.currentNota.setId(id);
            } catch (SQLException ex) {
                Logger.getLogger(NotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void actualizar(NotaServicioDTO nota){
        if(this.conn!=null){
            try {
                String values=nota.getPersona().getId()+",'"+
                            nota.getFecha()+"','"+
                            nota.getDescripcionCliente()+"',"+
                            nota.getEliminado();
                int id=this.conn.insert(tableName, all_columns, values);
                this.currentNota.setId(id);
            } catch (SQLException ex) {
                Logger.getLogger(NotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        if(this.currentTransaction==transactionID && this.currentNota!=null){
            
            this.currentTransaction=0;
        }
    }

    @Override
    public void cancel(long transactionID) {
        if(this.currentTransaction==transactionID){
            try {
                this.delete();
                this.currentNota=null;
                this.currentTransaction=0;
            } catch (SQLException ex) {
                Logger.getLogger(NotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public boolean validar(long transactionID, Object data) {
        if(this.currentTransaction==transactionID && data instanceof NotaServicioDTO){
            NotaServicioDTO nota=(NotaServicioDTO) data;
            if(nota.getFecha()!=null && nota.getPersona()!=null && 
                    !nota.getDescripcionCliente().trim().isEmpty()){
                this.currentNota=nota;
                this.insertar();
                return true;
            }
            return false;
        }
        return false;
    }
}
