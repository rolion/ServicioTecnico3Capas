/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciotecnico3capas;

import DatosSql.AgenteQuimicoDAO;
import DatosSql.PersonaDAO;
import DatosSql.TipoPersonaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class ServicioTecnico3Capas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            PersonaDAO dao=new PersonaDAO();
            List lista=dao.getAll();
            for (Object lista1 : lista) {
                System.out.println(lista1);
            }
            
    }
    
}
