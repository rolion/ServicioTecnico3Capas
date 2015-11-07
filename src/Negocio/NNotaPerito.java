/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.DetalleNotaServicioDAO;
import DatosSql.DetalleNotaServicioDTO;
import DatosSql.DetallePeritoDAO;
import DatosSql.DetallePeritoDTO;
import DatosSql.NotaPeritoDAO;
import DatosSql.NotaPeritoDTO;
import DatosSql.NotaServicioDAO;
import DatosSql.NotaServicioDTO;
import DatosSql.PersonaDTO;
import DatosSql.ServicioDTO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import interfaces.SpecificParticipant;
import interfaces.THibernateHelper;
import java.sql.SQLException;
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
   
    private NotaPeritoDAO notaPeritoDAO;
    private NotaPeritoDTO notaPeritoDTO;
    private List<SpecificParticipant> listaParticipantes;

    public NNotaPerito() throws SQLException, ClassNotFoundException {
        this.notaPeritoDAO=new NotaPeritoDAO();
        this.listaParticipantes=new ArrayList<>();
        this.listaParticipantes.add(notaPeritoDAO);
        
    }
     public boolean joinAll(long transactionID){
        boolean valid=true;
        for(SpecificParticipant participant: this.listaParticipantes){
            valid=valid && participant.join(transactionID);
        }
        return valid;
        //return this.notaServicioDAO.join(transactionID) &&this.detalleServicioDAO.join(transactionID);
    }
    public void commitAll(long transactionID) throws Exception{
         for(SpecificParticipant participant: this.listaParticipantes){
            participant.commit(transactionID);
        }
    }
    public void cancelAll(long transactionID) throws Exception{
        for(SpecificParticipant participant: this.listaParticipantes){
           participant.cancel(transactionID);
       }
    }
    
    public NotaPeritoDTO nuevaNotaPerito(NotaServicioDTO notaServicio, PersonaDTO perito, Date fecha, 
            List<DetallePeritoDTO> detalle) throws Exception{
        long idTransaction=new java.util.Date().getTime();
        this.notaPeritoDTO=new NotaPeritoDTO();
        this.notaPeritoDTO.setEliminado(Boolean.FALSE);
        this.notaPeritoDTO.setFecha(fecha);
        this.notaPeritoDTO.setNotaServicio(notaServicio);
        this.notaPeritoDTO.setPerito(perito);
        for (Object d : detalle) {
                SpecificParticipant participante=new DetallePeritoDAO();
                this.listaParticipantes.add(participante);
        }
        if(this.joinAll(idTransaction)){
                if(this.listaParticipantes.get(0).validar(idTransaction,notaPeritoDTO)){
                    boolean valid=true;
                    for(int i=0;i<detalle.size();i++){
                        DetallePeritoDTO d=(DetallePeritoDTO) detalle.get(i);
                        d.setNotaPerito(notaPeritoDTO);
                        valid=valid && this.listaParticipantes.get(i+1).validar(idTransaction, d);
                    }
                    if(valid){
                        this.commitAll(idTransaction);
                        return notaPeritoDTO;
                    }else
                        this.cancelAll(idTransaction);
                }else
                    this.listaParticipantes.get(0).cancel(idTransaction);
            }
        return null;
    }
    public NotaPeritoDTO darDeBaja(int id,
            NotaServicioDTO notaServicio, PersonaDTO perito, Date fecha, 
            List listDetalleNota) throws Exception{
      if(notaServicio!=null && perito !=null && fecha !=null){
            this.notaPeritoDTO=new NotaPeritoDTO();
            this.notaPeritoDTO.setId(id);
            this.notaPeritoDTO.setEliminado(Boolean.TRUE);
            this.notaPeritoDTO.setFecha(fecha);
            this.notaPeritoDTO.setNotaServicio(notaServicio);
            this.notaPeritoDTO.setPerito(perito);
            this.notaPeritoDAO.actualizarNota(notaPeritoDTO);
            return this.notaPeritoDTO;
        }else
            throw new Exception("Error al eliminar la nueva nota ");  
    }
    public NotaPeritoDTO buscarPorNotaSolicitud(int idNotaSolicitud) throws Exception{
        NotaServicioDTO n=new NotaServicioDTO();
        n.setId(idNotaSolicitud);
        this.notaPeritoDAO.getNotaPeritoById(notaPeritoDTO);
        return null;
    }
    public NotaPeritoDTO buscarPorId(int id) throws SQLException, ClassNotFoundException{
        NotaPeritoDTO n=new NotaPeritoDTO();
        n.setId(id);
        return this.notaPeritoDAO.getNotaPeritoById(n);
    }
    public List listarTodos() throws Exception{
        return this.notaPeritoDAO.getAll();
    }
    public List listarTodasSinNotaEntrega() throws SQLException, ClassNotFoundException{
        return this.notaPeritoDAO.getAllNotaPeritoSinNotaEntrega();
    }
    
}
