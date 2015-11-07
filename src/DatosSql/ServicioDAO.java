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
public class ServicioDAO {
    private ServicioDTO servicio;
    private MySqlConector conn;
    private final String tableName="servicio";
    private final String column_id="id";
    private final String column_precio="precio";
    private final String column_eliminado="eliminado";
    private final String column_descripcion="descripcion";
    private final String all_colum=
            column_precio+","+column_descripcion+","+
            column_eliminado;

    public ServicioDAO() throws SQLException, ClassNotFoundException {
        this.conn=MySqlConector.getInstance();
    }
    public ServicioDTO insertarServicio(ServicioDTO servicio) throws SQLException{
        if(this.conn!=null){
            String values=servicio.getPrecio()+",'"+
                    servicio.getDescripcion()+"',"+
                    servicio.getEliminado();
            int id=this.conn.insert(tableName, all_colum, values);
            servicio.setId(id);
            return servicio;
        }
        return null;
    }
    public boolean actualizarServicio(ServicioDTO servicio) throws SQLException{
        if(this.conn!=null){
            String set=column_precio+"="+servicio.getPrecio()+","+
                    column_descripcion+"='"+servicio.getDescripcion()+"',"
                    +column_eliminado+"="+servicio.getEliminado();
            String Where=column_id+"="+servicio.getId();
            this.conn.update(tableName, set, Where, "");
            return true;
        }
        return false;
    }
    public boolean eliminarServicio(ServicioDTO servicio) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+servicio.getId();
            this.conn.delete(tableName, where, "");
            return true;
        }
        return false;
    }
    public List getAll() throws SQLException{
        if(this.conn!=null){
            List lista=new ArrayList();
            String where=column_eliminado+"= 0";
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            while(rslt.next()){
                this.servicio=new ServicioDTO();
                this.servicio.setId(rslt.getInt(column_id));
                this.servicio.setDescripcion(rslt.getString(column_descripcion));
                this.servicio.setEliminado(rslt.getBoolean(column_eliminado));
                this.servicio.setPrecio(rslt.getFloat(column_precio));
                lista.add(this.servicio);
            }
            return lista;
        }
        return null;
    }
    public ServicioDTO getById(ServicioDTO servicio) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+servicio.getId()+" and "+
                    column_eliminado+"=0";
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            if(rslt.next()){
                this.servicio=new ServicioDTO();
                this.servicio.setId(rslt.getInt(column_id));
                this.servicio.setDescripcion(rslt.getString(column_descripcion));
                this.servicio.setEliminado(rslt.getBoolean(column_eliminado));
                this.servicio.setPrecio(rslt.getFloat(column_precio));
                return this.servicio;
            }
        }
        return null;
    }
    
}
