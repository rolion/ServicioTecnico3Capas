/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class NotaEntregaDAO {
    private NotaEntregaDTO notaEntrega;
    private MySqlConector conn;
    private final String tableName="nota_entrega";
    private final String column_id="id";
    private final String column_id_nota_perito="id_nota_perito";
    private final String column_fecha="fecha";
    private final String column_eliminado="eliminado";
    private final String all_colum=column_id_nota_perito+","+
                               
                                column_fecha+","+
                                column_eliminado;

    public NotaEntregaDAO() throws SQLException, ClassNotFoundException {
        this.conn=MySqlConector.getInstance();
    }
    public void insertarNotaEntrega(NotaEntregaDTO nota) throws SQLException{
        if(this.conn!=null){
            String values=nota.getNotaPerito().getId()+",'"+
                            nota.getFecha()+"',"+
                            nota.getEliminado();
            int id=this.conn.insert(tableName, all_colum, values);
            nota.setId(id);
        }
    }
    public void actualizarNotaEntrega(NotaEntregaDTO nota) throws SQLException{
        if(this.conn!=null){
            String set=column_id_nota_perito+"="+nota.getNotaPerito().getId()+","+
                        column_fecha+"='"+nota.getFecha()+"',"+
                           column_eliminado+"="+ nota.getEliminado();
            String where=column_id+"="+nota.getId();
            this.conn.update(tableName, set, where,"");
        }
    }public List getAll() throws SQLException, ClassNotFoundException{
        List lista=new ArrayList();
        if(this.conn!=null){
            String where=column_eliminado+"=0";
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            while(rslt.next()){
                this.notaEntrega=new NotaEntregaDTO();
                this.notaEntrega.setEliminado(rslt.getBoolean(column_eliminado));
                this.notaEntrega.setFecha(rslt.getDate(column_fecha));
                this.notaEntrega.setId(rslt.getInt(column_id));
                NotaPeritoDTO n=new NotaPeritoDTO();
                n.setId(rslt.getInt(column_id_nota_perito));
                this.notaEntrega.setNotaPerito(new NotaPeritoDAO().getNotaPeritoById(n));
                lista.add(this.notaEntrega);
            }
        }
        return lista;
    }
    public NotaEntregaDTO getById(NotaEntregaDTO nota) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
           String where=column_eliminado+"=0 and "+column_id+"="+nota.getId();
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            if(rslt.next()){
                this.notaEntrega=new NotaEntregaDTO();
                this.notaEntrega.setEliminado(rslt.getBoolean(column_eliminado));
                this.notaEntrega.setFecha(rslt.getDate(column_fecha));
                this.notaEntrega.setId(rslt.getInt(column_id));
                NotaPeritoDTO n=new NotaPeritoDTO();
                n.setId(rslt.getInt(column_id_nota_perito));
                this.notaEntrega.setNotaPerito(new NotaPeritoDAO().getNotaPeritoById(n));
                return this.notaEntrega;
            } 
        }
        return null;
    }
}
