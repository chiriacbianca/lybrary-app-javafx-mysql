/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bianca info
 */
@Entity
@Table(name = "borrows", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"borrowID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Borrows.findAll", query = "SELECT b FROM Borrows b")
    , @NamedQuery(name = "Borrows.findByBorrowID", query = "SELECT b FROM Borrows b WHERE b.borrowID = :borrowID")
    , @NamedQuery(name = "Borrows.findByDateOfBorrow", query = "SELECT b FROM Borrows b WHERE b.dateOfBorrow = :dateOfBorrow")})
public class Borrows implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "borrowID", nullable = false)
    private Integer borrowID;
    @Column(name = "dateOfBorrow")
    @Temporal(TemporalType.DATE)
    private Date dateOfBorrow;
    @JoinColumn(name = "bookID", referencedColumnName = "bookID", nullable = false)
    @ManyToOne(optional = false)
    private Book bookID;
    @JoinColumn(name = "userID", referencedColumnName = "personID", nullable = false)
    @ManyToOne(optional = false)
    private Persons userID;

    public Borrows() {
    }

    public Borrows(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public Integer getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public Date getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(Date dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public Book getBookID() {
        return bookID;
    }

    public void setBookID(Book bookID) {
        this.bookID = bookID;
    }

    public Persons getUserID() {
        return userID;
    }

    public void setUserID(Persons userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (borrowID != null ? borrowID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borrows)) {
            return false;
        }
        Borrows other = (Borrows) object;
        if ((this.borrowID == null && other.borrowID != null) || (this.borrowID != null && !this.borrowID.equals(other.borrowID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.library.entities.Borrows[ borrowID=" + borrowID + " ]";
    }
    
}
