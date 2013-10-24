/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.business;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import scout.entity.Membre;
import scout.entity.Staff;

/**
 *
 * @author Wfix
 */
@Stateless
public class MembreEJB {
    @PersistenceContext(unitName = "Scout34PU")
    private EntityManager em;


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Membre inscrire(String nom, String prenom, String totem, Date ddn, String login, String mdp, int statut,  int staff) {        
        Membre m = new Membre();
        m.setNom(nom);
        m.setPrenom(prenom);
        m.setTotem(totem);
        m.setDdn(ddn);
        m.setLogin(login);
        m.setMdp(mdp);
        m.setStatut(statut);
        Query q = em.createNamedQuery("Staff.findByIdstaff");
        q.setParameter("idStaff", staff);
        Staff f = (Staff) q.getSingleResult();
        System.out.println(f.getNom());
        em.persist(m);
        return m;
    }

    public Membre connecter(String login, String mdp) {
        System.out.println(login +" - "+mdp);
        Query q = em.createQuery("SELECT m FROM Membre m WHERE m.login = :login");
        q.setParameter("login", login);
        Membre m = (Membre) q.getSingleResult();
        if(m.getMdp().equals(mdp)){
            return m;
        }
        return null;
    }

}
