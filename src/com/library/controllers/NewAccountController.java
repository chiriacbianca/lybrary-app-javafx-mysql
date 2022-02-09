
package com.library.controllers;

import com.database.LoginQuery;
import com.library.entities.Persons;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewAccountController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    
    @FXML
    private Label message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    LoginQuery user;
    
    @FXML
    private void loginAction(ActionEvent event) throws IOException{
        if (!(password.getText().equals(confirmPassword.getText()))){
            message.setText("Password doesn't match!");
        }
        else
        {
            Persons person=new Persons();  
            person.setPersonName(name.getText());
            person.setUsername(username.getText());
            person.setEntryKey(password.getText());
            person.setAccess(false);
            
            LoginQuery loginQuery = new LoginQuery();
            if(loginQuery.signUp(person))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/MyAccount.fxml")); 
                        Parent parent = loader.load();
                        MyAccountController controller=(MyAccountController)loader.getController();
                        controller.setPerson(person);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Welcome "+person.getUsername());
                        stage.setScene(scene);
                        stage.show();
                        Stage oldStage = (Stage) message.getScene().getWindow();
                        oldStage.close();
            }
        }
    }
}
