/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.NotaEntrega;
import Datos.NotaPerito;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarNotaEntrega {
    
    private NotaEntrega notaEntrega;
    public NotaEntrega nuevaNotaEntrega(NotaPerito nperito, Date fecha) throws Exception{
        if(nperito!=null && fecha !=null){
            notaEntrega=new NotaEntrega();
            notaEntrega.setNotaPerito(nperito);
            this.notaEntrega.setFecha(fecha);
            this.notaEntrega.setEliminado(Boolean.FALSE);
            return (NotaEntrega) this.notaEntrega.guardar();
        }else
            throw new Exception("debe introducir valores no nulos");
    }
    public NotaEntrega anularNota(int id,NotaPerito nperito, Date fecha) throws Exception{
        if(nperito!=null && fecha!=null && id>0){
            this.notaEntrega=new NotaEntrega();
            this.notaEntrega.setId(id);
            this.notaEntrega.setNotaPerito(nperito);
            this.notaEntrega.setFecha(fecha);
            this.notaEntrega.setEliminado(Boolean.TRUE);
            return (NotaEntrega) this.notaEntrega.modificar();
        }else 
            throw new Exception("debe introducir valores no nulos");
    
    }
    public NotaEntrega buscarPorNotaPerito(NotaPerito nperito) throws Exception{
        if(nperito!=null){
            this.notaEntrega=new NotaEntrega();
            this.notaEntrega.setNotaPerito(nperito);
            NotaEntrega ne=(NotaEntrega) this.notaEntrega.buscar().get(0);
            if(ne!=null)
                return ne;
            else
                throw new Exception("No se encontraron resultados en la busqueda");
            
        }else{
            throw new Exception("debe introducir valores no nulos");
        }  
    }
    public List listarTodos(){
        this.notaEntrega=new NotaEntrega();
        ///this.notaEntrega
        return null;
    }
}
