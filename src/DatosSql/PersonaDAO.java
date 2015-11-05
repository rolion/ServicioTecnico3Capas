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
public class PersonaDAO implements SpecificParticipant{

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
    private TipoPersonaDAO tipoPersona;
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
            String values = colum_nombre + "=" + persona.getNombre()
                    + "," + column_apellido + "=" + persona.getApellido()
                    + column_ci + "=" + persona.getCi() + "," + column_telefono + "=" + persona.getTelefono() + ","
                    + column_email + "=" + persona.getEmail() + "," + column_nombre_empresa + "=" + persona.getNombre()
                    + column_tipo_persona + "=" + persona.getTipoPersona().getId()
                    + column_eliminado + "=" + persona.getEliminado();
            int id=this.conn.insert(tableName, all_columns, values);
            persona.setId(id);
            return persona;
        }
        return null;
    }
    public boolean updatePersona(PersonaDTO persona) throws SQLException{
        if(conn!=null){
            String where=column_id+"="+persona.getId();
            String set=colum_nombre + "=" + persona.getNombre()
                    + "," + column_apellido + "=" + persona.getApellido()
                    + column_ci + "=" + persona.getCi() + "," + column_telefono + "=" + persona.getTelefono() + ","
                    + column_email + "=" + persona.getEmail() + "," + column_nombre_empresa + "=" + persona.getNombre()
                    + column_tipo_persona + "=" + persona.getTipoPersona().getId()
                    + column_eliminado + "=" + persona.getEliminado();
            this.conn.update(tableName, set, where, "");
            return false;
        }
        return false;
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
            ResultSet rslt= this.conn.query("*", tableName, "", "");
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
            this.tipoPersona=new TipoPersonaDAO();
            return this.tipoPersona.getById(tipoPersona);
        }
            
        return null;
    }

    @Override
    public boolean insertar(long transactionID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean join(long transactionID) {
        if(this.currentTransction!=0)
            return false;
        this.currentTransction=transactionID;
        return true;
    }

    @Override
    public boolean commit(long transactionID) throws Exception {
        if(this.currentTransction==transactionID){
            this.insertarPesona(persona);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancel(long transactionID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
