/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.library.entities.Borrows;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bianca info
 */
public class BorrowsQuery {
    EntityManager em;
    EntityManagerFactory emf;

    public BorrowsQuery() {
        emf=Persistence.createEntityManagerFactory("LibraryPU");
        em=emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void addBorrow(Borrows borrow){
        try{
        em.persist(borrow);
        em.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
 
    
}
