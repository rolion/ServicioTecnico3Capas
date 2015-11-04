/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.TipoPersona;
import java.util.List;


/**
 *
 * @author oscar
 */
public class NGestionarTipoPersona {
    private TipoPersona tp;

    public NGestionarTipoPersona() {
        tp=new TipoPersona();
    }
    
    
    
    public TipoPersona guardarTipoPersona(String nombre, String descripcion) throws Exception{
        
        if(nombre.trim().length()>0){
            tp.setNombre(nombre);
            tp.setDescripcion(descripcion);
            tp.setEliminado(Boolean.FALSE);
            try {
                tp.guardar();
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }else
            throw new Exception("El campo nombre no puede estar vacio");
        
        return tp;
    }
    public TipoPersona modificarTipoPersona(int id, String nombre, String descripcion) throws Exception{
        if(id>0 && nombre.trim().length()>0 && descripcion.trim().length()>0){
            tp=new TipoPersona();
            tp.setId(id);
            tp.setNombre(nombre);
            tp.setDescripcion(descripcion);
            tp.setEliminado(Boolean.FALSE);
            return (TipoPersona) tp.modificar();
           
        }else
            throw new Exception("No se puede actualizar campos nulos");
    }
    public Boolean darDeBaja(int id,String nombre, String descripcion) throws Exception{
        
        
        if(id>0 && nombre.trim().length()>0){
            tp.setId(id);
            tp.setNombre(nombre);
            tp.setDescripcion(descripcion);
            tp.setEliminado(Boolean.TRUE);
            tp.modificar();
        }else
            throw new Exception("se necesita el Id");
            
        return true;
    }
    public List buscarTipoPersona(String nombre) throws Exception{
           if(nombre.trim().length()>0){
               tp=new TipoPersona();
                tp.setNombre(nombre);
                List l=tp.buscar();
                return l;
           }else
               throw new Exception("no se puede buscar valores nulos");

    }
    public List listarTodos(){
        return this.tp.listarTodos();
    }
    
}
