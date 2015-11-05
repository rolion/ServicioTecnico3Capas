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
public class TipoPersonaDAO {
    private final String tableName="tipo_persona";
    private final String column_id="id";
    private final String colum_nombre="nombre";
//    private final String column_apellido="apellido";
//    private final String column_ci="ci";
//    private final String column_telefono="telefono";
//    private final String column_email="email";
//    private final String column_tipo_persona="id_tipo_persona";
    private final String column_descripcion="descripcion";
    private final String column_eliminado="eliminado";
//    private final String all_columns=colum_nombre+","+
//            column_apellido+","+column_ci+","
//            +column_telefono+","+column_email+","
//            +column_tipo_persona+","+column_eliminado;
    private final String all_columns=colum_nombre+","+column_eliminado
            +","+column_descripcion;
    private MySqlConector conn;
    

    public TipoPersonaDAO() throws SQLException, ClassNotFoundException {
        this.conn=MySqlConector.getInstance();
    }
    
    public TipoPersonaDTO insertarTipoPesona(TipoPersonaDTO tipoPersona) throws SQLException{
        if(this.conn!=null){
            String values=colum_nombre+"="+tipoPersona.getNombre()
                    +","+column_eliminado+"="+tipoPersona.getEliminado()+
                    ","+column_descripcion+"="+tipoPersona.getDescripcion();
            int id=this.conn.insert(tableName, all_columns, values);
            tipoPersona.setId(id);
            return tipoPersona;
        }
        return null;
    }
    public boolean actulializarTipoPersona(TipoPersonaDTO tipoPersona) throws SQLException{
        if(this.conn!=null){
            String set=colum_nombre+"="+tipoPersona.getNombre()+","+
                    column_descripcion+"="+tipoPersona.getDescripcion()+","
                    + column_eliminado+"="+tipoPersona.getEliminado();
            String where=column_id+"="+tipoPersona.getId();
            this.conn.update(tableName, set, where, "");
            return true;
        }
        return false;
    }
    public boolean eliminarTipoPersona(TipoPersonaDTO tipoPersona) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+tipoPersona.getId();
            this.conn.delete(tableName, where, "");
        }
        return false;
    }
    public List getAll() throws SQLException{
        if(this.conn!=null){
            List lista=new ArrayList();
            ResultSet rslt=this.conn.query("*", tableName, "", "");
            while(rslt.next()){
                TipoPersonaDTO tipoPersona=new TipoPersonaDTO();
                tipoPersona.setId(rslt.getInt(column_id));
                tipoPersona.setDescripcion(rslt.getString(column_descripcion));
                tipoPersona.setEliminado(rslt.getBoolean(column_eliminado));
                tipoPersona.setNombre(rslt.getString(colum_nombre));
                lista.add(tipoPersona); 
            }
            return lista;
        }
        return null;
    }
    public TipoPersonaDTO getById(TipoPersonaDTO tipoPersona) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+tipoPersona.getId();
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            if(rslt.next()){
                tipoPersona.setId(rslt.getInt(column_id));
                tipoPersona.setDescripcion(rslt.getString(column_descripcion));
                tipoPersona.setEliminado(rslt.getBoolean(column_eliminado));
                tipoPersona.setNombre(rslt.getString(colum_nombre));
                return tipoPersona;
            }
        }
        return null;
    }
    
}
