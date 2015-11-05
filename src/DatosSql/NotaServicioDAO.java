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
public class NotaServicioDAO implements SpecificParticipant{
    
    private NotaServicioDTO nota;
    private PersonaDAO personaDAO;
    private DetalleNotaServicioDAO detalleDAO;
    private long currentTransaction;
    private MySqlConector conn;
    private final String tableName="nota_servicio";
    private final String column_id="id";
    private final String column_id_cliente="id_cliente";
    private final String column_fecha="fecha";
    private final String column_descripcion="descripcion";
    private final String column_eliminado="eliminado";
    private final String all_columns=column_id_cliente+","+
                            column_fecha+","+column_descripcion+","
                            +column_eliminado;

    public NotaServicioDAO() throws SQLException, ClassNotFoundException {
        this.personaDAO=new PersonaDAO();
        this.detalleDAO=new DetalleNotaServicioDAO();
        this.conn=MySqlConector.getInstance();
    }
    

    @Override
    public boolean insertar(long transactionID) {
        if(this.conn!=null && nota!=null){
            String values=nota.getPersona().getId()+","+
                    "'"+nota.getFecha().toString()+"',"+
                    nota.getDescripcionCliente()+","+
                    nota.getEliminado();
            try {
                int id=this.conn.insert(tableName, all_columns, values);
                this.nota.setId(id);
                boolean valid=true;
                for(Object de:nota.getDetalle()){
                    DetalleNotaServicioDTO d=(DetalleNotaServicioDTO) de;
                    d.setNotaServicio(nota);
                    this.detalleDAO.setDetalle(d);
                    valid=valid && this.detalleDAO.commit(transactionID);
                }
                return valid;
            } catch (SQLException ex) {
                Logger.getLogger(NotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(NotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    private PersonaDTO getPersonaById(PersonaDTO p) throws SQLException, ClassNotFoundException{
        return this.personaDAO.getById(p);
    }
    public List getAll() throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            List lista =new ArrayList();
            ResultSet rslt=this.conn.query("*", tableName, "'", "");
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
                nServicio.setDetalle(this.detalleDAO.getDetalleByIdNota(nServicio));
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
                n.setDetalle(this.detalleDAO.getDetalleByIdNota(n));
                return n;
            }
        }
        return null;
    }
    
    private boolean delete(NotaServicioDTO nota) throws SQLException{
        if(nota!=null && nota.getDetalle()!=null && this.conn!=null){
            List ldetalle=nota.getDetalle();
            for (Object ldetalle1 : ldetalle) {
                this.detalleDAO.setDetalle((DetalleNotaServicioDTO) ldetalle);
                this.detalleDAO.cancel(currentTransaction);
            }
            String where=column_id+"="+nota.getId();
            this.conn.delete(tableName, where, "");
            return true;
        }
        return false;
    }

    @Override
    public boolean join(long transactionID) {
        if(this.currentTransaction!=0){
            return false;
        }
        else{
            boolean valid=true && this.personaDAO.join(transactionID) && this.detalleDAO.join(transactionID);
            if(!valid){
                this.currentTransaction=0;
                this.personaDAO.setCurrentTransction(0);
                this.detalleDAO.setCurrentTransaction(0);
            }
            this.currentTransaction=transactionID;
            return valid;
        }
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
                this.delete(this.nota);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(NotaServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
}
