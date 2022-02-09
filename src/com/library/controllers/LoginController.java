/** 
 * 
 *      NewAccount
 *      ^    \
 *      |     \                       BorrowPage
 *      |      \                    /
 *      |  MyAccount (regular user)<
 *      | /                         \
 *      |/                            ReviewPage
 * Login<
 *       \                       AddBook
 *        \                    /
 *         MyLibrarianAccount<
 *                             \
 *                               BookHistory
 * 
 */
package com.library.controllers;

import com.database.LoginQuery;
import com.library.entities.Persons;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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



public class LoginController implements Initializable {

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Label message;
    
    @FXML
    private Label signUpMessage; 
    
    private List<Persons> listLogin = new ArrayList<>();
    private LoginQuery query = new LoginQuery();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listLogin = query.listOfLogin();
    }   
    
    @FXML
    private void loginAction(ActionEvent event) throws IOException
    { 
        boolean foundId=false;
        for (Persons users : listLogin) {
            if (username.getText().equals(users.getUsername())){
                foundId=true;
                if (password.getText().equals(users.getEntryKey())) {
                    if (!users.getAccess()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/MyAccount.fxml")); 
                        Parent parent = loader.load(); 
                        MyAccountController controller=(MyAccountController)loader.getController();
                        controller.setPerson(users); 
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Welcome "+users.getUsername());
                        stage.setScene(scene);
                        stage.show();
                        Stage oldStage = (Stage) message.getScene().getWindow();
                        oldStage.close();
                    }
                    else{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/MyLibrarianAccount.fxml"));
                        Parent parent = loader.load();
                        MyLibrarianAccountController controller=(MyLibrarianAccountController)loader.getController();
                        controller.setPerson(users);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Welcome "+users.getUsername());
                        stage.setScene(scene);
                        stage.show();
                        Stage oldStage = (Stage) message.getScene().getWindow();
                        oldStage.close();
                    }
               }
                else {
                    message.setText("Invalid password");
                }
            }
            
        }
        if (foundId==false){
            message.setText("Invalid username");
        }
  
    }
    
    @FXML
    private void signUpAction (ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/library/NewAccount.fxml"));
        Scene scene= new Scene(parent);
        Stage stage= new Stage();
        stage.setTitle("Create Account");
        stage.setScene(scene);
        stage.show();
        Stage oldStage = (Stage) message.getScene().getWindow(); 
        oldStage.close();
    }
}
