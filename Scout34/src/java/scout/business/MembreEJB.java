/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.business;

import ConversionChaine.MyString;
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

    public Membre inscrire(Membre nouveau, int intStaff) {
        // Vérifie pas d'injection SQL (devrait le faire pour tous les champs ...)
        if (!MyString.contientCaractere(nouveau.getLogin(), "<>$[]()")) {
            Query q = em.createQuery("SELECT m FROM Membre m WHERE m.login = :login");
            q.setParameter("login", nouveau.getLogin());
            if (q.getResultList().isEmpty()) {
                Membre m = new Membre();
                m.setNom(nouveau.getNom());
                m.setPrenom(nouveau.getPrenom());
                m.setTotem(nouveau.getTotem());
                m.setDdn(nouveau.getDdn());
                m.setLogin(nouveau.getLogin());
                m.setMdp(nouveau.getMdp());
                m.setStatut(nouveau.getStatut());
                if (intStaff != -1) {
                    Query q2 = em.createQuery("Select s FROM Staff s where s.idstaff = :idStaff");
                    q2.setParameter("idStaff", intStaff);
                    Staff f = (Staff) q2.getSingleResult();
                    m.setStaff(f);
                }
                m.setActif(1);
                m.setAdmin(0);
                em.persist(m);
                return m;
            }
        }
        return null;
    }

    public boolean loginPresent(String login) {
        if (!MyString.contientCaractere(login, "<>$[]()")) {
            Query q = em.createQuery("SELECT m FROM Membre m WHERE m.login = :login");
            q.setParameter("login", login);
            if (!q.getResultList().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Membre connecter(String login, String mdp) {
        if (!MyString.contientCaractere(login, "<>$[]()")) {
            Query q = em.createQuery("SELECT m FROM Membre m WHERE m.login = :login");
            q.setParameter("login", login);
            if (!q.getResultList().isEmpty()) {
                Membre m = (Membre) q.getSingleResult();
                if (m.getMdp().equals(mdp)) {
                    return m;
                }
            }
        }
        return null;
    }
}
