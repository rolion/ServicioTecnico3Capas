/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import Datos.AgenteQuimico;
import interfaces.ProtoType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author root
 */
public class ExtintorDAO {
    private ExtintorDTO extintorDTO;
    private AgenteQuimicoDAO agente;
    private MySqlConector conn;
    private final String tableName="extintor";
    private final String column_id="id";
    private final String column_id_agente_quimico="id_agente_quimico";
    private final String column_clasificacion="clasificacion";
    private final String column_eliminado="eliminado";
    private final String column_peso="peso";
    private final String all_column=
            column_clasificacion+","+column_eliminado+","+
            column_id_agente_quimico+","+column_peso;
    public ExtintorDAO() throws SQLException, ClassNotFoundException {
        this.conn=MySqlConector.getInstance();
    }
    public ExtintorDTO insertarExtintor(ExtintorDTO extintor) throws SQLException{
        if(this.conn!=null){
            String values=
                    column_clasificacion+"="+extintor.getClasificacion()+","
                    +column_eliminado+"="+extintor.getEliminado()+","+
                    column_id_agente_quimico+"="+extintor.getAgenteQuimico().getId()+
                    ","+column_peso+"="+extintor.getPeso();
            int id=this.conn.insert(tableName, all_column, values);
            extintor.setId(id);
            return extintor;
        }
        return null;
    }
    public boolean actualizarExtintor(ExtintorDTO extintor) throws SQLException{
        if(this.conn!=null){
            String set=column_clasificacion+"="+extintor.getClasificacion()+","
                    +column_eliminado+"="+extintor.getEliminado()+","+
                    column_id_agente_quimico+"="+extintor.getAgenteQuimico().getId()+
                    ","+column_peso+"="+extintor.getPeso();
            String where=column_id+"="+extintor.getId();
            this.conn.update(tableName, set, where, "");
            return true;
        }
        return false;
    }
    public boolean eliminarExtintor(ExtintorDTO extintor) throws SQLException{
        if(this.conn!=null){
            String where=column_id+"="+extintor.getId();
            this.conn.delete(tableName, where, "");
            return true;
        }
        return false;
    }
    public List getAll() throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            ResultSet rslt=this.conn.query("*", tableName, "", "");
            List lista=new ArrayList();
            while(rslt.next()){
                this.extintorDTO=new ExtintorDTO();
                this.extintorDTO.setId(rslt.getInt(column_id));
                AgenteQuimicoDTO a=new AgenteQuimicoDTO();
                a.setId(rslt.getInt(column_id_agente_quimico));
                a=getAgenteQuimico(a);
                this.extintorDTO.setAgenteQuimico(a);
                this.extintorDTO.setClasificacion(rslt.getString(column_clasificacion));
                this.extintorDTO.setEliminado(rslt.getBoolean(column_eliminado));
                this.extintorDTO.setPeso(rslt.getInt(column_peso));
                lista.add(this.extintorDTO);
            }
            return lista;
        }
        return null;
    }
    public ExtintorDTO getExtintorById(ExtintorDTO e) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String where=column_id+"="+e.getId();
            ResultSet rslt= this.conn.query("*", tableName, where, "");
            if(rslt.next()){
                this.extintorDTO=new ExtintorDTO();
                this.extintorDTO.setId(rslt.getInt(column_id));
                AgenteQuimicoDTO a=new AgenteQuimicoDTO();
                a.setId(rslt.getInt(column_id_agente_quimico));
                a=getAgenteQuimico(a);
                this.extintorDTO.setAgenteQuimico(a);
                this.extintorDTO.setClasificacion(rslt.getString(column_clasificacion));
                this.extintorDTO.setEliminado(rslt.getBoolean(column_eliminado));
                this.extintorDTO.setPeso(rslt.getInt(column_peso));
                return this.extintorDTO;
            }
        }
        return null;
    }
    private AgenteQuimicoDTO getAgenteQuimico(AgenteQuimicoDTO agenteQ) throws SQLException, ClassNotFoundException{
        this.agente=new AgenteQuimicoDAO();
        return this.agente.getAgenteById(agenteQ);
    }
    
    
}
