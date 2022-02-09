package com.library.controllers;

import com.library.entities.Book;
import com.library.entities.Borrows;
import com.library.entities.Persons;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BookHistoryController implements Initializable {
    
    @FXML
    private Label message;
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn<Borrows, Integer> columnNrCrt;
    
    @FXML
    private TableColumn<Borrows, Persons> columnUser;
    
    @FXML
    private TableColumn<Borrows, Date> columnDateOfBorrow;


    private Book book;
    ObservableList<Borrows> borrowDisplay;
    
    public void setBook(Book book){
        this.book=book;
    }
    
    public void initBook(){
        message.setText(book.toString());
        borrowDisplay=FXCollections.observableArrayList(book.getBorrowsCollection());
        table.setItems(borrowDisplay);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnNrCrt.setCellValueFactory(new PropertyValueFactory<>("borrowID"));
       columnUser.setCellValueFactory(new PropertyValueFactory<>("userID"));
       columnDateOfBorrow.setCellValueFactory(new PropertyValueFactory<>("dateOfBorrow"));
    }    
    
}
