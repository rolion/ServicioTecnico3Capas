/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import java.util.Date;

/**
 *
 * @author root
 */
public class NotaEntregaDTO {
     private Integer id;
     private NotaPeritoDTO notaPerito;
     private Date fecha;
     private Boolean eliminado;

    public NotaEntregaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotaPeritoDTO getNotaPerito() {
        return notaPerito;
    }

    public void setNotaPerito(NotaPeritoDTO notaPerito) {
        this.notaPerito = notaPerito;
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
     
}
