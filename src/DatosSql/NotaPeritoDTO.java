/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class NotaPeritoDTO {
     private Integer id;
     private NotaServicioDTO notaServicio;
     private PersonaDTO perito;
     private Date fecha;
     private Boolean eliminado;
     private List detalle;

    public NotaPeritoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public List getDetalle() {
        return detalle;
    }

    public void setDetalle(List detalle) {
        this.detalle = detalle;
    }
    
    

    public void setId(Integer id) {
        this.id = id;
    }

    public NotaServicioDTO getNotaServicio() {
        return notaServicio;
    }

    public void setNotaServicio(NotaServicioDTO notaServicio) {
        this.notaServicio = notaServicio;
    }

    public PersonaDTO getPerito() {
        return perito;
    }

    public void setPerito(PersonaDTO perito) {
        this.perito = perito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return  id.toString();
    }
    
}
