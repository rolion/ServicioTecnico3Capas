/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author root
 */
public interface SpecificParticipant extends TransactionParticipant{
    
    public boolean insertar(long transactionID);
    public boolean buscar(long transactionID);
    public boolean anular(long transactionID);
    public boolean actializar(long transactionID);
}
