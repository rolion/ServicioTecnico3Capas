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
public class AgenteQuimicoDTO implements ProtoType{
     private Integer id;
     private String nombre;
     private String clasificacion;
     private Boolean eliminado;
     private long currentTransaction;

    public AgenteQuimicoDTO() {
        this.currentTransaction=0;
    }

    public long getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(long currentTransaction) {
        this.currentTransaction = currentTransaction;
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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public Object copiarProfunda() {
        AgenteQuimicoDTO clone=new AgenteQuimicoDTO();
        clone.setId(id);
        clone.setEliminado(eliminado);
        clone.setClasificacion(clasificacion);
        clone.setNombre(nombre);
        return clone;
    }

    @Override
    public String toString() {
        return  nombre ;
    }
    
}
