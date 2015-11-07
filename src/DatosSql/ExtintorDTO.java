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
public class ExtintorDTO implements ProtoType {
       private Integer id;
     private AgenteQuimicoDTO agenteQuimico;
     private String clasificacion;
     private Boolean eliminado;
     private int peso;
     private long currentTransaction;

    public ExtintorDTO() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AgenteQuimicoDTO getAgenteQuimico() {
        return agenteQuimico;
    }

    public void setAgenteQuimico(AgenteQuimicoDTO agenteQuimico) {
        this.agenteQuimico = agenteQuimico;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public long getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(long currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

     
    @Override
    public Object copiarProfunda() {
        ExtintorDTO clone=new ExtintorDTO();
        clone.setId(id);
        clone.setAgenteQuimico(agenteQuimico);
        clone.setClasificacion(clasificacion);
        clone.setCurrentTransaction(currentTransaction);
        clone.setEliminado(eliminado);
        clone.setPeso(peso);
        return clone;
    }

    @Override
    public String toString() {
        return agenteQuimico.getNombre()+" "+this.getPeso()+"kg";
    }
    
}
