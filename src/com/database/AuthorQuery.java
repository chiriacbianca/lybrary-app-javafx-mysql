package com.database;

import com.library.entities.Author;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AuthorQuery {
    EntityManager em;
    EntityManagerFactory emf;

    public AuthorQuery() {
        emf=Persistence.createEntityManagerFactory("LibraryPU");
        em=emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Author> listOfAuthor(){
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }
    
    public boolean addAuthor(Author author)
    {
        try {
            em.persist(author);
            em.getTransaction().commit();
            return true;
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        } 
    }
}
