/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.AgenteQuimicoDAO;
import DatosSql.AgenteQuimicoDTO;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarAgenteQuimico {
    private AgenteQuimicoDTO agenteDTO;
    private AgenteQuimicoDAO agenteDAO;

    public NGestionarAgenteQuimico() {
    
    }

    public AgenteQuimicoDTO nuevoAgenteQuimico(String nombre, String clasificacion) throws Exception{
       if(!nombre.trim().isEmpty() && !clasificacion.trim().isEmpty()){
//            aq=new AgenteQuimico();
//            aq.setNombre(nombre);
//            aq.setClasificacion(clasificacion);
//            aq.setEliminado(Boolean.FALSE);
//            return (AgenteQuimico) aq.guardar();
           agenteDTO=new AgenteQuimicoDTO();
           agenteDAO=new AgenteQuimicoDAO();
           agenteDTO.setNombre(nombre);
           agenteDTO.setClasificacion(clasificacion);
           agenteDTO.setEliminado(Boolean.FALSE);
           agenteDTO.setNombre(nombre);
           agenteDTO=agenteDAO.insertarAgenteQUimico(agenteDTO);
           return agenteDTO;
           
       }else{
            throw new Exception("Ingrese datos validos");
       }
    }
    public AgenteQuimicoDTO modificarAgenteQuimico(int id, String nombre, String clasificacion) throws Exception{
        if(id>0 && !nombre.trim().isEmpty() && !clasificacion.trim().isEmpty()){
//            aq=new AgenteQuimico();
//            aq.setNombre(nombre);
//            aq.setClasificacion(clasificacion);
//            aq.setEliminado(Boolean.FALSE);
//            return (AgenteQuimico) aq.modificar();
           agenteDTO=new AgenteQuimicoDTO();
           agenteDAO=new AgenteQuimicoDAO();
           agenteDTO.setNombre(nombre);
           agenteDTO.setClasificacion(clasificacion);
           agenteDTO.setEliminado(Boolean.FALSE);
           agenteDTO.setNombre(nombre);
           agenteDTO.setId(id);
           agenteDAO.actualizaAgenteQuimico(agenteDTO);
           return agenteDTO;
       }else{
            throw new Exception("Ingrese datos validos");
       }
    }
    public boolean darDeBajaAgenteQuimico(int id, String nombre, String clasificacion) throws Exception{
        if(id>0 && !nombre.trim().isEmpty() && !clasificacion.trim().isEmpty()){
            agenteDTO = new AgenteQuimicoDTO();
            agenteDAO = new AgenteQuimicoDAO();
            agenteDTO.setNombre(nombre);
            agenteDTO.setClasificacion(clasificacion);
            agenteDTO.setEliminado(Boolean.TRUE);
            agenteDTO.setNombre(nombre);
            agenteDTO.setId(id);
            agenteDAO.actualizaAgenteQuimico(agenteDTO);
            return true;
        }else
             throw new Exception("Ingrese datos validos");
    }
//    public List buscar(String nombre) throws Exception{
//            if(!nombre.trim().isEmpty()){
//                aq=new AgenteQuimico();
//                aq.setNombre(nombre);
//                return aq.buscar();
//            }else
//                 throw new Exception("debe ingresar el nombre para buscar");
//    }
    public List listarTodos() throws Exception{
//        aq=new AgenteQuimico();
//        return aq.listarTodos();
        agenteDAO = new AgenteQuimicoDAO();
        return this.agenteDAO.getAll();
    }
}
