/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.TipoPersonaDAO;
import DatosSql.TipoPersonaDTO;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author oscar
 */
public class NGestionarTipoPersona {
    private TipoPersonaDTO tipoPersonaDTO;
    private TipoPersonaDAO tipoPersonaDAO;

    public NGestionarTipoPersona() throws SQLException, ClassNotFoundException {
        this.tipoPersonaDAO=new TipoPersonaDAO();
    }

    public TipoPersonaDTO guardarTipoPersona(String nombre, String descripcion) throws Exception{
        
        if(nombre.trim().length()>0){
            tipoPersonaDTO=new TipoPersonaDTO();
            tipoPersonaDTO.setNombre(nombre);
            tipoPersonaDTO.setDescripcion(descripcion);
            tipoPersonaDTO.setEliminado(Boolean.FALSE);
            try {
                tipoPersonaDAO.insertarTipoPesona(tipoPersonaDTO);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }else
            throw new Exception("El campo nombre no puede estar vacio");
        
        return tipoPersonaDTO;
    }
    public TipoPersonaDTO modificarTipoPersona(int id, String nombre, String descripcion) throws Exception{
        if(id>0 && nombre.trim().length()>0 && descripcion.trim().length()>0){
            tipoPersonaDTO=new TipoPersonaDTO();
            tipoPersonaDTO.setId(id);
            tipoPersonaDTO.setNombre(nombre);
            tipoPersonaDTO.setDescripcion(descripcion);
            tipoPersonaDTO.setEliminado(Boolean.FALSE);
            tipoPersonaDAO.actulializarTipoPersona(tipoPersonaDTO);
            return tipoPersonaDTO; 
           
        }else
            throw new Exception("No se puede actualizar campos nulos");
    }
    public Boolean darDeBaja(int id,String nombre, String descripcion) throws Exception{
        if(id>0 && nombre.trim().length()>0){
            tipoPersonaDTO=new TipoPersonaDTO();
            tipoPersonaDTO.setId(id);
            tipoPersonaDTO.setNombre(nombre);
            tipoPersonaDTO.setDescripcion(descripcion);
            tipoPersonaDTO.setEliminado(Boolean.TRUE);
            return tipoPersonaDAO.actulializarTipoPersona(tipoPersonaDTO);
        }else
            throw new Exception("se necesita el Id");
            
        
    }
//    public List buscarTipoPersona(String nombre) throws Exception{
//           if(nombre.trim().length()>0){
//               tipoPersonaDTO=new TipoPersona();
//                tipoPersonaDTO.setNombre(nombre);
//                List l=tipoPersonaDTO.buscar();
//                return l;
//           }else
//               throw new Exception("no se puede buscar valores nulos");
//
//    }
    public List listarTodos() throws SQLException{
        return this.tipoPersonaDAO.getAll();
    }
    
}
