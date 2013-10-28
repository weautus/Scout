/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import scout.business.MembreEJB;
import scout.entity.Membre;

/**
 *
 * @author Wfix
 */
@Named(value = "membreCtrl")
@SessionScoped
public class MembreController implements Serializable {

    @EJB
    private MembreEJB membreEJB;
    
    private String mdp;
    private String login;
    private Membre membreConnecte;
    private boolean estConnecte;
    private String erreur;
    private Membre membreACreer;
    private Date dateSel;
    private int intStaff;


    public String identifier() {

        membreConnecte = membreEJB.connecter(login, mdp);
        if (membreConnecte == null) {
            erreur = "Login ou mot de passe invalide.";
            return "FAILURE";
        }
        estConnecte = true;
        erreur = null;
        return "SUCCESS";
    }

    public String goInscription() {
        membreACreer = new Membre();
        erreur=null;
        return "INSCRIPTION";
    }

    public String goAccueil() {
        return "RETOURACCUEIL";
    }

    public String goInscriptionFin() {
        erreur = null;
        if (!membreEJB.loginPresent(membreACreer.getLogin())) {
            membreConnecte = membreEJB.inscrire(membreACreer, intStaff);
            if (membreConnecte == null) {
                System.out.println("ERRRRRRRREUR inscription");
                erreur = "Erreur lors de l'inscription : le pseudo est déjà utilisé";
                return "INSCRIPTIONERREUR";
            }
            membreACreer = null;
            estConnecte = true;
            return "INSCRIPTIONFINOK";
        } else {
            erreur = "login déjà utilisé";
            return "REINSCRIPTION";
        }
    }

    public void deconnexion() {
        estConnecte = false;
        membreConnecte = null;
        login = null;
        mdp = null;
    }

    /**
     * Creates a new instance of MembreController
     */
    public MembreController() {
    }
    
    /**
     * Get the value of membreConnecte
     *
     * @return the value of membreConnecte
     */
    public Membre getMembreConnecte() {
        return membreConnecte;
    }

    /**
     * Set the value of membreConnecte
     *
     * @param membreConnecte new value of membreConnecte
     */
    public void setMembreConnecte(Membre membreConnecte) {
        this.membreConnecte = membreConnecte;
    }

    /**
     * Get the value of mdp
     *
     * @return the value of mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Set the value of mdp
     *
     * @param mdp new value of mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the value of estConnecte
     *
     * @return the value of estConnecte
     */
    public boolean isEstConnecte() {
        return estConnecte;
    }

    /**
     * Set the value of estConnecte
     *
     * @param estConnecte new value of estConnecte
     */
    public void setEstConnecte(boolean estConnecte) {
        this.estConnecte = estConnecte;
    }
    
    /**
     * Get the value of erreur
     *
     * @return the value of erreur
     */
    public String getErreur() {
        return erreur;
    }

    /**
     * Set the value of erreur
     *
     * @param erreur new value of erreur
     */
    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    /**
     * Get the value of membreACreer
     *
     * @return the value of membreACreer
     */
    public Membre getMembreACreer() {
        return membreACreer;
    }

    /**
     * Set the value of membreACreer
     *
     * @param membreACreer new value of membreACreer
     */
    public void setMembreACreer(Membre membreACreer) {
        this.membreACreer = membreACreer;
    }

    /**
     * Get the value of dateSel
     *
     * @return the value of dateSel
     */
    public Date getDateSel() {
        return dateSel;
    }

    /**
     * Set the value of dateSel
     *
     * @param dateSel new value of dateSel
     */
    public void setDateSel(Date dateSel) {
        this.dateSel = dateSel;
    }

    /**
     * Get the value of intStaff
     *
     * @return the value of intStaff
     */
    public int getIntStaff() {
        return intStaff;
    }

    /**
     * Set the value of intStaff
     *
     * @param intStaff new value of intStaff
     */
    public void setIntStaff(int intStaff) {
        this.intStaff = intStaff;
    }
}
