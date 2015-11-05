/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosSql;

import Datos.TipoPersona;
import interfaces.ProtoType;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author root
 */
public class PersonaDTO implements ProtoType{
     private Integer id;
     private TipoPersonaDTO tipoPersona;
     private String nombre;
     private String apellido;
     private Integer ci;
     private Integer telefono;
     private String email;
     private String nombreEmpresa;
     private Boolean eliminado;
     private long currentTransaction;

    public PersonaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPersonaDTO getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersonaDTO tipoPersona) {
        this.tipoPersona = tipoPersona;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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
        PersonaDTO clone=new PersonaDTO();
        clone.setApellido(apellido);
        clone.setCi(ci);
        clone.setCurrentTransaction(currentTransaction);
        clone.setEliminado(eliminado);
        clone.setEmail(email);
        clone.setId(id);
        clone.setNombre(nombre);
        clone.setNombreEmpresa(nombreEmpresa);
        clone.setTelefono(telefono);
        clone.setTipoPersona(tipoPersona);
        return clone;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" + "id=" + id + ", tipoPersona=" + tipoPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", ci=" + ci + ", telefono=" + telefono + ", email=" + email + ", nombreEmpresa=" + nombreEmpresa + ", eliminado=" + eliminado + ", currentTransaction=" + currentTransaction + '}';
    }
    
    
}
