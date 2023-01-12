/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject.finalproject;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author rozan
 */
@Entity
@Table(catalog = "finalproject", schema = "")
@NamedQueries({
    @NamedQuery(name = "Finalproject.findAll", query = "SELECT f FROM Finalproject f"),
    @NamedQuery(name = "Finalproject.findById", query = "SELECT f FROM Finalproject f WHERE f.id = :id"),
    @NamedQuery(name = "Finalproject.findByName", query = "SELECT f FROM Finalproject f WHERE f.name = :name"),
    @NamedQuery(name = "Finalproject.findByNik", query = "SELECT f FROM Finalproject f WHERE f.nik = :nik"),
    @NamedQuery(name = "Finalproject.findByAddress", query = "SELECT f FROM Finalproject f WHERE f.address = :address")})
public class Finalproject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 30)
    private String name;
    @Column(length = 20)
    private String nik;
    @Column(length = 100)
    private String address;
    @Lob
    private byte[] photo;

    public Finalproject() {
    }

    public Finalproject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finalproject)) {
            return false;
        }
        Finalproject other = (Finalproject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalproject.finalproject.Finalproject[ id=" + id + " ]";
    }
    
}
