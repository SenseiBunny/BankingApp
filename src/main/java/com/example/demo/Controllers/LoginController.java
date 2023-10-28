package com.example.demo.Controllers;

import com.example.demo.Models.Model;
import com.example.demo.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_label;
    public TextField payee_address_field;
    public TextField password_fid;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(e-> onLogin());
    }

    private void onLogin(){
        Stage stage = (Stage)error_lbl.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            //Model.getInstance().getViewFactory().showClientWindow();
            Model.getInstance().evaluateClientCred(payee_address_field.getText(), password_fid.getText());
            if(Model.getInstance().getClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
            else{
                error_lbl.setText("Wrong credentials");
            }
        }
        else{
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
}
