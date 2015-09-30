/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lorenzo Addazi
 */
@Entity
@Table(name = "FRUITS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fruits.findAll", query = "SELECT f FROM Fruits f"),
    @NamedQuery(name = "Fruits.findById", query = "SELECT f FROM Fruits f WHERE f.id = :id"),
    @NamedQuery(name = "Fruits.findByName", query = "SELECT f FROM Fruits f WHERE f.name = :name"),
    @NamedQuery(name = "Fruits.findByPrice", query = "SELECT f FROM Fruits f WHERE f.price = :price"),
    @NamedQuery(name = "Fruits.findByQuantity", query = "SELECT f FROM Fruits f WHERE f.quantity = :quantity")})
public class Fruits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ID")
    private String id;
    @Size(max = 24)
    @Column(name = "NAME")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Fruits() {
    }

    public Fruits(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof Fruits)) {
            return false;
        }
        Fruits other = (Fruits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fruits[ id=" + id + " ]";
    }
    
}
