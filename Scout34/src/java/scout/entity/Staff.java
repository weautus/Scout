/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scout.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wfix
 */
@Entity
@Table(name = "STAFF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findByIdstaff", query = "SELECT s FROM Staff s WHERE s.idstaff = :idstaff"),
    @NamedQuery(name = "Staff.findByNom", query = "SELECT s FROM Staff s WHERE s.nom = :nom")})
public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSTAFF")
    private Integer idstaff;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOM")
    private String nom;
    @JoinColumn(name = "IDCHEFSECTION", referencedColumnName = "IDMEMBRE")
    @ManyToOne
    private Membre idchefsection;
    @OneToMany(mappedBy = "staff")
    private Collection<Membre> membreCollection;

    public Staff() {
    }

    public Staff(Integer idstaff) {
        this.idstaff = idstaff;
    }

    public Staff(Integer idstaff, String nom) {
        this.idstaff = idstaff;
        this.nom = nom;
    }

    public Integer getIdstaff() {
        return idstaff;
    }

    public void setIdstaff(Integer idstaff) {
        this.idstaff = idstaff;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Membre getIdchefsection() {
        return idchefsection;
    }

    public void setIdchefsection(Membre idchefsection) {
        this.idchefsection = idchefsection;
    }

    @XmlTransient
    public Collection<Membre> getMembreCollection() {
        return membreCollection;
    }

    public void setMembreCollection(Collection<Membre> membreCollection) {
        this.membreCollection = membreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstaff != null ? idstaff.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.idstaff == null && other.idstaff != null) || (this.idstaff != null && !this.idstaff.equals(other.idstaff))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "scout.entity.Staff[ idstaff=" + idstaff + " ]";
    }
    
}
