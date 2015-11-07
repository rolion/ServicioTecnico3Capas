/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import interfaces.ProtoType;


/**
 *
 * @author root
 */
public class DetalleNotaServicioDTO implements ProtoType{
     private Integer id;
     private ExtintorDTO extintor;
     private NotaServicioDTO notaServicio;
     private Integer cantidad;

    public DetalleNotaServicioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExtintorDTO getExtintor() {
        return extintor;
    }

    public void setExtintor(ExtintorDTO extintor) {
        this.extintor = extintor;
    }


    public NotaServicioDTO getNotaServicio() {
        return notaServicio;
    }

    public void setNotaServicio(NotaServicioDTO notaServicio) {
        this.notaServicio = notaServicio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public Object copiarProfunda() {
        DetalleNotaServicioDTO clone=new DetalleNotaServicioDTO();
        clone.setId(id);
        clone.setCantidad(cantidad);
        clone.setExtintor(extintor);
        clone.setNotaServicio(notaServicio);
        return clone;
    }
    
     
}
