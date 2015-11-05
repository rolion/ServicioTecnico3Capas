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
public class ServicioDTO implements ProtoType{
     private Integer id;
     private String descripcion;
     private Float precio;
     private Boolean eliminado;
     private long currentTransaction;

    public ServicioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public long getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(long currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public Object copiarProfunda() {
        ServicioDTO clone=new ServicioDTO();
        clone.setId(id);
        clone.setEliminado(eliminado);
        clone.setPrecio(precio);
        clone.setDescripcion(descripcion);
        clone.setCurrentTransaction(currentTransaction);
        return clone;
    }
     
}
