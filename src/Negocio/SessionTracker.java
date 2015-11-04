/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class SessionTracker {
    List sessions;

    public SessionTracker() {
        this.sessions=new ArrayList();
    }
    
    public long createSession(){
        Session session=new Session();
        Date d=new Date();
        session.setId(d.getTime());
        this.sessions.add(session);
        return session.getId();
    }
    public void destroySession(long id){
        for (Object s : sessions) {
            Session session=(Session)s;
            if(session.getId()==id){
                this.sessions.remove(s);
            }
        }
    }
}
