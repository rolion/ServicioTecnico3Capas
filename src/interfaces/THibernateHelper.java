package interfaces;

import conexion.NewHibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 * @author oscar
 * @version 1.0
 * @created 26-Aug-2015 11:31:40 AM
 */
public abstract class THibernateHelper implements TransactionParticipant{
        private Session session;

	public THibernateHelper(){

	}


	/**
	 * 
	 * @param entidad
	 */
	public abstract List buscar() throws Exception;

	/**
	 * 
	 * @param entidad
	 */
	protected List buscarEntidad(String query, int id) throws Exception{
            try{
                initTransaction();
                Query q=session.createQuery(query).setInteger("id", id)
                        .setBoolean("estado", Boolean.FALSE);
                List result=q.list();
                comitTransaction();
                return result;
            }catch(HibernateException he){
                System.out.println(he.getMessage());
                rollbackTransaciont();
                throw new Exception("Error al buscar");
            }
		
	}

	/**
	 * 
	 * @param entidad
	 */
	public abstract Boolean eliminar() throws Exception;

	/**
	 * 
	 * @param entidad
	 */
	protected Boolean eliminarEntidad(Object entidad)throws Exception{
            try{
                initTransaction();
                session.delete(entidad);
                comitTransaction();
                session=null;
                return true;
            }catch(HibernateException he){
                rollbackTransaciont();
                throw new Exception(he.getMessage()+", Error al eliminar la entidad");
            }
		
	}

	public Session getConection(){
            if(session==null){
                try{
                    session=NewHibernateUtil.getSessionFactory().getCurrentSession();
                }catch(HibernateException he){
                    System.out.println("Error al obtener la conexion");
                    
                }catch(ExceptionInInitializerError EIIE){
                    System.err.println(EIIE.getMessage());
                }
                
            }
            if(!session.isOpen())
                session=NewHibernateUtil.getSessionFactory().openSession();
        return session;
        };
        
        	/**
	 * 
	 * @param entidad
	 */
	public abstract Object guardar() throws Exception;

	/**
	 * 
	 * @param entidad
	 */
	public abstract Object guardar(long transactionID) throws Exception;
	/**
	 * 
	 * @param entidad
	 */
	protected Object guardarEntidad(Object entidad) throws Exception{
                
                
                    initTransaction();
                    session.save(entidad);
                    comitTransaction();
                    return entidad;
 

	}

	/**
	 * 
	 * @param entidad
     
	 */
	public abstract Object modificar() throws Exception;

	/**
	 * 
	 * @param entidad
	 */
	protected Object modificarEntidad(Object entidad) throws Exception{
            try{
                initTransaction();
                session.update(entidad);
                comitTransaction();
                return entidad;
            }catch(HibernateException he){
                System.out.println(he.getMessage());
                rollbackTransaciont();
                throw new Exception("Error al modificar");
                
            } catch (Exception ex) {
                rollbackTransaciont();
                throw new Exception(ex.getMessage());
            }
		
	}
        protected void initTransaction(){
            getConection().beginTransaction();
        }
        protected void comitTransaction(){
            if(session!=null && session.isOpen()){
                this.session.getTransaction().commit();
                this.session=null;
            }
                
        }
        protected void rollbackTransaciont(){
            if(session!=null && session.isOpen()){
                this.session.getTransaction().rollback();
                this.session=null;
            }
        }

}