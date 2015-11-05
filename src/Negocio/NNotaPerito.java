/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DetallePerito;
import Datos.NotaPerito;
import Datos.NotaServicio;
import Datos.Persona;
import Datos.Servicio;
import interfaces.THibernateHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author oscar
 */
public class NNotaPerito {
    private NotaPerito notaPerito;

    public NNotaPerito() {
    }
     private boolean joinAll(List detalle, long id){
        boolean valid=true;
        for (Object detalle1 : detalle) {
            valid=valid && ((THibernateHelper)detalle1).join(id);
        }
        
        return valid;
    }
    
    public NotaPerito nuevaNotaPerito(NotaServicio notaServicio, Persona perito, Date fecha, 
            List<Servicio> listServicio) throws Exception{
        if(notaServicio!=null && perito !=null && fecha !=null){
            long idTransaction=new Date().getTime();
            this.notaPerito=new NotaPerito();
            ArrayList detallePerito=new ArrayList();
            try{
                
                List<THibernateHelper> participantes=new ArrayList<>();
                participantes.addAll(listServicio);
                participantes.add(perito);
                participantes.add(notaServicio);
                participantes.add(this.notaPerito);
                boolean valid=this.joinAll(participantes, idTransaction);
               
                if(valid){
                    this.notaPerito.setEliminado(Boolean.FALSE);
                    this.notaPerito.setFecha(fecha);
                    this.notaPerito.setNotaServicio(notaServicio);
                    this.notaPerito.setPersona(perito);
                    this.notaPerito.commit(idTransaction);
                    for (Servicio s : listServicio) {
                        DetallePerito dp=new DetallePerito();
                        dp.setNotaPerito(notaPerito);
                        dp.setPrecioTotal((float)0.0);
                        dp.setServicio(s);
                        detallePerito.add(dp);
                        dp.commit(idTransaction);
                    }
                    return notaPerito;
                }
                throw new Exception("No se pudo guardar la nota");
               
            }catch(Exception ex){
                notaPerito.cancel(idTransaction);
                for (Object detalle : detallePerito) {
                    ((DetallePerito)detalle).cancel(idTransaction);  
                }
                throw ex;
            }
            
        }else
            throw new Exception("Error al guardar la nueva nota ");
        
    }
    public NotaPerito darDeBaja(int id,
            NotaServicio notaServicio, Persona perito, Date fecha, 
            List listDetalleNota) throws Exception{
      if(notaServicio!=null && perito !=null && fecha !=null){
            this.notaPerito=new NotaPerito();
            this.notaPerito.setId(id);
            this.notaPerito.setEliminado(Boolean.TRUE);
            this.notaPerito.setFecha(fecha);
            this.notaPerito.setNotaServicio(notaServicio);
            this.notaPerito.setPersona(perito);
            return (NotaPerito) this.notaPerito.modificar();
        }else
            throw new Exception("Error al eliminar la nueva nota ");  
    }
    public NotaPerito buscarPorNotaSolicitud(int idNotaSolicitud) throws Exception{
        NotaServicio ns=new NotaServicio();
        ns.setId(idNotaSolicitud);
        this.notaPerito=new NotaPerito();
        this.notaPerito.setNotaServicio(ns);
        List r =this.notaPerito.buscar();
        if(r!=null && !r.isEmpty()){
            return (NotaPerito) r.get(0);
        }else
            throw new Exception("No se encontraron notas");
    }
    
    public List listarTodos() throws Exception{
        this.notaPerito=new NotaPerito();
        return this.notaPerito.listarTodos();
        
    }
    
}
