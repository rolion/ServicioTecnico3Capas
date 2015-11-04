/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.Servicio;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarServicio {
    private Servicio servicio;
    public Servicio nuevoServicio( String descripcion, float precio) throws Exception{
        if( !descripcion.trim().isEmpty() && precio>0.0){
            this.servicio=new Servicio();
            this.servicio.setDescripcion(descripcion);
            this.servicio.setEliminado(Boolean.FALSE);
            this.servicio.setPrecio(precio);
            return (Servicio) this.servicio.guardar();
        }else
            throw new Exception("Debe introducir valores validos");
        
    }
    public Servicio modificar(int id, String descripcion, float precio) throws Exception{
        if(id>0 && !descripcion.trim().isEmpty() && precio>0.0){
            this.servicio=new Servicio();
            this.servicio.setId(id);
            this.servicio.setDescripcion(descripcion);
            this.servicio.setEliminado(Boolean.FALSE);
            this.servicio.setPrecio(precio);
            return (Servicio) this.servicio.modificar();
        }else
            throw new Exception("Debe introducir valores validos");
    }
    public List listarTodos() throws Exception{
        servicio=new Servicio();
        return servicio.listarTodos();
        
    }
    public boolean darDeBaja(int id, String descripcion, float precio) throws Exception{
       
        if(id>0 && !descripcion.trim().isEmpty() && precio>0.0){
            this.servicio=new Servicio();
            this.servicio.setId(id);
            this.servicio.setDescripcion(descripcion);
            this.servicio.setEliminado(Boolean.TRUE);
            this.servicio.setPrecio(precio);
            this.servicio.modificar();
            return  true;
        }else
            throw new Exception("Debe introducir valores validos");
    }
    public List buscarPorNombre(String nombre) throws Exception{
        if(!nombre.trim().isEmpty()){
            this.servicio=new Servicio();
            this.servicio.setDescripcion(nombre);
            return this.servicio.buscarPorNombre();
        }else
            throw new Exception("debe introducir el nombre del servicio");
    }
}
