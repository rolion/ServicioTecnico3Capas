/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author root
 */
public class DetallePeritoDTO {
     private Integer id;
     private NotaPeritoDTO notaPerito;
     private ServicioDTO servicio;
     private Integer cantidad;
     private Float precioTotal;

    public DetallePeritoDTO() {
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

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }
    
  
}
