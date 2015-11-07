/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.ServicioDAO;
import DatosSql.ServicioDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarServicio {
    private ServicioDTO servicioDTO;
    private ServicioDAO servicioDAO;

    public NGestionarServicio() throws SQLException, ClassNotFoundException {
        this.servicioDAO=new ServicioDAO();
    }
    
    public ServicioDTO nuevoServicio( String descripcion, float precio) throws Exception{
        if( !descripcion.trim().isEmpty() && precio>0.0){
            this.servicioDTO=new ServicioDTO();
            this.servicioDTO.setDescripcion(descripcion);
            this.servicioDTO.setEliminado(Boolean.FALSE);
            this.servicioDTO.setPrecio(precio);
            return  this.servicioDAO.insertarServicio(servicioDTO);
        }else
            throw new Exception("Debe introducir valores validos");
        
    }
    public ServicioDTO modificar(int id, String descripcion, float precio) throws Exception{
        if(id>0 && !descripcion.trim().isEmpty() && precio>0.0){
            this.servicioDTO=new ServicioDTO();
            this.servicioDTO.setId(id);
            this.servicioDTO.setDescripcion(descripcion);
            this.servicioDTO.setEliminado(Boolean.FALSE);
            this.servicioDTO.setPrecio(precio);
            this.servicioDAO.actualizarServicio(servicioDTO);
            return  this.servicioDTO;
        }else
            throw new Exception("Debe introducir valores validos");
    }
    public List listarTodos() throws Exception{
        return servicioDAO.getAll();
        
    }
    public boolean darDeBaja(int id, String descripcion, float precio) throws Exception{
       
        if(id>0 && !descripcion.trim().isEmpty() && precio>0.0){
            this.servicioDTO=new ServicioDTO();
            this.servicioDTO.setId(id);
            this.servicioDTO.setDescripcion(descripcion);
            this.servicioDTO.setEliminado(Boolean.TRUE);
            this.servicioDTO.setPrecio(precio);
            this.servicioDAO.actualizarServicio(servicioDTO);
            return  true;
        }else
            throw new Exception("Debe introducir valores validos");
    }
//    public List buscarPorNombre(String nombre) throws Exception{
//        if(!nombre.trim().isEmpty()){
//            this.servicioDTO=new Servicio();
//            this.servicioDTO.setDescripcion(nombre);
//            return this.servicioDTO.buscarPorNombre();
//        }else
//            throw new Exception("debe introducir el nombre del servicio");
//    }
}
