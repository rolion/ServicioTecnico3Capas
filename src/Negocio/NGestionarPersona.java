/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.Persona;
import Datos.TipoPersona;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarPersona {
    
    private Persona persona;

    public NGestionarPersona() {
       persona=null;
    }
    
    public Persona nuevo(String nombre, String apellido, int ci, int telefono, 
            String email, String empresa, TipoPersona tp) throws Exception{
        
        if(nombre.trim().length()>0 && ci>0 && apellido.trim().length()>0){
            persona=new Persona();
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setCi(ci);
            persona.setTelefono(telefono);
            persona.setEmail(email);
            persona.setNombreEmpresa(empresa);
            persona.setEliminado(Boolean.FALSE);
            persona.setTipoPersona(tp);
            persona.guardar();
        }else
            throw new Exception("por lo menos se necesita nombre, apellido y ci, para guardar");
        
            
        return persona;
    }
    public Persona modificar(int id,String nombre, String apellido, int ci, int telefono, 
            String email, String empresa, TipoPersona tp) throws Exception{
        
        if(id>0){
            persona=new Persona();
            persona.setId(id);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setCi(ci);
            persona.setTelefono(telefono);
            persona.setEmail(email);
            persona.setNombreEmpresa(empresa);
            persona.setEliminado(Boolean.FALSE);
            persona.setTipoPersona(tp);
            persona.modificar();
        }else
            throw new Exception("por lo menos se necesita el id, para modificar");
        return persona;
    }
    public Boolean darDeBaja(int id,String nombre, String apellido, int ci, int telefono, 
            String email, String empresa,TipoPersona tp) throws Exception{
        
         
        if(id>0){
            persona=new Persona();
            persona.setId(id);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setCi(ci);
            persona.setTelefono(telefono);
            persona.setEmail(email);
            persona.setNombreEmpresa(empresa);
            persona.setEliminado(Boolean.TRUE);
            persona.setTipoPersona(tp);
            persona.modificar();
        }else
            throw new Exception("por lo menos se necesita el id, para dar de baja");
        return true;
    
    }
    public List buscarPersona(String nombre) throws Exception{
        if(nombre.trim().length()>0){
            persona=new Persona();
            persona.setNombre(nombre);
            return persona.buscarPorNombre();
        }else
            throw new Exception("debe introducir el nombre");
        
    }
    public List listarTodos() throws Exception{
        this.persona=new Persona();
        
        return persona.listarTodos();
    }
    public List listarTecnico() throws Exception{
        this.persona=new Persona();
        return this.persona.listarTecnicos();
    }
    
}
