/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.PersonaDAO;
import DatosSql.PersonaDTO;
import DatosSql.TipoPersonaDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarPersona {
    
    private PersonaDTO personaDTO;
    private PersonaDAO personaDAO;

    public NGestionarPersona() throws SQLException, ClassNotFoundException {
       personaDTO=null;
       this.personaDAO=new PersonaDAO();
    }
    
    public PersonaDTO nuevo(String nombre, String apellido, int ci, int telefono, 
            String email, String empresa, TipoPersonaDTO tp) throws Exception{
        
        if(nombre.trim().length()>0 && ci>0 && apellido.trim().length()>0){
            personaDTO=new PersonaDTO();
            personaDTO.setNombre(nombre);
            personaDTO.setApellido(apellido);
            personaDTO.setCi(ci);
            personaDTO.setTelefono(telefono);
            personaDTO.setEmail(email);
            personaDTO.setNombreEmpresa(empresa);
            personaDTO.setEliminado(Boolean.FALSE);
            personaDTO.setTipoPersona(tp);
            personaDAO.insertarPesona(personaDTO);
        }else
            throw new Exception("por lo menos se necesita nombre, apellido y ci, para guardar");
        
            
        return personaDTO;
    }
    public PersonaDTO modificar(int id,String nombre, String apellido, int ci, int telefono, 
            String email, String empresa, TipoPersonaDTO tp) throws Exception{
        
        if(id>0){
            personaDTO=new PersonaDTO();
            personaDTO.setId(id);
            personaDTO.setNombre(nombre);
            personaDTO.setApellido(apellido);
            personaDTO.setCi(ci);
            personaDTO.setTelefono(telefono);
            personaDTO.setEmail(email);
            personaDTO.setNombreEmpresa(empresa);
            personaDTO.setEliminado(Boolean.FALSE);
            personaDTO.setTipoPersona(tp);
            personaDAO.updatePersona(personaDTO);
        }else
            throw new Exception("por lo menos se necesita el id, para modificar");
        return personaDTO;
    }
    public Boolean darDeBaja(int id,String nombre, String apellido, int ci, int telefono, 
            String email, String empresa,TipoPersonaDTO tp) throws Exception{
        
         
        if(id>0){
            personaDTO=new PersonaDTO();
            personaDTO.setId(id);
            personaDTO.setNombre(nombre);
            personaDTO.setApellido(apellido);
            personaDTO.setCi(ci);
            personaDTO.setTelefono(telefono);
            personaDTO.setEmail(email);
            personaDTO.setNombreEmpresa(empresa);
            personaDTO.setEliminado(Boolean.TRUE);
            personaDTO.setTipoPersona(tp);
            personaDAO.updatePersona(personaDTO);
        }else
            throw new Exception("por lo menos se necesita el id, para dar de baja");
        return true;
    
    }
//    public List buscarPersona(String nombre) throws Exception{
//        if(nombre.trim().length()>0){
//            personaDTO=new Persona();
//            personaDTO.setNombre(nombre);
//            return personaDTO.buscarPorNombre();
//        }else
//            throw new Exception("debe introducir el nombre");
//        
//    }
    public List listarTodos() throws Exception{
        return personaDAO.getAll();
    }
    public List listarTecnico() throws Exception{
        return this.personaDAO.listarTecnico();
    }
    
}
