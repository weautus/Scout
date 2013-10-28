/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scout.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wfix
 */
@Entity
@Table(name = "MEMBRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m"),
    @NamedQuery(name = "Membre.findByIdmembre", query = "SELECT m FROM Membre m WHERE m.idmembre = :idmembre"),
    @NamedQuery(name = "Membre.findByNom", query = "SELECT m FROM Membre m WHERE m.nom = :nom"),
    @NamedQuery(name = "Membre.findByPrenom", query = "SELECT m FROM Membre m WHERE m.prenom = :prenom"),
    @NamedQuery(name = "Membre.findByTotem", query = "SELECT m FROM Membre m WHERE m.totem = :totem"),
    @NamedQuery(name = "Membre.findByDdn", query = "SELECT m FROM Membre m WHERE m.ddn = :ddn"),
    @NamedQuery(name = "Membre.findByLogin", query = "SELECT m FROM Membre m WHERE m.login = :login"),
    @NamedQuery(name = "Membre.findByMdp", query = "SELECT m FROM Membre m WHERE m.mdp = :mdp"),
    @NamedQuery(name = "Membre.findByStatut", query = "SELECT m FROM Membre m WHERE m.statut = :statut"),
    @NamedQuery(name = "Membre.findByEmail", query = "SELECT m FROM Membre m WHERE m.email = :email"),
    @NamedQuery(name = "Membre.findByActif", query = "SELECT m FROM Membre m WHERE m.actif = :actif"),
    @NamedQuery(name = "Membre.findByAdmin", query = "SELECT m FROM Membre m WHERE m.admin = :admin")})
public class Membre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Membre")
    @TableGenerator(name="Membre", allocationSize=1)
    @Column(name = "IDMEMBRE")
    private Integer idmembre;
    @Size(max = 255)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 255)
    @Column(name = "PRENOM")
    private String prenom;
    @Size(max = 255)
    @Column(name = "TOTEM")
    private String totem;
    @Column(name = "DDN")
    @Temporal(TemporalType.DATE)
    private Date ddn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MDP")
    private String mdp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private int statut;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIF")
    private int actif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADMIN")
    private int admin;
    @OneToMany(mappedBy = "idchefsection")
    private Collection<Staff> staffCollection;
    @JoinColumn(name = "STAFF", referencedColumnName = "IDSTAFF")
    @ManyToOne
    private Staff staff;

    public Membre() {
    }

    public Membre(Integer idmembre) {
        this.idmembre = idmembre;
    }

    public Membre(Integer idmembre, String login, String mdp, int statut, int actif, int admin) {
        this.idmembre = idmembre;
        this.login = login;
        this.mdp = mdp;
        this.statut = statut;
        this.actif = actif;
        this.admin = admin;
    }

    public Integer getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(Integer idmembre) {
        this.idmembre = idmembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTotem() {
        return totem;
    }

    public void setTotem(String totem) {
        this.totem = totem;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmembre != null ? idmembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.idmembre == null && other.idmembre != null) || (this.idmembre != null && !this.idmembre.equals(other.idmembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "scout.entity.Membre[ idmembre=" + idmembre + " ]";
    }
    
}
