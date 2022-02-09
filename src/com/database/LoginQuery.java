/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.library.entities.Persons;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bianca info
 */
public class LoginQuery {
    EntityManager em;
    EntityManagerFactory emf;

    public LoginQuery() {
        emf=Persistence.createEntityManagerFactory("LibraryPU");
        em=emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Persons> listOfLogin(){
        return em.createNamedQuery("Persons.findAll", Persons.class).getResultList();
    }
    
    public boolean signUp(Persons p)
    {
        try {
            em.persist(p);
            em.getTransaction().commit();
            return true;
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        } 
    }
}
