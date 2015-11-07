/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.AgenteQuimicoDTO;
import DatosSql.ExtintorDAO;
import DatosSql.ExtintorDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarExtintor {
    private ExtintorDTO extintorDTO;
    private ExtintorDAO extintorDAO;
    public NGestionarExtintor() throws SQLException, ClassNotFoundException {
        this.extintorDAO=new ExtintorDAO();
    }
    public ExtintorDTO nuevoExtintor(AgenteQuimicoDTO aq, String clasificacion, int peso) throws Exception{
        if(aq!=null && !clasificacion.trim().isEmpty() && peso>0){
            extintorDTO=new ExtintorDTO();
            extintorDTO.setAgenteQuimico(aq);
            extintorDTO.setClasificacion(clasificacion);
            extintorDTO.setPeso(peso);
            extintorDTO.setEliminado(Boolean.FALSE);
            return  extintorDAO.insertarExtintor(extintorDTO);
        }else
            throw new Exception("no se puede guardar datos nulos");
        
    }
    public ExtintorDTO modificarExtintor(int id,AgenteQuimicoDTO aq, 
            String clasificacion, int peso) throws Exception{
        if(aq!=null && !clasificacion.trim().isEmpty() && peso>0){
            extintorDTO=new ExtintorDTO();
            extintorDTO.setId(id);
            extintorDTO.setAgenteQuimico(aq);
            extintorDTO.setClasificacion(clasificacion);
            extintorDTO.setPeso(peso);
            extintorDTO.setEliminado(Boolean.FALSE);
            extintorDAO.actualizarExtintor(extintorDTO);
            return  extintorDTO;
        }else
            throw new Exception("no se puede actualizar datos nulos");
    }
    public boolean darDeBajaExtintor(int id,AgenteQuimicoDTO aq, String clasificacion, int peso) throws Exception{
        if(aq!=null && !clasificacion.trim().isEmpty() && peso>0){
            extintorDTO=new ExtintorDTO();
            extintorDTO.setId(id);
            extintorDTO.setAgenteQuimico(aq);
            extintorDTO.setClasificacion(clasificacion);
            extintorDTO.setPeso(peso);
            extintorDTO.setEliminado(Boolean.TRUE);
            extintorDAO.actualizarExtintor(extintorDTO);
            return true;
        }else
            throw new Exception("insertar datos del extintor a eliminar");
    
    }
    public List listar() throws Exception{
            return this.extintorDAO.getAll();
    }
    public List listarTodos() throws Exception{
        return extintorDAO.getAll();
        
    }
}
