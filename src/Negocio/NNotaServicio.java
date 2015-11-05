/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DetalleNota;
import Datos.Extintor;
import Datos.NotaServicio;
import Datos.Persona;
import interfaces.THibernateHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author oscar
 */
public class NNotaServicio {
    private NotaServicio notaServicio;

    public NNotaServicio() {
    }
    private boolean joinAll(THibernateHelper nota, List<THibernateHelper> detalle, long id){
        boolean valid=nota.join(id);
        for (THibernateHelper detalle1 : detalle) {
            valid=valid && detalle1.join(id);
        }
        
        return valid;
    }
    public NotaServicio nuevaNota(Persona cliente, Date fecha, String descripcion,
            List detalle) throws Exception{
        
        if(cliente!=null && fecha!=null && detalle!=null && !detalle.isEmpty()){
            notaServicio=new NotaServicio();
            List listaDetalle=new ArrayList();
            long idTransaction=new Date().getTime();
            try{
                
                boolean valid=this.joinAll(notaServicio, detalle, idTransaction);
                if(valid){
                    notaServicio.setEliminado(Boolean.FALSE);
                    notaServicio.setPersona(cliente);
                    notaServicio.setFecha(fecha);
                    notaServicio.setDescripcionCliente(descripcion);
                    notaServicio.commit(idTransaction);
                    for (Object d : detalle) {
                        ((DetalleNota)d).setNotaServicio(notaServicio);
                        ((DetalleNota)d).setCantidad(1);
                        ((DetalleNota)d).commit(idTransaction);
                    }
                }else{
                    throw new Exception("No ");
                }
            }catch(Exception e){
                if(notaServicio!=null){
                    notaServicio.cancel(idTransaction);
                    for (Object lista : listaDetalle) {
                        DetalleNota d=(DetalleNota) lista;
                        d.cancel(idTransaction);
                    }
                }
                throw new Exception("Error al guardar la nota");
            }

            return notaServicio;
        }else{
            throw new Exception("Error, no se puede guardar valores nulos");
        }
        
    }
    public boolean anularNota(int id, Persona cliente, Date fecha, String descripcion) throws Exception{
        if(id>0 && cliente!=null && fecha!=null && !descripcion.trim().isEmpty()){
                notaServicio=new NotaServicio();
                notaServicio.setId(id);
                notaServicio.setPersona(cliente);
                notaServicio.setFecha(fecha);
                notaServicio.setDescripcionCliente(descripcion);
                notaServicio.setEliminado(Boolean.TRUE);
                notaServicio.modificar();
        }else
            throw new Exception("Debe introducir datos");
        return true;
    }
    public NotaServicio buscarNotaPorId(int id) throws Exception{
        notaServicio=new NotaServicio();
        notaServicio.setId(id);
        List result=notaServicio.buscar();
        if(result!=null && !result.isEmpty()){
            return (NotaServicio) notaServicio.buscar().get(0);
        }else
            throw new Exception("No se encontraron resultados");
        
    }

    public boolean eliminarNota(int id){
        notaServicio=new NotaServicio();
        try {
            notaServicio.setId(id);
            notaServicio.eliminar();
        } catch (Exception ex) {
            Logger.getLogger(NNotaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    public List buscarDetalle(int id) throws Exception{
        DetalleNota dn=new DetalleNota();
        this.notaServicio=new NotaServicio();
        this.notaServicio.setId(id);
        dn.setNotaServicio(notaServicio);
        return dn.buscar();
    }
    public List listarTodos() throws Exception{
        this.notaServicio=new NotaServicio();
        return this.notaServicio.listarTodos();
        
    }
}
