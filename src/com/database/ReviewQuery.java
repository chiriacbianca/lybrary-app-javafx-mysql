package com.database;

import com.library.entities.Book;
import com.library.entities.Reviews;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ReviewQuery {
    EntityManager em;
    EntityManagerFactory emf;
    
    public ReviewQuery() {
        emf=Persistence.createEntityManagerFactory("LibraryPU");
        em=emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Reviews> listOfReviews(){
        return em.createNamedQuery("Reviews.findAll", Reviews.class).getResultList();
    }
    
    public void addReview(Reviews r) {
        try {
            em.persist(r);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
