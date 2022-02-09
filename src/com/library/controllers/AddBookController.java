package com.library.controllers;

import com.database.AuthorQuery;
import com.database.BooksQuery;
import com.library.entities.Author;
import com.library.entities.Book;
import com.library.exceptions.NoBookException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddBookController implements Initializable {

    @FXML
    private TextField authorName;
    
    @FXML
    private TextField bookTitle;
       
    @FXML
    private TextField numberOfCopies;
    
    @FXML
    private Label message;
    
    private List<Author> listAuthor = new ArrayList<>();
    private AuthorQuery query = new AuthorQuery();
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        listAuthor = query.listOfAuthor();
        numberOfCopies.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberOfCopies.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }    
    
    private Author verifyAuthor(String nameOfAuthor){
        Author a=null;
        for (Author author: listAuthor)
        {
            System.out.println(nameOfAuthor.toUpperCase().trim()+" = "+author.getName().toUpperCase().trim()); 
            if (nameOfAuthor.toUpperCase().trim().equals(author.getName().toUpperCase().trim()))
            {
                 return author;
            }
        }
        Author newAuthor=new Author();
        newAuthor.setName(nameOfAuthor);
        
        AuthorQuery authorQuery=new AuthorQuery();
        if (authorQuery.addAuthor(newAuthor))
            return newAuthor;
        return a;
    }
    
    @FXML
    private void addBook(ActionEvent event) throws NoBookException {
        Book newBook= new Book();
        Author bookAuthor=verifyAuthor(authorName.getText()); 
        Integer numberInt=Integer.parseInt(numberOfCopies.getText());
        if (numberInt > 0) {
            newBook.setAuthor((bookAuthor.getName()));
            newBook.setAuthorID(bookAuthor);
            newBook.setTitle(bookTitle.getText());
            newBook.setRating(0.00);
            newBook.setNumberOfRatings(0);
            newBook.setNumberOfCopies(numberInt);
        }
        else throw new NoBookException();
        
        BooksQuery bookQuery = new BooksQuery();
        if (bookQuery.addBook(newBook))
            message.setText("Successfully added the book");
      
    }
}
