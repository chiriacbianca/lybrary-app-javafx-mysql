package com.library.controllers;

import com.database.BooksQuery;
import com.library.entities.Book;
import com.library.entities.Borrows;
import com.library.entities.Persons;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MyAccountController implements Initializable {
    
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> columnId;
    @FXML
    private TableColumn<Book, String> columnTitle;
    @FXML
    private TableColumn<Book, String> columnAuthor;
    @FXML
    private TableColumn<Book, Double> columnRating;
    @FXML
    private Label message;
    
    private BooksQuery book=new BooksQuery();
    ObservableList<Book> bookDisplay=FXCollections.observableArrayList(book.listOfBooks());
    
    private Persons person;
    
    public void setPerson(Persons person)
    {
        this.person=person;
        sendNotification();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tableEvents();
       columnId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
       columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
       columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
       columnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
       table.setItems(bookDisplay);
    }    
    
    public void tableEvents() {
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        Book rowData = row.getItem();
                        FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/library/BorrowPage.fxml"));
                        Parent parent = loader.load();
                        BorrowPageController controller=(BorrowPageController)loader.getController();
                        controller.setUserAndBook(person, rowData);
                        controller.initBook();
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle(rowData.getTitle());
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println(ex.getMessage());
                    }

                }
            });
            return row;
        });
    }
    
    public void sendNotification(){  
        if (!(person.getBorrowsCollection().isEmpty())) {            
            for (Borrows borrow : person.getBorrowsCollection()) {
                Date date = borrow.getDateOfBorrow();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.WEEK_OF_MONTH, 2);
                date = calendar.getTime();

                Date dateToday = new Date();
                if (date.before(dateToday)) {
                    message.setText("You are overdue returning your book to the library!!! -"+borrow.getBookID());
                }
            }
        }
        else message.setText("Welcome!");
    }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/library/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage oldStage = (Stage) message.getScene().getWindow();
        oldStage.close();

    }

}
