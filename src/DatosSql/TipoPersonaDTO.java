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
public class TipoPersonaDTO implements ProtoType{
     private Integer id;
     private String nombre;
     private Boolean eliminado;
     private String descripcion;
     private long currentTransaction;

    public TipoPersonaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(long currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public Object copiarProfunda() {
       TipoPersonaDTO clone=new TipoPersonaDTO();
       clone.setId(id);
       clone.setNombre(nombre);
       clone.setEliminado(eliminado);
       clone.setDescripcion(descripcion);
       clone.setCurrentTransaction(currentTransaction);
       return clone;
    }

    @Override
    public String toString() {
        return "TipoPersonaDTO{" + "id=" + id + ", nombre=" + nombre + ", eliminado=" + eliminado + ", descripcion=" + descripcion + ", currentTransaction=" + currentTransaction + '}';
    }
    
     
}
