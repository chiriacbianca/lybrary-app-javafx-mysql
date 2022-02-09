
package com.library.controllers;

import com.database.BooksQuery;
import com.library.entities.Book;
import com.library.entities.Persons;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MyLibrarianAccountController implements Initializable {
    
    @FXML
    private Label message;
    
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> columnId;
    @FXML
    private TableColumn<Book, String> columnTitle;
    @FXML
    private TableColumn<Book, String> columnAuthor;
    
    private BooksQuery book=new BooksQuery();
    ObservableList<Book> bookDisplay=FXCollections.observableArrayList(book.listOfBooks());
    
    private Persons person;
    
    public void setPerson(Persons person)
    {
        this.person=person;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        table.setItems(bookDisplay);
        tableEvents();
    }
  
    @FXML
    private void addNewBookAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/library/AddBook.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Book details");
        stage.setScene(scene);
        stage.show();

    }

    public void tableEvents() {
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        Book rowData = row.getItem();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/BookHistory.fxml"));
                        Parent parent = loader.load();
                        BookHistoryController controller = (BookHistoryController) loader.getController();
                        controller.setBook(rowData);
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
    
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/library/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
