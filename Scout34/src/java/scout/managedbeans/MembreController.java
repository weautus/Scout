/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    private String message;
    private String conversation;

    
    public String envoyer(){
        if(conversation==null){
            conversation="";
        }
        conversation += message + "<br/>";
        return "Message";
    }
    
    public String identifier() {
        membreConnecte = membreEJB.connecter(login, mdp);
        if (membreConnecte == null) {
            System.out.println("FAILURE");
            return "FAILURE";
        }
        estConnecte = true;
        System.out.println("SUCCESS");
        return "SUCCESS";
    }
    
    public String goInscription(){
        return "INSCRIPTION";
    }

    public String goChat() {
        if (membreConnecte != null) {
            return "chat.xhtml";
        }
        return "index.xhtml";
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
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }

        /**
     * Get the value of conversation
     *
     * @return the value of conversation
     */
    public String getConversation() {
        return conversation;
    }

    /**
     * Set the value of conversation
     *
     * @param conversation new value of conversation
     */
    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

}
