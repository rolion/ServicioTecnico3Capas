/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.AgenteQuimico;
import Datos.Extintor;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarExtintor {
    private Extintor extintor;

    public NGestionarExtintor() {
    }
    public Extintor nuevoExtintor(AgenteQuimico aq, String clasificacion, int peso) throws Exception{
        if(aq!=null && !clasificacion.trim().isEmpty() && peso>0){
            extintor=new Extintor();
            extintor.setAgenteQuimico(aq);
            extintor.setClasificacion(clasificacion);
            extintor.setPeso(peso);
            extintor.setEliminado(Boolean.FALSE);
            return (Extintor) extintor.guardar();
        }else
            throw new Exception("no se puede guardar datos nulos");
        
    }
    public Extintor modificarExtintor(int id,AgenteQuimico aq, 
            String clasificacion, int peso) throws Exception{
        if(aq!=null && !clasificacion.trim().isEmpty() && peso>0){
            extintor=new Extintor();
            extintor.setId(id);
            extintor.setAgenteQuimico(aq);
            extintor.setClasificacion(clasificacion);
            extintor.setPeso(peso);
            extintor.setEliminado(Boolean.FALSE);
            return (Extintor) extintor.modificar();
        }else
            throw new Exception("no se puede actualizar datos nulos");
    }
    public boolean darDeBajaExtintor(int id,AgenteQuimico aq, String clasificacion, int peso) throws Exception{
        if(aq!=null && !clasificacion.trim().isEmpty() && peso>0){
            extintor=new Extintor();
            extintor.setId(id);
            extintor.setAgenteQuimico(aq);
            extintor.setClasificacion(clasificacion);
            extintor.setPeso(peso);
            extintor.setEliminado(Boolean.TRUE);
            extintor.modificar();
            return true;
        }else
            throw new Exception("insertar datos del extintor a eliminar");
    
    }
    public List buscar(AgenteQuimico aq) throws Exception{
        if(aq!=null){
            extintor=new Extintor();
            extintor.setAgenteQuimico(aq);
            return extintor.buscar();
        }else
            throw new Exception("seleccione un agente quimico");
    }
    public List listarTodos() throws Exception{
        extintor=new Extintor();
        return extintor.listarTodos();
        
    }
}
