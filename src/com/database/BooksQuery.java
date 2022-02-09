
package com.database;

import com.library.entities.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BooksQuery {
     EntityManager em;
    EntityManagerFactory emf;

    public BooksQuery() {
        emf=Persistence.createEntityManagerFactory("LibraryPU");
        em=emf.createEntityManager();
        em.getTransaction().begin();
    }
    
     public List<Book> listOfBooks(){
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }
    
     public boolean addBook(Book b)
    {
        try {
            em.persist(b);
            em.getTransaction().commit();
            return true;
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        } 
    }
     
     public void editBook(Book b){
         try {
             em.merge(b);
             em.getTransaction().commit();
         } catch (Exception e){
             System.out.println(e.toString());
         }
     }
}
