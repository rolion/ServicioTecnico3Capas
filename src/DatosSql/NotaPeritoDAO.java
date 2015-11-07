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
public class NotaPeritoDAO implements SpecificParticipant{
    
    private NotaPeritoDTO currentNota;
    private MySqlConector conn;
    private long currentTransaction;
    
    private final String tableName="nota_perito";
    private final String colum_id="id";
    private final String colum_id_nota="id_nota";
    private final String colum_fecha="fecha";
    private final String colum_id_perito="id_perito";
    private final String colum_eliminado="eliminado";
    private final String all_colum=colum_id_nota+","+
                        colum_fecha+","+
                        colum_id_perito+","+
                        colum_eliminado;

    public NotaPeritoDAO() throws SQLException, ClassNotFoundException {
        this.conn=MySqlConector.getInstance();
        this.currentTransaction=0;
    }
    
    private void insertarNota() throws SQLException{
        if(this.conn!=null){
            String values=this.currentNota.getNotaServicio().getId()+",'"+
                         this.currentNota.getFecha()+"',"+
                         this.currentNota.getPerito().getId()+","+
                         this.currentNota.getEliminado();
            int id=this.conn.insert(tableName, all_colum, values);
            this.currentNota.setId(id);
        }
    }
    public void actualizarNota(NotaPeritoDTO nota) throws SQLException{
        if(this.conn!=null){
            String set=colum_id_nota+"="+nota.getNotaServicio().getId()+","+
                        colum_fecha+"='"+nota.getFecha()+"',"+
                        colum_id_perito+"="+ nota.getPerito().getId()+","+
                         colum_eliminado+"="+nota.getEliminado();
            String where=colum_id+"="+nota.getId();
            this.conn.update(tableName, set, where, "");
        }
    }
    public NotaPeritoDTO getNotaPeritoById(NotaPeritoDTO nota) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String where=colum_id+"="+nota.getId()+" and "+colum_eliminado+"=0";
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            if(rslt.next()){
                nota.setId(rslt.getInt(colum_id));
                nota.setFecha(rslt.getDate(colum_fecha));
                nota.setEliminado(rslt.getBoolean(colum_eliminado));
                PersonaDTO p= new PersonaDTO();
                p.setId(rslt.getInt(colum_id_perito));
                nota.setPerito(new PersonaDAO().getById(p));
                nota.setDetalle(new DetallePeritoDAO().getDetalleByIdNota(nota));
                NotaServicioDTO nServicio=new NotaServicioDTO();
                nServicio.setId(rslt.getInt(colum_id_nota));
                nota.setNotaServicio(new NotaServicioDAO().getNotaById(nServicio));
                return nota;
            }
        }
        return null;
    }
    private void eliminarNota(){
        if(this.conn!=null){
            String where=colum_id+"="+this.currentNota.getId();
            try {
                this.conn.delete(tableName, where, "");
            } catch (SQLException ex) {
                Logger.getLogger(NotaPeritoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public boolean validar(long transactionID, Object data) {
        if(this.currentTransaction==transactionID &&
                data instanceof NotaPeritoDTO){
            this.currentNota=(NotaPeritoDTO) data;
            if(this.currentNota.getNotaServicio()!=null &&
                this.currentNota.getPerito()!=null &&
                this.currentNota.getFecha()!=null &&
                this.currentNota.getEliminado()!=null){
                try {
                    this.insertarNota();
                } catch (SQLException ex) {
                    Logger.getLogger(NotaPeritoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }else
                this.currentNota=null;
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
            this.currentNota=null;
            this.currentTransaction=0;
        }
    }

    @Override
    public void cancel(long transactionID) {
        if(this.currentTransaction==transactionID){
            this.eliminarNota();
            this.currentTransaction=0;
            this.currentNota=null;
        }
    }
    public List getAll() throws SQLException, ClassNotFoundException{
        List lista=new ArrayList();
        if(this.conn!=null){
            //SELECT np.id, np.id_nota, np.id_perito FROM nota_perito np LEFT JOIN nota_entrega ne on np.id=ne.id_nota_perito
            String select="np.id, np.id_nota, np.id_perito, np.eliminado, np.fecha ";
            String from="nota_perito np LEFT JOIN nota_entrega ne ON np.id=ne.id_nota_perito";
            String where="np."+colum_eliminado+"= 0";
            ResultSet rslt=this.conn.query(select, from, where, "");
            while(rslt.next()){
                this.currentNota=new NotaPeritoDTO();
                this.currentNota.setEliminado(rslt.getBoolean(colum_eliminado));
                this.currentNota.setFecha(rslt.getDate(colum_fecha));
                this.currentNota.setId(rslt.getInt(colum_id));
                NotaServicioDTO nota=new NotaServicioDTO();
                nota.setId(rslt.getInt(colum_id_nota));
                PersonaDTO persona=new PersonaDTO();
                persona.setId(rslt.getInt(colum_id_perito));
                this.currentNota.setNotaServicio(new NotaServicioDAO().getNotaById(nota));
                this.currentNota.setPerito(new PersonaDAO().getById(persona));
                lista.add(this.currentNota);
            }
        }
        return lista;
    }
    
    public List getAllNotaPeritoSinNotaEntrega() throws SQLException, ClassNotFoundException{
    List lista=new ArrayList();
        if(this.conn!=null){
            
            String select="*";
            String from=tableName;
            String where="id not in(SELECT np.id FROM nota_perito np JOIN nota_entrega ne ON np.id = ne.id_nota_perito WHERE np.eliminado =0) and eliminado =0";
            ResultSet rslt=this.conn.query(select, from, where, "");
            while(rslt.next()){
                this.currentNota=new NotaPeritoDTO();
                this.currentNota.setEliminado(rslt.getBoolean(colum_eliminado));
                this.currentNota.setFecha(rslt.getDate(colum_fecha));
                this.currentNota.setId(rslt.getInt(colum_id));
                NotaServicioDTO nota=new NotaServicioDTO();
                nota.setId(rslt.getInt(colum_id_nota));
                PersonaDTO persona=new PersonaDTO();
                persona.setId(rslt.getInt(colum_id_perito));
                this.currentNota.setNotaServicio(new NotaServicioDAO().getNotaById(nota));
                this.currentNota.setPerito(new PersonaDAO().getById(persona));
                lista.add(this.currentNota);
            }
        }
        return lista;
    }
}
