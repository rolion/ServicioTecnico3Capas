/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;


import DatosSql.NotaEntregaDAO;
import DatosSql.NotaEntregaDTO;
import DatosSql.NotaPeritoDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NGestionarNotaEntrega {
    
    
    private NotaEntregaDAO notaEntregaDAO;
    private NotaEntregaDTO notaEntregaDTO;

    public NGestionarNotaEntrega() throws SQLException, ClassNotFoundException {
        this.notaEntregaDAO=new NotaEntregaDAO();
    }

    public NotaEntregaDTO nuevaNotaEntrega(NotaPeritoDTO nperito, Date fecha) throws Exception{
        if(nperito!=null && fecha !=null){
            this.notaEntregaDTO=new NotaEntregaDTO();
            this.notaEntregaDTO.setNotaPerito(nperito);
            this.notaEntregaDTO.setFecha(fecha);
            this.notaEntregaDTO.setEliminado(Boolean.FALSE);
            this.notaEntregaDAO.insertarNotaEntrega(notaEntregaDTO);
            return this.notaEntregaDTO;
        }else
            throw new Exception("debe introducir valores no nulos");
    }
    public NotaEntregaDTO anularNota(int id,NotaPeritoDTO nperito, Date fecha) throws Exception{
        if(nperito!=null && fecha!=null && id>0){
            this.notaEntregaDTO=new NotaEntregaDTO();
            this.notaEntregaDTO.setId(id);
            this.notaEntregaDTO.setNotaPerito(nperito);
            this.notaEntregaDTO.setFecha(fecha);
            this.notaEntregaDTO.setEliminado(Boolean.TRUE);
            this.notaEntregaDAO.actualizarNotaEntrega(notaEntregaDTO);
            return notaEntregaDTO;
        }else 
            throw new Exception("debe introducir valores no nulos");
    
    }

    public List listarTodos() throws SQLException, ClassNotFoundException{
       
        return this.notaEntregaDAO.getAll();
    }
}
