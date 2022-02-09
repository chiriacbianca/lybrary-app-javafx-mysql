
package com.library.controllers;

import com.database.BooksQuery;
import com.database.BorrowsQuery;
import com.library.entities.Book;
import com.library.entities.Borrows;
import com.library.entities.Persons;
import com.library.entities.Reviews;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BorrowPageController implements Initializable {
    
    @FXML
    private Label message;
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn<Reviews, Integer> columnReviewId;
    
    @FXML
    private TableColumn<Reviews, String> columnReview;
    
    @FXML
    private TableColumn<Reviews, Persons> columnReviewUser;

    private Persons user;
    private Book book;

    ObservableList<Reviews> reviewDisplay;
    
    

    public void setUserAndBook(Persons person, Book book){
        this.user=person;
        this.book=book;
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       columnReviewId.setCellValueFactory(new PropertyValueFactory<>("reviewID"));
       columnReview.setCellValueFactory(new PropertyValueFactory<>("review"));
       columnReviewUser.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
    
    public void initBook(){
        message.setText(book.toString());
        reviewDisplay = FXCollections.observableArrayList(book.getReviewsCollection());
        table.setItems(reviewDisplay);
    }
    
    
    @FXML
    public void borrowBook(ActionEvent event){
        //LocalDate localDate=LocalDate.now();
        //Date date=java.sql.Date.valueOf(localDate);
        Date date=new Date();
        Borrows borrow=new Borrows();
        borrow.setBookID(book);
        borrow.setUserID(user);
        borrow.setDateOfBorrow(date);
        
        BorrowsQuery borrowQuery = new BorrowsQuery();
        borrowQuery.addBorrow(borrow);
        
        book.setNumberOfCopies(book.getNumberOfCopies()-1);
        BooksQuery bookQuery=new BooksQuery();
        bookQuery.editBook(book);
        
        Stage oldStage = (Stage) message.getScene().getWindow();
        oldStage.close();
    }

    @FXML
    public void addReview(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/ReviewPage.fxml"));
            Parent parent = loader.load();
            ReviewPageController controller = (ReviewPageController) loader.getController();
            controller.setUserAndBook(user, book);
            controller.initBook();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle(book.getTitle());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
