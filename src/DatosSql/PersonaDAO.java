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

/**
 *
 * @author root
 */
public class PersonaDAO {

    private final String tableName = "persona";
    private final String column_id = "id";
    private final String colum_nombre = "nombre";
    private final String column_apellido = "apellido";
    private final String column_ci = "ci";
    private final String column_telefono = "telefono";
    private final String column_email = "email";
    private final String column_nombre_empresa = "nombre_empresa";
    private final String column_tipo_persona = "id_tipo_persona";
    private final String column_eliminado = "eliminado";
    private final String all_columns = colum_nombre + ","
            + column_apellido + "," + column_ci + ","
            + column_telefono + "," + column_email + "," + column_nombre_empresa
            + "," + column_tipo_persona + "," + column_eliminado;
    private PersonaDTO persona;
    private MySqlConector conn;
    private long currentTransction;
    public PersonaDAO() throws SQLException, ClassNotFoundException {
        this.conn = MySqlConector.getInstance();
        this.currentTransction=0;
    }

    public long getCurrentTransction() {
        return currentTransction;
    }

    public void setCurrentTransction(long currentTransction) {
        this.currentTransction = currentTransction;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
    
    public PersonaDTO insertarPesona(PersonaDTO persona) throws SQLException {
        if (this.conn != null) {
            String values = "'"+ persona.getNombre() + "','" + persona.getApellido()+"',"
                    + persona.getCi() + ","+ persona.getTelefono() + ",'"
                    + persona.getEmail() + "','" + persona.getNombre()+
                     "'," + persona.getTipoPersona().getId()+","
                     + persona.getEliminado();
            int id=this.conn.insert(tableName, all_columns, values);
            persona.setId(id);
            return persona;
        }
        return null;
    }
    public boolean updatePersona(PersonaDTO persona) throws SQLException{
        if(conn!=null){
            String where=column_id+"="+persona.getId();
            String set=colum_nombre + "='" + persona.getNombre()+ "'," + 
                    column_apellido + "='" + persona.getApellido()+"',"+
                    column_ci + "=" + persona.getCi() + "," + 
                    column_telefono + "=" + persona.getTelefono() + ","+ 
                    column_email + "='" + persona.getEmail() + "'," + 
                    column_nombre_empresa + "='" + persona.getNombreEmpresa()+"',"+ 
                    column_tipo_persona + "=" + persona.getTipoPersona().getId() + ","+
                    column_eliminado + "=" + persona.getEliminado();
            this.conn.update(tableName, set, where, "");
            return false;
        }
        return false;
    }
    public List listarTecnico() throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String columns="p.id,p.nombre, p.apellido,p.id_tipo_persona,p.ci, p.email,p.telefono,p.nombre_empresa,p.eliminado ";
            String From= " persona p JOIN tipo_persona tp on p.id_tipo_persona=tp.id ";
            String where= "tp.nombre like 'tecnico%' and p.eliminado=0";
            ResultSet rslt=this.conn.query(columns, From, where, "");
            List lista=new ArrayList();
            while(rslt.next()){
                PersonaDTO p=new PersonaDTO();
                p.setId(rslt.getInt(column_id));
                p.setNombre(rslt.getString(colum_nombre));
                p.setApellido(rslt.getString(column_apellido));
                p.setCi(rslt.getInt(column_ci));
                p.setEmail(rslt.getString(column_email));
                p.setNombreEmpresa(rslt.getString(column_nombre_empresa));
                p.setTelefono(rslt.getInt(column_telefono));
                p.setEliminado(rslt.getBoolean(column_eliminado));
                TipoPersonaDTO tp=new TipoPersonaDTO();
                tp.setId(rslt.getInt(column_tipo_persona));
                p.setTipoPersona(getTipoPersona(tp));
                lista.add(p);
            }
            return lista;
        }
        return null;
    }
    public boolean eliminarPersona(PersonaDTO persona) throws SQLException{
        if(conn!=null){
            String where=column_id+"="+persona.getId();
            this.conn.delete(tableName, where, "");
            return false;
        }
        return false;
    }
    public List getAll() throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String where=column_eliminado+"=0";
            ResultSet rslt= this.conn.query("*", tableName, where, "");
            List lista=new ArrayList();
            
            while(rslt.next()){
                PersonaDTO p=new PersonaDTO();
                p.setId(rslt.getInt(column_id));
                p.setNombre(rslt.getString(colum_nombre));
                p.setApellido(rslt.getString(column_apellido));
                p.setCi(rslt.getInt(column_ci));
                p.setEmail(rslt.getString(column_email));
                p.setNombreEmpresa(rslt.getString(column_nombre_empresa));
                p.setTelefono(rslt.getInt(column_telefono));
                p.setEliminado(rslt.getBoolean(column_eliminado));
                TipoPersonaDTO tp=new TipoPersonaDTO();
                tp.setId(rslt.getInt(column_tipo_persona));
                p.setTipoPersona(getTipoPersona(tp));
                lista.add(p);
            }
            return lista;
        }
        return null;
    }
    private TipoPersonaDTO getTipoPersona(TipoPersonaDTO tipoPersona) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){

            return new TipoPersonaDAO().getById(tipoPersona);
        }
            
        return null;
    }

    public PersonaDTO getById(PersonaDTO p) throws SQLException, ClassNotFoundException{
        if(this.conn!=null){
            String Where=column_id+"="+p.getId()+" and "+column_eliminado+"= 0";
            ResultSet rslt=this.conn.query("*", tableName, Where, "");
            if(rslt.next()){
                p.setId(rslt.getInt(column_id));
                p.setNombre(rslt.getString(colum_nombre));
                p.setApellido(rslt.getString(column_apellido));
                p.setCi(rslt.getInt(column_ci));
                p.setEmail(rslt.getString(column_email));
                p.setNombreEmpresa(rslt.getString(column_nombre_empresa));
                p.setTelefono(rslt.getInt(column_telefono));
                p.setEliminado(rslt.getBoolean(column_eliminado));
                TipoPersonaDTO tp=new TipoPersonaDTO();
                tp.setId(rslt.getInt(column_tipo_persona));
                p.setTipoPersona(getTipoPersona(tp));
                return p;
            }
        }
        return null;
    }
}
