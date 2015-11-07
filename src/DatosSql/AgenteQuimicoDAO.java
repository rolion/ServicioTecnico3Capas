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
public class AgenteQuimicoDAO {
    private final String tableName="agente_quimico";
    private final String column_id="id";
    private final String colum_nombre="nombre";
    private final String column_clasificacion="clasificacion";
    private final String column_eliminado="eliminado";
    private final String all_columns=colum_nombre+","+column_clasificacion+","+column_eliminado;
    private AgenteQuimicoDTO agenteQuimico;
    private MySqlConector conn;

    public AgenteQuimicoDAO() throws SQLException, ClassNotFoundException {
        this.conn=MySqlConector.getInstance();
    }
    public AgenteQuimicoDTO insertarAgenteQUimico(AgenteQuimicoDTO agente) throws SQLException{
        if(conn!=null){
            
            String values="'"+agente.getNombre()+"','"+agente.getClasificacion()+"',"+agente.getEliminado();
            int id=this.conn.insert(tableName, all_columns, values);
            agente.setId(id);
            return agente;
        }
        return null;
    }
    public boolean actualizaAgenteQuimico(AgenteQuimicoDTO agente) throws SQLException{
        if(this.conn!=null){
            String set=colum_nombre+"='"+agente.getNombre()+"',"
                    +column_clasificacion+"='"+agente.getClasificacion()+"',"
                    +column_eliminado+"="+agente.getEliminado();
            String where=column_id+"="+agente.getId();
            this.conn.update(tableName, set, where, "");
            return true;
        }
        return false;
    }
    public boolean eliminarAgenteQuimico(AgenteQuimicoDTO agente) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+agente.getId();
            this.conn.delete(tableName, where, "");
            return true;
        }
        return false;
    } 
    public List getAll() throws SQLException{
        if(this.conn!=null){
            ResultSet rslt;
            List lista=new ArrayList();
            String where=column_eliminado+"=0";
            rslt=this.conn.query("*", tableName, where,"");
            while(rslt.next()){
                AgenteQuimicoDTO agente=new AgenteQuimicoDTO();
                agente.setId(rslt.getInt(1));
                agente.setNombre(rslt.getString(2));
                agente.setClasificacion(rslt.getString(3));
                agente.setEliminado(rslt.getBoolean(4));
                lista.add(agente);
            }
            return lista;
        }
        return null;
    }
    public AgenteQuimicoDTO getAgenteById(AgenteQuimicoDTO agente) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+agente.getId();
            ResultSet rslt=this.conn.query("*", tableName, where, "");
            if(rslt.next()){
                //AgenteQuimicoDTO agente=new AgenteQuimicoDTO();
                agente.setId(rslt.getInt(1));
                agente.setNombre(rslt.getString(2));
                agente.setClasificacion(rslt.getString(3));
                agente.setEliminado(rslt.getBoolean(4));
                return agente;
            }
        }
        return null;
    }
    
}
