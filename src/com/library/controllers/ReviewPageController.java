package com.library.controllers;

import com.database.BooksQuery;
import com.database.ReviewQuery;
import com.library.entities.Book;
import com.library.entities.Persons;
import com.library.entities.Reviews;
import com.library.exceptions.TextLengthException;
import com.library.exceptions.TooHighRatingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ReviewPageController implements Initializable {
    
    @FXML
    private Label message;
    
    @FXML
    private TextArea reviewText;
    
    @FXML
    private TextField rating;
    

    private Persons user;
    private Book book;
    
    public void setUserAndBook(Persons person, Book book){
        this.user=person;
        this.book=book;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rating.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    rating.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }    
    
    public void initBook(){
        message.setText(book.toString());        
    }
    
    
    @FXML
    public void addReview(ActionEvent event) throws TextLengthException, TooHighRatingException{
        if (reviewText.getText().length()<500)
        {
            Reviews review = new Reviews();
            review.setBookID(book);
            review.setUserID(user);
            review.setReview(reviewText.getText());
            
            ReviewQuery rq=new ReviewQuery();
            rq.addReview(review);
        }
        else throw new TextLengthException();
        
        Integer ratingAsInteger=Integer.parseInt(rating.getText());
        if ((ratingAsInteger <= 5) && (ratingAsInteger >= 1)) {
            Double newRating=(book.getRating()*book.getNumberOfRatings()+ratingAsInteger)/(book.getNumberOfRatings()+1);
            book.setNumberOfRatings(book.getNumberOfRatings() + 1);
            book.setRating(newRating);
        }
        else throw new TooHighRatingException();
        
        BooksQuery booksQuery=new BooksQuery();
        booksQuery.editBook(book);   
        Stage oldStage = (Stage) message.getScene().getWindow();
        oldStage.close();
    }
}
