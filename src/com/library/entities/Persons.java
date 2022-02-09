/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bianca info
 */
@Entity
@Table(name = "persons", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"personID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p")
    , @NamedQuery(name = "Persons.findByPersonID", query = "SELECT p FROM Persons p WHERE p.personID = :personID")
    , @NamedQuery(name = "Persons.findByPersonName", query = "SELECT p FROM Persons p WHERE p.personName = :personName")
    , @NamedQuery(name = "Persons.findByUsername", query = "SELECT p FROM Persons p WHERE p.username = :username")
    , @NamedQuery(name = "Persons.findByEntryKey", query = "SELECT p FROM Persons p WHERE p.entryKey = :entryKey")
    , @NamedQuery(name = "Persons.findByAccess", query = "SELECT p FROM Persons p WHERE p.access = :access")})
public class Persons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personID", nullable = false)
    private Integer personID;
    @Column(name = "personName", length = 25)
    private String personName;
    @Column(name = "username", length = 25)
    private String username;
    @Column(name = "entryKey", length = 25)
    private String entryKey;
    @Column(name = "access")
    private Boolean access;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Reviews> reviewsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Borrows> borrowsCollection;

    public Persons() {
    }

    public Persons(Integer personID) {
        this.personID = personID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntryKey() {
        return entryKey;
    }

    public void setEntryKey(String entryKey) {
        this.entryKey = entryKey;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    @XmlTransient
    public Collection<Reviews> getReviewsCollection() {
        return reviewsCollection;
    }

    public void setReviewsCollection(Collection<Reviews> reviewsCollection) {
        this.reviewsCollection = reviewsCollection;
    }

    @XmlTransient
    public Collection<Borrows> getBorrowsCollection() {
        return borrowsCollection;
    }

    public void setBorrowsCollection(Collection<Borrows> borrowsCollection) {
        this.borrowsCollection = borrowsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personID != null ? personID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return username;
    }
    
}
