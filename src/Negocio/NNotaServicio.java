/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DatosSql.DetalleNotaServicioDAO;
import DatosSql.DetalleNotaServicioDTO;
import DatosSql.NotaServicioDAO;
import DatosSql.NotaServicioDTO;
import DatosSql.PersonaDTO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import interfaces.SpecificParticipant;
import interfaces.THibernateHelper;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author oscar
 */
public class NNotaServicio {
    private NotaServicioDTO notaServicioDTO;
    private NotaServicioDAO notaServicioDAO;
    private List<SpecificParticipant> listaParticipantes;

    public NNotaServicio() throws SQLException, ClassNotFoundException {
        this.listaParticipantes=new ArrayList<>();
        this.notaServicioDAO=new NotaServicioDAO();
        this.listaParticipantes.add(this.notaServicioDAO);
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
    public NotaServicioDTO nuevaNota(PersonaDTO cliente, Date fecha, String descripcion,
            List detalle) throws Exception{
        
            long idTransaction=new java.util.Date().getTime();
            notaServicioDTO=new NotaServicioDTO();
            notaServicioDTO.setEliminado(Boolean.FALSE);
            notaServicioDTO.setPersona(cliente);
            notaServicioDTO.setFecha(fecha);
            notaServicioDTO.setDescripcionCliente(descripcion);
            for (Object d : detalle) {
                SpecificParticipant participante=new DetalleNotaServicioDAO();
                this.listaParticipantes.add(participante);
            }
            if(this.joinAll(idTransaction)){
                if(this.listaParticipantes.get(0).validar(idTransaction,notaServicioDTO)){
                    boolean valid=true;
                    for(int i=0;i<detalle.size();i++){
                        DetalleNotaServicioDTO d=(DetalleNotaServicioDTO) detalle.get(i);
                        d.setNotaServicio(notaServicioDTO);
                        valid=valid && this.listaParticipantes.get(i+1).validar(idTransaction, d);
                    }
                    if(valid){
                        this.commitAll(idTransaction);
                        return notaServicioDTO;
                    }else
                        this.cancelAll(idTransaction);
                }else
                    this.listaParticipantes.get(0).cancel(idTransaction);
            }
            return null;
    }
    public boolean anularNota(int id, PersonaDTO cliente, Date fecha, String descripcion) throws Exception{
        if(id>0 && cliente!=null && fecha!=null && !descripcion.trim().isEmpty()){
                notaServicioDTO=new NotaServicioDTO();
                notaServicioDTO.setId(id);
                notaServicioDTO.setPersona(cliente);
                notaServicioDTO.setFecha(fecha);
                notaServicioDTO.setDescripcionCliente(descripcion);
                notaServicioDTO.setEliminado(Boolean.TRUE);
                notaServicioDAO.actualizar(notaServicioDTO);
        }else
            throw new Exception("Debe introducir datos");
        return true;
    }
    public NotaServicioDTO buscarNotaPorId(int id) throws Exception{
        notaServicioDTO=new NotaServicioDTO();
        notaServicioDTO.setId(id);
        notaServicioDTO=notaServicioDAO.getNotaById(notaServicioDTO);
        if(notaServicioDTO!=null){
            return  notaServicioDTO;
        }else
            throw new Exception("No se encontraron resultados");
        
    }
    public List listarTodos() throws Exception{
        
        return this.notaServicioDAO.getAll();
        
    }
}
