/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.AgenteQuimico;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarAgenteQuimico {
    private AgenteQuimico aq;

    public NGestionarAgenteQuimico() {
    
    }

    public AgenteQuimico nuevoAgenteQuimico(String nombre, String clasificacion) throws Exception{
       if(!nombre.trim().isEmpty() && !clasificacion.trim().isEmpty()){
            aq=new AgenteQuimico();
            aq.setNombre(nombre);
            aq.setClasificacion(clasificacion);
            aq.setEliminado(Boolean.FALSE);
            return (AgenteQuimico) aq.guardar();
       }else{
            throw new Exception("Ingrese datos validos");
       }
        
    }
    public AgenteQuimico modificarAgenteQuimico(int id, String nombre, String clasificacion) throws Exception{
        if(id>0 && !nombre.trim().isEmpty() && !clasificacion.trim().isEmpty()){
            aq=new AgenteQuimico();
            aq.setNombre(nombre);
            aq.setClasificacion(clasificacion);
            aq.setEliminado(Boolean.FALSE);
            return (AgenteQuimico) aq.modificar();
       }else{
            throw new Exception("Ingrese datos validos");
       }
    }
    public boolean darDeBajaAgenteQuimico(int id, String nombre, String clasificacion) throws Exception{
        if(id>0 && !nombre.trim().isEmpty() && !clasificacion.trim().isEmpty()){
            aq=new AgenteQuimico();
            aq.setId(id);
            aq.setNombre(nombre);
            aq.setClasificacion(clasificacion);
            aq.setEliminado(Boolean.TRUE);
            aq.modificar();
            return true;
        }else
             throw new Exception("Ingrese datos validos");
    }
    public List buscar(String nombre) throws Exception{
            if(!nombre.trim().isEmpty()){
                aq=new AgenteQuimico();
                aq.setNombre(nombre);
                return aq.buscar();
            }else
                 throw new Exception("debe ingresar el nombre para buscar");
    }
    public List listarTodos() throws Exception{
        aq=new AgenteQuimico();
        return aq.listarTodos();
    }
}
