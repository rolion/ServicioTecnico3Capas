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
public interface TransactionParticipant {
    public boolean join(long transactionID);
    public void commit(long transactionID) throws Exception;
    public void cancel(long transactionID);
}
