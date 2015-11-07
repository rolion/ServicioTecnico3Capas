/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import interfaces.ProtoType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class NotaServicioDTO implements ProtoType{
    
     private Integer id;
     private PersonaDTO persona;
     private Date fecha;
     private String descripcionCliente;
     private Boolean eliminado;
     private List detalle;
     private long currentTransaction;

    public NotaServicioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionCliente() {
        return descripcionCliente;
    }

    public void setDescripcionCliente(String descripcionCliente) {
        this.descripcionCliente = descripcionCliente;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List getDetalle() {
        return detalle;
    }

    public void setDetalle(List detalle) {
        this.detalle = detalle;
    }

    public long getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(long currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public Object copiarProfunda() {
        NotaServicioDTO clone=new NotaServicioDTO();
        clone.setDescripcionCliente(descripcionCliente);
        clone.setDetalle(detalle);
        clone.setEliminado(eliminado);
        clone.setFecha(fecha);
        clone.setId(id);
        clone.setPersona(persona);
        return clone;
    }

    @Override
    public String toString() {
        return  id.toString() ;
    }
     
}
